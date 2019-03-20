package com.zensar.service;

import com.zensar.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

    @Autowired
    private RestTemplate restTemplate;

    private String urlToGetProdctsList ="https://jl-nonprod-syst.apigee.net/v1/categories/{categoryId}/products?key=2ALHCAAs6ikGRBoy6eTHA58RaG097Fma";
    public List<Product> getProductInfo( String categoryId, String priceLabelType){

        JohnLewisProductList johnLewisProductList = restTemplate.getForObject(urlToGetProdctsList, JohnLewisProductList.class, categoryId);

         //Deserializing the response into JohnLewisProduct Model
        List<JohnLewisProduct> products = johnLewisProductList.getProducts();
         //Filtering for only Products which got price.now and price.was values
        List<JohnLewisProduct> filteredProducts = products.stream().filter(product -> (product.getPrice().getWas()!="" && product.getPrice().getWas()!=null) &&
                 (product.getPrice().getNow()!="" && product.getPrice().getNow()!=null) ).sorted((p1, p2) -> (new BigDecimal(p2.getPrice().getWas()).subtract(new BigDecimal(p2.getPrice().getNow())) )
                 .compareTo(new BigDecimal(p1.getPrice().getWas()).subtract(new BigDecimal(p1.getPrice().getNow())))).collect(Collectors.toList());
        //Serializing the filtered JohnLewisProduct into Product
        List<Product> list =  filteredProducts.stream().map(johnLewisProduct -> {
             Product product = new Product(johnLewisProduct.getProductId(), johnLewisProduct.getTitle(), johnLewisProduct.getColorSwatches(),
                                                                getPriceNow(johnLewisProduct), getPriceLabel(johnLewisProduct, priceLabelType)) ;
             return product;
         }).collect(Collectors.toList());

        return list;
    }

    private String getPriceNow(JohnLewisProduct johnLewisProduct){
        Currency c  = Currency.getInstance(johnLewisProduct.getPrice().getCurrency());
        Double doubleNowValue = new Double(johnLewisProduct.getPrice().getNow());

        String priceNowString = null;

        if(doubleNowValue != Math.floor(doubleNowValue) || doubleNowValue< 10.0){
            // need to display with precision
            priceNowString =  String.format("%s%.2f",c.getSymbol(), doubleNowValue);
        }else{
            //its an integer
            priceNowString = String.format("%s%.0f",c.getSymbol(), doubleNowValue);
        }
        return priceNowString;
    }

    private String getPriceLabel(JohnLewisProduct johnLewisProduct, String priceLabelType){
        if(priceLabelType !=null && !priceLabelType.isEmpty()){
            Currency c  = Currency.getInstance(johnLewisProduct.getPrice().getCurrency());
            Double doubleNowValue = new Double(johnLewisProduct.getPrice().getNow());
            Double doubleWasValue = new Double(johnLewisProduct.getPrice().getWas());
            String priceLabelString = null;
            switch (priceLabelType){
                case "ShowWasNow":
                    priceLabelString = String.format("Was %s%s, Now  %s%s",c.getSymbol(), johnLewisProduct.getPrice().getWas(), c.getSymbol(), johnLewisProduct.getPrice().getNow());;
                    break;
                case "ShowWasThenNow" :
                    if(johnLewisProduct.getPrice().getThen2()!=null && !johnLewisProduct.getPrice().getThen2().isEmpty()){
                        priceLabelString = String.format("Was %s%s, Then %s%s, Now  %s%s",c.getSymbol(), johnLewisProduct.getPrice().getWas(), c.getSymbol(), johnLewisProduct.getPrice().getThen2(), c.getSymbol(), johnLewisProduct.getPrice().getNow());;
                    }else if(johnLewisProduct.getPrice().getThen1()!=null && !johnLewisProduct.getPrice().getThen1().isEmpty()){
                        priceLabelString = String.format("Was %s%s, Then %s%s, Now  %s%s",c.getSymbol(), johnLewisProduct.getPrice().getWas(), c.getSymbol(), johnLewisProduct.getPrice().getThen1(), c.getSymbol(), johnLewisProduct.getPrice().getNow());;
                    }else{
                        priceLabelString = String.format("Was %s%s, Now  %s%s",c.getSymbol(), johnLewisProduct.getPrice().getWas(), c.getSymbol(), johnLewisProduct.getPrice().getNow());;
                    }

                    break;
                case "ShowPercDscount":
                    priceLabelString = String.format("%.0f%s Off, Now  %s%s", (((doubleWasValue.doubleValue() - doubleNowValue.doubleValue())/doubleNowValue.doubleValue())*100), "%",   c.getSymbol(), johnLewisProduct.getPrice().getNow());
                    break;
            }
            return priceLabelString;
        }
        return null;

    }
}

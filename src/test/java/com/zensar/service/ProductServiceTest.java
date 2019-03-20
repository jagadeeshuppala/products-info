package com.zensar.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zensar.model.*;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
@RunWith(SpringRunner.class)
public class ProductServiceTest {

    @Autowired
    private ProductService productService;

    @MockBean
    private RestTemplate restTemplate;

    private static final String SHOW_WAS_NOW = "ShowWasNow";
    private static final String SHOW_WAS_THEN_NOW = "ShowWasThenNow";
    private static final String SHOW_PERCENT_DISCOUNT = "ShowPercDscount";

    @MockBean
    private JohnLewisProduct product;


    @TestConfiguration
    static class ProductServiceTestContextConfiguration {

        @Bean
        public ProductService productService() {
            return new ProductService();
        }

    }

    @Test
    public void testProductsWithPriceNowWithDoublePrecission(){
        JohnLewisProduct johnLewisProduct = new JohnLewisProduct();
        johnLewisProduct.setProductId("3428696");
        johnLewisProduct.setTitle("Hobbs Kiona Dress, Green");
        johnLewisProduct.setPrice(new Price("149", "", "", "74.90", "GBP"));

        List<JohnLewisProduct> johnLewisProducts = Arrays.asList(johnLewisProduct);
        JohnLewisProductList list = new JohnLewisProductList();
        list.setProducts(johnLewisProducts);

        Mockito.when(restTemplate.getForObject(Mockito.anyString(), Mockito.any(), Mockito.anyString())).thenReturn(list);
        List<Product> productInfo = productService.getProductInfo("600001506",null);
        productInfo.stream().forEach(product -> {
            Assert.assertEquals("£74.90", product.getNowPrice());
        });

    }

    @Test
    public void testProductsWithPriceNowWithNowLessThan10(){
        JohnLewisProduct johnLewisProduct = new JohnLewisProduct();
        johnLewisProduct.setProductId("3428696");
        johnLewisProduct.setTitle("Hobbs Kiona Dress, Green");
        johnLewisProduct.setPrice(new Price("149", "", "", "2", "GBP"));

        List<JohnLewisProduct> johnLewisProducts = Arrays.asList(johnLewisProduct);
        JohnLewisProductList list = new JohnLewisProductList();
        list.setProducts(johnLewisProducts);

        Mockito.when(restTemplate.getForObject(Mockito.anyString(), Mockito.any(), Mockito.anyString())).thenReturn(list);
        List<Product> productInfo = productService.getProductInfo("600001506",null);
        productInfo.stream().forEach(product -> {
            Assert.assertEquals("£2.00", product.getNowPrice());
        });

    }

    @Test
    public void testProductsWithPriceNowWithNowGraterThan10AndInteger(){
        JohnLewisProduct johnLewisProduct = new JohnLewisProduct();
        johnLewisProduct.setProductId("3428696");
        johnLewisProduct.setTitle("Hobbs Kiona Dress, Green");
        johnLewisProduct.setPrice(new Price("149", "", "", "100.00", "GBP"));

        List<JohnLewisProduct> johnLewisProducts = Arrays.asList(johnLewisProduct);
        JohnLewisProductList list = new JohnLewisProductList();
        list.setProducts(johnLewisProducts);

        Mockito.when(restTemplate.getForObject(Mockito.anyString(), Mockito.any(), Mockito.anyString())).thenReturn(list);
        List<Product> productInfo = productService.getProductInfo("600001506",null);
        productInfo.stream().forEach(product -> {
            Assert.assertEquals("£100", product.getNowPrice());
        });

    }

    @Test
    public void testProductsWithColorSwatchRgbColorToHex() throws Exception{
        JohnLewisProduct johnLewisProduct = new JohnLewisProduct();
        johnLewisProduct.setProductId("3428696");
        johnLewisProduct.setTitle("Hobbs Kiona Dress, Green");
        johnLewisProduct.setPrice(new Price("149", "", "", "100.00", "GBP"));

       // ColorSwatch colorSwatch = new ColorSwatch("Mimosa Yellow", "FFFF00","237334029");
        ObjectMapper objectMapper = new ObjectMapper();


        ColorSwatch colorSwatch = objectMapper.readValue("{\"color\":\"Mimosa Yellow\",\"basicColor\":\"Yellow\",\"skuId\":\"237334029\"}", ColorSwatch.class);
        johnLewisProduct.setColorSwatches(new ArrayList<>(Arrays.asList(colorSwatch)));

        List<JohnLewisProduct> johnLewisProducts = Arrays.asList(johnLewisProduct);
        JohnLewisProductList list = new JohnLewisProductList();
        list.setProducts(johnLewisProducts);

        Mockito.when(restTemplate.getForObject(Mockito.anyString(), Mockito.any(), Mockito.anyString())).thenReturn(list);
        List<Product> productInfo = productService.getProductInfo("600001506",null);
        productInfo.stream().forEach(product -> {
            Assert.assertEquals("FFFF00", product.getColorSwatches().get(0).getRgbColor());
        });

    }

    @Test
    public void testProductsWithPriceLabelForShowWasNow(){
        JohnLewisProduct johnLewisProduct = new JohnLewisProduct();
        johnLewisProduct.setProductId("3428696");
        johnLewisProduct.setTitle("Hobbs Kiona Dress, Green");
        johnLewisProduct.setPrice(new Price("149", "", "", "74", "GBP"));

        List<JohnLewisProduct> johnLewisProducts = Arrays.asList(johnLewisProduct);
        JohnLewisProductList list = new JohnLewisProductList();
        list.setProducts(johnLewisProducts);

        Mockito.when(restTemplate.getForObject(Mockito.anyString(), Mockito.any(), Mockito.anyString())).thenReturn(list);
        List<Product> productInfo = productService.getProductInfo("600001506", SHOW_WAS_NOW);
        productInfo.stream().forEach(product -> {
            Assert.assertEquals("Was £149, Now  £74", product.getPriceLabel());
        });

    }

    @Test
    public void testProductsWithPriceLabelForShowWasThenNowThen2Present(){
        JohnLewisProduct johnLewisProduct = new JohnLewisProduct();
        johnLewisProduct.setProductId("3428696");
        johnLewisProduct.setTitle("Hobbs Kiona Dress, Green");
        johnLewisProduct.setPrice(new Price("149", "130", "120.00", "74", "GBP"));

        List<JohnLewisProduct> johnLewisProducts = Arrays.asList(johnLewisProduct);
        JohnLewisProductList list = new JohnLewisProductList();
        list.setProducts(johnLewisProducts);

        Mockito.when(restTemplate.getForObject(Mockito.anyString(), Mockito.any(), Mockito.anyString())).thenReturn(list);
        List<Product> productInfo = productService.getProductInfo("600001506",SHOW_WAS_THEN_NOW);
        productInfo.stream().forEach(product -> {
            Assert.assertEquals("Was £149, Then £120.00, Now  £74", product.getPriceLabel());
        });

    }

    @Test
    public void testProductsWithPriceLabelForShowWasThenNowThen2NotPresent(){
        JohnLewisProduct johnLewisProduct = new JohnLewisProduct();
        johnLewisProduct.setProductId("3428696");
        johnLewisProduct.setTitle("Hobbs Kiona Dress, Green");
        johnLewisProduct.setPrice(new Price("149", "130", "", "74", "GBP"));

        List<JohnLewisProduct> johnLewisProducts = Arrays.asList(johnLewisProduct);
        JohnLewisProductList list = new JohnLewisProductList();
        list.setProducts(johnLewisProducts);

        Mockito.when(restTemplate.getForObject(Mockito.anyString(), Mockito.any(), Mockito.anyString())).thenReturn(list);
        List<Product> productInfo = productService.getProductInfo("600001506", SHOW_WAS_THEN_NOW);
        productInfo.stream().forEach(product -> {
            Assert.assertEquals("Was £149, Then £130, Now  £74", product.getPriceLabel());
        });

    }

    @Test
    public void testProductsWithPriceLabelForShowPercentDiscount(){
        JohnLewisProduct johnLewisProduct = new JohnLewisProduct();
        johnLewisProduct.setProductId("3428696");
        johnLewisProduct.setTitle("Hobbs Kiona Dress, Green");
        johnLewisProduct.setPrice(new Price("149", "130", "", "74", "GBP"));

        List<JohnLewisProduct> johnLewisProducts = Arrays.asList(johnLewisProduct);
        JohnLewisProductList list = new JohnLewisProductList();
        list.setProducts(johnLewisProducts);

        Mockito.when(restTemplate.getForObject(Mockito.anyString(), Mockito.any(), Mockito.anyString())).thenReturn(list);
        List<Product> productInfo = productService.getProductInfo("600001506",SHOW_PERCENT_DISCOUNT);
        productInfo.stream().forEach(product -> {
            Assert.assertEquals("101% Off, Now  £74", product.getPriceLabel());
        });

    }


}

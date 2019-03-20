package com.zensar.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zensar.model.ColorSwatch;
import com.zensar.model.Product;
import com.zensar.service.ProductService;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.client.HttpClientErrorException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
@WebMvcTest(ProductController.class)
public class ProductControllerTest {

    private static final String SHOW_WAS_NOW = "ShowWasNow";
    private static final String SHOW_WAS_THEN_NOW = "ShowWasThenNow";
    private static final String SHOW_PERCENT_DISCOUNT = "ShowPercDscount";

    @Autowired
    private MockMvc mvc;

    @MockBean
    private ProductService productService;

    @Test
    public void getProductsWithLabelTypeAsShowWasNow() throws Exception{

        Product product = new Product();
        product.setProductId("3428696");
        product.setTitle("Hobbs Kiona Dress, Green");
        ObjectMapper objectMapper = new ObjectMapper();
        ColorSwatch colorSwatch = objectMapper.readValue("{\"color\":\"Mimosa Yellow\",\"basicColor\":\"Yellow\",\"skuId\":\"237334029\"}", ColorSwatch.class);

        product.setColorSwatches(new ArrayList<>(Arrays.asList(colorSwatch)));
        product.setNowPrice("£74");
        product.setPriceLabel("Was £149, Now  £74");

        List<Product> productList = Arrays.asList(product);


        Mockito.when(productService.getProductInfo("600001506", SHOW_WAS_NOW)).thenReturn(productList);

        mvc.perform(MockMvcRequestBuilders.get("/products/600001506?labelType=ShowWasNow")
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].colorSwatches[0].rgbColor", Matchers.is("FFFF00")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].nowPrice", Matchers.is("£74")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].priceLabel", Matchers.is("Was £149, Now  £74")))
        ;



    }


    @Test
    public void getProductsWithLabelTypeAsShowWasThenNow() throws Exception{

        Product product = new Product();
        product.setProductId("3428696");
        product.setTitle("Hobbs Kiona Dress, Green");
        ObjectMapper objectMapper = new ObjectMapper();
        ColorSwatch colorSwatch = objectMapper.readValue("{\"color\":\"Mimosa Yellow\",\"basicColor\":\"Yellow\",\"skuId\":\"237334029\"}", ColorSwatch.class);

        product.setColorSwatches(new ArrayList<>(Arrays.asList(colorSwatch)));
        product.setNowPrice("£74");
        product.setPriceLabel("Was £149, Then £120.00, Now  £74");

        List<Product> productList = Arrays.asList(product);


        Mockito.when(productService.getProductInfo("600001506", SHOW_WAS_THEN_NOW)).thenReturn(productList);

        mvc.perform(MockMvcRequestBuilders.get("/products/600001506?labelType=ShowWasThenNow")
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].colorSwatches[0].rgbColor", Matchers.is("FFFF00")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].nowPrice", Matchers.is("£74")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].priceLabel", Matchers.is("Was £149, Then £120.00, Now  £74")))
        ;



    }

    @Test
    public void getProductsWithLabelTypeAsShowPercentDiscount() throws Exception{

        Product product = new Product();
        product.setProductId("3428696");
        product.setTitle("Hobbs Kiona Dress, Green");
        ObjectMapper objectMapper = new ObjectMapper();
        ColorSwatch colorSwatch = objectMapper.readValue("{\"color\":\"Mimosa Yellow\",\"basicColor\":\"Yellow\",\"skuId\":\"237334029\"}", ColorSwatch.class);

        product.setColorSwatches(new ArrayList<>(Arrays.asList(colorSwatch)));
        product.setNowPrice("£74");
        product.setPriceLabel("101% Off, Now  £74");

        List<Product> productList = Arrays.asList(product);


        Mockito.when(productService.getProductInfo("600001506", SHOW_PERCENT_DISCOUNT)).thenReturn(productList);

        mvc.perform(MockMvcRequestBuilders.get("/products/600001506?labelType=ShowPercDscount")
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].colorSwatches[0].rgbColor", Matchers.is("FFFF00")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].nowPrice", Matchers.is("£74")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].priceLabel", Matchers.is("101% Off, Now  £74")))
        ;



    }

    @Test
    public void getProductsWithNoLabel() throws Exception{

        Product product = new Product();
        product.setProductId("3428696");
        product.setTitle("Hobbs Kiona Dress, Green");
        ObjectMapper objectMapper = new ObjectMapper();
        ColorSwatch colorSwatch = objectMapper.readValue("{\"color\":\"Mimosa Yellow\",\"basicColor\":\"Yellow\",\"skuId\":\"237334029\"}", ColorSwatch.class);

        product.setColorSwatches(new ArrayList<>(Arrays.asList(colorSwatch)));
        product.setNowPrice("£74");


        List<Product> productList = Arrays.asList(product);


        Mockito.when(productService.getProductInfo("600001506", null)).thenReturn(productList);

        mvc.perform(MockMvcRequestBuilders.get("/products/600001506")
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].colorSwatches[0].rgbColor", Matchers.is("FFFF00")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].nowPrice", Matchers.is("£74")))

        ;



    }


}

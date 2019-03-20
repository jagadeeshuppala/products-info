package com.zensar.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.TextNode;

import java.io.IOException;
import java.util.Currency;

public class PriceNowDeserializer extends JsonDeserializer<String> {

    /*@Override
    public Price deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        Price price = new Price();
        JsonNode priceNode = jsonParser.readValueAsTree();
        price.setWas(priceNode.get("was").asText());
        price.setThen1(priceNode.get("then1").asText());
        price.setThen2(priceNode.get("then2").asText());
        JsonNode now = priceNode.get("now");
        if( (priceNode.get("now") instanceof TextNode)){
            price.setNow(priceNode.get("now").asText());
        }
        return price;
    }*/

    /*@Override
    public String deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        JsonNode priceNode = jsonParser.readValueAsTree();
        JsonNode now = priceNode.get("now");
        if( (now instanceof TextNode)){
            JsonNode currencyNode = priceNode.get("currency");
            Currency c  = Currency.getInstance(currencyNode.asText());
            Double doubleValue = new Double(now.asText());

            if(doubleValue != Math.floor(doubleValue) || doubleValue< 10.0){
                // need to display with precision
                return String.format("%s%.2f",c.getSymbol(), doubleValue);
            }else{
                //its an integer
                return String.format("%s%.0f",c.getSymbol(), doubleValue);
            }

        }else{
            return null;
        }
    }*/


    @Override
    public String deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        JsonNode priceNow = jsonParser.readValueAsTree();

        if( (priceNow instanceof TextNode)){
            return priceNow.asText();

        }else{
            return null;
        }
    }
}

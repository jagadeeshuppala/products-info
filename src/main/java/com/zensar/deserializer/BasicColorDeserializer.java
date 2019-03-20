package com.zensar.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.TextNode;
import com.zensar.RGB;

import java.awt.*;
import java.io.IOException;
import java.util.Currency;

public class BasicColorDeserializer extends JsonDeserializer<String> {


    @Override
    public String deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        JsonNode colorSwatchesNode = jsonParser.readValueAsTree();
        if( (colorSwatchesNode instanceof TextNode)){
            return RGB.valueOf(colorSwatchesNode.asText()).getHex();
        }else{
            return null;
        }
    }
}

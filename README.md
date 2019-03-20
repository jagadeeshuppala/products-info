# Get the product list for the given categoryIs

suggestions related API's exposed

## Getting Started
This is a maven project, so you can start running the project using the below command. you can override the server port with your choice
```
mvn clean install spring-boot:run -Dserver.port=8081

```

### swagger ui
```
http://localhost:8080/swagger-ui.html
```



### Get All the products without any query parameters, its going to return products with a price reduction and should be sorted to show the highest price reduction first.
```
curl -X GET --header 'Accept: application/json' 'http://localhost:8080/products/600001506'
```

### Get All the products with  any query parameter labelType and the value ShowWasNow, it gives the priceLabel with a formatted string
```
curl -X GET --header 'Accept: application/json' 'http://localhost:8080/products/600001506?labelType=ShowWasNow'
```

### Get All the products with  any query parameter labelType and the value ShowWasThenNow, it gives the priceLabel with a formatted string
```
curl -X GET --header 'Accept: application/json' 'http://localhost:8080/products/600001506?labelType=ShowWasThenNow'
```

### Get All the products with  any query parameter labelType and the value ShowPercDscount, it gives the priceLabel with a formatted string
```
curl -X GET --header 'Accept: application/json' 'http://localhost:8080/products/600001506?labelType=ShowPercDscount'
```

### The API is handling all the possible exceptions
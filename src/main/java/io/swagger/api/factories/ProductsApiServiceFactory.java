package io.swagger.api.factories;

import io.swagger.api.ProductsApiService;
import io.swagger.api.impl.ProductsApiServiceImpl;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaResteasyServerCodegen", date = "2017-08-15T05:12:53.877Z")
public class ProductsApiServiceFactory {

   private final static ProductsApiService service = new ProductsApiServiceImpl();

   public static ProductsApiService getProductsApi()
   {
      return service;
   }
}

package io.swagger.api;

import io.swagger.api.*;
import io.swagger.model.*;


import io.swagger.model.ErrorModel;
import io.swagger.model.NewProduct;
import io.swagger.model.Product;

import java.util.List;
import io.swagger.api.NotFoundException;

import java.io.InputStream;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaResteasyServerCodegen", date = "2017-08-15T05:12:53.877Z")
public abstract class ProductsApiService {
      public abstract Response addProduct(NewProduct product,SecurityContext securityContext)
      throws NotFoundException;
      public abstract Response deleteProduct(Long id,SecurityContext securityContext)
      throws NotFoundException;
      public abstract Response findProductById(Long id,SecurityContext securityContext)
      throws NotFoundException;
      public abstract Response findProducts(List<String> productType,Integer limit,SecurityContext securityContext)
      throws NotFoundException;
}

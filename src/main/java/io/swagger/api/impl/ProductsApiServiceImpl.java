package io.swagger.api.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
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
public class ProductsApiServiceImpl extends ProductsApiService {
      @Override
      public Response addProduct(NewProduct product,SecurityContext securityContext)
      throws NotFoundException {
          //TODO replace with data store insert
          Product p = new Product();
          p.setId(1L);
          p.setName(product.getName());
          p.setProductType(product.getProductType());
          try {
              return Response.ok().entity(p.toJson()).build();
          } catch (JsonProcessingException e) {
              return Response.serverError().entity(new ErrorModel(500, e.getMessage()).toJson()).build();
          }
  }
      @Override
      public Response deleteProduct(Long id,SecurityContext securityContext)
      throws NotFoundException {
      return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "magic!")).build();
  }
      @Override
      public Response findProductById(Long id,SecurityContext securityContext)
      throws NotFoundException {
          Product p = new Product();
          p.setId(1L); // TODO replace with data store fetch against product id
          p.setName("prodname"); // TODO get product name from data store
          p.setProductType("prodtype");// TODO get product type from data store
          try {
              return Response.ok().entity(p.toJson()).build();
          } catch (JsonProcessingException e) {
              return Response.serverError().entity(new ErrorModel(500, e.getMessage()).toJson()).build();
          }
      }
      @Override
      public Response findProducts(List<String> productType,Integer limit,SecurityContext securityContext)
      throws NotFoundException {
      return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "magic!")).build();
  }
}

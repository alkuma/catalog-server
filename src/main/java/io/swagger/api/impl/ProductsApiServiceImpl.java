package io.swagger.api.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.swagger.api.*;
import io.swagger.model.*;


import io.swagger.model.ErrorModel;
import io.swagger.model.NewProduct;
import io.swagger.model.Product;

import java.util.List;
import io.swagger.api.NotFoundException;
import io.swagger.model.mappers.CatalogMapper;
import org.apache.ibatis.session.SqlSession;

import java.io.InputStream;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaResteasyServerCodegen", date = "2017-08-15T05:12:53.877Z")
public class ProductsApiServiceImpl extends ProductsApiService {
      @Override
      public Response addProduct(NewProduct product,SecurityContext securityContext)
      throws NotFoundException {
          try (SqlSession session = Datastore.openSession()) {
              CatalogMapper mapper = session.getMapper(CatalogMapper.class);
              Long id = mapper.getNextProductId();
              Product p = new Product();
              p.setId(id);
              p.setName(product.getName());
              p.setProductType(product.getProductType());
              mapper.insertProduct(p);
              session.commit();
              return Response.ok().entity(p.toJson()).build();
          } catch (JsonProcessingException e) {
              return Response.serverError().entity(new ErrorModel(500, e.getMessage()).toJson()).build();
          }
      }
      @Override
      public Response deleteProduct(Long id,SecurityContext securityContext)
      throws NotFoundException {
          try (SqlSession session = Datastore.openSession()) {
              CatalogMapper mapper = session.getMapper(CatalogMapper.class);
              mapper.deleteProduct(id);
              session.commit();
              return Response.ok().build();
          }
      }
      @Override
      public Response findProductById(Long id,SecurityContext securityContext)
      throws NotFoundException {
          try (SqlSession session = Datastore.openSession()) {
              CatalogMapper mapper = session.getMapper(CatalogMapper.class);
              Product p = mapper.getProductById(id);
              if(null == p) {
                  return Response.status(404).entity(new ErrorModel(404, "Not found").toJson()).build();
              }
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

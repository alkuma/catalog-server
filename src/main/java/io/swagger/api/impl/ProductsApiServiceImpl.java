package io.swagger.api.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.api.*;


import io.swagger.model.ErrorModel;
import io.swagger.model.NewProduct;
import io.swagger.model.Product;

import java.util.Arrays;
import java.util.List;
import io.swagger.api.NotFoundException;
import io.swagger.model.mappers.CatalogMapper;
import org.apache.ibatis.exceptions.PersistenceException;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;


import javax.servlet.http.HttpServletResponse;
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
          }
          catch (JsonProcessingException e) {
              return Response.serverError().entity(new ErrorModel(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage()).toJson()).build();
          } catch (PersistenceException e) {
              return Response.status(HttpServletResponse.SC_CONFLICT).entity(new ErrorModel(HttpServletResponse.SC_CONFLICT, e.getMessage()).toJson()).build();

          }
      }
      @Override
      public Response deleteProduct(Long id,SecurityContext securityContext)
      throws NotFoundException {
          try (SqlSession session = Datastore.openSession()) {
              CatalogMapper mapper = session.getMapper(CatalogMapper.class);
              mapper.deleteProduct(id);
              session.commit();
              return Response.status(HttpServletResponse.SC_NO_CONTENT).build();
          }
      }
      @Override
      public Response findProductById(Long id,SecurityContext securityContext)
      throws NotFoundException {
          try (SqlSession session = Datastore.openSession()) {
              CatalogMapper mapper = session.getMapper(CatalogMapper.class);
              Product p = mapper.getProductById(id);
              if(null == p) {
                  return Response.status(HttpServletResponse.SC_NOT_FOUND).entity(new ErrorModel(HttpServletResponse.SC_NOT_FOUND, "Not found").toJson()).build();
              }
              return Response.ok().entity(p.toJson()).build();
          } catch (JsonProcessingException e) {
              return Response.serverError().entity(new ErrorModel(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage()).toJson()).build();
          }
      }
      @Override
      public Response findProducts(List<String> productType,Integer limit,SecurityContext securityContext)
      throws NotFoundException {
          RowBounds rowBounds = null;
          if (null != limit) {
              rowBounds = new RowBounds(0, limit);
          } else {
              rowBounds = new RowBounds(0, 1000);
          }

          System.out.println("product types are ["+productType+"]");
          if(productType == null || productType.size() != 1) {
              return Response.status(HttpServletResponse.SC_BAD_REQUEST).entity(new ErrorModel(HttpServletResponse.SC_BAD_REQUEST, "product_type comma separated list is a mandatory query parameter").toJson()).build();
          }

          try (SqlSession session = Datastore.openSession()) {
              CatalogMapper mapper = session.getMapper(CatalogMapper.class);
              List<Product> ps = mapper.getProducts(Arrays.asList(productType.get(0).split(",")), rowBounds);
              if (null == ps || ps.size() == 0) {
                  return Response.status(HttpServletResponse.SC_NOT_FOUND).entity(new ErrorModel(HttpServletResponse.SC_NOT_FOUND, "Not found").toJson()).build();
              }

              ObjectMapper m = new ObjectMapper();

              return Response.ok().entity(m.writeValueAsString(ps)).build();
          } catch (JsonProcessingException e) {
              return Response.serverError().entity(new ErrorModel(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage()).toJson()).build();
          }
      }
}

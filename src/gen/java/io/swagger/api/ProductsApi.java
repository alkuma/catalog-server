package io.swagger.api;

import io.swagger.model.*;
import io.swagger.api.ProductsApiService;
import io.swagger.api.factories.ProductsApiServiceFactory;

import io.swagger.annotations.ApiParam;
import io.swagger.jaxrs.*;

import io.swagger.model.ErrorModel;
import io.swagger.model.NewProduct;
import io.swagger.model.Product;

import java.util.List;
import io.swagger.api.NotFoundException;

import java.io.InputStream;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.*;
import javax.validation.constraints.*;

@Path("/products")
@Consumes({ "application/json" })
@Produces({ "application/json" })
@io.swagger.annotations.Api(description = "the products API")
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaResteasyServerCodegen", date = "2017-08-15T05:12:53.877Z")
public class ProductsApi  {
   private final ProductsApiService delegate = ProductsApiServiceFactory.getProductsApi();

    @POST
    
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "", notes = "Creates a new product in the catalog.", response = Product.class, tags={  })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "product response", response = Product.class),
        
        @io.swagger.annotations.ApiResponse(code = 200, message = "unexpected error", response = Product.class) })
    public Response addProduct(@ApiParam(value = "product to add to the catalog" ,required=true) NewProduct product,@Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.addProduct(product,securityContext);
    }
    @DELETE
    @Path("/{id}")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "", notes = "deletes a single product based on the ID supplied", response = Void.class, tags={  })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 204, message = "product deleted", response = Void.class),
        
        @io.swagger.annotations.ApiResponse(code = 200, message = "unexpected error", response = Void.class) })
    public Response deleteProduct( @PathParam("id") Long id,@Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.deleteProduct(id,securityContext);
    }
    @GET
    @Path("/{id}")
    @Consumes({ "application/json" })
    @Produces({ "application/json", "application/xml", "text/xml", "text/html" })
    @io.swagger.annotations.ApiOperation(value = "", notes = "returns a product based on the single id", response = Product.class, tags={  })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "product response", response = Product.class),
        
        @io.swagger.annotations.ApiResponse(code = 200, message = "unexpected error", response = Product.class) })
    public Response findProductById( @PathParam("id") Long id,@Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.findProductById(id,securityContext);
    }
    @GET
    
    @Consumes({ "application/json" })
    @Produces({ "application/json", "application/xml", "text/xml", "text/html" })
    @io.swagger.annotations.ApiOperation(value = "", notes = "Returns all products from the system", response = Product.class, responseContainer = "List", tags={  })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "product response", response = Product.class, responseContainer = "List"),
        
        @io.swagger.annotations.ApiResponse(code = 200, message = "unexpected error", response = Product.class, responseContainer = "List") })
    public Response findProducts(  @QueryParam("product_type") List<String> productType,  @QueryParam("limit") Integer limit,@Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.findProducts(productType,limit,securityContext);
    }
}

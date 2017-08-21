package catalog.test;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.swagger.api.ProductsApi;
import io.swagger.model.NewProduct;
import org.jboss.resteasy.core.Dispatcher;
import org.jboss.resteasy.plugins.server.resourcefactory.POJOResourceFactory;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.jboss.resteasy.mock.*;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.MediaType;
import java.net.URISyntaxException;

public class TestCatalogApi {

    @Test()
    public void testGetExistingProduct() throws URISyntaxException {
        Dispatcher dispatcher = MockDispatcherFactory.createDispatcher();

        POJOResourceFactory noDefaults = new POJOResourceFactory(ProductsApi.class);
        dispatcher.getRegistry().addResourceFactory(noDefaults);

        MockHttpRequest request = MockHttpRequest.get("/products/1");
        MockHttpResponse response = new MockHttpResponse();

        dispatcher.invoke(request, response);

        Assert.assertEquals(HttpServletResponse.SC_OK, response.getStatus());
    }

    @Test()
    public void testGetAbsentProduct() throws URISyntaxException {
        Dispatcher dispatcher = MockDispatcherFactory.createDispatcher();

        POJOResourceFactory noDefaults = new POJOResourceFactory(ProductsApi.class);
        dispatcher.getRegistry().addResourceFactory(noDefaults);

        MockHttpRequest request = MockHttpRequest.get("/products/23425");
        MockHttpResponse response = new MockHttpResponse();

        dispatcher.invoke(request, response);

        Assert.assertEquals(HttpServletResponse.SC_NOT_FOUND, response.getStatus());
    }

    @Test()
    public void testAddProduct() throws URISyntaxException, JsonProcessingException {
        Dispatcher dispatcher = MockDispatcherFactory.createDispatcher();

        POJOResourceFactory noDefaults = new POJOResourceFactory(ProductsApi.class);
        dispatcher.getRegistry().addResourceFactory(noDefaults);

        MockHttpRequest request = MockHttpRequest.post("/products");

        NewProduct product = new NewProduct();
        product.setName("test product name");
        product.setProductType("test product type");

        request.accept(MediaType.APPLICATION_JSON);
        request.contentType(MediaType.APPLICATION_JSON_TYPE);
        request.content(product.toJson().getBytes());


        MockHttpResponse response = new MockHttpResponse();

        dispatcher.invoke(request, response);
        System.out.println(response.getContentAsString());

        Assert.assertEquals(HttpServletResponse.SC_OK, response.getStatus());
    }

    @Test()
    public void testDeleteProduct() throws URISyntaxException, JsonProcessingException {
        Dispatcher dispatcher = MockDispatcherFactory.createDispatcher();

        POJOResourceFactory noDefaults = new POJOResourceFactory(ProductsApi.class);
        dispatcher.getRegistry().addResourceFactory(noDefaults);

        MockHttpRequest request = MockHttpRequest.delete("/products/10");

        NewProduct product = new NewProduct();
        product.setName("test product name");
        product.setProductType("test product type");

        MockHttpResponse response = new MockHttpResponse();

        dispatcher.invoke(request, response);

        Assert.assertEquals(HttpServletResponse.SC_NO_CONTENT, response.getStatus());
    }
}

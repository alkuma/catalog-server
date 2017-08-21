package io.swagger.model.mappers;
import io.swagger.model.Product;

import java.util.List;

public interface CatalogMapper {
    public void createDatastoreObjectsIfAbsent();
    public Product getProductById(Long id);
    public List<Product> getProducts(Product crit);
    public Long getNextProductId();
    public void insertProduct(Product product);
    public void deleteProduct(Long id);
}
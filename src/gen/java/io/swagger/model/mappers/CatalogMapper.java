package io.swagger.model.mappers;
import io.swagger.model.Product;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

public interface CatalogMapper {
    void createDatastoreObjectsIfAbsent();
    Product getProductById(Long id);
    List<Product> getProducts(List<String> productTypes, RowBounds rowBounds);
    Long getNextProductId();
    void insertProduct(Product product);
    void deleteProduct(Long id);
}
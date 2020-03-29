package pl.normann.cachingwebinar.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;
import pl.normann.cachingwebinar.domain.Product;
import pl.normann.cachingwebinar.repositories.ProductRepository;

import java.util.List;

@Service
@Slf4j
public class ProductCrudService {

    private final ProductRepository productRepository;

    public ProductCrudService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Cacheable(cacheNames = "default")
    public List<Product> getAllProducts() {
        log.info("getAllProducts");
        return productRepository.findAll();
    }

    @Cacheable(cacheNames = "products", condition = "#id < 5")
    public Product getById(final Long id) {
        log.info("getById");
         return productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product with iid does not exist"));
    }

    @Caching(
            evict = {@CacheEvict(cacheNames = "products", allEntries = true),
                    @CacheEvict(cacheNames = "default", allEntries = true)}
    )
    public Product create (final Product product) {
        log.info("create");
        product.setId(null);
        return productRepository.save(product);
    }

    @CacheEvict(cacheNames = "products", allEntries = true)
    public Product update(final Product product, final Long id) {
        log.info("update");
        final Product existingProducts = getById(id);
        existingProducts.setCategory(product.getCategory());
        existingProducts.setName(product.getName());
        existingProducts.setReferenceNumber(product.getReferenceNumber());
        productRepository.save(existingProducts);
        return existingProducts;
    }

    @CacheEvict(cacheNames = "products", allEntries = true)
    public void deleteProduct(final Long id) {
        log.info("deleteProduct");
        productRepository.deleteById(id);
    }
}

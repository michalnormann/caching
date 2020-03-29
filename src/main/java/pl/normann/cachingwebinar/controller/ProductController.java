package pl.normann.cachingwebinar.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.normann.cachingwebinar.domain.Product;
import pl.normann.cachingwebinar.model.Products;
import pl.normann.cachingwebinar.services.ProductCrudService;

@RestController
@RequestMapping("api/products")
public class ProductController {

    private final ProductCrudService productCrudService;

    public ProductController(ProductCrudService productCrudService) {
        this.productCrudService = productCrudService;
    }

    @GetMapping
    public Products getAll() {
        return new Products(productCrudService.getAllProducts());
    }

    @GetMapping("/{id}")
    public Product getProduct(@PathVariable final Long id) {
        return  productCrudService.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Product createProduct(@RequestBody final Product product) {
        return productCrudService.create(product);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateProduct(@PathVariable final Long id, @RequestBody final Product product) {
        productCrudService.update(product, id);
    }


}

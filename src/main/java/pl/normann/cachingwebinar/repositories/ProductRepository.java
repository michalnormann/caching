package pl.normann.cachingwebinar.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.normann.cachingwebinar.domain.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

}

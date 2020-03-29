package pl.normann.cachingwebinar.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.normann.cachingwebinar.domain.Product;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Products {
    private List<Product> products;
}

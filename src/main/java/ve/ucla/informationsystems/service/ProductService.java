package ve.ucla.informationsystems.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ve.ucla.informationsystems.dto.SaveProduct;
import ve.ucla.informationsystems.persistence.entity.Product;

import java.util.Optional;

public interface ProductService {
    Page<Product> findAll(Pageable pageable);

    Product findOneById(Long productId);

    Product createOne(SaveProduct saveProduct);

    Product updateOneById(Long productId, SaveProduct saveProduct);

}

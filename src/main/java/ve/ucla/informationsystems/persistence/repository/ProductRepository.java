package ve.ucla.informationsystems.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ve.ucla.informationsystems.persistence.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {
}

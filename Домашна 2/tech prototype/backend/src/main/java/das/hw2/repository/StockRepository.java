package das.hw2.repository;

import das.hw2.model.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public interface StockRepository extends JpaRepository<Stock, Long> {
    List<Stock> findAllByName(String name);
}

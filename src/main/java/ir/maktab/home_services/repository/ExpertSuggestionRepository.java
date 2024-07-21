package ir.maktab.home_services.repository;

import ir.maktab.home_services.model.ExpertSuggestion;
import ir.maktab.home_services.model.Orders;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository

public interface ExpertSuggestionRepository extends JpaRepository<ExpertSuggestion,Integer> {

    List<ExpertSuggestion> findByOrders(Orders orders, Sort sort);


    Optional<ExpertSuggestion> findById(Integer id);
}

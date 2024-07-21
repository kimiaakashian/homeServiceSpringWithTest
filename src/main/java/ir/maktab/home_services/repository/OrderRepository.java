package ir.maktab.home_services.repository;

import ir.maktab.home_services.model.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository

public interface OrderRepository extends JpaRepository<Orders,Integer> {

Optional<Orders> findById (Integer id);
}

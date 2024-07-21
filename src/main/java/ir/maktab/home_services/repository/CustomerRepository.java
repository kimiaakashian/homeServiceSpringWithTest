package ir.maktab.home_services.repository;

import ir.maktab.home_services.model.Customer;
import ir.maktab.home_services.model.Expert;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository

public interface CustomerRepository extends JpaRepository<Customer,Integer> {
    Optional<Customer> findById(Integer id);

}

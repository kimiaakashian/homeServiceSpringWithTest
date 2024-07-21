package ir.maktab.home_services.repository;

import ir.maktab.home_services.model.HomeService;
import ir.maktab.home_services.model.SubService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository

public interface SubServiceRepository extends JpaRepository<SubService,Integer> {

    Optional<SubService> findBySubServiceName(String name);
    Optional<SubService> findById(Integer id);

}

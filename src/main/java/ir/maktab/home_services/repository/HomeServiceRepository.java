package ir.maktab.home_services.repository;

import ir.maktab.home_services.model.HomeService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.stream.BaseStream;
@Repository

public interface HomeServiceRepository extends JpaRepository<HomeService,Integer> {

    Optional<HomeService> findByName(String name);


}

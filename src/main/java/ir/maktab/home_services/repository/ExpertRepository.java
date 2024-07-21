package ir.maktab.home_services.repository;

import ir.maktab.home_services.model.Expert;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository

public interface ExpertRepository extends JpaRepository<Expert,Integer> {

    Optional <Expert> findById(Integer id);

}

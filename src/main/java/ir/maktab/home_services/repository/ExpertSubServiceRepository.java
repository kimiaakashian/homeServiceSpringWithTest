package ir.maktab.home_services.repository;

import ir.maktab.home_services.model.ExpertSubService;
import ir.maktab.home_services.model.SubService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface ExpertSubServiceRepository extends JpaRepository<ExpertSubService,Integer> {
    List<ExpertSubService> findBySubServices(SubService subService);

    ExpertSubService findByExperts_IdAndAndSubServices_Id(Integer expertId, Integer subServiceId);

}

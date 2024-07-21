package ir.maktab.home_services.service;

import ir.maktab.home_services.exception.InformationDuplicateException;
import ir.maktab.home_services.exception.NotFoundException;
import ir.maktab.home_services.model.HomeService;
import ir.maktab.home_services.model.Users;
import ir.maktab.home_services.repository.HomeServiceRepository;
import lombok.RequiredArgsConstructor;
import org.hibernate.dialect.function.array.HSQLArrayRemoveFunction;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HomeServiceService {
    private final HomeServiceRepository homeServiceRepository;

    public void addHomeService(HomeService homeService) {
        if (homeServiceRepository.findByName(homeService.getName()).isPresent())
            throw new InformationDuplicateException( "نام خدمت تکراری است");
        else
            homeServiceRepository.save(homeService);
    }

}

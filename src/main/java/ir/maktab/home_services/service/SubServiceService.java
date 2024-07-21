package ir.maktab.home_services.service;

import ir.maktab.home_services.exception.InformationDuplicateException;
import ir.maktab.home_services.exception.InvalidBasePriceException;
import ir.maktab.home_services.exception.NotFoundException;
import ir.maktab.home_services.model.HomeService;
import ir.maktab.home_services.model.SubService;
import ir.maktab.home_services.repository.SubServiceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SubServiceService {
    private final SubServiceRepository subServiceRepository;

    public void addSubService(SubService subService) {
        if (subServiceRepository.findBySubServiceName(subService.getSubServiceName()).isPresent())
            throw new InformationDuplicateException( "نام زیر خدمت تکراری است");
        else
            subServiceRepository.save(subService);
    }

    public void updateSubService(Integer subServiceId, double newBasePrice, String newDescription) {
        SubService subService = subServiceRepository.findById(subServiceId).orElseThrow(() -> new NotFoundException("کد خدمت زیر موجود نیست"));

        if (newBasePrice <= 0) {
            throw new InvalidBasePriceException("قیمت پایه باید بیشتر از صفر باشد");
        }
        subService.setBasePrice(newBasePrice);
        if (newDescription!= null &&!newDescription.isEmpty()) {
            subService.setDescription(newDescription);
        }
        subServiceRepository.save(subService);
    }

    public void deleteSubService(Integer subServiceId){
        SubService subService = subServiceRepository.findById(subServiceId).orElseThrow(() -> new NotFoundException("کد خدمت زیر موجود نیست"));
        subServiceRepository.delete(subService);

    }
}

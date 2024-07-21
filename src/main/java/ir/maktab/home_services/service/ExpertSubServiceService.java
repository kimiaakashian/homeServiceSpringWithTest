package ir.maktab.home_services.service;

import ir.maktab.home_services.exception.InvalidStatusException;
import ir.maktab.home_services.exception.NotFoundException;
import ir.maktab.home_services.model.Expert;
import ir.maktab.home_services.model.ExpertSubService;
import ir.maktab.home_services.model.SubService;
import ir.maktab.home_services.model.enums.ExpertStatus;
import ir.maktab.home_services.repository.ExpertRepository;
import ir.maktab.home_services.repository.ExpertSubServiceRepository;
import ir.maktab.home_services.repository.SubServiceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ExpertSubServiceService {
    private final ExpertSubServiceRepository expertSubServiceRepository;
    private final ExpertRepository expertRepository;
    private final SubServiceRepository subServiceRepository;

    public void addExpertToSubService(Integer expertId, Integer subServiceId) {
        Expert expert = expertRepository.findById(expertId).orElseThrow(() -> new NotFoundException("کاربر با این شناسه موجود نیست"));
        if (expert.getExpertStatus().equals(ExpertStatus.جدید))
            throw new InvalidStatusException("متخصص تایید نشده است");

        SubService subService = subServiceRepository.findById(subServiceId).orElseThrow(() -> new NotFoundException("زیر خدمت با این شناسه موجود نیست"));

        ExpertSubService expertSubService = new ExpertSubService();
        expertSubService.setExperts(expert);
        expertSubService.setSubServices(subService);

        expertSubServiceRepository.save(expertSubService);
    }

    public void removeExpertFromSubService(Integer expertId, Integer subServiceId) {

        ExpertSubService expertSubService = expertSubServiceRepository.findByExperts_IdAndAndSubServices_Id(expertId, subServiceId);

        if (expertSubService != null) {

            expertSubServiceRepository.delete(expertSubService);

        }

    }

}

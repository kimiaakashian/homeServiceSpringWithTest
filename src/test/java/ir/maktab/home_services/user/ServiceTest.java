package ir.maktab.home_services.user;

import ir.maktab.home_services.exception.*;
import ir.maktab.home_services.model.HomeService;
import ir.maktab.home_services.model.SubService;
import ir.maktab.home_services.service.*;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ServiceTest {

    @Autowired
    private HomeServiceService homeServiceService;


    @Autowired
    private SubServiceService subServiceService;

    @Autowired
    private ExpertSubServiceService expertSubServiceService;

    @Autowired
    private ExpertService expertService;

    @Test
    public void addHomeService() {
        HomeService homeService = HomeService.builder().name("ساختمان").build();
        homeServiceService.addHomeService(homeService);
    }

    @Test
    public void addHomeServiceByDuplicateName() {
        Exception exception = assertThrows(InformationDuplicateException.class, () -> {

            HomeService homeService = HomeService.builder().name("ساختمان").build();
            homeServiceService.addHomeService(homeService);
        });
        assertEquals("نام خدمت تکراری است", exception.getMessage());
        System.out.println("test :" + exception.getMessage());

    }


    @Test
    public void addSubService() {
        HomeService homeService = new HomeService();
        homeService.setId(1);
        SubService subService = SubService.builder().subServiceName("لوله کشی ").basePrice(100000D).description("لوله کشی ساختمان").homeServices(homeService).build();
        subServiceService.addSubService(subService);
    }


    @Test
    public void addSubServiceByDuplicateName() {
        Exception exception = assertThrows(InformationDuplicateException.class, () -> {

            HomeService homeService = new HomeService();
            homeService.setId(1);
            SubService subService = SubService.builder().subServiceName("لوله کشی ").homeServices(homeService).build();
            subServiceService.addSubService(subService);
        });
        assertEquals("نام زیر خدمت تکراری است", exception.getMessage());
        System.out.println("test :" + exception.getMessage());


    }

    @Test
    public void editSubServiceDescriptionAndInCorrectPrice() {
        Exception exception = assertThrows(InvalidBasePriceException.class, () -> {

            subServiceService.updateSubService(1, -1, "hi  hi");
        });
        assertEquals("قیمت پایه باید بیشتر از صفر باشد", exception.getMessage());
        System.out.println("test :" + exception.getMessage());


    }

    @Test
    public void editSubServiceDescriptionAndInCorrectSubServiceId() {
        Exception exception = assertThrows(NotFoundException.class, () -> {

            subServiceService.updateSubService(4, 10D, "hi  hi");
        });
        assertEquals("کد خدمت زیر موجود نیست", exception.getMessage());
        System.out.println("test :" + exception.getMessage());

    }

    @Test
    public void editSubService() {


        subServiceService.updateSubService(1, 500000, "لوله کشی جدید");


    }

    @Test
    public void verifiedExpertByInCorrectId() {
        Exception exception = assertThrows(NotFoundException.class, () -> {

            expertService.updateExpertStatusToVerified(10);

        });
        assertEquals("کاربر با این شناسه موجود نیست", exception.getMessage());
        System.out.println("test :" + exception.getMessage());
    }


    @Test
    public void verifiedExpert() {
        expertService.updateExpertStatusToVerified(5);

    }


    @Test
    public void addExpertToSubServiceByInCorrectExpertId() {
        Exception exception = assertThrows(NotFoundException.class, () -> {

            expertSubServiceService.addExpertToSubService(20, 2);
        });
        assertEquals("کاربر با این شناسه موجود نیست", exception.getMessage());
        System.out.println("test :" + exception.getMessage());

    }

    @Test
    public void addExpertToSubServiceByInCorrectExpertStatus() {
        Exception exception = assertThrows(InvalidStatusException.class, () -> {

            expertSubServiceService.addExpertToSubService(20, 2);
        });
        assertEquals("متخصص تایید نشده است", exception.getMessage());
        System.out.println("test :" + exception.getMessage());
    }

    @Test
    public void addExpertToSubServiceByInCorrectSubServiceId() {
        Exception exception = assertThrows(NotFoundException.class, () -> {

            expertSubServiceService.addExpertToSubService(2, 20);
        });
        assertEquals("زیر خدمت با این شناسه موجود نیست", exception.getMessage());
        System.out.println("test :" + exception.getMessage());
    }

    @Test
    public void addExpertToSubService() {
        expertSubServiceService.addExpertToSubService(5, 2);
    }


    @Test
    public void deleteExpertFromSubService()  {


        expertSubServiceService.removeExpertFromSubService(2, 1);
    }




}

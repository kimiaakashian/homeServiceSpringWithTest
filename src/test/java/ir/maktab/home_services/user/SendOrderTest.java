package ir.maktab.home_services.user;

import ir.maktab.home_services.exception.InvalidInformationException;
import ir.maktab.home_services.exception.InvalidSuggestionInput;
import ir.maktab.home_services.exception.NotFoundException;
import ir.maktab.home_services.service.ExpertSuggestionService;
import ir.maktab.home_services.service.OrderService;
import jakarta.annotation.PostConstruct;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class SendOrderTest {


    private static OrderService orderService;

    @Autowired
    private OrderService orderServiceInline;

    @PostConstruct
    public void init() {
        this.orderService = orderServiceInline;
    }

    @Test
    @Order(1)
    public void sendOrderByCustomerInCorrectDate(){
        String date = "1403/03/20";

        Exception exception = assertThrows(InvalidInformationException.class ,()-> {
            orderService.sendOrderByCustomer(2,1,100D,"lab lab","10:30",date,"tehran");
        });
        assertEquals("تاریخ وارد شده صحیح نمی باشد" , exception.getMessage());

        System.out.println("test1 : " + exception.getMessage());

    }
    @Test
    @Order(2)
    public void sendOrderByCustomerNotFoundSubService(){
        String date = "1403/04/01";
        int subServiceId=100;

        Exception exception = assertThrows(NotFoundException.class ,()-> {
            orderService.sendOrderByCustomer(subServiceId,1,100D,"lab lab","10:30",date,"tehran");
        });
        assertEquals("زیرخدمت با این شناسه موجود نیست" , exception.getMessage());
        System.out.println("test 2 : " + exception.getMessage());

    }

    @Test
    @Order(3)
    public void sendOrderByCustomerNotFoundCustomer(){
        String date = "1403/04/01";
        int customerId=100;

        Exception exception = assertThrows(NotFoundException.class ,()-> {
            orderService.sendOrderByCustomer(2,customerId,100D,"lab lab","10:30",date,"tehran");
        });
        assertEquals("مشتری با این شناسه موجود نیست" , exception.getMessage());
        System.out.println("test 3 : " + exception.getMessage());

    }
    @Test
    public  void sendOrderByCustomer(){
        orderService.sendOrderByCustomer(2,1,100D,"lab lab","10:30","1403/04/01","tehran");

    }





}

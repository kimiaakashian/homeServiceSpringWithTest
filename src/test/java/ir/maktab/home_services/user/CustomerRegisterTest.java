package ir.maktab.home_services.user;

import ir.maktab.home_services.exception.InvalidInformationException;
import ir.maktab.home_services.helper.DateConvertorNew;
import ir.maktab.home_services.model.Customer;
import ir.maktab.home_services.service.CustomerService;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CustomerRegisterTest {


   @Autowired
   private CustomerService customerService;


    @Test
    @Order(1)
    public void customerRegistrationByInCorrectFirstName()
    {
        Exception exception = assertThrows(InvalidInformationException.class ,()-> {
            Customer customer =Customer.builder().firstName("12").lastName("Customer").email("Customer@gmail.com").password("Customer@123").DateOfRegistration(DateConvertorNew.todayDate()).customerCredit(0D).build();
            customerService.CustomerRegistration(customer);
        });
        assertEquals("اطلاعات وارد شده برای فیلد firstName صحیح نمی باشد!", exception.getMessage());
        System.out.println("test 1 : " + exception.getMessage());
    }



    @Test
    @Order(2)
    public void customerRegistrationByInCorrectLastName()
    {
        Exception exception = assertThrows(InvalidInformationException.class ,()-> {
            Customer customer =Customer.builder().firstName("customer").lastName("13").email("Customer@gmail.com").password("Customer@123").DateOfRegistration(DateConvertorNew.todayDate()).customerCredit(0D).build();
            customerService.CustomerRegistration(customer);
        });
        assertEquals("اطلاعات وارد شده برای فیلد lastName صحیح نمی باشد!", exception.getMessage());
        System.out.println("test 2: " + exception.getMessage());
    }


    @Test
    @Order(3)
    public void customerRegistrationByInCorrectEmail()
    {
        Exception exception = assertThrows(InvalidInformationException.class ,()-> {
            Customer customer =Customer.builder().firstName("customer").lastName("Customer").email("Customergmail.com").password("Customer@123").DateOfRegistration(DateConvertorNew.todayDate()).customerCredit(0D).build();
            customerService.CustomerRegistration(customer);
        });
        assertEquals("اطلاعات وارد شده برای فیلد email صحیح نمی باشد!", exception.getMessage());
        System.out.println("test 3 : " + exception.getMessage());
    }


    @Test
    @Order(4)
    public void customerRegistrationByInCorrectPassword()
    {
        Exception exception = assertThrows(InvalidInformationException.class ,()-> {
            Customer customer =Customer.builder().firstName("customer").lastName("Customer").email("Customer@gmail.com").password("customer@123").DateOfRegistration(DateConvertorNew.todayDate()).customerCredit(0D).build();
            customerService.CustomerRegistration(customer);
        });
        assertEquals("اطلاعات وارد شده برای فیلد password صحیح نمی باشد!", exception.getMessage());
        System.out.println("test 4 : " + exception.getMessage());
    }



    @Test
    @Order(5)
    public void customerRegistration(){
        Customer customer =Customer.builder().firstName("fateme").lastName("nadi").email("fati@gmail.com").password("Fati@123").DateOfRegistration(DateConvertorNew.todayDate()).customerCredit(0D).build();
        customerService.CustomerRegistration(customer);
        System.out.println("test 5 ok");
    }





}

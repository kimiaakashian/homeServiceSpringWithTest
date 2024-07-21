package ir.maktab.home_services.user;

import ir.maktab.home_services.exception.*;
import ir.maktab.home_services.helper.DateConvertorNew;
import ir.maktab.home_services.helper.ImageUtil;
import ir.maktab.home_services.model.*;
import ir.maktab.home_services.model.enums.ExpertStatus;
import ir.maktab.home_services.service.*;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UserTest {
    @Autowired
    private UserService userService;

    @Autowired
    private ExpertService expertService;


    @Autowired
    private HomeServiceService homeServiceService;

    @Autowired
    private CustomerService customerService;


    @Test
    public void signUpTest() {
        Users users = Users.builder().email("kianaa@gmail").password("123").build();
        userService.userSignUp(users);

    }

    @Test
    public void signInNotOkTest() {
        Users users = Users.builder().email("kimiaa@gmail").password("kimia13").build();
        userService.signIn(users.getEmail(), users.getPassword());
    }

    @Test
    public void signInOkTest() {
        Users users = Users.builder().email("kimiaa@gmail").password("kimia123").build();
        userService.signIn(users.getEmail(), users.getPassword());
        if (users != null)
            System.out.println(users.getEmail() + " ورود موفقیت امیز");
    }

    @Test
    public void expertRegistration() {
        String picturePath = "/Users/kimia/Downloads/IMG_5449.JPG2";
//        String picturePath = "/Users/kimia/Downloads/IMG_2363.jpg"; // file size
//        String picturePath = "/Users/kimia/Downloads/HW15 2.zip";
//        String picturePath = "/Users/kimia/Downloads/IMG_2363.jpg";
        boolean isCorrectPicture = false;
        try {
            isCorrectPicture = expertService.isValidPicture(picturePath);
        } catch (NotFoundException e) {
            System.out.println(e.getMessage());
        } catch (InvalidTypeException e) {
            System.out.println(e.getMessage());
        } catch (InvalidSizeException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        if (isCorrectPicture) {
            byte[] picture = ImageUtil.readFileToByteArray(picturePath);
            Expert expert = Expert.builder().firstName("hadi").lastName("ghanbari").password("Masoud@123").picture(picture).expertCredit(0D).expertStatus(ExpertStatus.جدید).email("masoud@gmail").averageScore(5D).fieldOfExpertise("ساختمان").build();
            try {
                expertService.ExpertRegistration(expert);
            } catch (InvalidInformationException e) {
                System.out.println(e.getMessage());
            } catch (Exception e) {
                System.out.println("خطای پیش بینی نشده!");
            }
        }
    }


    @Test
    public void customerRegistration() {
        Exception exception = assertThrows(InvalidInformationException.class, () -> {
            Customer customer = Customer.builder().firstName("12").lastName("nadi").email("fati@gmail.com").password("Fati@123").DateOfRegistration(DateConvertorNew.todayDate()).customerCredit(0D).build();
            customerService.CustomerRegistration(customer);
        });
        assertEquals("اطلاعات وارد شده برای فیلد firstName صحیح نمی باشد!", exception.getMessage());
    }

    @Test
    public void changeUserPasswordInCorrectOldPassword() {
        Exception exception = assertThrows(InvalidPasswordException.class, () -> {

            userService.changePassword(2, "Masoud@123", "Kimia@123");
        });
        assertEquals("پسورد قبلی اشتباه است", exception.getMessage());

    }

    @Test
    public void addHomeService() {
        try {
            HomeService homeService = HomeService.builder().name("ساختمان").build();
            homeServiceService.addHomeService(homeService);
        } catch (InformationDuplicateException e) {
            System.out.println(e.getMessage());
        }

    }







}

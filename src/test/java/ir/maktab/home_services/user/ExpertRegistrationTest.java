package ir.maktab.home_services.user;

import ir.maktab.home_services.exception.InvalidSizeException;
import ir.maktab.home_services.exception.InvalidTypeException;
import ir.maktab.home_services.helper.DateConvertorNew;
import ir.maktab.home_services.helper.ImageUtil;
import ir.maktab.home_services.model.Expert;
import ir.maktab.home_services.model.enums.ExpertStatus;
import ir.maktab.home_services.service.ExpertService;
import ir.maktab.home_services.service.UserService;
import jakarta.annotation.PostConstruct;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ExpertRegistrationTest {
    private static ExpertService expertService;


    @Autowired
    private ExpertService expertServiceInline;

    @Autowired
    private UserService userServiceInline;

    @PostConstruct
    public void init() {
        this.expertService = expertServiceInline;
    }

    @Test
    @Order(1)

    public void expertRegistrationInCorrectImageType() {
        String picturePath = "/Users/kimia/Downloads/IMG_5449.JPG2";
//        String picturePath = "/Users/kimia/Downloads/IMG_2363.jpg"; // file size
//        String picturePath = "/Users/kimia/Downloads/HW15 2.zip";
//        String picturePath = "/Users/kimia/Downloads/IMG_2363.jpg";
        Exception exception = assertThrows(InvalidTypeException.class ,()-> {
            boolean isCorrectPicture = false;

            isCorrectPicture = expertService.isValidPicture(picturePath);
            if (isCorrectPicture) {
                byte[] picture = ImageUtil.readFileToByteArray(picturePath);
                Expert expert = Expert.builder().firstName("expert").lastName("Expert").password("Expert@123").picture(picture).expertCredit(0D).expertStatus(ExpertStatus.جدید).email("expert@gmail").averageScore(5D).DateOfRegistration(DateConvertorNew.todayDate()).fieldOfExpertise("ساختمان").build();
                expertService.ExpertRegistration(expert);
            }
        });
        assertEquals("فقط فایل با پسوند JPG مورد قبول می باشد!" , exception.getMessage());
        System.out.println("test 1 : " + exception.getMessage());


    }

    @Test
    @Order(2)

    public void expertRegistrationInCorrectImageSize() {
//        String picturePath = "/Users/kimia/Desktop/Untitled.jpg";
        String picturePath = "/Users/kimia/Downloads/IMG_2363.jpg"; // file size
//        String picturePath = "/Users/kimia/Downloads/HW15 2.zip";
//        String picturePath = "/Users/kimia/Downloads/IMG_2363.jpg";
        Exception exception = assertThrows(InvalidSizeException.class ,()-> {
            boolean isCorrectPicture = false;

            isCorrectPicture = expertService.isValidPicture(picturePath);
            if (isCorrectPicture) {
                byte[] picture = ImageUtil.readFileToByteArray(picturePath);
                Expert expert = Expert.builder().firstName("hadi").lastName("ghanbari").password("Masoud@123").picture(picture).expertCredit(0D).expertStatus(ExpertStatus.جدید).email("masoud@gmail").averageScore(5D).fieldOfExpertise("ساختمان").build();
                expertService.ExpertRegistration(expert);
            }
        });
        assertEquals("حجم فایل بیشتر از  300K می باشد!" , exception.getMessage());
        System.out.println("test 2 : " + exception.getMessage());


    }
    @AfterAll
    public static void expertRegistration(){
    String picturePath = "/Users/kimia/Downloads/IMG_5449.JPG";
        boolean isCorrectPicture = false;
        isCorrectPicture = expertService.isValidPicture(picturePath);
        if (isCorrectPicture) {
            byte[] picture = ImageUtil.readFileToByteArray(picturePath);
            Expert expert = Expert.builder().firstName("sara").lastName("kashian").password("Masoud@123").picture(picture).expertCredit(0D).expertStatus(ExpertStatus.جدید).email("masoud@gmail").averageScore(10D).fieldOfExpertise("ساختمان").build();
            expertService.ExpertRegistration(expert);
            System.out.println("test 4 is ok ");

        }



    }




}

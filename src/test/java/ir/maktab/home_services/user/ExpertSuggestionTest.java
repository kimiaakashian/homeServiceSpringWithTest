package ir.maktab.home_services.user;

import ir.maktab.home_services.exception.InvalidSuggestionInput;
import ir.maktab.home_services.exception.NotFoundException;
import ir.maktab.home_services.service.ExpertSuggestionService;
import jakarta.annotation.PostConstruct;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ExpertSuggestionTest {
    private static ExpertSuggestionService expertSuggestionService;

    @Autowired
    private ExpertSuggestionService expertSuggestionServiceInline;

    @PostConstruct
    public void init() {
        this.expertSuggestionService = expertSuggestionServiceInline;
    }


    @Test
    @Order(1)
    public void sendSuggestionForOrderByInCorrectOrderId() {

        Exception exception = assertThrows(NotFoundException.class ,()-> {

        expertSuggestionService.sendSuggestionForOrder(4, 5, 10D, "1", "14", "1402");
        });
        assertEquals("سفارش با این شناسه موجود نیست" , exception.getMessage());

        System.out.println("test 1 : " + exception.getMessage());

    }
    @Test
    @Order(2)
    public void sendSuggestionForOrderByInCorrectExpertId() {

        Exception exception = assertThrows(NotFoundException.class ,()-> {

            expertSuggestionService.sendSuggestionForOrder(1, 20, 10D, "1", "14", "1402");
        });
        assertEquals("متخصص با این شناسه موجود نیست" , exception.getMessage());

        System.out.println("test 2 : " + exception.getMessage());

    }

    @Test
    @Order(3)
    public void sendSuggestionForOrderByNotRelatedExpertToThisSubService() {

        Exception exception = assertThrows(InvalidSuggestionInput.class ,()-> {

            expertSuggestionService.sendSuggestionForOrder(1, 5, 10D, "1", "14", "1402");
        });
        assertEquals("متخصص به این زیرخدمت مرتبط نیست" , exception.getMessage());

        System.out.println("test 3 : " + exception.getMessage());

    }

    @Test
    @Order(4)
    public void sendSuggestionForOrderByInvalidOrderStatus() {

        Exception exception = assertThrows(InvalidSuggestionInput.class ,()-> {

            expertSuggestionService.sendSuggestionForOrder(1, 5, 10D, "1", "14", "1402");
        });
        assertEquals("سفارش در وضعیت اخذ پیشنهاد متخصصان نیست" , exception.getMessage());

        System.out.println("test 4 : " + exception.getMessage());

    }

    @Test
    @Order(4)
    public void sendSuggestionForOrderByInCorrectSuggestedPrice() {

        Exception exception = assertThrows(InvalidSuggestionInput.class ,()-> {

            expertSuggestionService.sendSuggestionForOrder(1, 5, 10D, "1", "14", "1402");
        });
        assertEquals("قیمت پیشنهادی نباید از قیمت پایه ی زیرخدمت کمتر باشد" , exception.getMessage());

        System.out.println("test 4 : " + exception.getMessage());

    }

    @Test
    @Order(5)
    public  void send(){
        expertSuggestionService.sendSuggestionForOrder(1, 5, 500000D, "1", "14", "1402");

    }
//    @AfterAll
//    public static void sendSuggestionForOrder(){
//        expertSuggestionService.sendSuggestionForOrder(1, 5, 20000D, "1", "14", "1402");
//
//    }




}

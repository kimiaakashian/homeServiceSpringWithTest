package ir.maktab.home_services.user;

import ir.maktab.home_services.exception.*;
import ir.maktab.home_services.service.*;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class OrderTest {

    @Autowired
    private ExpertSuggestionService expertSuggestionService;

    @Autowired
    private ir.maktab.home_services.service.OrderService orderService;

    @Test
    public void sendSuggestionForOrder(){
        try {


            //expertSuggestionService.sendSuggestionForOrder(5,2,10D,"1","14","1402");

        }catch (InvalidSuggestionInput e){
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void sendOrderByCustomer(){
        String date = "1403/03/31";
        orderService.sendOrderByCustomer(1,1,100D,"lab lab","10:30",date,"tehran");

    }

    @Test
    public void displaySuggestionForOrder(){
        orderService.getSuggestionsForOrder(1).
                forEach(e -> System.out.println("\n  آيدی " + e.getId() + "\n  امتیاز متخصص " + e.getExperts().getAverageScore() + "\n ساعت " + e.getTime() + "\n تاریخ " + e.getDate()+ "\n قیمت پیشنهادی " + e.getSuggestedPrice()));
        ;
    }

    @Test
    public void acceptExpertSuggestions(){
        orderService.acceptSuggestionsToOrder(1,1);

    }

    @Test
    public void StartWork(){
//        Exception exception = assertThrows(InvalidStatusException.class ,()-> {

            orderService.startWork(1);
//        });
//        assertEquals("این سفارش در حالتی که شروع را بزنید نمیباشد" , exception.getMessage());


    }

    @Test
    public void  endOfWorkIfNotYetStart(){
        Exception exception = assertThrows(InvalidStatusException.class ,()-> {

            orderService.endOfWork(5);
        });
        assertEquals("این سفارش در حالت شروع نشده است" , exception.getMessage());


    }


    @Test
    public void  endOfWork(){

            orderService.endOfWork(1);


    }


}

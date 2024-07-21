package ir.maktab.home_services.service;

import ir.maktab.home_services.exception.InvalidSuggestionInput;
import ir.maktab.home_services.exception.NotFoundException;
import ir.maktab.home_services.model.*;
import ir.maktab.home_services.model.enums.OrderStatus;
import ir.maktab.home_services.repository.ExpertRepository;
import ir.maktab.home_services.repository.ExpertSubServiceRepository;
import ir.maktab.home_services.repository.ExpertSuggestionRepository;
import ir.maktab.home_services.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ExpertSuggestionService {
    private final ExpertSuggestionRepository expertSuggestionRepository;
    private final OrderRepository orderRepository;
    private final ExpertRepository expertRepository;
    private final ExpertSubServiceRepository expertSubServiceRepository;

    public void sendSuggestionForOrder(Integer orderId, Integer expertId, Double suggestionPrice, String duration,String time ,String date) {
        Orders orders = orderRepository.findById(orderId).orElseThrow(() -> new NotFoundException("سفارش با این شناسه موجود نیست"));
        Expert expert = expertRepository.findById(expertId).orElseThrow(() -> new NotFoundException("متخصص با این شناسه موجود نیست"));
        SubService subService = orders.getSubServices();
        List<ExpertSubService> expertSubServices = expertSubServiceRepository.findBySubServices(subService);



        boolean foundٍExpert = false;
        for (ExpertSubService ess : expertSubServices) {
            if (ess.getExperts().equals(expert)) {
                foundٍExpert = true;
                break;
            }
        }
        if (foundٍExpert) {
            throw new InvalidSuggestionInput("متخصص به این زیرخدمت مرتبط نیست");
        }

        if (!(orders.getOrderStatus()== OrderStatus.منتظر_پیشنهاد_متخصصان
                || orders.getOrderStatus()==OrderStatus.منتظر_انتخاب_متخصص )) {
            throw new InvalidSuggestionInput("سفارش در وضعیت اخذ پیشنهاد متخصصان نیست");
        }

        if (suggestionPrice < subService.getBasePrice()) {
            throw new InvalidSuggestionInput("قیمت پیشنهادی نباید از قیمت پایه ی زیرخدمت کمتر باشد");
        }

        ExpertSuggestion expertSuggestion = new ExpertSuggestion();
        expertSuggestion.setOrders(orders);
        expertSuggestion.setExperts(expert);
        expertSuggestion.setSuggestedPrice(suggestionPrice);
        expertSuggestion.setDurationOfWork(duration);
        expertSuggestion.setDate(date);
        expertSuggestion.setTime(time);

        expertSuggestionRepository.save(expertSuggestion);

        orders.setOrderStatus(OrderStatus.منتظر_انتخاب_متخصص);
        orderRepository.save(orders);
    }



}

package ir.maktab.home_services.service;

import ir.maktab.home_services.exception.InvalidInformationException;
import ir.maktab.home_services.exception.InvalidStatusException;
import ir.maktab.home_services.exception.NotFoundException;
import ir.maktab.home_services.helper.FormattedDateMatcher;
import ir.maktab.home_services.model.Customer;
import ir.maktab.home_services.model.ExpertSuggestion;
import ir.maktab.home_services.model.Orders;
import ir.maktab.home_services.model.SubService;
import ir.maktab.home_services.model.enums.OrderStatus;
import ir.maktab.home_services.repository.CustomerRepository;
import ir.maktab.home_services.repository.ExpertSuggestionRepository;
import ir.maktab.home_services.repository.OrderRepository;
import ir.maktab.home_services.repository.SubServiceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final SubServiceRepository subServiceRepository;
    private final CustomerRepository customerRepository;
    private final ExpertSuggestionRepository expertSuggestionRepository;

    public void sendOrderByCustomer
            (Integer subServiceId, Integer customerId, double customerSuggestedPrice, String orderDescription, String time, String date, String address) {
        if (!FormattedDateMatcher.checkGraterThanToday(date))
            throw new InvalidInformationException("تاریخ وارد شده صحیح نمی باشد");
        else {
            SubService subService = subServiceRepository.findById(subServiceId).orElseThrow(() -> new NotFoundException("زیرخدمت با این شناسه موجود نیست"));
            Customer customer = customerRepository.findById(customerId).orElseThrow(() -> new NotFoundException("مشتری با این شناسه موجود نیست"));

            Orders orders = Orders.builder().subServices(subService).customer(customer)
                    .customerSuggestedPrice(customerSuggestedPrice).orderDescription(orderDescription)
                    .orderStatus(OrderStatus.منتظر_پیشنهاد_متخصصان)
                    .time(time).Date(date).address(address)
                    .build();
            orderRepository.save(orders);
        }
    }
    public List<ExpertSuggestion> getSuggestionsForOrder(Integer orderId) {
        Orders orders = orderRepository.findById(orderId).orElseThrow(() -> new NotFoundException("سفارش با این شناسه موجود نیست"));
        Sort sort = Sort.by("experts.averageScore").descending().by("suggestedPrice").ascending();
        List<ExpertSuggestion> expertSuggestions = expertSuggestionRepository.findByOrders(orders,sort);
        //باید مرتب شده بنویسم سورت کنم
        return expertSuggestions;
    }

    public void acceptSuggestionsToOrder(Integer orderId ,Integer suggestionId){
        Orders orders = orderRepository.findById(orderId).orElseThrow(() -> new NotFoundException("سفارش با این شناسه موجود نیست"));
        ExpertSuggestion expertSuggestion = expertSuggestionRepository.findById(suggestionId).orElseThrow(() -> new NotFoundException("سفارش با این شناسه موجود نیست"));

        orders.setExpertSuggestions(expertSuggestion);
        orders.setOrderStatus(OrderStatus.منتظر_آمدن_متخصص_به_محل_شما);
        orderRepository.save(orders);

    }

    public void endOfWork(Integer orderId){
        Orders orders = orderRepository.findById(orderId).orElseThrow(() -> new NotFoundException("سفارش با این شناسه موجود نیست"));
        if (orders.getOrderStatus().equals(OrderStatus.شروع_شده)){
            orders.setOrderStatus(OrderStatus.انجام_شده);
            orderRepository.save(orders);
        }
        else
            throw new InvalidStatusException("این سفارش در حالت شروع نشده است");
    }

    public void startWork(Integer orderId){
        Orders orders = orderRepository.findById(orderId).orElseThrow(() -> new NotFoundException("سفارش با این شناسه موجود نیست"));
        if (orders.getOrderStatus().equals(OrderStatus.منتظر_آمدن_متخصص_به_محل_شما)){
            orders.setOrderStatus(OrderStatus.شروع_شده);
            orderRepository.save(orders);
        }
        else
            throw new InvalidStatusException("این سفارش در حالتی که شروع را بزنید نمیباشد");
    }
}

package ir.maktab.home_services.service;

import ir.maktab.home_services.exception.InvalidInformationException;
import ir.maktab.home_services.model.Customer;
import ir.maktab.home_services.model.Expert;
import ir.maktab.home_services.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;

    public void CustomerRegistration(Customer customer)  {

        if (checkData("firstName", customer.getFirstName(), "^[a-zA-Z]+$")
                && checkData("lastName", customer.getLastName(), "^[a-zA-Z]+$")
                && checkData("email", customer.getEmail(), "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$")
                && checkData("password", customer.getPassword(), "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{8,}$")
        ) {
            customerRepository.save(customer);
        }
    }

    public boolean checkData(String fieldName, String fieldValue, String regx) {
        boolean isCorrect = false;
        if (fieldValue == null || !fieldValue.matches(regx) || fieldValue.isEmpty()) {
            throw new InvalidInformationException("اطلاعات وارد شده برای فیلد "
                    + fieldName + " صحیح نمی باشد!");
        } else {
            isCorrect = true;
        }
        return isCorrect;
    }

}

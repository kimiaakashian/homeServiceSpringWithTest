package ir.maktab.home_services.service;

import ir.maktab.home_services.exception.*;
import ir.maktab.home_services.model.Expert;
import ir.maktab.home_services.model.enums.ExpertStatus;
import ir.maktab.home_services.repository.ExpertRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
@RequiredArgsConstructor
public class ExpertService {
    private final ExpertRepository expertRepository;


    public void ExpertRegistration(Expert expert)  {

        if (checkData("firstName", expert.getFirstName(), "^[a-zA-Z]+$")
                && checkData("lastName", expert.getLastName(), "^[a-zA-Z]+$")
                && checkData("email", expert.getEmail(), "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$")
                && checkData("password", expert.getPassword(), "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{8,}$")
        ) {
            expertRepository.save(expert);
        }
    }

    private boolean checkData(String fieldName, String fieldValue, String regx) {
        boolean isCorrect = false;
        if (fieldValue == null || !fieldValue.matches(regx) || fieldValue.isEmpty()) {
            throw new InvalidInformationException("اطلاعات وارد شده برای فیلد "
                    + fieldName + " صحیح نمی باشد!");
        } else {
            isCorrect = true;
        }
        return isCorrect;
    }

    public boolean isValidPicture(String picturePath)  {
        boolean isValid = false;
        if (picturePath.indexOf(".") <= 0 || picturePath.split("\\.")[1].equalsIgnoreCase("JPG")) {
            File imageFile = new File(picturePath);
            if (imageFile.exists() && imageFile.length() <= 300000) {
                isValid = true;
            } else {
                if (!imageFile.exists()) {
                    throw new NotFoundException("فایل مورد نظر یافت نشد!");
                } else if (imageFile.length() > 300000) {
                    throw new InvalidSizeException("حجم فایل بیشتر از  300K می باشد!");
                }
            }
        } else {
            throw new InvalidTypeException("فقط فایل با پسوند JPG مورد قبول می باشد!");
        }
        return isValid;
    }

    public void updateExpertStatusToVerified(Integer expertId) {
        Expert expert = expertRepository.findById(expertId).orElseThrow(() -> new NotFoundException("کاربر با این شناسه موجود نیست"));
        if (expert.getExpertStatus().equals("تایید_شده")) {
            throw new InvalidStatusException("این کاربر قبلا تایید شده است");
        }
        expert.setExpertStatus(ExpertStatus.تایید_شده);
        expertRepository.save(expert);
    }

}

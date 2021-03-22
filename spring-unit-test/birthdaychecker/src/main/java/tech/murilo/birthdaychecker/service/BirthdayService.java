package tech.murilo.birthdaychecker.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BirthdayService {
    
    public static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    
    @Autowired
    private LocalDateService localDateService;
    
    public String getBirthdayMessage(String name, LocalDate bornDay) {
        var nextBirthDay = getNextBirthday(bornDay);
        
        var message = new StringBuilder()
                .append("Olá ")
                .append(name)
                .append(", seu próximo aniversário será no dia ")
                .append(DATE_FORMAT.format(nextBirthDay))
                .append("!");
        
        return message.toString();
    }

    private LocalDate getNextBirthday(LocalDate bornDay) {
        var currentDate = localDateService.getCurrentDate();
        var birthday = LocalDate.of(currentDate.getYear(), bornDay.getMonth(), bornDay.getDayOfMonth());

        if (currentDate.isAfter(birthday)) {
            birthday = birthday.plusYears(1);
        }

        return birthday;
    }
}

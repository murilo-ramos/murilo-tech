package tech.murilo.birthdaychecker.controller;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import tech.murilo.birthdaychecker.service.BirthdayService;

@RestController
@RequestMapping("/birthday")
public class BirthdayController {
    
    @Autowired
    private BirthdayService birthdayService;
    
    @GetMapping("/next")
    public String getNextBirthdayMessage(@RequestParam("name") String name, @RequestParam("born_day") String bornDay)  {
        return birthdayService.getBirthdayMessage(name, LocalDate.parse(bornDay, BirthdayService.DATE_FORMAT));
    }

}

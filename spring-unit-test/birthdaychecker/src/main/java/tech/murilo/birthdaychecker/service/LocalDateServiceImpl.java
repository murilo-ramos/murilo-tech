package tech.murilo.birthdaychecker.service;

import java.time.LocalDate;

import org.springframework.stereotype.Service;

@Service
public class LocalDateServiceImpl implements LocalDateService {

    @Override
    public LocalDate getCurrentDate() {
        return LocalDate.now();
    }
}

package tech.murilo.birthdaychecker.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

public class LocalDateServiceImplTest {
    
    @Test
    public void shouldGetCurrentDate() {
        LocalDateServiceImpl localDateService = new LocalDateServiceImpl();
        var currentDate = localDateService.getCurrentDate();
        var expectedCurrentDate = LocalDate.now();
        
        assertEquals(expectedCurrentDate, currentDate);
    }
}

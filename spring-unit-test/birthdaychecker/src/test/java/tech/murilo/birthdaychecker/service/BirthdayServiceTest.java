package tech.murilo.birthdaychecker.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

public class BirthdayServiceTest {

    @Test
    public void shouldReturnBirthdayMessage() {
        var name    = "Murilo";
        var bornDay = LocalDate.of(1989, 7, 10);

        LocalDateService localDateServiceMock = mock(LocalDateService.class);
        when(localDateServiceMock.getCurrentDate()).thenReturn(LocalDate.of(2021, 3, 15));

        BirthdayServiceAlternative birthdayService = new BirthdayServiceAlternative();
        birthdayService.setLocalDateService(localDateServiceMock);

        var message = birthdayService.getBirthdayMessage(name, bornDay);

        assertEquals(message, "Olá Murilo, seu próximo aniversário será no dia 10/07/2021!");
    }

    @Test
    public void shouldReturnBirthdayMessageForNextYear() {
        var name    = "Murilo";
        var bornDay = LocalDate.of(1989, 7, 10);

        LocalDateService localDateServiceMock = mock(LocalDateService.class);
        when(localDateServiceMock.getCurrentDate()).thenReturn(LocalDate.of(2021, 10, 15));

        BirthdayServiceAlternative birthdayService = new BirthdayServiceAlternative();
        birthdayService.setLocalDateService(localDateServiceMock);

        var message = birthdayService.getBirthdayMessage(name, bornDay);

        assertEquals("Olá Murilo, seu próximo aniversário será no dia 10/07/2022!", message);
    }
}

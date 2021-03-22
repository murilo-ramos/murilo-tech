package tech.murilo.birthdaychecker.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class BirthdayServiceWithMockitoExtensionTest {
    
    @Mock
    private LocalDateService localDateServiceMock;
    
    @InjectMocks
    private BirthdayService birthdayService;

    @Test
    public void shouldReturnBirthdayMessage() {
        var name    = "Murilo";
        var bornDay = LocalDate.of(1989, 7, 10);

        when(localDateServiceMock.getCurrentDate()).thenReturn(LocalDate.of(2021, 3, 15));

        var message = birthdayService.getBirthdayMessage(name, bornDay);

        assertEquals("Olá Murilo, seu próximo aniversário será no dia 10/07/2021!", message);
    }

    @Test
    public void shouldReturnBirthdayMessageForNextYear() {
        var name    = "Murilo";
        var bornDay = LocalDate.of(1989, 7, 10);

        when(localDateServiceMock.getCurrentDate()).thenReturn(LocalDate.of(2021, 10, 15));

        var message = birthdayService.getBirthdayMessage(name, bornDay);

        assertEquals("Olá Murilo, seu próximo aniversário será no dia 10/07/2022!", message);
    }
}

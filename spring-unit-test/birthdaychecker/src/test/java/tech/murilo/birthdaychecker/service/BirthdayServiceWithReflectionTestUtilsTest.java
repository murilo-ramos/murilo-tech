package tech.murilo.birthdaychecker.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

public class BirthdayServiceWithReflectionTestUtilsTest {

    private LocalDateService localDateServiceMock;

    @BeforeEach
    public void setUp() {
        localDateServiceMock = mock(LocalDateService.class);
    }

    @Test
    public void shouldReturnBirthdayMessage() {
        var name    = "Murilo";
        var bornDay = LocalDate.of(1989, 7, 10);

        when(localDateServiceMock.getCurrentDate()).thenReturn(LocalDate.of(2021, 3, 15));

        BirthdayService birthdayService = buildBirthdayService();
        var message = birthdayService.getBirthdayMessage(name, bornDay);

        assertEquals("Olá Murilo, seu próximo aniversário será no dia 10/07/2021!", message);
    }

    @Test
    public void shouldReturnBirthdayMessageForNextYear() {
        var name    = "Murilo";
        var bornDay = LocalDate.of(1989, 7, 10);

        when(localDateServiceMock.getCurrentDate()).thenReturn(LocalDate.of(2021, 10, 15));

        BirthdayService birthdayService = buildBirthdayService();
        var message = birthdayService.getBirthdayMessage(name, bornDay);

        assertEquals("Olá Murilo, seu próximo aniversário será no dia 10/07/2022!", message);
    }

    private BirthdayService buildBirthdayService() {
        BirthdayService birthdayService = new BirthdayService();

        ReflectionTestUtils.setField(birthdayService, "localDateService", localDateServiceMock);

        return birthdayService;
    }
}

package tech.murilo.birthdaychecker.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;

import java.time.LocalDate;

import org.easymock.EasyMockExtension;
import org.easymock.Mock;
import org.easymock.TestSubject;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(EasyMockExtension.class)
public class BirthdayServiceWithEasyMockExtensionTest {
    
    @Mock
    private LocalDateService localDateServiceMock;
    
    @TestSubject
    private BirthdayService birthdayService;

    @Test
    public void shouldReturnBirthdayMessage() {
        var name    = "Murilo";
        var bornDay = LocalDate.of(1989, 7, 10);

        expect(localDateServiceMock.getCurrentDate()).andReturn(LocalDate.of(2021, 3, 15));
        replay(localDateServiceMock);

        var message = birthdayService.getBirthdayMessage(name, bornDay);

        assertEquals("Olá Murilo, seu próximo aniversário será no dia 10/07/2021!", message);
    }

    @Test
    public void shouldReturnBirthdayMessageForNextYear() {
        var name    = "Murilo";
        var bornDay = LocalDate.of(1989, 7, 10);

        expect(localDateServiceMock.getCurrentDate()).andReturn(LocalDate.of(2021, 10, 15));
        replay(localDateServiceMock);

        var message = birthdayService.getBirthdayMessage(name, bornDay);

        assertEquals("Olá Murilo, seu próximo aniversário será no dia 10/07/2022!", message);
    }
}

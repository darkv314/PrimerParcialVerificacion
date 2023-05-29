package calendarTest;

import calendar.Calendar;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class CalendarTest {
    //Ejercicio 6.1
    Calendar calendar = new Calendar();

    @ParameterizedTest
    @CsvSource({
            "1,1,0,02/01/0001",
            "1,1,9999,02/01/9999",
            "31,12,0,01/01/0001",
            "28,2,0,29/02/0001",
            "28,2,2021,01/03/2021",
            "28,2,2020,29/02/2020",
            "29,2,2020,01/03/2020",
            "30,4,2023,01/05/2023",
            "1,1,1,02/01/0001",
            "31,12,1,01/01/0002",
            "1,1,2021,02/01/2021",
            "31,12,2023,01/01/2024",

    })
    public void verifyNextDay(int day, int month, int year, String expectedResult) throws Exception{
        String actual = calendar.nextDay(day, month, year);
         Assertions.assertEquals(expectedResult, actual, "La fecha es incorrecta" + actual + " != " + expectedResult);
    }

    @ParameterizedTest
    @CsvSource({
        "0,1,1",
        "1,0,1",
        "32,1,1",
        "1,13,1",
        "1,1,-1",
        "32,13,1",
        "32,1,-1",
        "1,13,-1",
        "32,13,-1",
        "-1,1,1",
        "1,-1,1",
        "1,1,-1"
    })
    public void verifyNextDayExeption(int day, int month, int year) throws Exception{
        Calendar calendar = new Calendar();
        Assertions.assertThrows(Exception.class, () -> {calendar.nextDay(day, month, year);});
    }
}

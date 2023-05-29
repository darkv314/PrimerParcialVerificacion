package calendar;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Calendar {
// Implementacion Ejercicio 6.1
    public Calendar() {}

    public String nextDay(int day, int month, int year) throws Exception{
        if(day <=0 || day > 31 || month <= 0 || month > 12 || year < 0){
            throw new Exception("Verifique los valores ingresados");
        }
        LocalDate fechaActual = LocalDate.of(year, month, day);
        LocalDate fechaSiguiente = fechaActual.plusDays(1);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return fechaSiguiente.format(formatter);
    }

}

package agenciaVuelosStaticTest;

import agenciaVueloStatic.AgenciaVuelosServiceStatic;
import agenciaVueloStatic.AgenciaVuelosStatic;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.MockedStatic;

import static org.mockito.Mockito.mockStatic;

public class AgenciaVuelosStaticTest {
    // Ejercicio 6.3
    MockedStatic<AgenciaVuelosServiceStatic> mockedStatic = mockStatic(AgenciaVuelosServiceStatic.class);

    @AfterEach
    public void deregister() {
        mockedStatic.close();
    }

    @ParameterizedTest
    @CsvSource({
            "La Paz,2,29,5,2023,el dia Lunes 29 Mayo 2023 existen 2 pasajes para La Paz",
            "La Paz,2,30,5,2023,el dia Martes 30 Mayo 2023 existen 2 pasajes para La Paz",
            "La Paz,2,31,5,2023,el dia Miercoles 31 Mayo 2023 existen 2 pasajes para La Paz",
            "La Paz,2,1,6,2023,el dia Jueves 1 Junio 2023 existen 2 pasajes para La Paz",
            "La Paz,2,2,6,2023,el dia Viernes 2 Junio 2023 existen 2 pasajes para La Paz",
            "La Paz,2,3,6,2023,el dia Sabado 3 Junio 2023 existen 2 pasajes para La Paz",
            "La Paz,2,4,6,2023,el dia Domingo 4 Junio 2023 existen 2 pasajes para La Paz",
            "La Paz,30,29,5,2023,no existen suficientes pasajes para La Paz",
            "Cochabamba,2,29,5,2024,no existen suficientes pasajes para Cochabamba",
            "Santa Cruz, 1000,1,1,2021,el dia Viernes 1 Enero 2021 existen 1000 pasajes para Santa Cruz",
            "Santa Cruz, 1000,1,1,2000,el dia Sabado 1 Enero 2000 existen 1000 pasajes para Santa Cruz",
            "Santa Cruz, 1001,1,1,0,no existen suficientes pasajes para Santa Cruz",
            "Tarija, 1000,1,1,0,no existen suficientes pasajes para Tarija",
    })
    public void verifyReservaVuelo(String destino, int cantidad, int day, int month, int year, String expected) throws Exception{
        mockedStatic.when(() -> AgenciaVuelosServiceStatic.existenPasajes("La Paz", 2)).thenReturn(true);
        mockedStatic.when(() -> AgenciaVuelosServiceStatic.existenPasajes("La Paz", 30)).thenReturn(false);
        mockedStatic.when(() -> AgenciaVuelosServiceStatic.existenPasajes("Cochabamba", 2)).thenReturn(false);
        mockedStatic.when(() -> AgenciaVuelosServiceStatic.existenPasajes("Santa Cruz", 1000)).thenReturn(true);
        mockedStatic.when(() -> AgenciaVuelosServiceStatic.existenPasajes("Santa Cruz", 1001)).thenReturn(false);
        mockedStatic.when(() -> AgenciaVuelosServiceStatic.existenPasajes("Tarija", 1000)).thenReturn(false);
        mockedStatic.when(() -> AgenciaVuelosServiceStatic.getDay(29, 5, 2023)).thenReturn("Lunes");
        mockedStatic.when(() -> AgenciaVuelosServiceStatic.getDay(30, 5, 2023)).thenReturn("Martes");
        mockedStatic.when(() -> AgenciaVuelosServiceStatic.getDay(31, 5, 2023)).thenReturn("Miercoles");
        mockedStatic.when(() -> AgenciaVuelosServiceStatic.getDay(1, 6, 2023)).thenReturn("Jueves");
        mockedStatic.when(() -> AgenciaVuelosServiceStatic.getDay(2, 6, 2023)).thenReturn("Viernes");
        mockedStatic.when(() -> AgenciaVuelosServiceStatic.getDay(3, 6, 2023)).thenReturn("Sabado");
        mockedStatic.when(() -> AgenciaVuelosServiceStatic.getDay(4, 6, 2023)).thenReturn("Domingo");
        mockedStatic.when(() -> AgenciaVuelosServiceStatic.getDay(1, 1, 2021)).thenReturn("Viernes");
        mockedStatic.when(() -> AgenciaVuelosServiceStatic.getDay(1, 1, 2000)).thenReturn("Sabado");

        AgenciaVuelosStatic agenciaVuelos = new AgenciaVuelosStatic();
        String actual = agenciaVuelos.reservaVuelo(destino, cantidad, day, month, year);
        Assertions.assertEquals(expected, actual, "El resultado es incorrecto" + actual + " != " + expected);
    }

    @ParameterizedTest
    @CsvSource({
            "La Paz,0,29,5,2023",
            "La Paz,-1,29,5,2023",
            "La Paz,2,-1,5,2023",
            "La Paz,2,0,5,2023",
            "La Paz,2,32,5,2023",
            "La Paz,2,29,-1,2023",
            "La Paz,2,29,0,2023",
            "La Paz,2,29,13,2023",
            "La Paz,2,29,5,-1",
    })
    public void verifyReservaVueloExeption(String destino, int cantidad, int day, int month, int year) throws Exception{
        AgenciaVuelosStatic agenciaVuelos = new AgenciaVuelosStatic();
        Assertions.assertThrows(Exception.class, () -> {agenciaVuelos.reservaVuelo(destino, cantidad, day, month, year);});
    }
}

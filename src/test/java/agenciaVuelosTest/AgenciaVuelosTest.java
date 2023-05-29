package agenciaVuelosTest;

import agenciaVuelos.AgenciaVuelos;
import agenciaVuelos.AgenciaVuelosService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Mockito;

public class AgenciaVuelosTest {
    // Ejercicio 6.2
    AgenciaVuelosService agenciaVuelosService;

    @BeforeEach
    public void setUp() {
        agenciaVuelosService = Mockito.mock(AgenciaVuelosService.class);
    }

    @ParameterizedTest
    @CsvSource({
        "La Paz,2,29,5,2023,el dia Lunes 29 Mayo 2023 existen 2 pasajes para La Paz",
        "La Paz,30,29,5,2023,no existen suficientes pasajes para La Paz",
        "Cochabamba,2,29,5,2024,no existen suficientes pasajes para Cochabamba",
        "Santa Cruz, 1000,1,1,2021,el dia Viernes 1 Enero 2021 existen 1000 pasajes para Santa Cruz",
        "Santa Cruz, 1000,1,1,2000,el dia Sabado 1 Enero 2000 existen 1000 pasajes para Santa Cruz",
        "Tarija, 1000,1,1,0,no existen suficientes pasajes para Tarija",
    })
    public void verifyReservaVuelo(String destino, int cantidad, int day, int month, int year, String expected) throws Exception{
        Mockito.when(agenciaVuelosService.existenPasajes("La Paz", 2)).thenReturn(true);
        Mockito.when(agenciaVuelosService.existenPasajes("La Paz", 30)).thenReturn(false);
        Mockito.when(agenciaVuelosService.existenPasajes("Cochabamba", 2)).thenReturn(false);
        Mockito.when(agenciaVuelosService.existenPasajes("Santa Cruz", 1000)).thenReturn(true);
        Mockito.when(agenciaVuelosService.existenPasajes("Tarija", 1000)).thenReturn(false);
        Mockito.when(agenciaVuelosService.getDay(29, 5, 2023)).thenReturn("Lunes");
        Mockito.when(agenciaVuelosService.getDay(1, 1, 2021)).thenReturn("Viernes");
        Mockito.when(agenciaVuelosService.getDay(1, 1, 2000)).thenReturn("Sabado");

        AgenciaVuelos agenciaVuelos = new AgenciaVuelos(agenciaVuelosService);
        String actual = agenciaVuelos.reservaVuelo(destino, cantidad, day, month, year);
        Assertions.assertEquals(expected, actual, "El resultado es incorrecto" + actual + " != " + expected);
    }
}

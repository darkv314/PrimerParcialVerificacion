package agenciaVuelos;

import java.util.HashMap;
import java.util.Map;

public class AgenciaVuelos {

    AgenciaVuelosService agenciaVuelosService;
    Map<Integer, String> months = new HashMap<>();
    public AgenciaVuelos(AgenciaVuelosService agenciaVuelosService) {
        this.agenciaVuelosService = agenciaVuelosService;
        months.put(1, "Enero");
        months.put(2, "Febrero");
        months.put(3, "Marzo");
        months.put(4, "Abril");
        months.put(5, "Mayo");
        months.put(6, "Junio");
        months.put(7, "Julio");
        months.put(8, "Agosto");
        months.put(9, "Septiembre");
        months.put(10, "Octubre");
        months.put(11, "Noviembre");
        months.put(12, "Diciembre");
    }

    public String reservaVuelo(String destino, int cantidad, int day, int month, int year) throws Exception{
        if(day <=0 || day > 31 || month <= 0 || month > 12 || year < 0 || cantidad <= 0){
            throw new Exception("Verifique los valores ingresados");
        }
        if(this.agenciaVuelosService.existenPasajes(destino, cantidad)){
            String weekDay = this.agenciaVuelosService.getDay(day, month, year);
            return "el dia " + weekDay + " " + day + " " + months.get(month) + " " + year + " existen " + cantidad + " pasajes para " + destino;
        }else{
            return "No existen suficientes pasajes para " + destino;
        }
    }

}

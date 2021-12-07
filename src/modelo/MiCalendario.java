/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author ZURITA
 */
public class MiCalendario {
    public static int getDistanceMonth(String dateStr1,String dateStr2) throws ParseException {
        // Analiza el objeto de fecha
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date1 = sdf.parse(dateStr1);
        Date date2 = sdf.parse(dateStr2);
        // Usa el objeto Calendario para manipular el objeto de fecha
        Calendar c1 = Calendar.getInstance();
        Calendar c2= Calendar.getInstance();
        c1.setTime(date1);
        c2.setTime(date2);
        // Obtenga el número de milisegundos de la fecha, que se utiliza para comparar quién viene primero y quién sigue la fecha
        long time1 = date1.getTime();
        long time2 = date2.getTime();
        // Restar años
        int yearSubtract = c2.get(Calendar.YEAR) - c1.get(Calendar.YEAR);
        // Restar meses
        int monthSubtract = 0;
        // Aplica diferentes fórmulas según la secuencia de fechas
        if (time1 < time2) {
            monthSubtract =c2.get(Calendar.MONTH) - c1.get(Calendar.MONTH) + 1;
        } else {
            monthSubtract =c2.get(Calendar.MONTH) - c1.get(Calendar.MONTH) - 1;
        }

        return (int)Math.abs(yearSubtract * 12 + monthSubtract);


    }
}


package fuentereportes;
import datos.GestorEquipos;
import datos.GestorProveedores;
import java.util.ArrayList;
import modelo.Equipo;
import modelo.Proveedor;
import modelo.Sesion;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

import net.sf.jasperreports.view.save.*;
import net.sf.jasperreports.export.*;


public class LlamadasPorProveedor implements JRDataSource{
    private final Object [] [] listado;
    private int index; //sirve para posicionarse en la coleccion de datos
    private String empleado = Sesion.getNomyAp();
    
    public LlamadasPorProveedor(){
        index = -1;
        //se trabaja con objetos para trabajar con distintos tipos de datos
        
        ArrayList<Proveedor> proveedores = GestorProveedores.leerProveedores();
        int[] cantidad = new int[proveedores.size()];
        int i = 0;
        
        for(Proveedor proveedor : proveedores){
            ArrayList<Equipo> equipos = GestorEquipos.leerEquiposPorProveedor(proveedor.getId());
            cantidad[i] = equipos.size();
            i++;
        }
        
        listado = new Object[proveedores.size()][3];
        
        for(int j = 0; j < listado.length; j++){
            listado[j][0] = proveedores.get(j).getCuit()+"";
            listado[j][1] = proveedores.get(j).getRazonSocial();
            listado[j][2] = cantidad[j]+"";
        }
    }




// sirve para obtener y recorrer toda la informacion que hay en toda la coleccion de datos
    @Override
    public boolean next() throws JRException {
        index++;
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        return (index < listado.length);
    }

    @Override
    public Object getFieldValue(JRField jrf) throws JRException {
        //procesos para ubicar los elementos 
        Object value = null;
        String fielName = jrf.getName(); // para obtener el campo donde estamos posicionados
        
        switch(fielName){
            case "CUIT":
                value = listado[index][0];
            break;
            
            case "Razon Social":
                value = listado[index][1];
            break;
            
            case "Cantidad de llamadas":
                value = listado[index][2];
            break;
            
            case "Empleado":
                value = empleado;
            break;
                
        }
        return value;
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public static JRDataSource getDataSource(){
        return new LlamadasPorProveedor();//para inicializar la fuente de datos
    }
    
}

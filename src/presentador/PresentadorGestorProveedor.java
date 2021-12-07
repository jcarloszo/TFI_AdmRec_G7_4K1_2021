/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentador;

import datos.GestorDomicilio;
import datos.GestorEquipos;
import datos.GestorEstados;
import datos.GestorProveedores;
import datos.GestorTecnicos;
import fuentereportes.LlamadasPorProveedor;
import interfaz.IGestionProveedor;
import java.util.ArrayList;
import java.util.Date;
import modelo.Equipo;
import modelo.Estado;
import modelo.Proveedor;
import modelo.Tecnico;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author ZURITA
 */
public class PresentadorGestorProveedor {
    private IGestionProveedor vista;
    private ArrayList<Proveedor> proveedores = new ArrayList();

    public PresentadorGestorProveedor(IGestionProveedor vista) {
        this.vista = vista;
    }

    public void leerProveedores() {
        proveedores = GestorProveedores.leerProveedores();
        
        double calificaciones[] = calcularCalificaciones(proveedores);
        ArrayList<Estado> estados = leerEstados(proveedores);
        ArrayList<String> direcciones = leerDireccion(proveedores);
        
        vista.llenarTablaProveedores(proveedores, estados, direcciones, calificaciones);
    }

    private ArrayList<Estado> leerEstados(ArrayList<Proveedor> proveedores) {
        ArrayList<Estado> estados = new ArrayList();
        for(Proveedor prov : proveedores){
            estados.add(GestorEstados.buscarEstado(prov.getIdEstado()));
        }
        return estados;
    }

    private ArrayList<String> leerDireccion(ArrayList<Proveedor> proveedores) {
        ArrayList<String> direcciones = new ArrayList();
        for(Proveedor prov : proveedores){
            direcciones.add(GestorDomicilio.buscarDomicilio(prov.getIdDomicilio()).toString());
        }
        return direcciones;
    }

    public void leerTecnicos(long cuit) {
        Proveedor proveedor = GestorProveedores.buscarPorCuit(cuit);
        ArrayList<Tecnico> tecnicos = GestorTecnicos.leerTecnicosPorProveedor(proveedor.getId());
        
        ArrayList<String> direcciones = new ArrayList();
        ArrayList<Estado> estados = new ArrayList();
        
        for(int i = 0; i < tecnicos.size(); i++){
            direcciones.add(GestorDomicilio.buscarDomicilio(tecnicos.get(i).getIdDomicilio()).toString());
            estados.add(GestorEstados.buscarEstado(tecnicos.get(i).getIdEstado()));
        }
        
        vista.llenarTablaTecnicos(tecnicos, estados, direcciones);
    }

    public void buscarProveedor(long cuit) {
        Proveedor proveedor = GestorProveedores.buscarPorCuit(cuit);
        if(proveedor != null){
            proveedores = new ArrayList();
            proveedores.add(proveedor);
        
            double calificaciones[] = calcularCalificaciones(proveedores);
            ArrayList<Estado> estados = leerEstados(proveedores);
            ArrayList<String> direcciones = leerDireccion(proveedores);

            vista.llenarTablaProveedores(proveedores, estados, direcciones, calificaciones);
        } else {
            vista.mostrarMje("Error en el CUIT o el proveedor no existe.");
        }
    }

    private double[] calcularCalificaciones(ArrayList<Proveedor> proveedores) {
        double[] intArray = new double[proveedores.size()];
        
        for(int i = 0; i < intArray.length; i++){
            ArrayList<Equipo> equipos = GestorEquipos.leerEquiposPorProveedor(proveedores.get(i).getId());
            if(equipos.size() == 0){
                intArray[i] = 0;
            } else {
                double total = (double) equipos.size() * 3;
                double sumaReal = 0;
                for(Equipo equipo : equipos){
                    sumaReal += (double) equipo.getIdEstado();
                }
                intArray[i] = (sumaReal / total) * 100;
            }
        }
        
        return intArray;
    }

    public void eliminarProveedor(long cuit) {
        Proveedor prov = GestorProveedores.buscarPorCuit(cuit);
        ArrayList<Equipo> equipos = GestorEquipos.leerEquiposPorProveedor(prov.getId());
        ArrayList<Tecnico> tecnicos = GestorTecnicos.leerTecnicosPorProveedor(prov.getId());
        if(equipos.size() > 0 || tecnicos.size() > 0){
            vista.mostrarMje("El proveedor tiene información importante vinculada. NO se puede eliminar.");
        } else {
            GestorProveedores.borrar(prov.getId());
            vista.mostrarMje("El proveedor se ha eliminado del sistema");
            leerProveedores();
        }
    }

    public void generarReporte() {
        try{
            //con esta clase se carga el archivo del reporte
            JasperReport report = (JasperReport) JRLoader.loadObject(getClass().getResource("/reportes/LlamadasPorProveedor.jasper"));
            
            /*
            Para cargar los datos en el reporte. como la clase FuenteDeDatos implemento la interfaz
            JRDataSources se convirtio en la fuente de datos 
            */
            JasperPrint jprint = JasperFillManager.fillReport(report, null, LlamadasPorProveedor.getDataSource());
            
            //Para mostrar lo que cargamos. Generamos un Frame "view"
            JasperViewer view = new JasperViewer(jprint, false);
            //view.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            view.setVisible(true);
            
        }
        catch(JRException ex){
            System.out.print(ex);
            ex.getMessage();
        }
    }


}

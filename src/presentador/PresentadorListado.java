/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentador;

import fuentereportes.*;
import datos.GestorEquipos;
import datos.GestorEstados;
import datos.GestorProveedores;
import interfaz.IListado;
import java.util.ArrayList;
import modelo.Equipo;
import modelo.EstadoEquipo;
import modelo.Proveedor;
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
public class PresentadorListado {
    private IListado vista;
    private ArrayList<Proveedor> proveedores = new ArrayList<>();
    private ArrayList<Proveedor> seleccionados = new ArrayList<>();
    private ArrayList<Equipo> equipos = new ArrayList<>();

    public PresentadorListado(IListado vista) {
        this.vista = vista;
    }

    public void leerProveedores() {
        proveedores = GestorProveedores.leerProveedores();
        vista.cargarTablaProveedor(proveedores);
    }

    public void selecProveedor(long cuit) {
        boolean bandera = false;
        for(Proveedor selec : seleccionados){
            if(selec.getCuit() == cuit){
                bandera = true;
                break;
            }
        }
        if(!bandera){
            Proveedor prov = GestorProveedores.buscarPorCuit(cuit);
            seleccionados.add(prov);
            leerProveedores();
            vista.cargarTablaSeleccionados(seleccionados);
        }
    }

    public void quitarSeleccion(long cuit) {
        ArrayList<Proveedor> nuevo = new ArrayList<>();
        for(Proveedor prov : seleccionados){
            if(prov.getCuit() != cuit){
                nuevo.add(prov);
            }
        }
        seleccionados = nuevo;
        leerProveedores();
        vista.cargarTablaSeleccionados(seleccionados);
    }

    public void buscarProveedor(long cuit) {
        Proveedor prov = GestorProveedores.buscarPorCuit(cuit);
        if(prov != null){
            ArrayList<Proveedor> proveedores = new ArrayList<>();
            proveedores.add(prov);
            vista.cargarTablaProveedor(proveedores);
        } else {
            vista.mostrarMje("CUIT erroneo o inexistente");
        }
    }

    public void listarEquiposAdquiridos() {
        ArrayList<Proveedor> provSelect = seleccionados;
        if(provSelect.size()==0) provSelect = GestorProveedores.leerProveedores();
        
        equipos = new ArrayList<>();
        
        for(Proveedor proveedor : provSelect){
            for(Equipo eq : GestorEquipos.leerEquiposPorProveedor(proveedor.getId())){
                equipos.add(eq);
            }
        }
        
        vista.cargarTablaEquipos(equipos);
    }

    public void leerDatosEquipo(int rowIndex) {
        Equipo equipo = equipos.get(rowIndex);
        EstadoEquipo estadoEquipo = GestorEstados.buscarEstadoEquipo(equipo.getIdEstado());
        vista.mostrarDatosEquipo(equipo.getComentarios(), estadoEquipo.getEstadoEquipo());
    }

    public void generarReporte() {
        try{
            //con esta clase se carga el archivo del reporte
            JasperReport report = (JasperReport) JRLoader.loadObject(getClass().getResource("/reportes/PromedioDeAntigüedad.jasper"));
            
            /*
            Para cargar los datos en el reporte. como la clase FuenteDeDatos implemento la interfaz
            JRDataSources se convirtio en la fuente de datos 
            */
            JasperPrint jprint = JasperFillManager.fillReport(report, null, PromedioDeAntigüedad.getDataSource());
            
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

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author ZURITA
 */
public class Equipo {
    private int id;
    private String descripcion;
    private int garantia;   //En meses
    private int idProveedor;
    private int idEstado;
    private Date adquisicion;
    private String comentarios;
    
    public Equipo() {
        this.id = (int) (Math.random() * 100000) + 1;
        this.adquisicion = new Date();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getGarantia() {
        return garantia;
    }

    public void setGarantia(int garantia) {
        this.garantia = garantia;
    }

    public int getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(int idProveedor) {
        this.idProveedor = idProveedor;
    }

    public int getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(int idEstado) {
        this.idEstado = idEstado;
    }

    public Date getAdquisicion() {
        return adquisicion;
    }

    public void setAdquisicion(Date adquisicion) {
        this.adquisicion = adquisicion;
    }

    public String getComentarios() {
        return comentarios;
    }

    public void setComentarios(String comentarios) {
        this.comentarios = comentarios;
    }
    
    public String formatoFecha(){
        SimpleDateFormat formateadorFecha = new SimpleDateFormat("yyyy-MM-dd");
        return formateadorFecha.format(this.adquisicion);
    }
    
    public String vencimiento(){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(this.getAdquisicion());
        calendar.add(Calendar.MONTH, this.getGarantia());
        
        SimpleDateFormat formateadorFecha = new SimpleDateFormat("yyyy-MM-dd");
        return formateadorFecha.format(calendar.getTime());
    }
}

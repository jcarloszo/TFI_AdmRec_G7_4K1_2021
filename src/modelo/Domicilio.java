/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 *
 * @author ZURITA
 */
public class Domicilio {
    private int id;
    private String calle;
    private int numero;
    private int codPostalLocalidad;

    public Domicilio() {
        this.id = (int) (Math.random() * 100000) + 1;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public int getCodPostalLocalidad() {
        return codPostalLocalidad;
    }

    public void setCodPostalLocalidad(int codPostalLocalidad) {
        this.codPostalLocalidad = codPostalLocalidad;
    }

    @Override
    public String toString() {
        return this.calle + " " + this.numero;
    }
    
    
    
}

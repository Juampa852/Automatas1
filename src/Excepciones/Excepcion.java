/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Excepciones;

/**
 * Clase para realizar excepciones
 * @author juamp_000
 */
public class Excepcion extends Exception {
    /**
     * Constructor
     * @param msj mensaje de error a mostrar
     */
    public Excepcion (String msj){
        super(msj);
    }
}

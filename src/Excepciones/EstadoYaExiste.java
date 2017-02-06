/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Excepciones;

/**
 * Excepción en caso de que un estado nuevo, que ya existe, quiera ingresarse
 * @author juampa
 */
public class EstadoYaExiste extends Exception {
    public EstadoYaExiste(String msj){
        super(msj);
    }
}

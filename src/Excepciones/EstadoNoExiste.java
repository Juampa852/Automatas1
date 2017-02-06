/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Excepciones;

/**
 * Excepción si un estado que quiere eliminarse, no existiera
 * @author juampa
 */
public class EstadoNoExiste extends Exception{
    public EstadoNoExiste (String msj){
        super(msj);
    }
}

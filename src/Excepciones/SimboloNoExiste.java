/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Excepciones;

/**
 * Excepción en caso de que se quiera eliminar un símbolo inexistente del alfabeto
 * @author juampa
 */
public class SimboloNoExiste extends Exception {
    public SimboloNoExiste(String msj){
        super(msj);
    }
}

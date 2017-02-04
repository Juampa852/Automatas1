/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Excepciones;

/**
 * Excepcion por si al querer ingresar un símbolo nuevo al alfabeto, este ya existiera
 * @author juampa
 */
public class SimboloYaExiste extends Exception {
    public SimboloYaExiste(String msj){
        super(msj);
    }
}

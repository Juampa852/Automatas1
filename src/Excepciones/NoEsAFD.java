/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Excepciones;

/**
 * Excepción en caso de que el autómata no sea AFD
 * @author juamp
 */
public class NoEsAFD extends Exception{
    public NoEsAFD(String msj){
        super(msj);
    }
}

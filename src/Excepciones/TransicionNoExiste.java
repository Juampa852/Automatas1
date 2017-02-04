/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Excepciones;

/**
 * Excepcion en caso de que una transicion no exista
 * @author juampa
 */
public class TransicionNoExiste extends Exception {
    public TransicionNoExiste(String msj){
        super(msj);
    }
}

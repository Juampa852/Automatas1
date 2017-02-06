/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Excepciones;

/**
 * Excepcion en caso de que una transicion ya exista en un estado
 * @author juampa
 */
public class TransicionYaExiste extends Exception {
    public TransicionYaExiste(String msj){
        super(msj);
    }
}

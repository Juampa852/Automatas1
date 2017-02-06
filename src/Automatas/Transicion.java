/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Automatas;

/**
 * Clase para controlar las propiedades de las transiciones de un estado
 * @author juampa
 */
public class Transicion {
    private char letra;
    private Estado siguiente;
    /**
     * Constructor de la transicion
     * @param letra la letra con la cual cambia de estado
     */
    public Transicion(char letra){
        this.letra=letra;
    }
    /**
     * Obtiene la letra con la cual se ejecuta la transicion
     * @return letra con la cual cambia de estado
     */
    public char getLetra() {
        return letra;
    }
    /**
     * Obtiene el estado al cual se cambia con esta transición (con la letra de esta transicion)
     * @return estado al cual se cambia
     */
    public Estado getSiguiente() {
        return siguiente;
    }
    /**
     * Establece cual es el estado siguiente al cual se cambia
     * @param est estado al cual se cambia
     */
    public void setSiguiente(Estado est){
        this.siguiente=est;
    }
    
}

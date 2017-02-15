/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Automatas;

/**
 *
 * @author Lenovo
 */
public class NodoDoble {
    private int dato;
 private NodoDoble anterior;
 private NodoDoble siguiente;

    public NodoDoble() {
        anterior=null;
        siguiente=null;
    }

    public NodoDoble(int dato, NodoDoble anterior, NodoDoble siguiente) {
        this.dato = dato;
        this.anterior = anterior;
        this.siguiente = siguiente;
    }
 
    public int getDato(){
        return dato;
    }
    
    public NodoDoble getAnterior(){
        return anterior;
    }
    
    public NodoDoble getSiguiente() {
        return siguiente;
    }

    public void setDato(int dato) {
        this.dato = dato;
    }

    public void setAnterior(NodoDoble anterior) {
        this.anterior = anterior;
    }

    public void setSiguiente(NodoDoble siguiente) {
        this.siguiente = siguiente;
    }
    @Override
    public String toString() {
        return "" + dato;
    }
    
}

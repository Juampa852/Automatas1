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
    private String dato;
    private String dato2;
 private NodoDoble anterior;
 private NodoDoble siguiente;

    public NodoDoble() {
        anterior=null;
        siguiente=null;
    }

    public NodoDoble(String dato,String dato2,NodoDoble anterior, NodoDoble siguiente) {
        this.dato = dato;
        this.dato2=dato2;
        this.anterior = anterior;
        this.siguiente = siguiente;
    }
 
    public String getDato(){
        return dato;
    }

    public String getDato2() {
        return dato2;
    }
    

    public NodoDoble(String dato2) {
        this.dato2 = dato2;
    }
    
    public NodoDoble getAnterior(){
        return anterior;
    }
    
    public NodoDoble getSiguiente() {
        return siguiente;
    }

    public void setDato(String dato) {
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
        return "" + dato+ dato2;
    }
    
}

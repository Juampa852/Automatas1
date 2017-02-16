/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Automatas;

/**
 *clase encargada y relacionada con ListaDoble1 para la implementación de la lista
 * @author Juan Miguel
 */
public class NodoDoble {
    private String dato;
    private String dato2;
 private NodoDoble anterior;
 private NodoDoble siguiente;
 /**
  * constuctor de la clase
 */
    public NodoDoble() {
        anterior=null;
        siguiente=null;
    }
    /**
     * constructor de cada variable tanto privada como publica
     * @param dato
     * @param dato2
     * @param anterior
     * @param siguiente 
     */
    public NodoDoble(String dato,String dato2,NodoDoble anterior, NodoDoble siguiente) {
        this.dato = dato;
        this.dato2=dato2;
        this.anterior = anterior;
        this.siguiente = siguiente;
    }
 /**
  * acceso al dato numero 1 es decir al estado inicial del automata 1
  * @return 
  */
    public String getDato(){
        return dato;
    }
/**
 * acceso al dato numero 2 es decir al estado inicial del automata 2
 * @return 
 */
    public String getDato2() {
        return dato2;
    }
    /**
     * los siguientes son los getters y setters necesarios para poder accesar y tener la informacion de 
     * cada nodo de la lista, en nuetro caso de cada transición que se vaya realizando
     * @param dato2 
     */

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

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Automatas;
import Excepciones.*;

/**
 *
 * @author Lenovo
 */
public class ListaDoble1 {
     public NodoDoble inicio = new NodoDoble();
    public NodoDoble fin = new NodoDoble();
    private int tamano =0;
    
    public ListaDoble1(){
        inicio.setSiguiente(fin);
        fin.setAnterior(inicio); 
}
    
    public void agregarAlInicio (int dato){
        NodoDoble nuevo =new NodoDoble(dato,inicio,inicio.getSiguiente());
        inicio.setSiguiente(nuevo);
        nuevo.getSiguiente().setAnterior(nuevo);
        tamano++;
    }
    public void InsertarAlfinal(int dato){
        NodoDoble nuevo = new NodoDoble(dato,fin.getAnterior(),fin);
        fin.setAnterior(nuevo);
        nuevo.getAnterior().setSiguiente(nuevo);
        tamano++;
    }
    public int eliminarAlIncio()throws ExcepcionesListaVacia{
        if(tamano==0)
            throw new ExcepcionesListaVacia("La lista está vacía");
        NodoDoble borrar= inicio.getSiguiente();
        inicio.setSiguiente(borrar.getSiguiente());
        inicio.getSiguiente().setAnterior(inicio);
        borrar.setAnterior(null);
        borrar.setSiguiente(null);
        tamano--;
        return borrar.getDato();
    }
    public int eliminarAlFinal()throws ExcepcionesListaVacia{
        int p;
        
        if(tamano==0)
            throw new ExcepcionesListaVacia("Lista Vacía");
        int dato =fin.getAnterior().getDato();
        NodoDoble borrar=fin.getAnterior();
        fin.setAnterior((borrar.getAnterior()));
        p=Integer.parseInt(borrar.toString());
        borrar.getAnterior().setSiguiente(fin);
        borrar.setAnterior(null);
        borrar.setSiguiente(null);
        tamano--;
        return p;
    }

    @Override
    public String toString() {
        String a ="";
        NodoDoble aux =inicio.getSiguiente();
        for (int i=0; i<=tamano; i++)
        {
           
           a=""+ a + "->" + aux.toString();
           aux=aux.getSiguiente();
        }
        return a;
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Automatas;

import java.util.ArrayList;
import javax.swing.AbstractListModel;

/**
 * Clase para manejo de Transiciones por medio de JLists
 * @author juampa
 */
public class TransicionesCustomListModel extends AbstractListModel {
    private ArrayList<Transicion> lista = new ArrayList<>();
    /**
     * Método para cambiar un estado, y cargar sus transiciones a la lista
     * @param seleccion estado nuevo del que se desea obtener las transiciones
     */
    public void cambiarEstado(Estado seleccion){
        this.lista=seleccion.getTransiciones();
    }
    /**
     * Método por defecto heredado. Obtiene el número de objetos en la lista
     * @return numero de posiciones en el arreglo
     */
    @Override
    public int getSize() {
        return lista.size();
    }
     /**
     * Obtiene el nombre de un elemento de la lista, especificando su indice en el arreglo
     * @param index índice del elemento que se desea
     * @return el nombre del objeto que se busca
     */
    @Override
    public Object getElementAt(int index) {
        return lista.get(index).getLetra();
    }
    /**
     * Agrega un nuevo elemento a la lista
     * @param p el elemento a agregar
     */
    public void addTransicion(Transicion p){
        lista.add(p);
        this.fireIntervalAdded(this, getSize(), getSize()+1);
    }
    /**
     * Elimina un elemento de la lista, especificando su indice
     * @param index0 el indice del elemento a borrar
     */
    public void eliminarTransicion(int index0){
        lista.remove(index0);
        this.fireIntervalRemoved(index0, getSize(), getSize()+1);
    }
    /**
     * Obtiene un elemento de la lista, especificando su indice
     * @param index indice del elemento
     * @return el elemento que se desea
     */
    public Transicion getTransicion(int index){
        return lista.get(index);
    }
}

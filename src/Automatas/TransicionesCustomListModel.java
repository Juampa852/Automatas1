/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Automatas;

import java.util.ArrayList;
import javax.swing.AbstractListModel;

/**
 *
 * @author juampa
 */
public class TransicionesCustomListModel extends AbstractListModel {
    private ArrayList<Transicion> lista = new ArrayList<>();
    @Override
    public int getSize() {
        return lista.size();
    }

    @Override
    public Object getElementAt(int index) {
        return lista.get(index).getLetra();
    }
    
    public void addTransicion(Transicion p){
        lista.add(p);
        this.fireIntervalAdded(this, getSize(), getSize()+1);
    }
    
    public void eliminarTransicion(int index0){
        lista.remove(index0);
        this.fireIntervalRemoved(index0, getSize(), getSize()+1);
    }
    public Transicion getTransicion(int index){
        return lista.get(index);
    }
}

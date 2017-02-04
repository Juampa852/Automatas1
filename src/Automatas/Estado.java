/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Automatas;

import java.util.ArrayList;

/**
 *
 * @author juampa
 */
public class Estado {
    private String nombre;
    private boolean esFinal;
    private ArrayList<Transicion> transiciones=new ArrayList<Transicion>();

    public String getNombre() {
        return nombre;
    }

    public boolean isFinal() {
        return esFinal;
    }

    public ArrayList<Transicion> getTransiciones() {
        return transiciones;
    }
    
    
}

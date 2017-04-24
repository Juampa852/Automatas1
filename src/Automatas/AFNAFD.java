/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Automatas;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.TreeSet;

/**
 *
 * @author Hola Perronas Locas :3
 */
public class AFNAFD {    
    private String Nombre="";
    private ArrayList<String> Estados;
    private boolean finalito;

    public AFNAFD(String Nombre, ArrayList<String> Estados) {
        this.Nombre = Nombre;
        this.Estados = Estados;

    }

    public AFNAFD() {
         this.Nombre = "";
        this.Estados = null;
        this.finalito = false;
    }

    public String getNombre() {
        return Nombre;
    }

    public ArrayList<String> getEstados() {
        return Estados;
    }

    public boolean isFinalito() {
        return finalito;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public void setEstados(ArrayList<String> Estados) {
        this.Estados = Estados;
    }

    public void setFinalito(boolean finalito) {
        this.finalito = finalito;
    }
    
    
    
    
    
    
    
    
    
    
 
}

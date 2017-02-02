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
    String nombre;
    boolean esFinal;
    ArrayList<Transicion> transiciones=new ArrayList<Transicion>();
    
}

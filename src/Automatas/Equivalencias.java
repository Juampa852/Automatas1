/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Automatas;

import Excepciones.EstadoNoExiste;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author Lenovo
 */
public class Equivalencias { 
    public ListaDoble1 holis = new ListaDoble1();
    private Automata auto1;
    private Automata auto2;
    public void Equivalentes(Automata auto1, Automata auto2){
        try {
            this.auto1=auto1;
            this.auto2=auto2;
            if(auto1.getEstado(auto1.getPocEstadoInicial()).isFinal()==auto2.getEstado(auto2.getPocEstadoInicial()).isFinal()){
                for (int i = 0; i <auto1.getLenguaje().size(); i++) {
                    EstadosHijos(auto1.getEstado(auto1.getPocEstadoInicial()),(auto2.getEstado(auto2.getPocEstadoInicial())));
                }
            }
        } catch (EstadoNoExiste ex) {
            Logger.getLogger(Equivalencias.class.getName()).log(Level.SEVERE, null, ex);
        }      
    } 
    
    public boolean EstadosHijos(Estado p1, Estado p2){
        boolean temp=true;
        if(holis.buscar(p1.getNombre(),p2.getNombre()))
            return true;
        else{
            if(p1.isFinal()!=p2.isFinal())
                return false;
            else{
                for (int i = 0; i <p1.getTransiciones().size(); i++) {
                    try {
                        String a = p1.getTransiciones().get(i).getSiguiente();
                        Estado b = auto1.getEstado(a);
                        String c = p2.getTransiciones().get(i).getSiguiente();
                        Estado d = auto2.getEstado(c);
                       temp = temp&&(EstadosHijos(b,d)); 
                    } catch (EstadoNoExiste ex) {
                        Logger.getLogger(Equivalencias.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                return temp;
            }
        }
      }
        
          
}

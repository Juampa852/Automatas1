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
    public void equivalente (Automata auto1, Automata  auto2){
        try {
            if (auto1.getEstado(0).isFinal()==auto2.getEstado(0).isFinal()) {
                for (int i = 0; i < auto1.getLenguaje().size(); i++) {
                    EstadosHijos(auto1.getEstado(0).getTransiciones(i),auto2.getEstado(0).getTransiciones(i));
                    
                }
                            }
        } catch (EstadoNoExiste ex) {
            Logger.getLogger(Equivalencias.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
  
    
    
    
}

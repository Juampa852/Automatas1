/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Automatas;

import Excepciones.EstadoNoExiste;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;


/**
 *clase que es la encarga de poder realizar la equivalencia entre los dos automatas y verificar si son validos 
 * entre sí o no
 * @author Juan Miguel
 */
public class Equivalencias { 
    public ListaDoble1 holis = new ListaDoble1();
    private Automata auto1;
    private Automata auto2;
    public Equivalencias(Automata auto1,Automata auto2)
    {
        this.auto1=auto1;
        this.auto2=auto2;
    }
    /**
     * metodo encargado de accesar a los automatas ingresados y apartir de estos poder accesar a los estados 
     * inicales y enviarlos al siguoiente metodo para utilizacion
     * @param auto1
     * @param auto2 
     */
    public void Equivalentes(Automata auto1, Automata auto2){
        try {
            this.auto1=auto1;// declaración de atributos de tipo automata y su instanciación
            this.auto2=auto2;
            if(auto1.getEstado(auto1.getPocEstadoInicial()).isFinal()==auto2.getEstado(auto2.getPocEstadoInicial()).isFinal()){ //cpndición que verifica si los dos estados iniciales son iguales
                for (int i = 0; i <auto1.getLenguaje().size(); i++) { // ciclo que accede a todas las transiciones posibles en funcion del tamaño del alfabeto o lenguaje
                    EstadosHijos(auto1.getEstado(auto1.getPocEstadoInicial()),(auto2.getEstado(auto2.getPocEstadoInicial())));//llamada al siguiente método
                }
            }
        } catch (EstadoNoExiste ex) {
            Logger.getLogger(Equivalencias.class.getName()).log(Level.SEVERE, null, ex);
        }      
    } 
    /**
     * metodo booleano recursivo que nos dice si son equivalentes los automatas o no
     * @param p1
     * @param p2
     * @return 
     */
    public boolean EstadosHijos(Estado p1, Estado p2) throws EstadoNoExiste{
        System.out.println("**************************************************");
        boolean temp=true;
        if(holis.buscar(p1.getNombre(),p2.getNombre()))//aca entramos y tomamos el nombre  y registramos los nodos iniciales en un nodo de la lista y buscamos si ya existen o no por el metodo buscar
        {
            System.out.println("Ya esta en lista: "+p1.getNombre()+", "+p2.getNombre());
        }
        else{
            
            if(p1.isFinal()!=p2.isFinal())// si los dos son finales continua con el ciclo si no de una retorna un false y dice que no son equivalentes
            {
                System.out.println("No son equivalentes: p1: "+p1.isFinal()+", p2: "+p2.isFinal());
                temp=false;
            }
            else{
                holis.agregarAlInicio(p1.getNombre(), p2.getNombre());
                System.out.println("Se agrego: "+p1.getNombre()+", "+p2.getNombre());
                for (int i = 0; i <p1.getTransiciones().size()&&temp==true; i++) { //al cumplir la condicion de esta forma accesamos a sus transiciones en las letras del alfabeto segun sea e ingresamos a todas ellas 
                        String a = p1.getTransiciones().get(i).getSiguiente();
                        Estado b = auto1.getEstado(a);
                        String c = p2.getTransiciones().get(i).getSiguiente();
                        Estado d = auto2.getEstado(c);// en estas lineas se realiza la obtencion de las transiciones tanto del estado p1 o del automata 1 y del 2
                        System.out.println("Hijos "+p1.getTransiciones().get(i).getLetra()+": "+b.getNombre()+", "+d.getNombre());
                        temp = temp&&(EstadosHijos(b,d)); //llamadda recursiva mediante la multiplicacion de numeros booleanos ya que de esta forma sabremos donde acabar el cilo para saber si ya termino la hora de evalucion y saber que ya son equivalentes o no 
                        System.out.println("Temp: "+temp+"    "+p1.getNombre()+", "+p2.getNombre());
                        
                }
                
            }
        }
        System.out.println("Esta devolviendo Temp: "+temp+"  la pareja: "+p1.getNombre()+", "+p2.getNombre());
        System.out.println("__________________________________________________________");
                return temp; // regreso de temporal ya sea cin un valor true o false 
                
      }
        
          
}

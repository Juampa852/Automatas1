/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AFD_REGEX;
import Automatas.Automata;
import Automatas.Estado;
import Automatas.Transicion;
import Excepciones.EstadoNoExiste;
import java.util.ArrayList;
/**Clase que genera una expresión regular a partir de un automata finito determinista
 * Esta clase implementa el LEMA DE ARDEN para convertir un AFD en REGEX
 * Genera un conjunto de Ecuaciones  en base a las transiciones de cada estado
 * Resuelve dichas ecuaciones para obtener la expresion regular
 * @see Ecuaciones
 * @author Roberto
 */
public class ExpresionRegular {
    private String regex=""; 
    public ArrayList<Ecuacion> ecuaciones= new ArrayList<Ecuacion>();
    private ArrayList<String> registro=new ArrayList<String>();
    public ExpresionRegular() {
    }
    public ExpresionRegular(Automata auto)
    {
      
    }
    /**
     * Funcion que genera la expresion regular del automata finito determinista
     * @param auto automata del cual se desea obtener la expresion regular
     * @return Expresion regular en una cadena
     * @throws EstadoNoExiste Excepcion cuando se busca un estado que no existe
     */
    public void generarExpresion(Automata auto) throws EstadoNoExiste
    {
        String expresion="";
        Estado estado;
        for(int i=0;i<auto.noEstados();i++)//Se hace un ciclo que recorre el numero de estados del automata 
        {    
            estado=auto.getEstado(i);//Se guarda el estado que se esta analizando
            registro.add(estado.getNombre());
            ecuaciones.add(new Ecuacion(estado.getNombre()));//Se añade una nueva ecuacion unicamente con el nombre
            for(int o=0;o<auto.noLetras();o++)//Ciclo que recorre el lenguaje del automata para genera los terminos de la ecuacion
            {
                Transicion trans=estado.getTransiciones().get(o);//Se guarda cada transicion
                ecuaciones.get(i).añadirTermino(new Termino(trans.getSiguiente(),trans.getLetra()+""));/*se añaden terminos
                ala ecuacion, enviando el simbolo de la transicion y el nombre del estado al que conduce
                */   
            }
            if(estado.isFinal())
                ecuaciones.get(i).añadirTermino(new Termino(""));//Si el estado es final se añade una transicion con cadena vacia
        }
        //return expresion
    }
    public boolean existeEnRegistro(String nombre)
    {
        for (Ecuacion ecuacione : ecuaciones) {
            if(ecuacione.getNombre().equals(nombre))
                return true;
        }
        return false;
    }
    
}

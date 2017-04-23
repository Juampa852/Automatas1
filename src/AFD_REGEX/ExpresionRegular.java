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
    public String generarExpresion(Automata auto) throws EstadoNoExiste
    {
        registro.clear();
        ecuaciones.clear();
        registro.clear();
        crearEcuaciones(auto,auto.getPocEstadoInicial());
        /*for(int i=0;i<auto.noEstados();i++)//Se hace un ciclo que recorre el numero de estados del automata 
        {    
            estado=auto.getEstado(i);//Se guarda el estado que se esta analizando
            registro.add(estado.getNombre());
            ecuaciones.add(new Ecuacion(estado.getNombre()));//Se añade una nueva ecuacion unicamente con el nombre
            for(int o=0;o<auto.noLetras();o++)//Ciclo que recorre el lenguaje del automata para genera los terminos de la ecuacion
            {
                Transicion trans=estado.getTransiciones().get(o);//Se guarda cada transicion
                ecuaciones.get(i).añadirTermino(new Termino(trans.getSiguiente(),trans.getLetra()+""));/*se añaden terminos
                ala ecuacion, enviando el simbolo de la transicion y el nombre del estado al que conduce
                  
            }
            if(estado.isFinal())
                ecuaciones.get(i).añadirTermino(new Termino(""));//Si el estado es final se añade una transicion con cadena vacia
        }*/
        for(int i=registro.size()-1;i>=0;i--)//recorre todos los registros desde el ultimo
        {
            System.out.println("****************************");
            int posicion=buscarEcuacion(registro.get(i));
            System.out.println("REGISTRO: "+i+"  NOMBRE: "+registro.get(i)+"  POSICION "+posicion);
            System.out.println("ECUACION "+ecuaciones.get(posicion));
            ecuaciones.get(posicion).lemaDeArden();//aplica el lema de arden sobre cada ecuacion
            System.out.println("LEMMA DE ARDEN "+ecuaciones.get(posicion)+"  Numero Terminos"+ecuaciones.get(posicion).noTerminos);
            if(ecuaciones.get(posicion).noTerminos<1)/*Si la ecuacion queda sin terminos por el lema de arden 
                significa que es un estado sumidero, es decir, un estado no final del cual no se puede salir
                por lo que la expresion regular no lo toma en cuenta
                */
            {
                System.out.println("SE REMOVIO DE ECUACIONES: "+ecuaciones.get(posicion));
                ecuaciones.remove(ecuaciones.get(posicion));//Remueve el estado sumidero del array de ecuaciones, pero no del registro
            }
            if(i<registro.size()-1)//Si la ecuacion que se esta analizando no es la ultima del registro
                //se recorren todas las posiciones del registro por debajo del actual y se sustituyen las ecuaciones en la actuak
            {
                for(int o=i+1;o<registro.size();o++)
                {
                    System.out.println("REGISTRO: "+o+"   NOMBRE: "+registro.get(o));
                    int posicionSustitucion=buscarEcuacion(registro.get(o));//se busca la ecuacion de sustitucion del registro dentro del ARRAY de ecuaciones
                    System.out.println("Posicion de sustitucion en Ecuaciones: "+posicionSustitucion);
                    if(posicionSustitucion>-1)//si la posicion es -1 quiere decir que la ecuacion ya no existe en el array
                        ecuaciones.get(posicion).sustituir(ecuaciones.get(posicionSustitucion));//si existe se sustituye dentro de la ecuacion
                    else
                        ecuaciones.get(posicion).removerEcuacion(registro.get(o));//si no existe, se elimina los terminos que lo contengan
                }
            }
            System.out.println("*********************************");
        }
        return ecuaciones.get(0).expresionEcuacion();
    }
    /**
     * Metodo recursivo que genera las ecuaciones para los estados si estos no se han añadido a registros
     * Recorre todas las transiciones de cada estado para generarlas 
     * @param auto automata
     * @param i posicion del estado dentro del Array de Estados del Automata
     * @throws EstadoNoExiste 
     */
    private void crearEcuaciones(Automata auto, int i) throws EstadoNoExiste
    {
        Estado estado=auto.getEstado(i);//Se guarda el estado que se esta analizando
        registro.add(estado.getNombre());
        int indexEcuacion;              
        ecuaciones.add(new Ecuacion(estado.getNombre()));//Se añade una nueva ecuacion unicamente con el nombre
        indexEcuacion=ecuaciones.size()-1;
        System.out.println("Estado de registro "+estado.getNombre()+"  i: "+i+"  indexEcuacion "+indexEcuacion);
        for(int o=0;o<auto.noLetras();o++)//Ciclo que recorre el lenguaje del automata para genera los terminos de la ecuacion
        {
            Transicion trans=estado.getTransiciones().get(o);//Se guarda cada transicion
            ecuaciones.get(indexEcuacion).añadirTermino(new Termino(trans.getSiguiente(),trans.getLetra()+""));/*se añaden terminos
            ala ecuacion, enviando el simbolo de la transicion y el nombre del estado al que conduce
            */   
            if(!existeEnRegistro(trans.getSiguiente()))//se comprueba si el estado de la transicion existe, si no existe se llama de nuevo al metodo
                        crearEcuaciones(auto,auto.getPocEstado(trans.getSiguiente()));
            }
            if(estado.isFinal())
            ecuaciones.get(indexEcuacion).añadirTermino(new Termino(""));//Si el estado es final se añade una transicion con cadena vacia
            System.out.println("ECUACION "+ecuaciones.get(indexEcuacion));
            ecuaciones.get(indexEcuacion).agrupar();//se agrupa los terminos que tengan el mismo estado
            System.out.println("AGRUPADA "+ecuaciones.get(indexEcuacion));
            System.out.println("------------------------------------------------");
    }
    /**
     * Funcion booleana que comprueba si un estado se encuentra en el registro
     * @param nombre nombre del estado que se busca
     * @return TRUE si el estado si esta en el registro - FALSE si el estado no se ha añadido al registro
     */
    private boolean existeEnRegistro(String nombre)
    {
        boolean existe=false;
        for (String registro1 : registro) 
        {
            if(registro1.equals(nombre))
                existe=true;
        }
        return existe;
    }
    /**
     * Metodo que busca la posicion de una ecuacion dentro del Array de ecuaciones
     * @param nombre Nombre de la ecuacion que se busca
     * @return (-1) si no esta dentro del array  -  (numero>-1) la posicion en el Array
     */
    private int buscarEcuacion(String nombre)
    {
        for(int i=0;i<ecuaciones.size();i++)
        {
            if(ecuaciones.get(i).getNombre().equals(nombre))
                return i;
        }
        return -1;
    }
}

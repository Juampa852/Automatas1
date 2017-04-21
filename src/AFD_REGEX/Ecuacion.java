/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AFD_REGEX;

import java.util.ArrayList;

/**Clase que representa una ecuacion del Lema de Arden para convertir un automata finito determinista
 * en una expresion regular. Esta s conforma de varios terminos combinados por el signa + 
 *
 * @author Roberto
 */
public class Ecuacion{
    private ArrayList<Termino>terminos=new ArrayList<Termino>();
    private String nombre="";
    /**
     * Constructor de la clase ecuacion
     * asigna el nombre del estado que esta representando
     * @param nombre nombre del estado que se representa
     */
    
    public String getNombre() {
        return nombre;
    }

    public Ecuacion(String nombre) {
        this.nombre=nombre;
    }
    public void añadirTermino(Termino term)
    {
        terminos.add(term);
    }
    public void removerTermino(Termino term)
    {
        terminos.remove(term);
    }

    @Override
    public String toString() {
        String cadena="";
        for(int i=0;i<terminos.size();i++)//Se recorren todos los terminos de la ecuacion
        {
            if(i>0)//Si el iterador es 0 es el primer termino, por lo que no hay queconcatenarle "+" al inicio
                cadena+="+";
            cadena+=terminos.get(i).toString();//Se añade ell termino
        }
        cadena=nombre+"="+cadena;//por ultimo se suma el nombre del estado seguido por un "="
        return cadena;
    }
    /**
    * Metodo que se encarga de agrupar las transiciones que tengan un estado de destino comun
    * Por ejemplo: en la ecuacion q0=(a)q0+(b)q0+(c)q1, al agrupar resultaria q0=(a+b)q0+(c)q1
    */
    public void agrupar(){
        for(int i=0;i<terminos.size();i++){//Se recorre todos los terminos de la ecuacio para buscar terminos con el mismo estado
            for(int o=i+1;o<terminos.size();o++)//luego se recorren todos los terminos, a partir del siguiente del que se esta anlizando
            {
                if(terminos.get(i).getEstado().equals(terminos.get(o).getEstado()))//SI el nombre del estado oincide, pueden agruparse
                {
                    terminos.get(i).setRegex(terminos.get(i).getRegex()+"+"+terminos.get(o).getRegex());//se suman las expresiones del estado que se analiza, 
                    //y de los que se encuentra
                    terminos.remove(terminos.get(o));//Se remueve el estado que se encontro
                    o--;//al reducirse el tamaño del Array, se retrocede una posicion del iterador
                }
            }
            if(terminos.get(i).getRegex().length()>1)//En el caso de que la expresion tenga mas de un caracter se añaden parentesis
                terminos.get(i).setRegex("("+terminos.get(i).getRegex()+")");
        }
    }
}

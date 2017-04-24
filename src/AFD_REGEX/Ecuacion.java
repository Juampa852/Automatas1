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
    public int noTerminos=0;
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
        noTerminos=terminos.size();
    }
    public void removerTermino(Termino term)
    {
        terminos.remove(term);
        noTerminos=terminos.size();
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
                if(terminos.get(i).getEstado()!=null && terminos.get(o).getEstado()!=null){
                    if(terminos.get(i).getEstado().equals(terminos.get(o).getEstado()))//SI el nombre del estado oincide, pueden agruparse
                    {
                        terminos.get(i).setRegex(terminos.get(i).getRegex()+"+"+terminos.get(o).getRegex());//se suman las expresiones del estado que se analiza, 
                        //y de los que se encuentra
                        terminos.remove(terminos.get(o));//Se remueve el estado que se encontro
                        o--;//al reducirse el tamaño del Array, se retrocede una posicion del iterador
                    }
                }
            }
            if(terminos.get(i).getRegex().length()>1)//En el caso de que la expresion tenga mas de un caracter se añaden parentesis
            {
                if(terminos.get(i).getRegex().charAt(0)!='(' && terminos.get(i).getRegex().charAt(terminos.get(i).getRegex().length()-1)!=')')
                    terminos.get(i).setRegex("("+terminos.get(i).getRegex()+")");
            }
            noTerminos=terminos.size();
        }
    }
    /**
     * Metodo que aplica el Lema de Arden en una ecuacion.
     * Para una ecuacion A=sA+r -> A=s*r
     * Donde A es un estado, a y r son expresiones regulares
     */
    public void lemaDeArden()
    {
        String expresion="";
        for(int i=0;i<terminos.size();i++)
        {            
            if(terminos.get(i).getEstado()!=null)
            {
                if(terminos.get(i).getEstado().equals(nombre))
                {
                    expresion=terminos.get(i).getRegex();
                    if(expresion.charAt(0)!='(' || expresion.charAt(expresion.length()-1)!=')')
                        expresion="("+expresion+")";
                    expresion+="*";
                        
                    terminos.remove(terminos.get(i));
                    break;
                }
            }
        }
        for(Termino term:terminos)
        {
            term.setRegex(expresion+term.getRegex());
        }
        noTerminos=terminos.size();
    }
    /**
     * Metodo que elimina los terminos cuyo estado tiene el nombre del parametro recibido
     * @param nombre nombre de estado que se desea eliminar
     */
    public void removerEcuacion(String nombre)
    {
        for(int i=0;i<terminos.size();i++)//recorre todos los terminos de la ecuacion
        {
                if(terminos.get(i).getEstado()!=null)
                {
                    if(terminos.get(i).getEstado().equals(nombre))/*si se encuentra una coincidencia entre el estado del termino y el 
                    nombre de estado, se elimina*/
                        removerTermino(terminos.get(i));
                }
        }
    }
    /**
     * Metodo que sustituye una ecuacion en la actual.
     * Recorre todos los terminos buscando las coincidencia y al encontrarlos concatena sus expresiones y reemplaza
     * el estado con el estado de la ecuacion de sustitucion.
     * Por ejemplo: q0=(a+b)q1+cq2  y   q1=bq0 -> q0=((a+b)b)q0+cq2;
     * Luego se agrupan los terminos con el metodo agrupar()
     * @param ecua Ecuacion que se va a sustituir en la actual
     */
    public void sustituir(Ecuacion ecua)
    {
        System.out.println("/////////////SUSTITUCION////////");
        System.out.println("SUSTITUIR "+ecua+"   EN "+this);
        for(int actual=0;actual<terminos.size();actual++)//Recorre todos los terminos de la ecuacion actual
        {
            if(terminos.get(actual).getEstado()!=null)
            {
                if(terminos.get(actual).getEstado().equals(ecua.nombre))
                {
                    System.out.println("    TERMINO: "+terminos.get(actual));
                    for(int term=0;term<ecua.noTerminos;term++)//si encuentra coincidencia
                    //añade todos los terminos de la ecuacion de sustitucion y les concatena al inicio de su expresion
                    //la expresion del termino de la ecuacion actual que se esta analizando
                    {
                        if(ecua.terminos.get(term).getEstado()!=null)
                        //si el estado del termino es diferente de nulo se añade un termino normal
                        {
                            añadirTermino(new Termino(ecua.terminos.get(term).getEstado(),terminos.get(actual).getRegex()+ecua.terminos.get(term).getRegex()));
                            System.out.println("    SE AÑADIO: "+terminos.get(terminos.size()-1)+" en"+nombre);
                        }
                        //si es nulo se añade un termino de cadena vacia
                        else
                        {
                            añadirTermino(new Termino(terminos.get(actual).getRegex()+ecua.terminos.get(term).getRegex()));
                            System.out.println("    SE AÑADIO: "+terminos.get(terminos.size()-1)+" en"+nombre);
                        }
                    }
                    //se remueve el termino de la ecuacion actual
                        System.out.println("    SE REMOVIO: "+terminos.get(actual)+" en"+nombre);
                        removerTermino(terminos.get(actual));
                    break;
                }
            }
        }
        System.out.println("    ECUACION RESULTANTE "+this);
        agrupar();
        System.out.println("    AGRUPADA "+this);
    }
    public String expresionEcuacion()
    {
        String expresion="";
        for(Termino term:terminos)
        {
            if(term.getEstado()==null)
            {
                if(!"".equals(expresion))
                    expresion+="+";
                expresion+=term.getRegex();
            }
        }
        return expresion;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Automatas;
import Excepciones.*;

import java.util.ArrayList;

/**
 * Clase para generar un autómata, con sus respectivas caracteristicas
 * @author juampa
 */
public class Automata {
    private ArrayList<String> lenguaje=new ArrayList<>();
    //private Estado inicial=null;
     private ArrayList<AFNAFD> estaditos;
    private ArrayList<Estado> estados=new ArrayList<>();
    private ArrayList<Estado> vacio= new ArrayList<>();
    private boolean estInicial=false;
    private int pocEstInicial=ESTADO_INICIAL_DEFAULT;
    public static final int ESTADO_INICIAL_DEFAULT=-1;
    public static final String TRANSICION_VACIA="ε";
    private Estado vacios = new Estado(); 
   
    private  ArrayList<ArrayList<String>> estados1 = new ArrayList<>();
    public Automata() {}
    /**
     * Getter del lenguaje
     * @return array con los símbolos del lenguaje
     */
    public ArrayList<String> getLenguaje(){
        return lenguaje;
    }
    /**
     * Método para agregar símbolos al alfabeto del autómata
     * @param nuevo nuevo simbolo a agregar
     * @throws SimboloYaExiste por si el símbolo ya existiera, no se agrega nada
     */
    public void agregarSimbolo (String nuevo) throws SimboloYaExiste{
        if (lenguaje.contains(nuevo))
            throw new SimboloYaExiste("Este simbolo ya existe en el alfabeto");
        lenguaje.add(nuevo);
    }
    /**
     * Funcion que retorna el numero de estados del automata
     * @return entero con la longitud del array de estados en el automata
     */
    public int noEstados(){
        return estados.size();
    }
    /**
     * Método para eliminar símbolos del alfabeto
     * @param viejo símbolo a eliminar del alfabeto
     * @throws SimboloNoExiste en caso de que no existiera, no se hace cambios
     */
    public void eliminarSimbolo (String viejo) throws SimboloNoExiste{
        if(lenguaje.contains(viejo))
            lenguaje.remove(viejo);
        else
            throw new SimboloNoExiste("Este simbolo no existe en el alfabeto actual");
    }
    /**
     * Método para agregar estados al autómata
     * @param est estado nuevo a agregar
     * @throws EstadoYaExiste por si un estado con este nombre ya existiera en al autómata, no se ingresa nada
     */
    public void agregarEstado(Estado est) throws EstadoYaExiste{
        boolean existe=false;
        for (int i = 0; i < estados.size(); i++) {
            Estado temp=estados.get(i);
            if (temp.getNombre().equals(est.getNombre())){
                existe=true;
                break;
            }
        }
        if(existe)
            throw new EstadoYaExiste("Este estado ya existe en el automata");
        estados.add(est);
    }
    /**
     * Método para eliminar estados
     * @param est estado a eliminar
     * @throws EstadoNoExiste si no hay ningun estado con este nombre, no se elimina nada
     */
    public void eliminarEstado(Estado est) throws EstadoNoExiste{
        String nombreBorrar=est.getNombre();
        boolean existe=false;
        int poc=-1;
        for (int i = 0; i < estados.size(); i++) {
            Estado temp=estados.get(i);
            if (temp.getNombre().equals(est.getNombre())){
                existe=true;
                poc=i;
                break;
            }
        }
        if(!existe)
            throw new EstadoNoExiste("Este estado no existe en el automata");
        if (poc==pocEstInicial) {
            pocEstInicial=ESTADO_INICIAL_DEFAULT;
            estInicial=false;
        }
        Estado temp;
        for (int i = 0; i < estados.size(); i++) {
            temp=estados.get(i);
            for (int j = 0; j < temp.getTransiciones().size(); j++) {
                Transicion tran=temp.getTransiciones().get(j);
                if(tran.getSiguiente().equals(nombreBorrar)){
                    estados.get(i).getTransiciones().get(j).setSiguiente(Transicion.ESTADO_SIGUIENTE_VACIO);
                }
            }
        }
        estados.remove(poc);  
    }
    /**
     * Método para marcar el estado inicial del autómata, y establecer su posición
     * @param nombreEst el nombre del estado que se marcará como inicial
     * @throws EstadoNoExiste en caso de que el estado que se busca no existe
     */
    public void marcarEstadoInicial(String nombreEst) throws EstadoNoExiste{
        boolean existe=false;
        for (int i = 0; i < estados.size(); i++) {
            Estado temp=estados.get(i);
            if (temp.getNombre().equals(nombreEst)){
                existe=true;
                pocEstInicial=i;
                estInicial=true;
                break;
            }
        }
        if(!existe)
            throw new EstadoNoExiste("El estado que quiere marcarse como inicial no existe");
    }
    /**
     * Método para saber si ya existe un estado inicial
     * @return si existe un  estado incial 
     */
    public boolean existeEstadoInicial(){
        return estInicial;
    }
    /**
     * Obtiene la posición del estado inicial en el arreglo
     * @return la posicion del estado
     */
    public int getPocEstadoInicial() {
        return pocEstInicial;
    }
    /**
     * Método para obtener la posición de un estado en el arreglo, por su nombre
     * @param nombre nombre del estado que se solicita
     * @return estado solicitado
     * @throws EstadoNoExiste en caso de que ningun estado con ese nombre exista
     */
    public int getPocEstado(String nombre) throws EstadoNoExiste{
        boolean existe=false;
        int poc=0;
        for (int i = 0; i < estados.size(); i++) {
            Estado temp=estados.get(i);
            if (temp.getNombre().equals(nombre)){
                existe=true;
                poc=i;
                break;
            }
        }
        if(!existe)
            throw new EstadoNoExiste("Este estado no existe en el automata");
        return poc;
    }
    /**
     * Obtiene un estado en especifico, por su indice
     * @param posicion posicion del estado a solicitar
     * @return el estado solicitado
     * @throws Excepciones.EstadoNoExiste en caso de que no existira esa posicion en el array de estados
     */
    public Estado getEstado(int posicion) throws EstadoNoExiste{
         if(posicion>(estados.size()-1))
            throw new EstadoNoExiste("Este estado no existe en el autómata");
        return estados.get(posicion);
    }
    /**
     * Obtiene un estado por su nombre
     * @param nombre nombre del estado que se quiere obtener
     * @return el estado solicitado
     * @throws EstadoNoExiste en caso de que este estado no existiera
     */
    public Estado getEstado(String nombre) throws EstadoNoExiste{
        boolean existe=false;
        int poc=0;
        for (int i = 0; i < estados.size(); i++) {
            Estado temp=estados.get(i);
            if (temp.getNombre().equals(nombre)){
                existe=true;
                poc=i;
                break;
            }
        }
        if(!existe)
            throw new EstadoNoExiste("Este estado no existe en el automata");
        return estados.get(poc);
    }
    /**
     * Obtiene el nombre del estado en una posición en específico
     * @param poc posicion del estado 
     * @return nombre del estado
     * @throws EstadoNoExiste en caso de que el estado no existira
     */
    public String getNombreEstado(int poc) throws EstadoNoExiste{
        if(poc>(estados.size()-1))
            throw new EstadoNoExiste("Este estado no existe en el autómata");
        return estados.get(poc).getNombre();
    }
    /**
     * Método para que el autómata valide o no una cadena
     * @param cadena cadena a validar
     * @return true si la cadena pertenece al lenguaje, false si no
     */
    public boolean comprobarCadena(String cadena) throws NoEsAFD{
        if(isAFD()){
            int cont=0;
            Estado est=estados.get(pocEstInicial);
            while(cont<cadena.length()){
                char letra=cadena.charAt(cont);
                est=estados.get(buscarEstado(est.cambiarDeEstado(new String()+letra)));
                cont++;
            }
            return est.isFinal();
        }else
            throw new NoEsAFD("El autómata no es AFD, no puede comprobar la cadena");
    }
    /**
     * Busca estados por su nombre
     * @param nombre nombre del estado a buscar
     * @return posicion en el arreglo de estados del que se busca, si no existe devuelve "-1"
     */
    private int buscarEstado(String nombre){
        int poc=-1;
        for (int i = 0; i < estados.size(); i++) {
            Estado temp=estados.get(i);
            if (temp.getNombre().equals(nombre)){
                poc=i;
                break;
            }
        }
        return poc;
    }
    /**
     * Devuelve el número de símbolos que contiene el alfabeto
     * @return simbolos del alfabeto
     */
    public int noLetras(){
        return lenguaje.size();
    }
    /**
     * Comprueba si el autómata es finito determinista
     * @return true si es AFD y false si es AFND
     */
    public boolean isAFD(){
        Estado est;
        //Transicion tran;
        boolean es=true;
        for (int i = 0; i < estados.size(); i++) {
            est=estados.get(i);
            if(est.getTransiciones().size()!=lenguaje.size()){
                es=false;
                break;
            }
            for (int j = 0; j < lenguaje.size(); j++) {
                if(est.getTransiciones().get(j).getSiguiente().equals(Transicion.ESTADO_SIGUIENTE_VACIO)){
                    es=false;
                    break;
                }
            }
        }
        return (es&&estInicial);
        
    }
    /**
     * Modifica una transicion ya existente en el estado
     * @param estado el estado al que se desea cambiarse la transicion
     * @param tran la letra con la cual se hace la transicion
     * @param siguiente el nombre del estado al cual se pasa
     * @throws TransicionNoExiste en caso de que la transicion no exista
     * @throws EstadoNoExiste en caso de que el estado al que quiere irse no existiera
     */
    public void modificarTransicion (Estado estado, char tran, String siguiente) throws TransicionNoExiste, EstadoNoExiste{
        int posicion=estados.get(buscarEstado(estado.getNombre())).buscarTransicion(new String()+tran);
        if(posicion==-1)
            throw new TransicionNoExiste("No hay ninguna transicion con esta letra del alfabeto");
        if(buscarEstado(siguiente)==-1)
            throw new EstadoNoExiste("No hay ningun estado con ese nombre");
        estados.get(buscarEstado(estado.getNombre())).getTransiciones().get(posicion).setSiguiente(siguiente);
    }
    /**
     * Método para realizar la conversion de AFN a AFD 
     * 
     * @param afd recibe un automata finito no determinista para realizar y llamar a todos los métodos para poder realizar el metodo por subconjuntos
     * @return un arraylist donde posee la tabla final del automata finito determinista 
     */
    public ArrayList<AFNAFD> conversionAFN(Automata afd){
        String nueva = "", men = "";
        ArrayList<String> orden = new ArrayList<>();//guarda estados nuevos que se van creando segun las funciones de mover y transiciones vacias
        ArrayList<String> orden1 = new ArrayList<>();// guarda estados 
        estados1=new ArrayList<>();
        try{
            orden=estados.get(pocEstInicial).buscarTransiciones(TRANSICION_VACIA); //lo hace con el estado encontrado de primero
            guardaEstadosnuevos(orden);
            for (int i = 0; i < lenguaje.size(); i++) {
                for (int j = 0; j < orden.size(); j++) {
                    mover(lenguaje.get(i), getEstado(orden.get(j)));
                }
            }
            for (int i = 0; i < orden.size(); i++) { //itera con el que encontro y la cadena obtenida a su vez
                if(pocEstInicial!=i){
                    orden1=getEstado(orden.get(i)).buscarTransiciones(Automata.TRANSICION_VACIA);
                    orden.addAll(orden1);
                    guardaEstadosnuevos(orden);
                    for (int k = 0; k < lenguaje.size(); k++) {
                        for (int j = 0; j < orden.size(); j++) {
                            mover(lenguaje.get(k), getEstado(orden1.get(j)));
                        }
                    }
                }
            }
        }catch(EstadoNoExiste ex){
            
        }
        for (int i = 0; i < estaditos.size(); i++) {
            for (int j = 0; j < estaditos.size(); j++) {
                if(estaditos.get(i)==estaditos.get(j)){
                    estaditos.set(i, estaditos.get(j));   
                }
                
            }
        }
        
        return estaditos; 
   }
   /**
    * Metodo encargado de poder con el estado nuevo creado poder ir a las transiciones de cada uno de los estados que 
    * se fueron creando conforme al método de trans vacias 
    * @param letra letra del alfabeto con la qué va
    * @param est estado por analizar 
    * @return 
    */
   public ArrayList<String> mover(String letra, Estado est){
            // Transicion trans = new Transicion(letra);
             //Transicion trans1 = new Transicion(est.toString());
            // ArrayList<Transicion> transicionesnuevas=new ArrayList<>();
             //ArrayList<Transicion> estadonuevo = new ArrayList<>();
             //transicionesnuevas.add(trans);
            // estadonuevo.add(trans1);
            int cont =0;
             estaditos = new ArrayList<AFNAFD>(); //ArrayList de la clase AFNAFD que es el constructor de la tabla final
            char letra1=letra.charAt(0);
            ArrayList<String> temp= new ArrayList<>();
            boolean siguiente=true;
            while(siguiente){ // ciclo que para ver a que estados se llega con la letra del alfabeto correspondiente
                int posicion=buscarEstado(est.cambiarDeEstado(new String()+letra));
                if(posicion!=-1){
                    est=estados.get(posicion);
                    estaditos.add(new AFNAFD("N" + cont, temp)); // se llama al constructor para la tabla final 
                    temp.add(est.getNombre());
                }else
                    siguiente=false;
            }
            guardaEstadosnuevos(temp); // llamada a metodo de creacion de tabla intermedia
            return temp;
    }
    /**
     * metodo que se encarga de poder crear estados nuevos de la tabla intermedia y a la vez compara si existen ya en ella, 
     * si existen no agrega, si no, los agrega
     * @param cadena  obtenida en su llamado que posee el nombre de los estados a los que se accedieron para crear el grupo
     */
    public void guardaEstadosnuevos(ArrayList<String> cadena){
        if(!estados1.isEmpty()){
            for (int i = 0; i < estados1.size(); i++) {
                //estados1.add(cadena);
                boolean existe2=true;
                for (int j = 0; j < cadena.size(); j++) {
                    if(!estados1.get(i).contains(cadena.get(j))){
                        existe2=false;
                        break;
                    }
                }
                if(!existe2&&(cadena.size()!=estados1.get(i).size())){
                    estados1.add(cadena);
                    break;
                }
            }
        }else
            estados1.add(cadena);
    }
}
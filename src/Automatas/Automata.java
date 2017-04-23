/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Automatas;
import Excepciones.*;
import java.awt.List;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Clase para generar un autómata, con sus respectivas caracteristicas
 * @author juampa
 */
public class Automata {
    private ArrayList<String> lenguaje=new ArrayList<>();
    //private Estado inicial=null;
    private ArrayList<Estado> estados=new ArrayList<>();
    private boolean estInicial=false;
    private int pocEstInicial=ESTADO_INICIAL_DEFAULT;
    public static final int ESTADO_INICIAL_DEFAULT=-1;
    public static final String TRANSICION_VACIA="ε";
    /**
     * Constructor para crear un nuevo AFD
     */
    public Automata() {}
    /**
     * Constructor para crear un nuevo AFD a partir de una expresión regular
     * @param RegEx expresión regualar desde la cual se generará el autómata
     * @throws java.lang.Exception cualquier excepcion que pudiera darse
     */
    public Automata(String RegEx) throws Exception{
        try {
            ERhaciaAFND(RegEx);
        } catch (Excepcion ex) {
            throw new Excepcion(ex.toString());
        }
    }
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
    public boolean comprobarCadena(String cadena) throws NoEsAFD, Excepcion{
        if(isAFD()){
            int cont=0;
            Estado est=estados.get(pocEstInicial);
            while(cont<cadena.length()){
                char letra=cadena.charAt(cont);
                if(lenguaje.contains(new String()+letra)){
                    est=estados.get(buscarEstado(est.cambiarDeEstado(new String()+letra)));
                    cont++;
                }else
                    throw new Excepcion ("Hay caracteres que no pertenecen al lenguaje");
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
    public void modificarTransicion (Estado estado, String tran, String siguiente) throws TransicionNoExiste, EstadoNoExiste{
        int posicion=estados.get(buscarEstado(estado.getNombre())).buscarTransicion(tran);
        if(posicion==-1)
            throw new TransicionNoExiste("No hay ninguna transicion con esta letra del alfabeto");
        if(buscarEstado(siguiente)==-1)
            throw new EstadoNoExiste("No hay ningun estado con ese nombre");
        estados.get(buscarEstado(estado.getNombre())).getTransiciones().get(posicion).setSiguiente(siguiente);
    }
    
    public void ERhaciaAFND(String ER) throws Excepcion{
        ArrayList<Integer> abrir,cerrar,mas,asterisco, especial;
        abrir = new ArrayList<>();
        cerrar = new ArrayList<>();
        mas = new ArrayList<>();
        asterisco = new ArrayList<>();
        especial = new ArrayList<>();
        for (int i = 0; i < ER.length(); i++) {
            char caract=ER.charAt(i);
            if(caract=='(')
                abrir.add(i);
            if(caract==')')
                cerrar.add(i);
            if(caract=='+')
                mas.add(i);
            if(caract=='*')
                asterisco.add(i);
            if(caract=='\\')
                especial.add(i);
            if(!lenguaje.contains(new String()+caract))
                throw new Excepcion("Hay caracteres que no pertenecen al lenguaje");
        }
        if(abrir.size()!=cerrar.size())
            throw new Excepcion ("Formato invalido");
        if(!abrir.isEmpty()){
            ArrayList<int[]> pares= new ArrayList<>();
            for (int i = 0;  i< cerrar.size(); i++) {
                int cerrarAct=cerrar.get(i);
                for (int j = abrir.size(); j >0; j--) {
                    int abrirAct=abrir.get(j);
                    if(cerrarAct<abrirAct){
                        int[] temp={abrirAct,cerrarAct};
                        pares.add(temp);
                        abrir.remove(j);
                        break;
                    }
                }
            }
            if(pares.size()!=cerrar.size())
                throw new Excepcion ("Formato invalido");
            
            
        }
    }
}

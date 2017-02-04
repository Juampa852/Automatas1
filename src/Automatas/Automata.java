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
    private ArrayList<String> lenguaje=new ArrayList<String>();
    //private Estado inicial=null;
    private ArrayList<Estado> estados=new ArrayList<Estado>();
    private boolean estInicial=false;
    private int pocEstInicial=-1;
    public Automata() {}
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
        boolean existe=false;
        int poc=0;
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
}
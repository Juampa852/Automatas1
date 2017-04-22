/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Automatas;
import Excepciones.*;
import java.util.ArrayList;

/**
 * Clase para controlar las caracteristicas del estado de un automata
 * @author juampa
 */
public class Estado{
    private String nombre="";
    private boolean esFinal=false;
    private ArrayList<Transicion> transiciones=new ArrayList<>();
    public Estado(){}
    /**
     * Constructor del estado
     * @param nombre nombre del nuevo estado
     */
    public Estado(String nombre){
        this.nombre=nombre;
    }    
    /**
     * Constructor del estado
     * @param nombre nombre del nuevo estado
     * @param marcaFinal marca si el estado es final o no
     */
    public Estado(String nombre, boolean marcaFinal){
        this.nombre=nombre;
        this.esFinal=marcaFinal;
    }    
    /**
     * Método para buscar una transicion en el automata, segun la letra que usa para cambiar de estado
     * @param letra la letra con la se "ejecuta" la transicion
     * @return retorna "-1" si no se ha encontrado la transicion, de lo contrario devuelve la posicion en el array de la transicion buscada
     */
    public int buscarTransicion(String letra){
        int poc=-1;
        for (int i = 0; i < transiciones.size(); i++) {
            if (letra==transiciones.get(i).getLetra()) {
                poc=i;
                break;
            }
        }
        return poc;
    }
    /**
     * Agrega una transicion al estado
     * @param tran la letra que se usará para ejecutar la nueva transicion
     * @throws TransicionYaExiste en caso de que la transicion con esta letra ya exista, se lanza una excepción
     */
    public void agregarTransicion (String tran) throws TransicionYaExiste{
        if(buscarTransicion(tran)!=-1)
            throw new TransicionYaExiste("La transición con esta letra del alfabeto, ya existe en este estado");
        Transicion nuevo=new Transicion(tran);
        transiciones.add(nuevo);
    }
    /**
     * Agrega una transicion al estado
     * @param siguiente nombre del estado siguiente
     * @param tran la letra que se usará para ejecutar la nueva transicion
     * @throws TransicionYaExiste en caso de que la transicion con esta letra ya exista, se lanza una excepción
     */
    public void agregarTransicion (String siguiente, String tran) throws TransicionYaExiste{
        if(buscarTransicion(tran)!=-1)
            throw new TransicionYaExiste("La transición con esta letra del alfabeto, ya existe en este estado");
        Transicion nuevo=new Transicion(siguiente, tran);
        transiciones.add(nuevo);
    }
    /**
     * Elimina una transcisión del estado
     * @param letra 
     * @throws TransicionNoExiste 
     */
    public void eliminarTransicion(String letra) throws TransicionNoExiste{
        int posicion=buscarTransicion(letra);
        if(posicion==-1)
            throw new TransicionNoExiste("No hay ninguna transicion con esta letra del alfabeto");
        transiciones.remove(posicion);
    }
    /**
     * Método para retornar el nombre del estado
     * @return nombre del estado
     */
    public String getNombre() {
        return nombre;
    }
    /**
     * Método para saber si es un estado final (aceptador)
     * @return true si es final, false si no lo es
     */
    public boolean isFinal() {
        return esFinal;
    }
    /**
     * Retorna el arreglo de transiciones del automata
     * @return transiciones del automata
     */
    public ArrayList<Transicion> getTransiciones() {
        return transiciones;
    }
    /**
     * Método para saber a que estado se cambia, con una letra del alfabeto
     * @param letra letra del alfabeto con la cual se cambia
     * @return el estado al que se traslada
     */
    public String cambiarDeEstado(String letra){
        Transicion tran = transiciones.get(buscarTransicion(letra));
        return  tran.getSiguiente();
        
    }
    /**
     * Cambia el nombre del estado
     * @param nombre nombre nuevo del estado 
     */
    public void setNombre(String nombre){
        this.nombre=nombre;
    }
    /**
     * Marca el estado como final
     * @param cambio se manda un true si el estado quiere marcarse como final, false de lo contrario
     */
    public void setFinal(boolean cambio){
        this.esFinal=cambio;
    }
}

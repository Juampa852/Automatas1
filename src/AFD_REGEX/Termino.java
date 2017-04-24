package AFD_REGEX;


import java.util.ArrayList;

/**
 * Clase que representa un termino de la forma A(Q0) donde A representa una cadena
 * que simboliza las transiciones para llegar al estado y Q0 representa el estado de destino
 * @author Roberto
 */
public class Termino{
    private String estado="",regex=""; //Cadenas para el nombre del estado del automata y la expresion regular, respectivamente
    public String CARACTER_OR="+";
    /**
     * Constructor de la clase Termino
     * Asigna valores al nombre de estado y la expresion regular del mismo
     * @param estado nombre del estado del Automata
     * @param regex expresion regular del estado
     */
    public Termino(String estado,String regex) {
        this.estado=estado;
        this.regex=regex;
    }
    /**
     * Constructor de la clase Termino
     * Deja como nombre del estado NULL que representa cadena vacia 
     * @param regex expresion regular
     */
    public Termino(String regex) {
        this.regex = regex;
        estado=null;
    }
    
    public String getEstado() {
        return estado;
    }

    public String getRegex() {
        return regex;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public void setRegex(String regex) {
        this.regex = regex;
    }

    @Override
    public String toString() {
        return  ""+regex + "" + estado ;
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Automatas;

import Excepciones.EstadoNoExiste;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**Clase que hereda de JPanel
 *permite dibujar un automata en una frame
 * @author Roberto Arriola
 */
public class Dibujar extends JPanel{
    private JFrame ventana= new JFrame("Automata");//Se crea un nuevo frame
    private Automata automataPrueba=new Automata();
    private ArrayList<estadoG> estados=new ArrayList<>();
    /**
     * Constructor de la clase que dibuja el automata
     * Genera un arreglo de estados graficos marcando en la primera posicion el estado inicial
     * @param auto automata a dibujar
     * @throws Excepciones.EstadoNoExiste
     * @throws java.lang.InterruptedException
     */
    public Dibujar(Automata auto) throws EstadoNoExiste, InterruptedException
    {       
            if(auto.getPocEstadoInicial()>-1)
            {
                actualizar(auto);
                this.setBackground(Color.WHITE);
                ventana.add(this);//Añade el lienzo al frame
                ventana.setVisible(true);//Hace visible todo el frame
            }
    }
    /**
     * metodo para actualizar el dibujo del automata
     * @param auto  automata a sibujar
     * @throws Excepciones.EstadoNoExiste
     * @throws java.lang.InterruptedException
     */
    public void actualizar(Automata auto) throws EstadoNoExiste, InterruptedException
    {
                estados=new ArrayList<>();
                int cuadrado=1;//se inicia un contador para calcular la raiz cuadrada exacta minima necesaria
                //para distribuir los estados en una matriz cuadrada
                while(Math.pow(cuadrado, 2)<auto.noEstados())//Cuando el cuadrado de la base es mayor al numero de estados
                    //se encuentra la raiz minima
                {
                    cuadrado++;
                }
                estadoG ini= new estadoG(auto.getEstado(auto.getPocEstadoInicial()),50, 50);//se genera el estado grafico inicial
                estadoG est;
                estados.add(ini);//se añade como primer elemento el estado inicial al arreglo de estados graficos
                int col=1, fil=0;
                for(int o=0;o<auto.noEstados();o++)//se recorre el arreglo de estados del automata, para agregarlas a un estado grafico
                    //y añadirlos en el arreglo de la clase dibujar
                {
                    est = new estadoG(auto.getEstado(o),150*col+50, 50+150*fil);//se les asignan posiciones en X y Y dependiendo del recorrido
                    if(!ini.est.getNombre().equals(est.est.getNombre()))//se evita agregar el estado inicial dos veces
                    {
                        estados.add(est);
                        col++;
                        if(col==cuadrado)
                        {
                            col=0;
                            fil++;
                        }
                    }
                }
                calcularDimensiones();
    }
    /**
     * Método que genera las dimensiones de la ventana dependiendo del numero de estados
     * Este mpetodo busca que las dimensiones permitan dibujar los estados como una matriz cuadrada
     * utilizando el cuadrado entero superior, por ejemplo, si existen 7 estados, se acomodan en una matriz de 3x3 (9)
     */
    private void calcularDimensiones()
    {
        int cuadrado=1;//Entero que almacena la raiz exacta minima N, de forma que pudan guardarse los estados como una matriz NxN
        while(Math.pow(cuadrado, 2)<estados.size())
        {
            cuadrado++;
        }
        int x=cuadrado, y=1;
        while(y*x<estados.size())//se calculan las dimensiones, calculando cuantos estados iran en X y cuantos en Y
        {
            y++;
        }
        x=(x)*100+(x+1)*50; //Se multiplican el numero de estados en X por 2 veces el radio de los ovalos
        //y se suman la cantidad de espacios entre estados*radio
        y=(y)*100+(y+2)*50; //se calcula igual que x
        ventana.setSize(x,y);//se le da al frame estas dimensiones
    }
    @Override
    public void paint(Graphics g)
    {
        super.paint(g);       
        for(int i=0;i<estados.size();i++)
        {
            dibujarEstado(i,estados.get(i),g);
        }
        
    }
    /**
     * Metodo que dibuja en el panel un estado grafico, una flecha si este es inicial,un doble circulo si es final
     * @param posicion posicion que tiene el estado grafico en el arreglo de la clase
     * @param estG el objeto estado grafico a dibujar
     * @param g objeto de tipo graphics que permite realizar dibujos en el panel
     */
    private void dibujarEstado(int posicion, estadoG estG,Graphics g){
        Graphics2D g2d= (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        if(posicion==0)
        {
            g2d.drawLine(estG.x-30,estG.y+20, estG.x,estG.y+50);
            g2d.drawLine(estG.x-30,estG.y+80, estG.x,estG.y+50);             
        }
        g2d.setColor(asignarColor(posicion));
        g2d.drawOval(estG.x,estG.y,100,100);   
        if(estG.est.isFinal())
                g2d.drawOval(estG.x+10,estG.y+10,80,80);
        g2d.drawChars(estG.est.getNombre().toCharArray(), 0,estG.est.getNombre().length() ,estG.x+50,estG.y+50);
        if(estG.est.getTransiciones().size()>0)
        {
            for (Transicion trans : estG.est.getTransiciones()) {
                estadoG t=buscarEstado(trans);
                String caracteres=concatenar(t,estG.est.getTransiciones());
                if(!estG.est.getNombre().equals(t.est.getNombre()))
                {
                    double angulo=Math.atan2(t.y-estG.y, t.x-estG.x);
                    g2d.drawLine(estG.x+50+(int)(Math.cos(angulo)*50), estG.y+50+(int)(Math.sin(angulo)*50), t.x+50-(int)(Math.cos(angulo)*50), t.y+50-(int)(Math.sin(angulo)*50));
                    g2d.fillOval(t.x+50-(int)(Math.cos(angulo)*50)-3, t.y+50-(int)(Math.sin(angulo)*50)-3, 6, 6);
                    g2d.drawChars(caracteres.toCharArray(),0,caracteres.length(), estG.x+50+(int)(Math.cos(angulo)*50)-5,estG.y+50+(int)(Math.sin(angulo)*50)-5);
                }
                else
                {
                    g2d.drawOval(estG.x+30-(int)(50*Math.sin(45)), estG.y+30-(int)(50*Math.sin(45)), 30, 30);
                    g2d.fillOval(estG.x+50-(int)(50*Math.sin(45))+3, estG.y+50-(int)(50*Math.sin(45))+3, 6, 6);
                    g2d.drawChars(caracteres.toCharArray(),0,caracteres.length(),estG.x+30-(int)(50*Math.sin(45)), estG.y+30-(int)(50*Math.sin(45)));
                }
            }
        }
    }
    /**
     * Clase estado grafico, es una clase interna de la clase Dibujar
     * Su funcion es almacenar un estado y la posicion que se le dara en el panel
     * de esa forma, al graficar una transicion, puede conocerse las posiciones de los estados en el lienzo
     */
    private class estadoG{
        int x, y;
        Estado est;
        public estadoG(Estado est, int x, int y)
        {
            this.est=est;
            this.x=x;
            this.y=y;
        }
    }
    /**
     * Funcion que retorna una cadena con los caracteres que lleven a un mismo estado
     * @param destino estado grafico al que se quiere acceder
     * @param array arreglo de transiciones de un estado
     * @return caracteres que llevan al estado destino
     */
    private String concatenar(estadoG destino, ArrayList<Transicion> array)
    {
        String cadena="";
        for (Transicion array1 : array) {
            if(array1.getSiguiente().equals(destino.est.getNombre()))
                cadena=cadena+array1.getLetra();
            if(!array1.equals(array.get(array.size()-1)))
                cadena=cadena+",";
        }
        if(cadena.length()<3)
        {
            String[] cadena1=cadena.split(",");
            cadena="";
            for (String cadena11 : cadena1) {
                cadena += cadena11;
            }
            return cadena;
        }
        else
            return cadena;
    }
    /**
     * Funcion que busca un estadoGrafico en el arreglo de la clase y lo returna
     * @param trans Onjeto trancision que se quiere buscar
     * @return Estado buscado a partir de una transicion
     */
    private estadoG buscarEstado(Transicion trans)
    {
        trans.getSiguiente();
        for (estadoG estado : estados) {
            if(estado.est.getNombre().equals(trans.getSiguiente()))
                return estado;
        }
        return null;
    }
    /**
     * Funcion que retorna colores, dependiendo del numero, teniendo 9 posibilidades
     * @param posicion posicion en el arreglo de Estados gráficos
     * @return color para dibujar el estado y sus transiciones
     */
    private Color asignarColor(int posicion)
    {
        while(posicion>8)
        { posicion=posicion-9;}
        switch (posicion) {
            case 1:
                return Color.BLACK;
            case 2:
                return Color.CYAN;
            case 3:
                return Color.GREEN;
            case 4:
                return Color.MAGENTA;
            case 5:
                return Color.ORANGE;
            case 6:
                return Color.RED;
            case 7:
                return Color.PINK;
            case 8:
                return Color.YELLOW;
            default:
                return Color.BLUE;
        }
    }
}

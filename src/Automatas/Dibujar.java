/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Automatas;

import Excepciones.EstadoNoExiste;
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
     */
    public Dibujar(Automata auto) throws EstadoNoExiste
    {
              
            automataPrueba=auto; 
            int i=auto.getPocEstadoInicial();
            if(i>-1)
            {
                estadoG ini= new estadoG(auto.getEstado(auto.getPocEstadoInicial()),80, 80);
                estadoG est;
                estados.add(ini);
                for(int o=0;o<auto.noEstados();o++)
                {
                    est = new estadoG(auto.getEstado(o),150*o+80, 80);
                    if(!ini.est.getNombre().equals(est.est.getNombre()))
                        estados.add(est);
                }
            }
            calcularDimensiones();
            ventana.setVisible(true);//Hace visible todo el frame
            ventana.add(this);//Añade el lienzo al frame
    }
    /**
     * Método que genera las dimensiones de la ventana dependiendo del numero de estados
     * Este mpetodo busca que las dimensiones permitan dibujar los estados como una matriz cuadrada
     * utilizando el cuadrado entero superior, por ejemplo, si existen 7 estados, se acomodan en una matriz de 3x3 (9)
     */
    public void calcularDimensiones()
    {
        int cuadrado=1;
        while(Math.pow(cuadrado, 2)<estados.size())
        {
            cuadrado++;
        }
        int x=cuadrado, y=1;
        while(y*x<estados.size())
        {
            y++;
        }
        x=(x+2)*100;
        y=(y+2)*100;
        ventana.setSize(x,y);
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
    public void dibujarEstado(int posicion, estadoG estG,Graphics g){
        Graphics2D g2d= (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        if(posicion==0)
        {
            g2d.drawLine(estG.x-30,estG.y+20, estG.x,estG.y+50);
            g2d.drawLine(estG.x-30,estG.y+80, estG.x,estG.y+50);             
        }
        g2d.drawOval(estG.x,estG.y,100,100);   
        if(estG.est.isFinal())
                g2d.drawOval(estG.x+10,estG.y+10,80,80);
            g2d.drawChars(estG.est.getNombre().toCharArray(), 0,estG.est.getNombre().length() ,estG.x+50,estG.y+50);
    }
    /**
     * Clase estado grafico, es una clase interna de la clase Dibujar
     * Su funcion es almacenar un estado y la posicion que se le dara en el panel
     * de esa forma, al graficar una transicion, puede conocerse las pocisiones de los estados en el lienzo
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
}

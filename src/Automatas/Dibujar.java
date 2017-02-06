/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Automatas;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Label;
import java.awt.RenderingHints;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**Clase que hereda de JPanel
 *permite dibujar un automata en una frame
 * @author Roberto Arriola
 */
public class Dibujar extends JPanel{
    JFrame ventana= new JFrame("Automata");//Se crea un nuevo frame
    Automata automataPrueba=new Automata();
    /**
     * Constructor de la clase que dibuja el automata
     * @param auto automata a dibujar
     */
    public Dibujar(Automata auto)
    {
        ventana.setSize(300, 300);//Setea el tamaño del lienzo
        ventana.setVisible(true);//Hace visible todo el frame
        ventana.add(this);//Añade el lienzo al frame
        
    }
    @Override
    public void paint(Graphics g)//Sobreescritura del metodo paint
    {
        super.paint(g);
        this.repaint();
        Graphics2D g2d= (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        int menor;
        if(this.getWidth()<this.getHeight())
            menor=this.getWidth();
        else 
            menor=this.getHeight();
        g2d.drawOval(this.getWidth()/10,this.getHeight()/2-menor/4,menor/2,menor/2);
    }
    private class estadoG extends Estado{
        int x, y;
        public estadoG(Estado est, int x, int y)
        {
            this.x=x;
            this.y=y;
        }
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package classes;

import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ImageIcon;
import java.net.URL;
import java.awt.Font;

/**
 * Esta clase crea la ventana donde se agregan, crean y seleccionan los usuarios
 * @author RDG, basado en codigo HENAO
 *
 */
public class printerQueueUI extends JFrame implements ActionListener
	{
		private Container contenedor;
		JLabel labelTitulo;/*declaramos el objeto Label*/
		JTextArea areaDeTexto;
                String titulo = "Cola Completa";
		
                public printerQueueUI(){}
                
		public printerQueueUI(int[] relativePos){//constructor
                    int posX = relativePos[0];
                    int posY = relativePos[1];
                    
                    
			contenedor=getContentPane();
			contenedor.setLayout(null);
			
			labelTitulo= new JLabel();
			labelTitulo.setText(titulo);
			labelTitulo.setBounds(250, 0, 180, 30);
                        
       		//Asigna un titulo a la barra de titulo
                    setTitle("COLA IMPRESORA MANAGER");
                    setSize(600,200);
                    //pone la ventana en el Centro de la pantalla
//                    setLocationRelativeTo(null);
                    
                    setLocation(posX, posY+300);
                    
                    contenedor.add(labelTitulo);
			
	        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		}

		@Override
		public void actionPerformed(ActionEvent evento) {
                    
		}              
	}

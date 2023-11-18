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
import java.time.Instant;
import java.time.Duration;

/**
 * Esta clase crea la ventana principal donde el usuario podra trabajar todo
 * y permite abrir pestañas para las otras funciones
 * @author RDG, basado en codigo HENAO
 *
 */
public class centralUI extends JFrame implements ActionListener
	{
		private Container contenedor;
		JLabel labelTitulo;/*declaramos el objeto Label*/
		JTextArea areaDeTexto;
                int clockSec;
                int clockMin;
                int clockMult = 1;
                usrUI lastUsrUI = new usrUI();
                fileUI lastFileUI = new fileUI();
                treeUI lastTreeUI = new treeUI();
                printerQueueUI lastExtendQueue = new printerQueueUI();
                clock clockClass = new clock();
                
                //FILE UI
                JButton botonAddFile;
                JButton botonDelFile;
                
                //TREE UI
                JButton botonArbol;
                
                //USR UI
                //botonAddUsr
                //botonDelUsr
                //botonSelUsr
		JButton botonAddUsr;/*declaramos el objeto Boton*/
		JButton botonDelUsr;/*declaramos el objeto Boton*/
		JButton botonSelUsr;/*declaramos el objeto Boton*/
                
                //CLOCK UI
                //botonSimPlay
                //botonSimPause
                //botonSimT1
                //botonSimT2
                //botonSimT3
                //clock
		JButton botonSimPlay;/*declaramos el objeto Boton*/                
		JButton botonSimPause;/*declaramos el objeto Boton*/                
		JButton botonSimT1;/*declaramos el objeto Boton*/                
		JButton botonSimT2;/*declaramos el objeto Boton*/                
		JButton botonSimT3;/*declaramos el objeto Boton*/
                JLabel clock;
                
                //QUEUE PREVIEW UI
                //printerIcon
                //fileIcon
                //backgroundQueue
                JLabel printerIcon;
                JLabel fileIcon;
                JLabel arrowIcon;
                JButton extendQueue;
                //ICONES A USAR
                ImageIcon printerImage = new ImageIcon("icons/printer50.png");
                ImageIcon fileImage = new ImageIcon("icons/fileIcon50.png");
                ImageIcon arrowImage = new ImageIcon("icons/arrow50.png");
                
//		JScrollPane scrollPaneArea;
		JFileChooser fileChooser; /*Declaramos el objeto fileChooser*/
		String texto;
    
		
		public centralUI()//constructor
		{
			contenedor=getContentPane();
			contenedor.setLayout(null);
			
			/*Creamos el objeto*/
			fileChooser =new JFileChooser();
			
			/*Propiedades del Label, lo instanciamos, posicionamos y
			 * activamos los eventos*/
			labelTitulo= new JLabel();
			labelTitulo.setText("CENTRAL HUB");
			labelTitulo.setBounds(250, 0, 180, 30);
			
//			areaDeTexto = new JTextArea();
			//para que el texto se ajuste al area
//			areaDeTexto.setLineWrap(true);
			//permite que no queden palabras incompletas al hacer el salto de linea
//			areaDeTexto.setWrapStyleWord(true);
//		   	scrollPaneArea = new JScrollPane();
//			scrollPaneArea.setBounds(20, 50, 350, 270);
//	        scrollPaneArea.setViewportView(areaDeTexto);
	       	
                /*Propiedades del boton, lo instanciamos, posicionamos y
                 * activamos los eventos*/
                    //BOTONES PARA USR
                    //botonAddUsr
                    //botonDelUsr
                    //botonSelUsr
			botonAddUsr= new JButton();
			botonAddUsr.setText("Agregar Usuario");
			botonAddUsr.setBounds(10, 20, 150, 25);
			botonAddUsr.addActionListener(this);
			
			botonDelUsr= new JButton();
			botonDelUsr.setText("Borrar Usuario");
			botonDelUsr.setBounds(10, 50, 150, 25);
			botonDelUsr.addActionListener(this);
                        
			botonSelUsr= new JButton();
			botonSelUsr.setText("Editar Usuario");
			botonSelUsr.setBounds(10, 80, 150, 25);
			botonSelUsr.addActionListener(this);
                        
			botonAddFile= new JButton();
			botonAddFile.setText("Crear Archivo");
			botonAddFile.setBounds(180, 50, 150, 25);
			botonAddFile.addActionListener(this);
                        
			botonDelFile= new JButton();
			botonDelFile.setText("Borrar Archivo");
			botonDelFile.setBounds(180, 80, 150, 25);
			botonDelFile.addActionListener(this);
                        
                        botonArbol = new JButton();
                        botonArbol.setText("Ver Arbol");
                        botonArbol.setBounds(480, 115, 90, 25);
                        botonArbol.addActionListener(this);
			
                    //CLOCK UI
                    //botonSimPlay
                    //botonSimPause
                    //botonSimT1
                    //botonSimT2
                    //botonSimT3
                    //clock
			botonSimPlay= new JButton();
			botonSimPlay.setText("PLAY");
			botonSimPlay.setBounds(350, 20, 80, 30);
			botonSimPlay.addActionListener(this);
			
                        botonSimPause= new JButton();
			botonSimPause.setText("PAUSE");
			botonSimPause.setBounds(350, 50, 80, 30);
			botonSimPause.addActionListener(this);
			
                        botonSimT1= new JButton();
			botonSimT1.setText("1X");
			botonSimT1.setBounds(430, 65, 50, 15);
                        botonSimT1.setBackground(Color.gray);
			botonSimT1.addActionListener(this);
			
                        botonSimT2= new JButton();
			botonSimT2.setText("2X");
			botonSimT2.setBounds(480, 65, 50, 15);
                        botonSimT2.setBackground(Color.lightGray);
			botonSimT2.addActionListener(this);
			
                        botonSimT3= new JButton();
			botonSimT3.setText("3X");
			botonSimT3.setBounds(530, 65, 50, 15);
                        botonSimT3.setBackground(Color.lightGray);
			botonSimT3.addActionListener(this);
                        
                        clock = new JLabel();
                        clock.setText("00:00");
                        clock.setFont(new Font("Serif", Font.PLAIN, 50));
                        clock.setBounds(447, 15, 150, 50);
                        
                    //QUEUE PREVIEW UI
                    //printerIcon
                    //fileIcon
                    //backgroundQueue        
                    JLabel arrowIconArray[] = new JLabel[4];
                    for (int i = 0; i < arrowIconArray.length; i++) {
                        arrowIconArray[i] = new JLabel(arrowImage);
                        arrowIconArray[i].setBounds((150+(100*i)), 180, 50, 50);
                    }
                    
                    JButton fileIconArray[] = new JButton[4];
                    for (int i = 0; i < fileIconArray.length; i++) {
                        fileIconArray[i] = new JButton(fileImage);
                        fileIconArray[i].setBounds((200+(100*i)), 180, 50, 50);
                        fileIconArray[i].addActionListener(this);
                    }
                        printerIcon = new JLabel(printerImage);
                        printerIcon.setBounds(100, 180, 50, 50);
                        
                        extendQueue = new JButton();
			extendQueue.setText("Ver Cola Completa");
			extendQueue.setBounds(200, 235, 200, 30);
			extendQueue.addActionListener(this);
                        
			/*Agregamos los componentes al Contenedor*/
			contenedor.add(labelTitulo);
                        contenedor.add(botonArbol);
//			contenedor.add(scrollPaneArea);
                        
                        //BOTONES PARA USR
                        //botonAddUsr
                        //botonDelUsr
                        //botonSelUsr
			contenedor.add(botonAddUsr);
			contenedor.add(botonDelUsr);
			contenedor.add(botonSelUsr);
                        
                        //FILE UI
                        contenedor.add(botonAddFile);
                        contenedor.add(botonDelFile);
                        
                        //CLOCK UI
                        //botonSimPlay
                        //botonSimPause
                        //botonSimT1
                        //botonSimT2
                        //botonSimT3
                        //clock
			contenedor.add(botonSimPlay);
			contenedor.add(botonSimPause);
			contenedor.add(botonSimT1);
			contenedor.add(botonSimT2);
			contenedor.add(botonSimT3);
			contenedor.add(clock);
                        
                        //QUEUE PREVIEW UI
                        //printerIcon
                        //fileIcon
                        //backgroundQueue
                        for (int i = 0; i < arrowIconArray.length; i++) {
                        contenedor.add(arrowIconArray[i]);
                        }
                        
                        for (int i = 0; i < fileIconArray.length; i++) {
                        contenedor.add(fileIconArray[i]);
                        }
                        contenedor.add(printerIcon);
                        contenedor.add(extendQueue);

                        
       		//Asigna un titulo a la barra de titulo
                    setTitle("COLA IMPRESORA MANAGER");
                    setSize(600,300);
                    //pone la ventana en el Centro de la pantalla
                    setLocationRelativeTo(null);
			
	        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		}

		@Override
		public void actionPerformed(ActionEvent evento) {
                       //FILE UI
                        if (evento.getSource()==botonAddFile)
			{
                            lastUsrUI.dispose();
                            lastFileUI.dispose();
                            fileUI miVentana = new fileUI(this.getCurrentPos(),1);
                            miVentana.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                            miVentana.setVisible(true);
                            lastFileUI = miVentana;
			}
                        
                        if (evento.getSource()== botonArbol){
                            lastTreeUI.dispose();
                            treeUI miVentana = new treeUI(this.getCurrentPos(),2);
                            miVentana.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                            miVentana.setVisible(true);
                            lastTreeUI = miVentana;
                        }
                        
			if (evento.getSource()==botonDelFile)
			{
                            lastUsrUI.dispose();
                            lastFileUI.dispose();
                            fileUI miVentana = new fileUI(this.getCurrentPos(),2);
                            miVentana.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                            miVentana.setVisible(true);
                            lastFileUI = miVentana;
			}
                    
                       //USR UI
                       //botonAddUsr
                       //botonDelUsr
                       //botonSelUsr
			if (evento.getSource()==botonAddUsr)
			{
                            lastFileUI.dispose();
                            lastUsrUI.dispose();
                            usrUI miVentana = new usrUI(this.getCurrentPos(),1);
                            miVentana.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                            miVentana.setVisible(true);
                            lastUsrUI = miVentana;
			}
			
			if (evento.getSource()==botonDelUsr)
			{
                            lastFileUI.dispose();
                            lastUsrUI.dispose();
                            usrUI miVentana = new usrUI(this.getCurrentPos(),2);
                            miVentana.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                            miVentana.setVisible(true);
                            lastUsrUI = miVentana;
			}
                        
			if (evento.getSource()==botonSelUsr)
			{
                            lastFileUI.dispose();
                            lastUsrUI.dispose();
                            usrUI miVentana = new usrUI(this.getCurrentPos(),3);
                            miVentana.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                            miVentana.setVisible(true);
                            lastUsrUI = miVentana;
			}
                        
                        //CLOCK UI
                        //botonSimPlay
                        //botonSimPause
                        //botonSimT1
                        //botonSimT2
                        //botonSimT3
                        //clock
                        if (evento.getSource()==botonSimPlay){
                            clockClass.start();
                            clock.setText((String) clockClass.getTime());
			}
                        
                        if (evento.getSource()==botonSimPause)
			{
                            clockClass.pause();
                            clock.setText((String) clockClass.getTime());
			}
                        
                        if (evento.getSource()==botonSimT1)
			{
                            botonSimT1.setBackground(Color.gray);
                            botonSimT2.setBackground(Color.lightGray);
                            botonSimT3.setBackground(Color.lightGray);
                            clockMult = 1;

			}
                        
                        if (evento.getSource()==botonSimT2)
			{
                            botonSimT1.setBackground(Color.lightGray);
                            botonSimT2.setBackground(Color.gray);
                            botonSimT3.setBackground(Color.lightGray);
                            clockMult = 2;
			}
                        
                        if (evento.getSource()==botonSimT3)
			{
                            botonSimT1.setBackground(Color.lightGray);
                            botonSimT2.setBackground(Color.lightGray);
                            botonSimT3.setBackground(Color.gray);
                            clockMult = 3;
			}
                        
                        if (evento.getSource()==extendQueue)
			{
                            lastExtendQueue.dispose();
                            printerQueueUI miVentana = new printerQueueUI(this.getCurrentPos());
                            miVentana.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                            miVentana.setVisible(true);
                            lastExtendQueue = miVentana;
			}
                        
                        
		}
                
                public int[] getCurrentPos(){
                    int[] pos = new int[2];
                    pos[0] = this.getX();
                    pos[1] = this.getY();
                    return pos;
                }
                
                public void updateClock(){
                    clock.setText((String) clockClass.getTime());
                }
                
//        public static void main(String[] args) {
//		centralUI miVentana = new centralUI();
//		miVentana.setVisible(true);
//	}
              
	}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package classes;

import java.awt.BorderLayout;
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
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import java.net.URL;
import java.awt.Font;
import java.io.BufferedWriter;
import javax.swing.BorderFactory;
import javax.swing.JList;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import primitivas.*;

/**
 * Esta clase crea la ventana donde se agregan, crean y seleccionan los usuarios
 * @author RDG, basado en codigo HENAO
 *
 */
public class usrUI extends JFrame implements ActionListener
	{
		private Container contenedor;
		JLabel labelTitulo;/*declaramos el objeto Label*/
		JLabel usrFeedback;
                String titulo = "ERROR ERROR ERROR";
                String newUsr = "";
                String newUsrPriority;
                String finalUsr;
                
                JScrollPane usrScroller;
                JTextArea usrScrollerData;
                String usrDataLocation = "files/users.csv";
                JList newScrollerData;
                
                JButton createUsr;
                JTextField newUsrName;
                JButton priority[];
                
                JButton deleteUsr;
                String selectedItem;
                
                JButton editPriority;
                JLabel editPriorityLabel;
		
                public usrUI(){}
                
		public usrUI(int[] relativePos, int mode)//constructor
		{
                    switch (mode) {
                        case 1:
                            titulo = ("Agregar Usuario");
                            break;
                        case 2:
                            titulo = ("Borrar Usuario");
                            break;
                        case 3:
                            titulo = ("Editar Usuario");
                            break;
                        default:
                            break;
                    }
                    int posX = relativePos[0];
                    int posY = relativePos[1];
                    
                    
			contenedor=getContentPane();
			contenedor.setLayout(null);
			
			labelTitulo= new JLabel();
			labelTitulo.setText(titulo);
			labelTitulo.setBounds(20, 0, 180, 30);
                        
                        usrFeedback = new JLabel();
                        usrFeedback.setText("");
                        usrFeedback.setBounds(150, 0, 180, 30);
                        
                        usrScroller = new JScrollPane();
                        usrScroller.setBounds(20, 30, 250, 200);
                        
                        usrScrollerData = new JTextArea();
                        usrScrollerData.setLineWrap(true);
                        usrScrollerData.setWrapStyleWord(true);
                        usrScrollerData.setEditable(false);
                        
                        priority = new JButton[3];
                        
                        updateUsrScrollerData();
                        
                        getSelectedUsrScrollData();
                        
                        if(mode == 1){
                            createUsr = new JButton();
                            createUsr.setText("Crear");
                            createUsr.setBounds(200,235,70,20);
                            createUsr.addActionListener(this);
                            
                            newUsrName = new JTextField();
                            newUsrName.setBounds(20,235,70,20);
                            
                                for (int i = 0; i < priority.length; i++) {
                                    priority[i] = new JButton();
                                    if (i == 0){
                                        priority[i].setText("B");
                                        priority[i].setBackground(Color.lightGray);
                                    }else if(i == 1){
                                        priority[i].setText("M");
                                        priority[i].setBackground(Color.lightGray);
                                    }else if(i == 2){
                                        priority[i].setText("A");
                                        priority[i].setBackground(Color.lightGray);
                                    }
                                    priority[i].setBounds((92+(35*i)), 235, 35, 20);
                                    Border emptyBorder = BorderFactory.createEmptyBorder(-10, -10, -10, -10);
                                    Border compoundBorder = BorderFactory.createCompoundBorder(priority[i].getBorder(), emptyBorder);
                                    priority[i].setBorder(compoundBorder);
                                    priority[i].addActionListener(this);
                                    contenedor.add(priority[i]);
                                }
                                
                            contenedor.add(createUsr);
                            contenedor.add(newUsrName);
                        }else if(mode == 2){
                            deleteUsr = new JButton();
                            deleteUsr.setText("Borrar");
                            deleteUsr.setBounds(100,235,80,20);
                            deleteUsr.addActionListener(this);
                            
                            contenedor.add(deleteUsr);
                        }else if(mode == 3){                            
                            editPriority = new JButton();
                            editPriority.setText("Editar");
                            editPriority.setBounds(200,235,70,20);
                            editPriority.addActionListener(this);
                            
                            for (int i = 0; i < priority.length; i++) {
                                priority[i] = new JButton();
                                if (i == 0){
                                    priority[i].setText("B");
                                    priority[i].setBackground(Color.lightGray);
                                }else if(i == 1){
                                    priority[i].setText("M");
                                    priority[i].setBackground(Color.lightGray);
                                }else if(i == 2){
                                    priority[i].setText("A");
                                    priority[i].setBackground(Color.lightGray);
                                }
                                priority[i].setBounds((92+(35*i)), 235, 35, 20);
                                Border emptyBorder = BorderFactory.createEmptyBorder(-10, -10, -10, -10);
                                Border compoundBorder = BorderFactory.createCompoundBorder(priority[i].getBorder(), emptyBorder);
                                priority[i].setBorder(compoundBorder);
                                priority[i].addActionListener(this);
                                contenedor.add(priority[i]);
                            }
                            
                            editPriorityLabel = new JLabel();
                            editPriorityLabel.setText("PRIORIDAD");
                            editPriorityLabel.setBounds(20,235,130,20);
                           
                            contenedor.add(editPriority);                                                     
                            contenedor.add(editPriorityLabel);                                                   
                        }else{
                            System.out.println("ERROR ERROR ERROR");
                        }
                        
       		//Asigna un titulo a la barra de titulo
                    setTitle("COLA IMPRESORA MANAGER");
                    setSize(300,300);
                    //pone la ventana en el Centro de la pantalla
//                    setLocationRelativeTo(null);
                    
                    setLocation(posX-300, posY);
                    contenedor.add(labelTitulo);
                    contenedor.add(usrFeedback);
                    contenedor.add(usrScroller);
			
	        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		}

		@Override
		public void actionPerformed(ActionEvent evento) {             
                    if (evento.getSource()==deleteUsr){
                        deleteUsrScrollerData(getSelectedUsrScrollData());
                        selectedItem = null;
                        getSelectedUsrScrollData();
                    }
                    
                    if (evento.getSource()==editPriority){
                        if ((newUsrPriority != null) && (getSelectedUsrScrollData() != null)){
                            editPriorityUsrScrollerData(getSelectedUsrScrollData());
                            priority[0].setBackground(Color.lightGray);
                            priority[1].setBackground(Color.lightGray);
                            priority[2].setBackground(Color.lightGray);
                            selectedItem = null;
                            getSelectedUsrScrollData();
                            System.out.println("EDITANDO");
                        }else if((newUsrPriority != null)){
                            usrFeedback.setText("*ERROR: SELECCION*");
                        }else if((getSelectedUsrScrollData() != null)){
                            usrFeedback.setText("*ERROR: PRIORIDAD*");
                        }else{
                            usrFeedback.setText("*ERROR: NO USR*");
                        }
                    }
                    
                    if (evento.getSource()==createUsr){
                        newUsr = newUsrName.getText();
                        if ((newUsrPriority != null) && (newUsr.trim().length() != 0)){
                            finalUsr = (newUsr+", "+newUsrPriority);
                            newUsrName.setText("");
                            usrFeedback.setText("");
                            addUsrScrollerData(newUsr,newUsrPriority);
                            priority[0].setBackground(Color.lightGray);
                            priority[1].setBackground(Color.lightGray);
                            priority[2].setBackground(Color.lightGray);
                            newUsrPriority = null;
                            updateUsrScrollerData();
                        }else if((newUsrPriority != null)){
                            usrFeedback.setText("*ERROR: NOMBRE*");
                        }else if((newUsr.trim().length() != 0)){
                            usrFeedback.setText("*ERROR: PRIORIDAD*");
                        }else{
                            usrFeedback.setText("*ERROR: NO USR*");
                        }
                    }
                    
                    if (evento.getSource() == priority[0]) {
                        newUsrPriority = "prioridad_baja";
                        priority[0].setBackground(Color.gray);
                        priority[1].setBackground(Color.lightGray);
                        priority[2].setBackground(Color.lightGray);
                    }
                    if (evento.getSource() == priority[1]) {
                        newUsrPriority = "prioridad_media";
                        priority[0].setBackground(Color.lightGray);
                        priority[1].setBackground(Color.gray);
                        priority[2].setBackground(Color.lightGray);
                    }
                    if (evento.getSource() == priority[2]){
                        newUsrPriority = "prioridad_alta";
                        priority[0].setBackground(Color.lightGray);
                        priority[1].setBackground(Color.lightGray);
                        priority[2].setBackground(Color.gray);
                    }
		}
                public List getUsrUsrScrollData(){
                    List usr = new List();
                    try(BufferedReader reader = new BufferedReader(new FileReader(usrDataLocation))){
                        String line;
                        while((line = reader.readLine()) != null){
                            String[] fields = line.split(",");
                            usr.addEnd(fields[0]);
                        }
                    }catch(IOException e){
                        System.out.println("ERROR LEER");
                    }
                    return usr;
                }
                
                public void getSelectedUsrValue(String data){
                    selectedItem = data;
                }
                
                public String getSelectedUsrScrollData(){
                    try{
                        BufferedReader reader = new BufferedReader(new FileReader(usrDataLocation));
                        int lines = 0;
                        while (reader.readLine() != null) lines++;
                        reader.close();

                        reader = new BufferedReader(new FileReader(usrDataLocation));
                        String line;
                        String prioridad = "";
                        String[] dataArray = new String[lines];
                        int dataNum = 0;
                        while((line = reader.readLine()) != null){
                            String[] fields = line.split(",");
                            if (fields[1].equals("prioridad_baja")){
                                prioridad = "Baja";
                            }else if (fields[1].equals("prioridad_media")){
                                prioridad = "Media";
                            }else if (fields[1].equals("prioridad_alta")){
                                prioridad = "Alta";
                            }
                            dataArray[dataNum] = ("Usuario: "+fields[0]+" (Prioridad "+prioridad+")");
                            dataNum++;
                        }
                        reader.close();

                        if (usrScroller != null) {
                            JList<String> newScrollerData = new JList<>(dataArray);
                            newScrollerData.addListSelectionListener(new ListSelectionListener(){
                                @Override
                                public void valueChanged(ListSelectionEvent e) {
                                    if (!e.getValueIsAdjusting()) {
                                        String selectedItem = (String) newScrollerData.getSelectedValue();
                                        getSelectedUsrValue(selectedItem);
                                    }
                                }
                            });
                            usrScroller.setViewportView(newScrollerData);
                        }
                    }catch(IOException e){
                        System.out.println("ERROR LEER");
                    }
                    return selectedItem;
                }
                
                public void deleteUsrScrollerData(String delUsr){
                        try{
                        BufferedReader reader = new BufferedReader(new FileReader(usrDataLocation));
                        int lines = 0;
                        while (reader.readLine() != null) lines++;
                        reader.close();

                        reader = new BufferedReader(new FileReader(usrDataLocation));
                        String line;
                        String prioridad = "";
                        String[] dataArray = new String[lines];
                        int dataNum = 0;
                        
                        while((line = reader.readLine()) != null){
                            String[] fields = line.split(",");
                            if (fields[1].equals("prioridad_baja")){
                                prioridad = "Baja";
                            }else if (fields[1].equals("prioridad_media")){
                                prioridad = "Media";
                            }else if (fields[1].equals("prioridad_alta")){
                                prioridad = "Alta";
                            }
                            dataArray[dataNum] = ("Usuario: "+fields[0]+" (Prioridad "+prioridad+")");
                            dataNum++;
                        }
                        reader.close();

                        BufferedWriter writer = new BufferedWriter(new FileWriter(usrDataLocation));
                        for (String data : dataArray) {
                            if (data.equals(delUsr)){
                            }else{
                            writer.write(data.replaceAll("Usuario: ","").replaceAll(" \\(Prioridad Baja\\)",",prioridad_baja").replaceAll(" \\(Prioridad Media\\)",",prioridad_media").replaceAll(" \\(Prioridad Alta\\)",",prioridad_alta") + "\n");
                            }
                        }
                        writer.close();

                        updateUsrScrollerData();
                    }catch(IOException e){
                        System.out.println("ERROR ESCRIBIR");
                    }
                }
                
                public void updateUsrScrollerData(){
                    try{
                        BufferedReader reader = new BufferedReader(new FileReader(usrDataLocation));
                        int lines = 0;
                        while (reader.readLine() != null) lines++;
                        reader.close();

                        reader = new BufferedReader(new FileReader(usrDataLocation));
                        String line;
                        String prioridad = "";
                        String[] dataArray = new String[lines];
                        int dataNum = 0;
                        while((line = reader.readLine()) != null){
                            String[] fields = line.split(",");
                            if (fields[1].equals("prioridad_baja")){
                                prioridad = "Baja";
                            }else if (fields[1].equals("prioridad_media")){
                                prioridad = "Media";
                            }else if (fields[1].equals("prioridad_alta")){
                                prioridad = "Alta";
                            }
                            dataArray[dataNum] = ("Usuario: "+fields[0]+" (Prioridad "+prioridad+")");
                            dataNum++;
                        }
                        reader.close();

                        JList newScrollerData = new JList(dataArray);
                        usrScroller.setViewportView(newScrollerData);
                        }
                    catch(IOException e){
                        System.out.println("ERROR LEER");
                    }
                }
                
                public void editPriorityUsrScrollerData(String editUsr){
                    try{
                        BufferedReader reader = new BufferedReader(new FileReader(usrDataLocation));
                        int lines = 0;
                        while (reader.readLine() != null) lines++;
                        reader.close();

                        reader = new BufferedReader(new FileReader(usrDataLocation));
                        String line;
                        String prioridad = "";
                        String[] dataArray = new String[lines];
                        int dataNum = 0;
                        
                        while((line = reader.readLine()) != null){
                            String[] fields = line.split(",");
                            if (fields[1].equals("prioridad_baja")){
                                prioridad = "Baja";
                            }else if (fields[1].equals("prioridad_media")){
                                prioridad = "Media";
                            }else if (fields[1].equals("prioridad_alta")){
                                prioridad = "Alta";
                            }
                            dataArray[dataNum] = ("Usuario: "+fields[0]+" (Prioridad "+prioridad+")");
                            dataNum++;
                        }
                        reader.close();

                        BufferedWriter writer = new BufferedWriter(new FileWriter(usrDataLocation));
                        for (String data : dataArray) {
                            if (data.equals(editUsr)){
                                writer.write(data.replaceAll("Usuario: ","").replaceAll(" \\(Prioridad Baja\\)",","+newUsrPriority).replaceAll(" \\(Prioridad Media\\)",","+newUsrPriority).replaceAll(" \\(Prioridad Alta\\)",","+newUsrPriority) + "\n");
                            }else{
                            writer.write(data.replaceAll("Usuario: ","").replaceAll(" \\(Prioridad Baja\\)",",prioridad_baja").replaceAll(" \\(Prioridad Media\\)",",prioridad_media").replaceAll(" \\(Prioridad Alta\\)",",prioridad_alta") + "\n");
                            }
                        }
                        writer.close();

                        updateUsrScrollerData();
                    }catch(IOException e){
                        System.out.println("ERROR ESCRIBIR");
                    }
                }
                
                public void addUsrScrollerData(String newUsr,String newUsrPriority){
                    try{
                        BufferedReader reader = new BufferedReader(new FileReader(usrDataLocation));
                        int lines = 0;
                        while (reader.readLine() != null) lines++;
                        reader.close();

                        reader = new BufferedReader(new FileReader(usrDataLocation));
                        String line;
                        String prioridad = "";
                        String[] dataArray = new String[lines];
                        int dataNum = 0;
                        boolean canWrite = true;
                        while((line = reader.readLine()) != null){
                            String[] fields = line.split(",");
                            if (fields[0].equals(newUsr)){
                                canWrite = false;
                                usrFeedback.setText("*ERROR: USR EXISTE*");
                            }
                            if (fields[1].equals("prioridad_baja")){
                                prioridad = "Baja";
                            }else if (fields[1].equals("prioridad_media")){
                                prioridad = "Media";
                            }else if (fields[1].equals("prioridad_alta")){
                                prioridad = "Alta";
                            }
                            dataArray[dataNum] = ("Usuario: "+fields[0]+" (Prioridad "+prioridad+")");
                            dataNum++;
                        }
                        reader.close();

                        if (canWrite){
                            BufferedWriter writer = new BufferedWriter(new FileWriter(usrDataLocation));
                            writer.write(newUsr+","+newUsrPriority+"\n");
                            for (String data : dataArray) {
                                writer.write(data.replaceAll("Usuario: ","").replaceAll(" \\(Prioridad Baja\\)",",prioridad_baja").replaceAll(" \\(Prioridad Media\\)",",prioridad_media").replaceAll(" \\(Prioridad Alta\\)",",prioridad_alta") + "\n");
                            }
                            writer.close();
                        }

                        updateUsrScrollerData();
                    }catch(IOException e){
                        System.out.println("ERROR ESCRIBIR");
                    }
                }                
	}

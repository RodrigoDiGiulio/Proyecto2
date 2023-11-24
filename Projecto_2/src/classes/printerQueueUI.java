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
import java.io.BufferedWriter;

/**
 * Esta clase crea la ventana donde se agregan, crean y seleccionan los usuarios
 * @author RDG, basado en codigo HENAO
 *
 */
public class printerQueueUI extends JFrame implements ActionListener
	{
		private Container contenedor;
		JLabel labelTitulo;/*declaramos el objeto Label*/
                String titulo = "Cola Completa";
                
                JLabel printerIcon;
                JLabel fileIcon;
                JLabel arrowIcon;
                ImageIcon printerImage = new ImageIcon("icons/printer50.png");
                ImageIcon fileImage = new ImageIcon("icons/fileIcon50.png");
                ImageIcon arrowImage = new ImageIcon("icons/arrow50.png");
                
                JButton fileIconArray[];
                JLabel arrowIconArray[];
                JLabel tooMuch = new JLabel();
                
                int frameSize = 0;
                
                String savedDataPath = "files/savedQueue.txt";
                String fileDataLocation = "files/files.csv";
                
                String names[] = {"A","B","C","D","E","F","G","H","I","J","K","L","M","N"}; 
                String newNames[];
                
                int totalDocs = names.length;
		
                public printerQueueUI(){}
                
		public printerQueueUI(int[] relativePos){//constructor
                    int posX = relativePos[0];
                    int posY = relativePos[1];
                    
                    contenedor=getContentPane();
                    contenedor.setLayout(null);

                    labelTitulo= new JLabel();
                    labelTitulo.setText(titulo);
                    labelTitulo.setBounds(250, 0, 180, 30);
                    
                    fileIconArray = new JButton[totalDocs];
                    arrowIconArray = new JLabel[totalDocs];
                    
                    //Sorting algorithm
                    String[][] fileDataArray = new String[100][3];  //Max 100
                    int lineCount = 0;

                    try (BufferedReader reader = new BufferedReader(new FileReader(savedDataPath))) {
                        String line;
                        while ((line = reader.readLine()) != null) {
                            fileDataArray[lineCount++] = line.split(",");
                        }
                    } catch (IOException e) {
                        System.out.println("ERROR LEER");
                    }

                    // Bubble sort (creo)
                    for (int i = 0; i < lineCount - 1; i++) {
                        for (int j = 0; j < lineCount - i - 1; j++) {
                            if (fileDataArray[j][1].compareTo(fileDataArray[j + 1][1]) > 0) {
                                String[] temp = fileDataArray[j];
                                fileDataArray[j] = fileDataArray[j + 1];
                                fileDataArray[j + 1] = temp;
                            }
                        }
                    }

                    try (BufferedWriter writer = new BufferedWriter(new FileWriter(savedDataPath))) {
                        for (int i = 0; i < lineCount; i++) {
                            writer.write(String.join(",", fileDataArray[i]) + "\n");
                        }
                    } catch (IOException e) {
                        System.out.println("ERROR ESCRIBIR");
                    }

                    
                    
                    //Showing all files
                    try{
                        BufferedReader reader = new BufferedReader(new FileReader(savedDataPath));
                        int lines = 0;
                        while (reader.readLine() != null) lines++;
                        reader.close();

                        reader = new BufferedReader(new FileReader(savedDataPath));
                        String line;
                        String[] dataArray = new String[lines];
                        int dataNum = 0;
                        while((line = reader.readLine()) != null){
                            String[] fields = line.split(",");
                            dataArray[dataNum] = (fields[0]);
                            dataNum++;
                        }
                        names = dataArray;
                        totalDocs = dataArray.length;
                        reader.close();
                    }catch(IOException e){
                        System.out.println("ERROR LEER");
                    }
                    
                    updateArrowIconArray();

                    updateFileIconArray();
                    
                    printerIcon = new JLabel(printerImage);
                    printerIcon.setBounds(10, 50, 50, 50);

                    contenedor.add(printerIcon);
                        
       		//Asigna un titulo a la barra de titulo
                    setTitle("COLA IMPRESORA MANAGER");
                    printerIcon = new JLabel(printerImage);
                    if(frameSize == 0){
                        setSize(600,160);
                    }else if(frameSize == 1){
                        setSize(600,250);
                    }else{
                        setSize(600,300);
                    }
                    
                    setLocation(posX, posY+300);
                    
                    contenedor.add(labelTitulo);
			
	        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		}

		@Override
		public void actionPerformed(ActionEvent evento) {
                String actionCommand = evento.getActionCommand();
                if(actionCommand.startsWith("File ")){
                    String temp = actionCommand.replaceAll("File ","");
                    int disable = Integer.parseInt(temp);
                    newNames = new String[names.length-1];
                    JButton[] newFileIconArray = new JButton[names.length-1];
                    int newIndex = 0;
                    for (int i = 0; i < names.length; i++) {
                        contenedor.remove(fileIconArray[i]);
                        if(i != disable){
                            newNames[newIndex] = names[i];
                            newFileIconArray[newIndex] = fileIconArray[i];
                            newIndex++;
                        }
                    }
                    JLabel[] newArrowIconArray = new JLabel[names.length-1];
                    newIndex = 0;
                    for (int i = 0; i < names.length; i++) {
                        contenedor.remove(arrowIconArray[i]);
                        if(i != disable){
                            newArrowIconArray[newIndex] = arrowIconArray[i];
                            newIndex++;
                        }
                    }
                    arrowIconArray = newArrowIconArray;
                    names = newNames;
                    
                    
                    fileIconArray = newFileIconArray;
                    totalDocs--;
                    contenedor.revalidate();
                    contenedor.repaint();
                    updateFileIconArray();
                    updateArrowIconArray();
                }
            }
            private void updateFileIconArray(){
                tooMuch.setBounds(20, 185, 200, 70);
                fileIconArray = new JButton[totalDocs];
                for (int i = 0; i < fileIconArray.length; i++) {
                    fileIconArray[i] = new JButton("ERROR", fileImage);
                    if(i < 5){
                        fileIconArray[i].setBounds((100+(100*i)), 40, 70, 70);
                        frameSize = 0;
                    }else if(i < 10){
                        fileIconArray[i].setBounds((100+(100*i)-500), 130, 70, 70);
                        frameSize = 1;
                    }else{
                        fileIconArray[i].setBounds((100+(100*i)-1000), 230, 70, 70);
                        frameSize = 2;
                    }
                    fileIconArray[i].setHorizontalTextPosition(JButton.CENTER);
                    fileIconArray[i].setVerticalTextPosition(JLabel.BOTTOM);
                    fileIconArray[i].setText(names[i]);
                    fileIconArray[i].setActionCommand("File " + i);
                    fileIconArray[i].addActionListener(this);
                    contenedor.add(fileIconArray[i]);
                }
                if(fileIconArray.length < 6){
                    tooMuch.setText("");
                }else if(fileIconArray.length < 11){
                    tooMuch.setText("");
                }else{
                    tooMuch.setText("HAY MUCHOS, VER ARBOL");
                }
                contenedor.add(tooMuch);
                contenedor.revalidate();
                contenedor.repaint();
            }
            
            private void updateArrowIconArray(){
                arrowIconArray = new JLabel[totalDocs];
                    for (int i = 0; i < arrowIconArray.length; i++) {
                        arrowIconArray[i] = new JLabel(arrowImage);
                        if(i < 5){
                            arrowIconArray[i].setBounds((50+(100*i)), 40, 70, 70);
                            frameSize = 0;
                        }else if(i < 10){
                            arrowIconArray[i].setBounds((50+(100*i)-500), 130, 70, 70);
                            frameSize = 1;
                        }else{
                            arrowIconArray[i].setBounds((50+(100*i)-1000), 230, 70, 70);
                            frameSize = 2;
                        }
                        contenedor.add(arrowIconArray[i]);
                }
            }
            public boolean canPrint(){
                return ((names.length-1) > -1);
            }
            
            public void stepPrint(){
                String replaceData[];
                int disable = 0;
                newNames = new String[names.length-1];
                JButton[] newFileIconArray = new JButton[names.length-1];
                int newIndex = 0;
                for (int i = 0; i < names.length; i++) {
                    contenedor.remove(fileIconArray[i]);
                    if(i != disable){
                        newNames[newIndex] = names[i];
                        newFileIconArray[newIndex] = fileIconArray[i];
                        newIndex++;
                    }
                }
                JLabel[] newArrowIconArray = new JLabel[names.length-1];
                newIndex = 0;
                for (int i = 0; i < names.length; i++) {
                    contenedor.remove(arrowIconArray[i]);
                    if(i != disable){
                        newArrowIconArray[newIndex] = arrowIconArray[i];
                        newIndex++;
                    }
                }
                arrowIconArray = newArrowIconArray;
                names = newNames;


                fileIconArray = newFileIconArray;
                totalDocs--;
                contenedor.revalidate();
                contenedor.repaint();
                updateFileIconArray();
                updateArrowIconArray();
                
                try{
                    BufferedReader reader = new BufferedReader(new FileReader(savedDataPath));
                    int lines = 0;
                    while (reader.readLine() != null) lines++;
                    reader.close();

                    reader = new BufferedReader(new FileReader(savedDataPath));
                    String line;
                    String[] dataArray = new String[lines];
                    int dataNum = 0;
                    while((line = reader.readLine()) != null){
                        String[] fields = line.split(",");
                        dataArray[dataNum] = (fields[0]+","+fields[1]+","+fields[2]);
                        dataNum++;
                    }
                    replaceData = dataArray;
                    reader.close();
                    
                    BufferedWriter writer = new BufferedWriter(new FileWriter(savedDataPath));
                    for (int i = 0; i < replaceData.length; i++) {
                        if(i != 0){
                        writer.write(replaceData[i]+"\n");
                        }
                    }
                    writer.close();
                    
                }catch(IOException e){
                    System.out.println("ERROR ESCRIBIR");
                }
            }
	}

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
import java.util.HashSet;
import java.util.Set;
import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import primitivas.*;

/**
 *
 * @author RDG
 */
public class fileUI extends JFrame implements ActionListener{
    JLabel labelTitulo;
    JLabel usrFeedback;
    String titulo = "ERROR ERROR ERROR";
    String selectedItem;
    
    private Container contenedor;
    JButton createFile;
    JButton deleteFile;
    JButton printFile;
    JTextField fileName;
    JTextField fileSize;
    
    JScrollPane usrScroller;
    JTextArea usrScrollerData;
    String usrDataLocation = "files/users.csv";
    String fileDataLocation = "files/files.csv";
    JList newScrollerData;
    JComboBox owners;
    
    String lastData;
    String savedDataPath = "files/savedQueue.txt";
    
    public fileUI(){}
    
    public fileUI(int[] relativePos, int mode){
        switch (mode) {
            case 1:
                titulo = ("Crear Archivo");
                break;
            case 2:
                titulo = ("Borrar Archivo");
                break;
            case 3:
                titulo = ("Agregar Archivo Cola");
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
        
        //Deleting non existing users
        try {
                Set<String> fileOwners = getFileOwners(fileDataLocation);
                Set<String> users = getUsers(usrDataLocation);
                String replaceData[];
                for (String owner : fileOwners) {
                    if (!users.contains(owner)) {
                        try{
                        BufferedReader reader = new BufferedReader(new FileReader(fileDataLocation));
                        int lines = 0;
                        while (reader.readLine() != null) lines++;
                        reader.close();

                        reader = new BufferedReader(new FileReader(fileDataLocation));
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

                        BufferedWriter writer = new BufferedWriter(new FileWriter(fileDataLocation));
                        for (int i = 0; i < replaceData.length; i++) {
                            if(replaceData[i].contains(owner) == false){
                            writer.write(replaceData[i]+"\n");
                            }
                        }
                        writer.close();

                    }catch(IOException e){
                        System.out.println("ERROR ESCRIBIR");
                    }
                    }
                }
            }catch(IOException e){
                e.printStackTrace();
            }
        
        updateFileScrollerData();
        
        getSelectedFileScrollData();
        
        if (mode == 1){
            usrScroller.setBounds(20, 30, 250, 170);
            
            createFile = new JButton();
            createFile.setText("Crear");
            createFile.setBounds(200,235,70,20);
            createFile.addActionListener(this);
            
            owners = new JComboBox();
            owners.setBounds(20,205,250,20);

            fileName = new JTextField();
            fileName.setBounds(20,235,130,20);

            fileSize = new JTextField();
            fileSize.setBounds(150,235,50,20);

            contenedor.add(createFile);                                                       
            contenedor.add(fileName);                           
            contenedor.add(fileSize);                                                   
            contenedor.add(owners);    
            
            updateComboBox();
        }else if(mode == 2){
            deleteFile = new JButton();
            deleteFile.setText("Eliminar");
            deleteFile.setBounds(100,235,90,20);
            deleteFile.addActionListener(this);
            contenedor.add(deleteFile);
        }else if(mode == 3){
            printFile = new JButton();
            printFile.setText("Imprimir");
            printFile.setBounds(100,235,90,20);
            printFile.addActionListener(this);
            contenedor.add(printFile);
        }
        
        setTitle("COLA IMPRESORA MANAGER");
        setSize(300,300);
        
        setLocation(posX-300, posY);
        contenedor.add(labelTitulo);
        contenedor.add(usrFeedback);
        contenedor.add(usrScrollerData);
        contenedor.add(usrScroller);
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent evento) {
        if (evento.getSource()==createFile){
            String newName = fileName.getText();
            String newSize = fileSize.getText();
            if ((newName.trim().length() != 0) && (isInt(newSize)) && (owners.getSelectedItem() != null)){
                updateComboBox();
                String newOwner = (String) owners.getSelectedItem();
                addFileScrollerData(newName,newSize,newOwner);
                fileName.setText("");
                fileSize.setText("");
                usrFeedback.setText("");
                updateFileScrollerData();
            }else if((newName.trim().length() == 0) && (isInt(newSize)==false) && (owners.getSelectedItem() == null)){
                usrFeedback.setText("*ERROR: NO DATA*");
            }else if(newName.trim().length() == 0){
                usrFeedback.setText("*ERROR: NOMBRE*");
            }else if(isInt(newSize)==false){
                usrFeedback.setText("*ERROR: PESO*");
            }else if(owners.getSelectedItem() == null){
                usrFeedback.setText("*ERROR: OWNER*");
            }
        }
        
        if (evento.getSource()==deleteFile){
            deleteFileScrollerData(getSelectedFileScrollData());
//            updateFileScrollerData();
        }
        
        if (evento.getSource()==printFile){
            System.out.println("IMPRIMIENDO");
            String x = getSelectedFileScrollData();
            if(x != null){
                String temp = (x.replaceAll("Archivo: ","").replaceAll("Mb","").replaceAll(" \\(",",").replaceAll("\\)","").replaceAll("->",",")+"\n");
                if(lastData != null){
                    lastData = (lastData+temp);
                }else{
                    lastData = temp;
                }
                File file = new File(savedDataPath);
                try (FileWriter writer = new FileWriter(file)) {
                    writer.write(lastData);
                }catch(IOException exc){
                    exc.printStackTrace();
                }
                System.out.println(temp);
            }
            selectedItem = null;
            }
//            updateFileScrollerData();
    }
    
    public boolean isInt(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    
    public List getFileOwnerFileScrollData(){
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
    
    public void updateComboBox(){
        List ownersList = new List();
        ownersList = getFileOwnerFileScrollData();
        String[] allOwners = new String[ownersList.isSize()];
        for (int i = 0; i < ownersList.isSize(); i++) {
            allOwners[i] = ownersList.searchPos(i).toString();
            owners.addItem(allOwners[i]);
        }
    }

    public void getSelectedFileValue(String data){
        selectedItem = data;
    }

    public String getSelectedFileScrollData(){
        try{            
            BufferedReader reader = new BufferedReader(new FileReader(fileDataLocation));
            int lines = 0;
            while (reader.readLine() != null) lines++;
            reader.close();

            reader = new BufferedReader(new FileReader(fileDataLocation));
            String line;
            String[] dataArray = new String[lines];
            int dataNum = 0;
            while((line = reader.readLine()) != null){
                String[] fields = line.split(",");
                dataArray[dataNum] = ("Archivo: "+fields[0]+"->"+fields[1]+"Mb"+" ("+fields[2]+")");
                dataNum++;
            }
            reader.close();

            if (usrScroller != null) {
                JList<String> newScrollerData = new JList<>(dataArray);
                newScrollerData.addListSelectionListener(new ListSelectionListener(){
                    @Override
                    public void valueChanged(ListSelectionEvent e) {
                        if (!e.getValueIsAdjusting()) {
                            String newSelectedItem = (String) newScrollerData.getSelectedValue();
                            getSelectedFileValue(newSelectedItem);
                        }
                    }
                });
                usrScroller.setViewportView(newScrollerData);
            }
        }catch(IOException e){
            System.out.println("ERROR LEER");
        }
        String newDataItem = selectedItem;
        return newDataItem;
    }

    public void deleteFileScrollerData(String delUsr){
            try{
            BufferedReader reader = new BufferedReader(new FileReader(fileDataLocation));
            int lines = 0;
            while (reader.readLine() != null) lines++;
            reader.close();

            reader = new BufferedReader(new FileReader(fileDataLocation));
            String line;
            String[] dataArray = new String[lines];
            int dataNum = 0;

            while((line = reader.readLine()) != null){
                String[] fields = line.split(",");
                dataArray[dataNum] = ("Archivo: "+fields[0]+"->"+fields[1]+"Mb"+" ("+fields[2]+")");
                dataNum++;
            }
            reader.close();

            BufferedWriter writer = new BufferedWriter(new FileWriter(fileDataLocation));
            for (String data : dataArray) {
                if (data.equals(delUsr)){
                }else{
                writer.write(data.replaceAll("Archivo: ","").replaceAll("Mb","").replaceAll(" \\(",",").replaceAll("\\)","").replaceAll("->",",")+"\n");
                }
            }
            writer.close();

            updateFileScrollerData();
        }catch(IOException e){
            System.out.println("ERROR ESCRIBIR");
        }
    }

    public void updateFileScrollerData(){
        try{
            BufferedReader reader = new BufferedReader(new FileReader(fileDataLocation));
            int lines = 0;
            while (reader.readLine() != null) lines++;
            reader.close();

            reader = new BufferedReader(new FileReader(fileDataLocation));
            String line;
            String[] dataArray = new String[lines];
            int dataNum = 0;
            while((line = reader.readLine()) != null){
                String[] fields = line.split(",");
                dataArray[dataNum] = ("Archivo: "+fields[0]+"->"+fields[1]+"Mb"+" ("+fields[2]+")");
                dataNum++;
            }
            reader.close();

            JList newScrollerData = new JList(dataArray);
            usrScroller.setViewportView(newScrollerData);
            }catch(IOException e){
                System.out.println("ERROR LEER");
            }
        }

    public void addFileScrollerData(String newFile, String newSize, String newOwner){
        try{
            BufferedReader reader = new BufferedReader(new FileReader(fileDataLocation));
            int lines = 0;
            while (reader.readLine() != null) lines++;
            reader.close();

            reader = new BufferedReader(new FileReader(fileDataLocation));
            String line;
            String[] dataArray = new String[lines];
            int dataNum = 0;
            boolean canWrite = true;
            while((line = reader.readLine()) != null){
                String[] fields = line.split(",");
                dataArray[dataNum] = ("Archivo: "+fields[0]+"->"+fields[1]+"Mb"+" ("+fields[2]+")");
                dataNum++;
            }
            reader.close();

            if (canWrite){
                BufferedWriter writer = new BufferedWriter(new FileWriter(fileDataLocation));
                System.out.println(newOwner);
                System.out.println(newFile+","+newSize+","+newOwner);
                writer.write(newFile+","+newSize+","+newOwner+"\n");
                for (String data : dataArray) {
                    writer.write(data.replaceAll("Archivo: ","").replaceAll("Mb","").replaceAll(" \\(",",").replaceAll("\\)","").replaceAll("->",",")+ "\n");
                }
                writer.close();
            }

            updateFileScrollerData();
        }catch(IOException e){
            System.out.println("ERROR ESCRIBIR");
        }
    } 
    
    private static Set<String> getFileOwners(String filename) throws IOException {
        Set<String> fileOwners = new HashSet<>();
        BufferedReader reader = new BufferedReader(new FileReader(filename));

        String line;
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(",");
            fileOwners.add(parts[2].trim());
        }

        reader.close();
        return fileOwners;
    }

    private static Set<String> getUsers(String filename) throws IOException {
        Set<String> users = new HashSet<>();
        BufferedReader reader = new BufferedReader(new FileReader(filename));

        String line;
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(",");
            users.add(parts[0].trim());
        }

        reader.close();
        return users;
    }
    
}
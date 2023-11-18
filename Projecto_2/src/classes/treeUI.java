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
public class treeUI extends JFrame implements ActionListener{
    //Atributos
    JLabel labelTitulo;
    String titulo = "ERROR ERROR ERROR";
    private Container contenedor;
    
    public treeUI(){}
    
    public treeUI(int[] relativePos, int mode){
        int posX = relativePos[0];
        int posY = relativePos[1];
        
        contenedor=getContentPane();
        contenedor.setLayout(null);

        labelTitulo= new JLabel();
        labelTitulo.setText(titulo);
        labelTitulo.setBounds(20, 0, 180, 30);
        
        setTitle("COLA IMPRESORA MANAGER");
        setSize(300,300);
        
        setLocation(posX+600, posY);
        contenedor.add(labelTitulo);
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent eventos) {
        System.out.println("AQUI VAN LOS EVENTOS");
    }
}
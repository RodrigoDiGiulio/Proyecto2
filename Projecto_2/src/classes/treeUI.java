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
import org.graphstream.graph.*;
import org.graphstream.graph.implementations.*;
import org.graphstream.graph.*;
import org.graphstream.graph.implementations.*;
import org.graphstream.ui.view.Viewer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;
import org.graphstream.ui.swing_viewer.SwingViewer;
import org.graphstream.ui.swing_viewer.ViewPanel;
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
    String savedDataPath = "files/savedQueue.txt";
    String names[];
    
    public treeUI(){
    }
    
    public treeUI(int[] relativePos, int mode){
        int posX = relativePos[0];
        int posY = relativePos[1];
        
        contenedor=getContentPane();
        contenedor.setLayout(null);

        labelTitulo= new JLabel();
        labelTitulo.setText(titulo);
        labelTitulo.setBounds(20, 0, 180, 30);
        
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
            reader.close();
        }catch(IOException e){
            System.out.println("ERROR LEER");
        }
        
        List ordenPrintNames = new List();        
        BinaryTree BT = new BinaryTree();
        for (int i = 0; i < names.length; i++) {
            String x = names[i];
            ordenPrintNames.addEnd(x);
            if (BT.getRoot() != null) {
                BT.setRoot(BT.add(BT.getRoot(), BT.getRoot().getFather(), i));
            } else {
                BT.setRoot(BT.add(null, null, i));
            }
        }
        
        System.out.println("INORDEN");
        BT.inOrder(BT.getRoot());
        System.out.println("\nPREORDEN");
        BT.preOrder(BT.getRoot());
        System.out.println("\nPOSTORDEN");
//        BT.postOrder(BT.getRoot());
        ordenPrintNames.print();
        
        List newOrder = new List();
        newOrder = BT.postOrder(BT.getRoot(),newOrder);
        System.out.println("NEWORDER");
        newOrder.print();
        
        List fatherOrder = new List();
        fatherOrder = BT.postOrderFather(BT.getRoot(), fatherOrder);
        System.out.println("FATHERS");
        fatherOrder.print();
        
        System.setProperty("org.graphstream.ui", "swing");
        Graph graph = new SingleGraph("Grafico");
        graph.setAttribute("ui.stylesheet", 
                "node{\n"                   + 
                "size: 30px, 30px;\n"       +
                "fill-color: #b0c4de;\n"    +
                "text-mode: normal;\n"      +
                "}");        
        
        for (int i = 0; i < names.length; i++) {
            int realName = (int) newOrder.searchPos(i);
            Node node = graph.addNode(Integer.toString(realName));
            node.setAttribute("ui.label", names[realName]);
//            System.out.println(realName);
            System.out.println(fatherOrder.searchPos(realName));
            if(i > 0){
                graph.addEdge(Integer.toString((int) fatherOrder.searchPos(realName))
                        + Integer.toString(realName),
                        Integer.toString((int) fatherOrder.searchPos(realName)), 
                        Integer.toString(realName));
            }
        }
        
        SwingViewer swing = new SwingViewer(graph, Viewer.ThreadingModel.GRAPH_IN_GUI_THREAD);
        swing.enableAutoLayout();
        ViewPanel view = (ViewPanel) swing.addDefaultView(false);
        view.setBounds(10, 40, 265, 220);
        contenedor.add(view);
        
        setTitle("COLA IMPRESORA MANAGER");
        setSize(300,300);
        
        setLocation(posX+600, posY);
        contenedor.add(labelTitulo);
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent eventos) {
        System.out.println("AQUI VAN LOS EVENTOS");
    }
}
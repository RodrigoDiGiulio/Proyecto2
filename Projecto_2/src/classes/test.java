import java.awt.BorderLayout;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;
import primitivas.List;

public class test extends JFrame {

  JScrollPane scrollpane;
  JScrollPane usrScroller;
  String usrDataLocation = "files/users.csv";


  public test() {
        String selectedItem = "";
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
        if (newScrollerData.getSelectedIndex() != -1) {
                selectedItem = (String) newScrollerData.getSelectedValue();
                System.out.println(selectedItem);
            }
        }
    catch(IOException e){
        System.out.println("ERROR");
        }
    }

  public static void main(String args[]) {
    test sl = new test();
    sl.setVisible(true);
  }
}
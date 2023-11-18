/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package projecto_2;

import classes.*;

/**
 *
 * @author RDG
 */
public class main {

        public static void main(String[] args) {
		centralUI centralUIWindow = new centralUI();
		centralUIWindow.setVisible(true);
                while(true){
                    centralUIWindow.updateClock();
                    try{
                        Thread.sleep(1000);
                    }catch(InterruptedException e){
                        e.printStackTrace();
                    }
                }
	}
}

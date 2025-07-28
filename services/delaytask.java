/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

/**
 *
 * @author Junaid Mansuri
 */
public class delaytask {
    public static void delay(int miliseconds,Runnable Task){
    new java.util.Timer().schedule(new java.util.TimerTask() {
        @Override
        public void run() {
          Task.run();
        
        }
    },miliseconds);
    
    }
}

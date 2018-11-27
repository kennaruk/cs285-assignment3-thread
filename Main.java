/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reserveseatinflight;

import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author User
 */
public class Main {
    public static void main(String[] args) {
        for(int i=1; i<=10; i++){
            try {
                AFlight f1 = new AFlight("TG930");
                
                /* Define Songsak (Ajarn thread) */
                Thread thread1 = new BuyFlightTicketThread(f1, "Songsakdi",
                        "A1");
                thread1.setName("Reserve for Ajarn");
                
                /* Define Prayut (Prime M. thread) */
                Thread thread2 = new BuyFlightTicketThread(f1, "Prayut",
                        "A1");
                thread2.setName("Reserve for Thailand's Prime Minister");
                
                /* Sleep and cancel thread */
                Thread sleepAndCancelThread = new CancelFlightTicketThread(f1, "NOT SPECIFIED", "A1");
                sleepAndCancelThread.setName("Cancel seat for A1");
                
                thread1.start();
                thread2.start();
                sleepAndCancelThread.start();
                
                thread2.join();
                thread1.join();
                sleepAndCancelThread.join();
                
                System.out.println("+++++++++++++++++++++++++++");
                System.out.println("Round " + i +":");
                f1.showSeatsStatus();
                System.out.println("+++++++++++++++++++++++++++");
                
            } catch (InterruptedException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        
        
    }
    
}

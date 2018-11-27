/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reserveseatinflight;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author User
 */
public class ReserveSeatInFlight {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        for (int i = 1; i <= 5; i++) {
            try {
                System.out.println("Round " + i + ":");
                Flight flight1 = new Flight("TG930");
                ReserveSeatThread reservation1
                        = new ReserveSeatThread("พี่ประยุทธ์", flight1, "A1");
                reservation1.setName("Reserve for PM");
                reservation1.start();
                ReserveSeatThread reservation2
                        = new ReserveSeatThread("Songsakdi", flight1, "A1");
                reservation2.setName("Reserve for Ajarn");
                reservation2.start();
                
                reservation2.join();
                reservation1.join();
                flight1.showSeatsStatus();
                System.out.println("End of Round " + i + ":");
            } catch (InterruptedException ex) {
                Logger.getLogger(ReserveSeatInFlight.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

    }

}

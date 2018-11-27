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
public class BuyFlightTicketThread extends Thread {

    private AFlight flight ;
    private String passengerName;
    private String seatNO ;

    public BuyFlightTicketThread() {
    }
    
    public BuyFlightTicketThread(AFlight flight, 
                 String passengerName, String seatNO) {
        this.flight =  flight ;
        this.passengerName = passengerName ;
        this.seatNO = seatNO ;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(100);
            flight.reserveSeatForPassenger(passengerName, 
                    flight.getFlightNO(), seatNO);
        } catch (InterruptedException ex) {
            Logger.getLogger(BuyFlightTicketThread.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    
}

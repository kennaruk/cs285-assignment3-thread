/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reserveseatinflight;

import java.util.Date;

/**
 *
 * @author User
 */
public class ReserveSeatThread extends Thread{

    private Flight flight ;
    private String passenger ;
    private String seatNO ;
    
    public ReserveSeatThread() {
    }

    
    public ReserveSeatThread(String passenger, 
                             Flight flight, String seatNO) {
        super();
        this.passenger = passenger;
        this.flight = flight ;
        this.seatNO = seatNO;
    }

    @Override
    public void run() {
        System.out.println(passenger + " reserved seat " + seatNO 
           + "of flight " + flight.getFlightNO() 
           + " at " + new Date()
           + "by thread named:" + Thread.currentThread().getName() );
        flight.reserveSeatForPassenger(passenger,
                flight.getFlightNO(), seatNO);
    }
    
    
    
    
    
}

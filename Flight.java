/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reserveseatinflight;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author User
 */
public class Flight {
  private HashMap<String, String> seats ;    
  private String flightNO ;

    public Flight() {
        seats =  new HashMap<String, String>();
        seats.put("A1", "Not Specified");
        seats.put("A2", "Not Specified");
        seats.put("A3", "Not Specified");
        seats.put("A4", "Not Specified");
        seats.put("A5", "Not Specified");
    }

    public Flight(String flightNO) {
        this();
        this.flightNO = flightNO;
    }
  
    
    public synchronized void reserveSeatForPassenger(String passengerName, 
                                        String flightNO, String seatNO) {
        if (flightNO.equals(this.flightNO) && 
            seats.get(seatNO).equals("Not Specified")){
            try {
                System.out.println("Reserving a seat for " + passengerName + ", then going to sleep.");
                Thread.sleep((int)(Math.random()*10));
                seats.put(seatNO, passengerName);
            } catch (InterruptedException ex) {
                Logger.getLogger(Flight.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else {
            System.out.println("Seat "+ seatNO + " is reserved for " + seats.get(seatNO));
        }                               
    }
    
    public synchronized void cancelSeatForPassenger(String passengerName,
    		 							String flightNO, String seatNO) {
    	if (flightNO.equals(this.flightNO) && 
                !seats.get(seatNO).equals("Not Specified")){
                System.out.println("Canceling a seat" + seatNO);
				seats.put(seatNO, "Not Specified");
            }else {
                System.out.println("Seat "+ seatNO + " is not reserved");
            }      
    }
    
    public void showSeatsStatus(){
        for (Map.Entry<String, String> entrySet : seats.entrySet()) {
            String seat = entrySet.getKey();
            String passenger = entrySet.getValue();
            System.out.println(seat + "[" + passenger +"]");
        }
    }

    public String getFlightNO() {
        return flightNO ;
    }

  
    
    
  
  
  
    
}

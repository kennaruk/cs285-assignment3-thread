package reserveseatinflight;

import java.util.concurrent.TimeUnit;

public class CancelFlightTicketThread  extends Thread {
	private AFlight flight ;
    private String passengerName;
    private String seatNO ;
    
    public CancelFlightTicketThread() {
	}
    
    public CancelFlightTicketThread(AFlight flight, 
            String passengerName, String seatNO) {
    	this.flight =  flight ;
        this.passengerName = passengerName ;
        this.seatNO = seatNO ;
	}
    
	@Override
	public void run() {
		try {
			TimeUnit.SECONDS.sleep(1);
			flight.cancelSeatForPassenger(passengerName, flight.getFlightNO(), seatNO);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

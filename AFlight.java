/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reserveseatinflight;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author User
 */
public class AFlight {

	private HashMap<String, String> seats;
	private String flightNO;

	public AFlight() {
		seats = new HashMap<String, String>();
		seats.put("A1", "NOT SPECIFIED");
		seats.put("A2", "NOT SPECIFIED");
		seats.put("A3", "NOT SPECIFIED");
	}

	public AFlight(String flightNO) {
		this();
		this.flightNO = flightNO;
	}

	public String getFlightNO() {
		return flightNO;
	}

	public void reserveSeatForPassenger(String passengerName, String flightNO, String seatNO) {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

		int tryToReserveCount = 1;
		do {
			synchronized(this) {
				if (this.flightNO.equals(flightNO) && seats.get(seatNO).equals("NOT SPECIFIED")) {
					try {
						System.out.println(Thread.currentThread().getName() + " is reserving seat" + seatNO + " at"
								+ sdf.format(new Date()));

						Thread.sleep((int) Math.random() * 100);

						seats.put(seatNO, passengerName);

						System.out.println(Thread.currentThread().getName() + " finally got " + seatNO + " for "
								+ seats.get(seatNO) + " at" + sdf.format(new Date()));
						break;
					} catch (InterruptedException ex) {
						Logger.getLogger(AFlight.class.getName()).log(Level.SEVERE, null, ex);
					}
				} else {
					if (tryToReserveCount++ <= 10) {
						try {
	//						TimeUnit.SECONDS.sleep(1);
							wait(1000);
							continue;
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}

					System.out.println(Thread.currentThread().getName() + " failed to reserve seat" + seatNO + " at"
							+ sdf.format(new Date()) + " since Seat: " + seatNO + " already reserved for "
							+ seats.get(seatNO));
					break;
				}
			}			
		} while (true);
	}

	public void cancelSeatForPassenger(String passengerName, String flightNO, String seatNO) {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

		synchronized(this) {
			if (this.flightNO.equals(flightNO) && !seats.get(seatNO).equals("NOT SPECIFIED")) {
				System.out.println(
						Thread.currentThread().getName() + " is canceling seat" + seatNO + " at" + sdf.format(new Date()));

				seats.put(seatNO, passengerName);

				System.out.println(Thread.currentThread().getName() + " finally cancel " + " at" + sdf.format(new Date()));
				notifyAll();

			} else {
				System.out.println(Thread.currentThread().getName() + " failed to cancel seat" + seatNO + " at"
						+ sdf.format(new Date()) + " since Seat: " + seatNO + " already not reserved for "
						+ seats.get(seatNO));
			}
		}
		
	}

	public void showSeatsStatus() {
		for (Map.Entry<String, String> aSeat : seats.entrySet()) {
			String seatNumber = aSeat.getKey();
			String passengerName = aSeat.getValue();

			System.out.println("Flight: " + flightNO + ",seatNumber" + seatNumber + " for " + passengerName);
		}
	}

}

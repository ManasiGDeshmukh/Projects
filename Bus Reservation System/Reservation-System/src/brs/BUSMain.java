package brs;
import java.util.*;
import brs.*;

// main class
public class BUSMain {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int g=1, ch=0; // for menu
		int[] depd; // array to store destination and departure
		PassengerDetails psg;    
		Bus bus;
		BusOperation bo = new BusOperation(); // operation class
		bo.initiate(); // Initialize all buses
		do {

			//display menu
			System.out.println(" --------------------------------");
			System.out.println("|\t1. BOOKING\t\t|\n|_______________________________|\n|\t2. CANCELLATION\t\t|\n|_______________________________|\n|\t3. CHECK STATUS\t\t|\n|_______________________________|\n|\t4. EXIT\t\t\t|\n|_______________________________|\n");
			

			
			try {
				ch=sc.nextInt();
				System.out.println("");
				switch(ch) {
				case 1:

					//choice 1 Booking is selected

					//displays the available locations
					//for comfort of running the code
					System.out.println("          Available Stops");
					System.out.println("--------------------------------------");
					for(int i=0; i< bo.AvailableStops.length; i++) {
						System.out.print(" "+ bo.AvailableStops[i]+" | ");
						if(i%4==3) {
							System.out.println("");
						}
					}
					System.out.println();
					System.out.println("--------------------------------------");


					//Accepting the departure and destination from the user

					System.out.print("  Enter the departure:  ");
					String dep=sc.next();
					System.out.print("  Enter the destination:  ");
					String des=sc.next();

					//Displays the buses available
					depd=bo.search(dep,des);

					//if there are buses available
					if(depd!=null) {
						//Accepting the Bus id out of the displayed choices
						System.out.print("  Enter Bus Id: ");
						int id=sc.nextInt();
						//returns the object of the class Bus corresponding to the id entered
						bus=bo.BusFinder(id);
						bus.ID=id;
						//reinitializing the bus stops for the above bus
						depd=bo.busF(bus, dep, des);
						//booking of the seat
						bo.bookSeat(bus, depd);
					}
					bo.Queueseat_Confirmation();
					break;

				case 2:
					//Choice 2 Cancelling a booked seat

					System.out.print("  Enter Bus Id: ");
					bus=bo.BusFinder(sc.nextInt());
					sc.nextLine();
					if(bus!=null) {
						System.out.print("  Enter PNR No: ");
						int prn =sc.nextInt();
						sc.nextLine();
						psg=bo.Passengerfinder(bus, prn);
						if(psg!=null) {
							System.out.print("  Comfirm Cancellation? Y/N :  ");
							char cancel=sc.nextLine().charAt(0);
							if(cancel=='Y'||cancel=='y') {
								bo.cancellation(bus,psg);
							}
							else {
								System.out.println("  Cancellation Declined !");
							}

						}else {
							System.out.println("  Passenger Not Found !");
						}
					}
					else {
						System.out.println("  Bus Not Found!!");
					}
					bo.Queueseat_Confirmation();
					break;

				case 3:
					System.out.print("  Enter Bus Id: ");
					bus=bo.BusFinder(sc.nextInt());
					sc.nextLine();
					if(bus!=null) {
						System.out.print("  Enter PNR No: ");
						int prn =sc.nextInt();
						if(bo.Passengerfinder(bus, prn)==null) {
							psg=bo.Passenger_in_Queue(bus, prn);
							if(psg!=null) {
								System.out.println("  Passenger in queue");
							}
							else {
								System.out.println("  Passenger not found");
							}
						}
						else {
							System.out.println("  Booking Status: Seat Confirmed");
						}
					}
					bo.Queueseat_Confirmation();
					break;

				case 4:
					g=0;
					break;
				default:
					System.out.println("");
				}

			}
			catch(Exception e)  
			{  
				System.out.println("Error has occured try again"+e); 
				BUSMain Main = new BUSMain();
				Main.main(args);
				
			}  
			
		}while(g==1);
		sc.close();
	}
}


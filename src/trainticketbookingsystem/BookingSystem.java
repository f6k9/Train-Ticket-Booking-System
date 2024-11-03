package trainticketbookingsystem;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class BookingSystem {

    public static void main(String[] args) throws FileNotFoundException {

        
        TrainTicketBookingSystem system = new TrainTicketBookingSystem();
        
        File file = new File("input.txt");
 
        //if file doesn't exist terminate program
        if (!file.exists()) {
            System.out.println("File doesn't exist");
            System.exit(0);
        }
        
        Scanner read = new Scanner(file);
               
        //go through all commands
        while (read.hasNext()) {
            
            String command = read.next();
            
            if (command.equalsIgnoreCase("add_train")) {
                
                system.addTrain(read.next(), read.nextInt());
                
            }else if (command.equalsIgnoreCase("add_passenger")) {
                
                system.addPassenger(read.next(), read.next());
                
            }else if (command.equalsIgnoreCase("book")) {
                
                system.bookSeat(read.next());
                
            }else if (command.equalsIgnoreCase("cancel")) {
                
                system.cancelBooking(read.next());
                
            }else if (command.equalsIgnoreCase("show_waiting")) {
                
                system.showWaitingTickets();
                
            }else if (command.equalsIgnoreCase("show_confirmed")) {
                
                system.showConfirmedTickets();
                
            }
        }
        
        system.close();
        
    }
    
}

package trainticketbookingsystem;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class TrainTicketBookingSystem {

    private Queue<Passenger> passengerQueue;
    private Stack<Ticket> confirmStack;
    private Queue<Ticket> waitingQueue;
    private Train train;
    private PrintWriter pen;

    public TrainTicketBookingSystem() throws FileNotFoundException {
        passengerQueue = new Queue<Passenger>();
        confirmStack = new Stack<Ticket>();
        waitingQueue = new Queue<Ticket>();

        File file = new File("output.txt");
        pen = new PrintWriter(file);

    }

    public void addTrain(String trainID, int capacity) {

        // Initialize a new Train object with the specified ID and capacity
        this.train = new Train(trainID, capacity);
        
        // Log the addition of the new train to the output file
        pen.println("Train " + train.getTrainID() + " with capacity "+ capacity + " has been added.");

    }

    public void addPassenger(String passengerID, String name) {
        
        // Create a new Passenger object with the given ID and name
        Passenger passenger = new Passenger(passengerID,name);
        
        // Enqueue the passenger into the passenger queue
        passengerQueue.enqueue(passenger);
        
        // Log the addition of the new passenger to the output file
        pen.println("Passenger " + passenger.getName() + " (ID: "+ passenger.getPassengerID() +") has been added to the system.");
    }

    public void bookSeat(String passengerID) {
        
        Passenger passenger = findPassengerByID(passengerID);
        if (passenger == null) {
            pen.println("Passenger " + passengerID + " not found.");
            return;
        }

        String bookingResult = train.bookSeat(passenger); // Attempt to book a seat
        pen.println(bookingResult); // Print booking result

        if (!bookingResult.contains("No seats")) {
            confirmStack.push(new Ticket(passenger, train)); // Confirm the booking if successful
        } else {
            waitingQueue.enqueue(new Ticket(passenger, train)); // Handle waiting ticket
        }
    }

    public void cancelBooking(String passengerID) {
        
        // Find the passenger in the system by their ID
        Passenger passenger = findPassengerByID(passengerID);
        
        // Remove the passenger's ticket from the confirmed stack
        removePassengerFromComfirmed(passenger);
        
        // Log the cancellation action with details to the output file
        pen.println(train.cancelSeat(passenger));
        
        if (!waitingQueue.isEmpty()) {
            bookSeat(waitingQueue.dequeue().getPassenger().getPassengerID());
        }
        pen.flush(); 
    }

    public void showConfirmedTickets() {
        
        pen.println("------------------CONFIRMED PASSENGER------------------");
        pen.println("| Passenger ID    | Name            | Train ID        |");
        pen.println("| --------------- | --------------- | --------------- |");
    
        // Temporary stack to hold tickets while displaying them
        Stack<Ticket> tempStack = new Stack<Ticket>();
    
        // Traverse the confirmed stack and display each ticket
        while (!confirmStack.isEmpty()) {
            Ticket ticket = confirmStack.pop();
            Passenger passenger = ticket.getPassenger();
        
            // Display each ticket in the specified format
            pen.printf("| %-15s | %-15s | %-15s |%n",
                        passenger.getPassengerID(),
                        passenger.getName(),
                        ticket.getTrain().getTrainID());
        
            // Push the ticket into a temporary stack to maintain order
            tempStack.push(ticket);
        }
    
        // Restore the original order of confirmStack
        while (!tempStack.isEmpty()) {
            confirmStack.push(tempStack.pop());
        }
        pen.flush(); 
    }

    public void showWaitingTickets() {
        pen.println("------------------WAITING PASSENGER--------------------");
        pen.println("| Passenger ID    | Name            | Train ID        |");
        pen.println("| --------------- | --------------- | --------------- |");
    
        // Temporary queue to hold tickets while displaying them
        Queue<Ticket> tempQueue = new Queue<>();
    
        // Traverse the waiting queue and display each ticket
        while (!waitingQueue.isEmpty()) {
            Ticket ticket = waitingQueue.dequeue();
            Passenger passenger = ticket.getPassenger();
            
            // Display each ticket in the specified format
             pen.printf("| %-15s | %-15s | %-15s |%n",
                        passenger.getPassengerID(),
                        passenger.getName(),
                        ticket.getTrain().getTrainID());
        
            // Enqueue the ticket into a temporary queue to maintain order
            tempQueue.enqueue(ticket);
        }
    
        // Restore the original order of waitingQueue
        while (!tempQueue.isEmpty()) {
            waitingQueue.enqueue(tempQueue.dequeue());
        }
        pen.flush(); 
    }

    public Passenger findPassengerByID(String passengerID) {
        
        // Return null if the queue is empty
        if (passengerQueue.isEmpty()) {
            return null;
        }

            Passenger wantedPassenger = null;
            Passenger currentPassenger;

            // Declare first passenger to know when to stop and not get stuck in an infinite loop
            Passenger firstPassenger = passengerQueue.dequeue();
            passengerQueue.enqueue(firstPassenger); 

                // Loop through the queue until the wanted passenger is found or we reach the start
                do {
                    
                    currentPassenger = passengerQueue.dequeue();
                    
                        // Check if the current passenger has the target ID
                        if (currentPassenger.getPassengerID().equals(passengerID)) {
                        wantedPassenger = currentPassenger; // Found target passenger
                        }
                    passengerQueue.enqueue(currentPassenger); // Re-enqueue current passenger
                } while (wantedPassenger == null && currentPassenger != firstPassenger);

        return wantedPassenger; // Return found passenger or null if not found
        
    }

    public Ticket removePassengerFromComfirmed(Passenger passenger) {
        
        // Check if the confirmStack is empty
        if (confirmStack.isEmpty()) {
        return null;
        }
        
        // Temporary stack to hold tickets while we search for the target ticket
        Stack<Ticket> tempStack = new Stack<Ticket>();
        
        Ticket tempTicket = null;
        Ticket ticket = null; // Holds the ticket to be removed if found
        
        // Loop through confirmStack to find the ticket for the specified passenger
        while (!confirmStack.isEmpty()) {
        
        tempTicket = confirmStack.pop();
        
            // Check if this ticket's passenger matches the one we're looking for
            if (tempTicket.getPassenger().equals(passenger)) {
                ticket = tempTicket;  // Found the target ticket
                continue; // Skip pushing it into tempStack to remove it from confirmStack
            }
        
        // If it's not the target ticket push it into tempStack for temporary storage
        tempStack.push(tempTicket);
        
        }
        
        // Restore the tickets back to confirmStack from tempStack
        while (!tempStack.isEmpty()) {
            
            tempTicket = tempStack.pop();
            
            confirmStack.push(tempTicket);
        }
        
        // Return the removed ticket
        return ticket;
    }
    public void close() {
        if (pen != null) {
            pen.flush(); // Flush before closing to ensure all data is written
            pen.close();
        }
    }

}
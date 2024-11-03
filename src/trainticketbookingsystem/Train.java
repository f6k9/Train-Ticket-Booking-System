
package trainticketbookingsystem;

public class Train {
    private String trainID;
    private int capacity;
    private int availableSeats;

    public Train(String trainID, int capacity) {
        this.trainID = trainID;
        this.capacity = capacity;
        this.availableSeats = capacity;
    }

    public String getTrainID() {
        return trainID;
    }
    
    
    public String bookSeat(Passenger passenger){
        if (availableSeats > 0) {
            int seatNumber = capacity - availableSeats + 1;
            availableSeats--;
            return "Booking confirmed for Passenger " + passenger.getName() + " (ID: " + passenger.getPassengerID() + ").";
        }
        return "No seats for " + passenger.getName() + " (ID: " + passenger.getPassengerID() + "), added to waiting list.";
    }
    
    public String cancelSeat(Passenger passenger){
        availableSeats++;
        return "Booking cancelled for Passenger " + passenger.getName() + " (ID: " + passenger.getPassengerID() + ").";
        
    }
}

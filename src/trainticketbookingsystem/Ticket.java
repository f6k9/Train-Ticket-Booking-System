
package trainticketbookingsystem;

public class Ticket {
    Passenger passenger;
    Train train;

    public Ticket(Passenger passenger, Train train) {
        this.passenger = passenger;
        this.train = train;
    }

    public Passenger getPassenger() {
        return passenger;
    }

    public Train getTrain() {
        return train;
    }

    @Override
    public String toString() {
        return "Ticket{" + "passenger =" + passenger + ", train =" + train + '}';
    }
    
    
}


package trainticketbookingsystem;

public class Passenger {
    private String passengerID;
    private String name;

    public Passenger(String passengerID, String name) {
        this.passengerID = passengerID;
        this.name = name;
    }

    public String getPassengerID() {
        return passengerID;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Passenger{" + "passengerID =" + passengerID + ", name =" + name + '}';
    }
    
}


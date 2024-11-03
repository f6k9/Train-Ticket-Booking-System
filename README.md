# 🚊 Train Ticket Booking System

A simple Java program that manages train reservations using custom data structures built from scratch instead of using standard Java libraries.

## 🌟 What It Does
* **Passenger Queue:** Uses a custom **Queue** to handle adding passengers in a fair, first-come, first-served order.
* **Confirmed Bookings:** Uses a custom **Stack** to store confirmed tickets.
* **Waiting List:** Uses a second **Queue** to hold passengers automatically when the train hits its maximum capacity.
* **Auto-Promotion:** If a passenger cancels their ticket, the system automatically removes them from the stack and promotes the next person from the waiting list into their seat.

## 📁 Project Structure
* `BookingSystem.java` — The main file that reads inputs (`input.txt`) and runs the program.
* `TrainTicketBookingSystem.java` — Controls the booking logic, waiting lists, and cancellations.
* `Queue.java` & `Stack.java` — Custom-built data structures created using memory-linked nodes (`Node.java`).
* `Train.java`, `Passenger.java`, & `Ticket.java` — Basic models that hold train details, passenger profiles, and receipts.

## 🚀 How to Run It

1. Make sure your command file (`input.txt`) is in the same folder as your code files.
2. Open your terminal and compile the project:
   ```bash
   javac trainticketbookingsystem/*.java

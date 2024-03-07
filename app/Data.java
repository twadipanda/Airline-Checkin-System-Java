package app;

import java.util.HashMap;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import exceptions.*;

public class Data {
  private String bookingsPath;
  private String flightsPath;

  public Data(String bookingsPath, String flightsPath) {
    this.bookingsPath = bookingsPath;
    this.flightsPath = flightsPath;
  }

  public Controller read() throws FileNotFoundException, InvalidBookingReference {
    HashMap<String, Booking> bookings = new HashMap<String, Booking>();
    ArrayList<Flight> flights = new ArrayList<Flight>();
    HashMap<String, String> csvData;
    HashMap<String, Flight> mappedFlights = new HashMap<String, Flight>();
    Pattern pattern = Pattern.compile("^[A-Z]{2}-\\d{3}$");
    
    try (Scanner scanner = new Scanner(new File(flightsPath))) {
      while (scanner.hasNextLine()) {
        Scanner rowScanner = new Scanner(scanner.nextLine());
        rowScanner.useDelimiter(",");
        csvData = new HashMap<String, String>();
        while (rowScanner.hasNext()) {
          if (!csvData.containsKey("dst")) {
            csvData.put("dst", rowScanner.next());
          }
          else if (!csvData.containsKey("carrier")) {
            csvData.put("carrier", rowScanner.next());
          }
          else if (!csvData.containsKey("capacity")) { 
            csvData.put("capacity", rowScanner.next());
          }
          else if (!csvData.containsKey("maxBagWeight")) {
            csvData.put("maxBagWeight", rowScanner.next());
          }
          else if (!csvData.containsKey("maxBagVolume")) { 
            csvData.put("maxBagVolume", rowScanner.next());
          }
          else {
            csvData.put("flightCode", rowScanner.next());
          }
        }
        Flight newFlight = new Flight(csvData.get("dst"), csvData.get("carrier"), Integer.parseInt(csvData.get("capacity")), Integer.parseInt(csvData.get("maxBagWeight")), Integer.parseInt(csvData.get("maxBagVolume")), csvData.get("flightCode"));
        flights.add(newFlight);
        mappedFlights.put(csvData.get("flightCode"), newFlight);
      }
    }
    catch (FileNotFoundException e) {
      System.out.println("File for flights not found...please make sure it is present!");
      throw e;
    }


    try (Scanner scanner = new Scanner(new File(bookingsPath))) {
      while (scanner.hasNextLine()) {
        Scanner rowScanner = new Scanner(scanner.nextLine());
        rowScanner.useDelimiter(",");
        csvData = new HashMap<String, String>();
        while (rowScanner.hasNext()) {
          if (!csvData.containsKey("ref")) {
            csvData.put("ref", rowScanner.next());
            if (!pattern.matcher(csvData.get("ref")).find()) {
              throw new InvalidBookingReference(csvData.get("ref") + ": is an invalid booking referene.");
            }
          }
          else if (!csvData.containsKey("fname")) {
            csvData.put("fname", rowScanner.next());
          }
          else if (!csvData.containsKey("lname")) {
            csvData.put("lname", rowScanner.next());
          }
          else {
            csvData.put("flightCode", rowScanner.next());
          }
        }
        Booking booking = new Booking(csvData.get("ref"), csvData.get("fname"), csvData.get("lname"), mappedFlights.get(csvData.get("flightCode")), csvData.get("flightCode"));
        bookings.put(csvData.get("lname")+csvData.get("ref"), booking);
      }
    }
    catch (FileNotFoundException e) {
      System.out.println("File for bookings not found...please make sure it is present!");
      throw e;
    }

    return new Controller(bookings, flights);

  }

  public void write() {
    System.out.println("writting report...");
  }
}

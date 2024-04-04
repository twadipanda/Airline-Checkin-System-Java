package app;

import java.util.HashMap;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.regex.Pattern;

import constants_and_singleton_classes.Enums.BookingClass;
import constants_and_singleton_classes.Enums.FlightClass;
import exceptions.*;
import models.Booking;
import models.Flight;

/**
 * The Data class reads booking and flight data from CSV files and constructs a Controller object.
 */
public class Data {
  private String bookingsPath;
  private String flightsPath;

  /**
   * Constructs a Data object with the specified paths to bookings and flights CSV files.
   * 
   * @param bookingsPath the path to the bookings CSV file
   * @param flightsPath the path to the flights CSV file
   */
  public Data(String bookingsPath, String flightsPath) {
    this.bookingsPath = bookingsPath;
    this.flightsPath = flightsPath;
  }
  
  /**
   * Reads booking and flight data from CSV files and constructs a Controller object.
   * 
   * @return a Controller object containing bookings and flights
   * @throws InvalidBookingReference if the booking reference is invalid
   * @throws IOException if an I/O error occurs
   */
  public Controller read() throws InvalidBookingReference, IOException {
      HashMap<String, Booking> bookings = new HashMap<String, Booking>();
      ArrayList<Flight> flights = new ArrayList<Flight>();
      HashMap<String, Flight> mappedFlights = new HashMap<String, Flight>();
      Pattern pattern = Pattern.compile("^[A-Z]{2}-\\d{3}$");

      // Read flights data
      try (
    		  InputStream flightStream = getClass().getClassLoader().getResourceAsStream(this.flightsPath);
    		  BufferedReader reader = new BufferedReader(new InputStreamReader(flightStream))) {
          String line;
          while ((line = reader.readLine()) != null) {
              String[] csvData = line.split(",");
              Flight newFlight = new Flight(csvData[0], csvData[1], Integer.parseInt(csvData[2]),
                      Integer.parseInt(csvData[3]), Integer.parseInt(csvData[4]), csvData[5],
                      FlightClass.valueOf(csvData[6]), Integer.parseInt(csvData[7]));
              flights.add(newFlight);
              mappedFlights.put(csvData[5], newFlight);
          }
      }
      catch (FileNotFoundException e) {
        System.out.println("File for flights not found...please make sure it is present!");
        throw e;
      }
      // Read bookings data
      try (		
    		  InputStream bookingStream = getClass().getClassLoader().getResourceAsStream(this.bookingsPath);
    		  BufferedReader reader = new BufferedReader(new InputStreamReader(bookingStream));) {
          String line;
          while ((line = reader.readLine()) != null) {
              String[] csvData = line.split(",");
              if (!pattern.matcher(csvData[0]).find()) {
                  throw new InvalidBookingReference(csvData[0] + ": is an invalid booking reference.");
              }
              Booking booking = new Booking(csvData[0], csvData[1], csvData[2], mappedFlights.get(csvData[3]),
                      csvData[3], BookingClass.valueOf(csvData[4]));
              bookings.put(csvData[2] + csvData[0], booking);
          }
      }
      catch (FileNotFoundException e) {
        System.out.println("File for bookings not found...please make sure it is present!");
        throw e;
      }
      return new Controller(bookings, flights);
  }

  /**
   * Writes a report.
   */
  public void write() {
      System.out.println("writing report...");
  }
  
}

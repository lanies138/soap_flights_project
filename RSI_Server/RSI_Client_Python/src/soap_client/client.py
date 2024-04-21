# WSDL
from zeep import Client

from src.model.flight import Flight
from src.model.ticket import Ticket
# Custom imports
from src.utils.helpers import change_frame

# SOAP client setup
wsdl_url = 'http://localhost:8080/ws/reservations.wsdl'
client = Client(wsdl=wsdl_url)


def get_flights(from_city, to_city, date_entry, flights_table, search_flights_frame, flights_frame):
  flights = client.service.GetFlightsByFromCityAndToCityAndDate(
    fromCity=from_city, toCity=to_city, date=date_entry)

  # Clear existing data in the flights table
  for row in flights_table.get_children():
    flights_table.delete(row)

  # Insert new data into the flights table
  for flight in flights:
    flights_table.insert("", "end", values=(
    flight["fromCity"], flight["toCity"], flight["date"], flight["time"]))

  change_frame(search_flights_frame, flights_frame)


def get_ticket_by_flight(flight):
  response = client.service.GetTicketByFlight(flight=flight.to_dict());

  flight_data = response['flight']
  flight_obj = Flight(flight_data['fromCity'], flight_data['toCity'],
                      flight_data['date'], flight_data['time'])

  ticket = Ticket(flight=flight_obj,
                  passengerName=response['passengerName'],
                  price=response['price'],
                  status=response['status'])
  return ticket


def update_ticket(ticket):
  ticket.passengerName = "Jan Kowalski" # TODO: temporary mocked
  client.service.UpdateTicket(1, ticket.to_dict())

import tkinter as tk
from tkinter import ttk
import fitz

# WSDL
from zeep import Client

# Custom imports
from src.model.flight import Flight
from src.model.ticket import Ticket
from src.utils.helpers import change_frame

# SOAP client setup
wsdl_url = 'http://localhost:8080/ws/reservations.wsdl'
client = Client(wsdl=wsdl_url)


def get_flights(from_city, to_city, date_entry, flights_table, flights_frame):
  flights = client.service.GetFlightsByFromCityAndToCityAndDate(
    fromCity=from_city, toCity=to_city, date=date_entry)

  # Clear existing data in the flights table
  for row in flights_table.get_children():
    flights_table.delete(row)

  # Insert new data into the flights table
  for flight in flights:
    flights_table.insert("", "end", values=(flight["id"], flight["fromCity"], flight["toCity"], flight["date"], flight["time"]))

  change_frame(flights_frame)


def get_ticket_by_flight(flight):
  response = client.service.GetTicketByFlight(flight=flight.to_dict());

  flight_data = response['flight']
  flight_obj = Flight(flight_data['id'], flight_data['fromCity'], flight_data['toCity'], flight_data['date'], flight_data['time'])

  ticket = Ticket(id=response['id'],
                  flight=flight_obj,
                  passengerName=response['passengerName'],
                  price=response['price'],
                  status=response['status'])
  return ticket


def update_ticket(ticket, successful_purchase_frame, search_flights_frame, pdf_frame):
  ticket.passengerName = "Jan Kowalski" # TODO: temporary mocked

  client.service.UpdateTicket(ticket.id, ticket.to_dict())

  successful_purchase_details = (f"You successfully booked a ticket for flight from {ticket.flight.fromCity} to {ticket.flight.toCity} on {ticket.flight.date} at {ticket.flight.time} for {ticket.price} euro.")

  successful_purchase_label = ttk.Label(successful_purchase_frame, text=successful_purchase_details)
  successful_purchase_label.grid(row=2, column=0, columnspan=2, sticky=tk.W + tk.E)

  download_purchase_button = ttk.Button(successful_purchase_frame, text="Download purchase in PDF", command=lambda: download_purchase_pdf(ticket.id, pdf_frame))
  download_purchase_button.grid(row=3, column=0, sticky=(tk.EW))

  back_to_search_flights_button = ttk.Button(successful_purchase_frame, text="Back To Search Flights", command=lambda: change_frame(search_flights_frame), style='redbutton.TButton')
  back_to_search_flights_button.grid(row=3, column=1, sticky=(tk.EW))
  change_frame(successful_purchase_frame)


def download_purchase_pdf(id, pdf_frame):
  response = client.service.GenerateTicketPDF(id=id);

  display_pdf(response, pdf_frame)

def display_pdf(pdf_content, pdf_frame):
  # Load PDF from binary data
  pdf_document = fitz.open("pdf", pdf_content)

  # Get the first page of the PDF
  page = pdf_document[0]

  zoom = 1
  mat = fitz.Matrix(zoom, zoom)
  pix = page.get_pixmap(matrix=mat)

  # Convert to a format Tkinter can use
  img = tk.PhotoImage(data=pix.tobytes("ppm"))

  # Create a canvas and add the image
  canvas = tk.Canvas(pdf_frame, width=pix.width, height=pix.height)
  canvas.pack()
  canvas.create_image(0, 0, image=img, anchor="nw")

  # This is necessary to keep a reference to the image
  canvas.image = img

  change_frame(pdf_frame)


def get_reservations(my_reservations_table, my_reservations_frame):
  reservations = client.service.GetTicketsByPassengerName(passengerName="Jan Kowalski")

  # Clear existing data in the flights table
  for row in my_reservations_table.get_children():
    my_reservations_table.delete(row)

  # Insert new data into the flights table
  for reservation in reservations:
    my_reservations_table.insert("", "end", values=(reservation.id, reservation.flight.fromCity, reservation.flight.toCity, reservation.flight.date, reservation.flight.time, reservation.price))

  change_frame(my_reservations_frame)
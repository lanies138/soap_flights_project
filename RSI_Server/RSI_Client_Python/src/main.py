# GUI
import tkinter as tk
from tkinter import ttk

# Custom imports
from utils.style import createCustomStyle
from model.flight import Flight
from soap_client.client import *


def on_row_select(event):
  selected_item = flights_table.focus()
  flight_details = flights_table.item(selected_item, "values")

  flight = Flight(flight_details[0], flight_details[1], flight_details[2], flight_details[3], flight_details[4])
  ticket = get_ticket_by_flight(flight)

  sale_details = f"Flight from {ticket.flight.fromCity} to {ticket.flight.toCity} on {ticket.flight.date} at {ticket.flight.time} starts from {ticket.price} euro."

  sale_label = ttk.Label(sale_frame, text=sale_details)
  sale_label.grid(row=2, column=0, columnspan=2, sticky=tk.W + tk.E)

  buy_ticket_button = ttk.Button(sale_frame, text="Buy", command=lambda: update_ticket(ticket, sale_frame, successful_purchase_frame, search_flights_frame))
  buy_ticket_button.grid(row=3, column=0, sticky=(tk.EW))

  cancel_ticket_button = ttk.Button(sale_frame, text="Cancel", command=lambda: change_frame(sale_frame, flights_frame), style='redbutton.TButton')
  cancel_ticket_button.grid(row=3, column=1, sticky=(tk.EW))

  change_frame(flights_frame, sale_frame)


# ------------------------------------------------------------------------------
# Set up the main application window
root = tk.Tk()
root.title("Airline Ticket Reservation")
root.geometry("1000x600")
# ------------------------------------------------------------------------------
createCustomStyle(root)
# ------------------------------------------------------------------------------
# Main frame for layout
main_frame = ttk.Frame(root, padding="3 3 12 12")
main_frame.grid(column=0, row=0, sticky=(tk.N, tk.W, tk.E, tk.S))
root.columnconfigure(0, weight=1)
root.rowconfigure(0, weight=1)

# ------------------------------------------------------------------------------
# Frame for search flights
search_flights_frame = ttk.Frame(main_frame, padding="10 10 10 10")
search_flights_frame.grid(column=0, row=0, sticky=(tk.N, tk.E, tk.S, tk.W))
search_flights_frame.columnconfigure(1, weight=1)

# Widgets for entering flight search criteria
from_city_label = ttk.Label(search_flights_frame, text="From City:")
from_city_label.grid(row=0, column=0, sticky=tk.W)
from_city_entry = ttk.Entry(search_flights_frame, width=20)
from_city_entry.grid(row=0, column=1, sticky=(tk.W, tk.E))

to_city_label = ttk.Label(search_flights_frame, text="To City:")
to_city_label.grid(row=1, column=0, sticky=tk.W)
to_city_entry = ttk.Entry(search_flights_frame, width=20)
to_city_entry.grid(row=1, column=1, sticky=(tk.W, tk.E))

date_label = ttk.Label(search_flights_frame, text="Date:")
date_label.grid(row=2, column=0, sticky=tk.W)
date_entry = ttk.Entry(search_flights_frame, width=20)
date_entry.grid(row=2, column=1, sticky=(tk.W, tk.E))

# Button to trigger the flight search
get_flights_button = ttk.Button(search_flights_frame, text="Search Flights", command=lambda: get_flights(from_city_entry.get(), to_city_entry.get(), date_entry.get(), flights_table, search_flights_frame, flights_frame))
get_flights_button.grid(row=3, column=0, columnspan=2, sticky=(tk.EW))

# ------------------------------------------------------------------------------
# Frame for flights table
flights_frame = ttk.Frame(main_frame, padding="10 10 10 10")
flights_frame.grid(column=0, row=0, sticky=(tk.N, tk.E, tk.S, tk.W))
flights_frame.columnconfigure(0, weight=1)
flights_frame.grid_remove()  # Initially hidden

# Table to display the flights
flights_table = ttk.Treeview(flights_frame, columns=("Id", "From City", "To City", "Date", "Time"), show="headings")
flights_table.heading("Id", text="Id")
flights_table.heading("From City", text="From City")
flights_table.heading("To City", text="To City")
flights_table.heading("Date", text="Date")
flights_table.heading("Time", text="Time")
flights_table.pack(fill=tk.BOTH, expand=True)
flights_table.bind("<<TreeviewSelect>>", on_row_select)

# ------------------------------------------------------------------------------
# Frame for sale
sale_frame = ttk.Frame(main_frame, padding="10 10 10 10")
sale_frame.grid(column=0, row=0, sticky=(tk.N, tk.E, tk.S, tk.W))
sale_frame.grid_columnconfigure(0, weight=1)
sale_frame.grid_columnconfigure(1, weight=1)
sale_frame.grid_remove()  # Initially hidden

# ------------------------------------------------------------------------------
# Frame for successful purchase
successful_purchase_frame = ttk.Frame(main_frame, padding="10 10 10 10")
successful_purchase_frame.grid(column=0, row=0, sticky=(tk.N, tk.E, tk.S, tk.W))
successful_purchase_frame.columnconfigure(0, weight=1)
successful_purchase_frame.grid_remove()  # Initially hidden

# ------------------------------------------------------------------------------
# Pre-filled values
from_city_entry.insert(0, 'Warsaw')
to_city_entry.insert(0, 'Berlin')
date_entry.insert(0, '23.04.2024')

# Start the GUI event loop
root.mainloop()

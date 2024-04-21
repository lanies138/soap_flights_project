# GUI
import tkinter as tk
from tkinter import ttk, messagebox

# WSDL
from zeep import Client

# Custom imports
from style import createCustomStyle

# SOAP client setup
wsdl_url = 'http://localhost:8080/ws/reservations.wsdl'
client = Client(wsdl=wsdl_url)

def get_flights():
  try:
    # Make the SOAP call with the user-provided data
    response = client.service.GetFlightsByFromCityAndToCityAndDate(fromCity=from_city_entry.get(), toCity=to_city_entry.get(), date=date_entry.get())
    # Update the table with the new data
    update_table(response)
    toggle_view()  # 2nd view
  except Exception as e:
    # If an error occurs, show an error message
    messagebox.showerror("Error", str(e))

def update_table(flights):
  # Clear existing data in the table
  for row in result_table.get_children():
    result_table.delete(row)
  # Insert new data into the table
  for flight in flights:
    result_table.insert("", "end", values=(flight["fromCity"], flight["toCity"], flight["date"], flight["time"]))

def toggle_view():
  # Hide input frame and show result frame
  content_frame.grid_remove()
  result_frame.grid()

def on_row_select(event):
  # Get selected item
  selected_item = result_table.focus()
  # Retrieve the record values
  flight_details = result_table.item(selected_item, "values")
  # Construct details string
  details = f"Flight from {flight_details[0]} to {flight_details[1]} on {flight_details[2]} at {flight_details[3]}"
  # Show message box with details
  messagebox.showinfo("Flight Details", details)

# Set up the main application window
root = tk.Tk()
root.title("Flight Lookup")

createCustomStyle(root)

# Main frame for layout
main_frame = ttk.Frame(root, padding="3 3 12 12") # 0nd view
main_frame.grid(column=0, row=0, sticky=(tk.N, tk.W, tk.E, tk.S))
root.columnconfigure(0, weight=1)
root.rowconfigure(0, weight=1)

# Frame for main content
content_frame = ttk.Frame(main_frame, padding="10 10 10 10") # 1nd view
content_frame.grid(column=0, row=0, sticky=(tk.N, tk.E, tk.S, tk.W))
content_frame.columnconfigure(1, weight=1)

# Widgets for entering flight search criteria
from_city_label = ttk.Label(content_frame, text="From City:")
from_city_label.grid(row=0, column=0, sticky=tk.W)
from_city_entry = ttk.Entry(content_frame, width=20)
from_city_entry.grid(row=0, column=1, sticky=(tk.W, tk.E))

to_city_label = ttk.Label(content_frame, text="To City:")
to_city_label.grid(row=1, column=0, sticky=tk.W)
to_city_entry = ttk.Entry(content_frame, width=20)
to_city_entry.grid(row=1, column=1, sticky=(tk.W, tk.E))

date_label = ttk.Label(content_frame, text="Date:")
date_label.grid(row=2, column=0, sticky=tk.W)
date_entry = ttk.Entry(content_frame, width=20)
date_entry.grid(row=2, column=1, sticky=(tk.W, tk.E))

# Button to trigger the flight search
get_flights_button = ttk.Button(content_frame, text="Search Flights", command=get_flights)
get_flights_button.grid(row=3, column=0, columnspan=2, sticky=(tk.EW))

# Frame for results
result_frame = ttk.Frame(main_frame, padding="10 10 10 10")
result_frame.grid(column=0, row=0, sticky=(tk.N, tk.E, tk.S, tk.W))
result_frame.columnconfigure(0, weight=1)
result_frame.grid_remove()  # Initially hidden

# Table to display the results
result_table = ttk.Treeview(result_frame, columns=("From City", "To City", "Date", "Time"), show="headings")
result_table.heading("From City", text="From City")
result_table.heading("To City", text="To City")
result_table.heading("Date", text="Date")
result_table.heading("Time", text="Time")
result_table.pack(fill=tk.BOTH, expand=True)
result_table.bind("<<TreeviewSelect>>", on_row_select) # 3rd view

# Pre-filled values
from_city_entry.insert(0, 'Warsaw')
to_city_entry.insert(0, 'Berlin')
date_entry.insert(0, '23.04.2024')

# Start the GUI event loop
root.mainloop()

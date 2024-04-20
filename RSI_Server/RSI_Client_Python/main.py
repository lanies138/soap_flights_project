import tkinter as tk
from tkinter import ttk
from zeep import Client

# Klient SOAP
wsdl_url = 'http://localhost:8080/ws/reservations.wsdl'
client = Client(wsdl=wsdl_url)

def update_flights():
  try:
    flights = client.service.GetFlights()
    for flight in flights:
      flights_listbox.insert(tk.END, flight)
  except Exception as e:
    flights_listbox.insert(tk.END, "Błąd: Nie można pobrać lotów")
    print(e)

# Utworzenie głównego okna GUI
root = tk.Tk()
root.title("Lista Lotów")

# Lista lotów w GUI
flights_listbox = tk.Listbox(root, height=15, width=50, border=0)
flights_listbox.pack(padx=10, pady=10)

# Przycisk do odświeżania listy lotów
refresh_button = ttk.Button(root, text="Odśwież listę lotów", command=update_flights)
refresh_button.pack(pady=10)

# Uruchomienie aplikacji
root.mainloop()



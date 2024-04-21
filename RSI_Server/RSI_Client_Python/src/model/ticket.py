class Ticket:
  def __init__(self, flight, passengerName, price, status):
    self.flight = flight
    self.passengerName = passengerName
    self.price = price
    self.status = status

  def to_dict(self):
    return {
      'flight': self.flight.to_dict(),
      'passengerName': self.passengerName,
      'price': self.price,
      'status': self.status
    }

class Ticket:
  def __init__(self, id, flight, passengerName, price, status):
    self.id = id
    self.flight = flight
    self.passengerName = passengerName
    self.price = price
    self.status = status

  def to_dict(self):
    return {
      'id': self.id,
      'flight': self.flight.to_dict(),
      'passengerName': self.passengerName,
      'price': self.price,
      'status': self.status
    }

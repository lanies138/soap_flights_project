class Flight:
  def __init__(self, id, fromCity, toCity, date, time):
    self.id = id
    self.fromCity = fromCity
    self.toCity = toCity
    self.date = date
    self.time = time

  def to_dict(self):
    return {
      'id': self.id,
      'fromCity': self.fromCity,
      'toCity': self.toCity,
      'date': self.date,
      'time': self.time
    }

class Flight:
  def __init__(self, fromCity, toCity, date, time):
    self.fromCity = fromCity
    self.toCity = toCity
    self.date = date
    self.time = time

  def to_dict(self):
    return {
      'fromCity': self.fromCity,
      'toCity': self.toCity,
      'date': self.date,
      'time': self.time
    }

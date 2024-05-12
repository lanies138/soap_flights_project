current_frame = None

def change_frame(new_frame):
  global current_frame
  if current_frame is not None:
    current_frame.grid_remove()
  new_frame.grid()
  current_frame = new_frame
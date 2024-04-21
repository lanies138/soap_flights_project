# Set a theme that supports color customization
from tkinter import ttk

def createCustomStyle(root):
  style = ttk.Style(root)
  style.theme_use('clam')
  style.configure('TButton', background='green', foreground='black')
import tkinter as tk
from PIL import Image, ImageTk
from timer.timer import open_timer_window

#main window
root = tk.Tk()
root.title("Egg Timer")

window_width = 500
window_height = 550
root.geometry(f"{window_width}x{window_height}")
root.resizable(False, False)  # Prevent resizing

#background color
background_color = "#F0E68C"  # Soft pastel yellow
root.configure(bg=background_color)

#button image
image_path = "utils/buton.png"
button_image = Image.open(image_path)
button_image = button_image.resize((130, 130), Image.Resampling.LANCZOS)
button_image = ImageTk.PhotoImage(button_image)


button_frame = tk.Frame(root, bg=background_color)
button_frame.pack(expand=True, pady=10)

#title
header = tk.Label(
    button_frame,
    text="🥚 Egg Timer ⏳",
    font=("Comic Sans MS", 24, "bold"),
    fg="#D35400",
    bg=background_color
)
header.grid(row=0, column=0, columnspan=3, pady=15)

#Choose how you want your egg
instruction_label = tk.Label(
    button_frame,
    text="Choose how you want your egg:",
    font=("Arial", 13),
    fg="#5D4037",
    bg=background_color
)
instruction_label.grid(row=1, column=0, columnspan=3, pady=5)

#button styling
def on_enter(e):
    e.widget.config(bg="#FFD54F")

def on_leave(e):
    e.widget.config(bg=background_color)

button_style = {
    "font": ("Helvetica", 12, "bold"),
    "image": button_image,
    "compound": "center",
    "borderwidth": 0,
    "highlightthickness": 0,
    "bg": background_color,
    "activebackground": background_color,
    "width": 130,
    "height": 130
}

# Soft Boiled button (5 minutes)
soft_button = tk.Button(
    button_frame,
    text="Soft\n Boiled\n (5 min)",
    command=lambda: open_timer_window(300, "Soft Boiled Egg"),
    **button_style
)
soft_button.grid(row=2, column=0, padx=10, pady=10, sticky="nsew")
soft_button.bind("<Enter>", on_enter)
soft_button.bind("<Leave>", on_leave)

# Medium Boiled button (7 minutes)
medium_button = tk.Button(
    button_frame,
    text="Medium\n Boiled\n (7 min)",
    command=lambda: open_timer_window(420, "Medium Boiled Egg"),
    **button_style
)
medium_button.grid(row=2, column=1, padx=10, pady=10, sticky="nsew")
medium_button.bind("<Enter>", on_enter)
medium_button.bind("<Leave>", on_leave)

# Hard Boiled button (10 minutes)
hard_button = tk.Button(
    button_frame,
    text="Hard\n Boiled\n (10 min)",
    command=lambda: open_timer_window(600, "Hard Boiled Egg"),
    **button_style
)
hard_button.grid(row=2, column=2, padx=10, pady=10, sticky="nsew")
hard_button.bind("<Enter>", on_enter)
hard_button.bind("<Leave>", on_leave)

#close button
close_button = tk.Button(
    button_frame,
    text="❌ Close",
    command=root.destroy,
    font=("Helvetica", 14, "bold"),
    fg="white",
    bg="#D32F2F",
    relief="flat",
    width=15,
    height=2
)
close_button.grid(row=3, column=0, columnspan=3, pady=15)

#columns expand evenly
for i in range(3):
    button_frame.columnconfigure(i, weight=1)


root.mainloop()

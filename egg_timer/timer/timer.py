import tkinter as tk
import time
import threading
from PIL import Image, ImageTk
from sounds.sound import play_alarm, stop_alarm_sound

def open_timer_window(duration, title):
#new window
    timer_window = tk.Toplevel()
    timer_window.title(title)
    timer_window.geometry("300x350")
    timer_window.config(bg="#F0F0F0")

#timer label
    timer_label = tk.Label(timer_window, text="Time left: --:--", font=("Helvetica", 18, "bold"), fg="#333", bg="#F0F0F0")
    timer_label.pack(pady=20)

#hourglass gif
    hourglass_gif = Image.open("utils/icon.gif")
    frames = []
    try:
        #resize each frame
        for frame in range(hourglass_gif.n_frames):
            hourglass_gif.seek(frame)
            frame_image = hourglass_gif.copy()
            frame_image.thumbnail((50, 50))
            frame_image = ImageTk.PhotoImage(frame_image)
            frames.append(frame_image)
    except Exception as e:
        print(f"Error loading GIF frames: {e}")


    hourglass_label = tk.Label(timer_window, bg="#F0F0F0")  #transparent background
    hourglass_label.pack(pady=10)


    def update_hourglass(frame_index):
        frame = frames[frame_index]
        hourglass_label.config(image=frame)
        frame_index = (frame_index + 1) % len(frames)
        hourglass_label.after(60, update_hourglass, frame_index)  #speed up

    stop_timer = threading.Event()  #controls the countdown
    alarm_playing = threading.Event()  #controls the alarm sound

    def countdown():
        remaining = duration
        while remaining >= 0 and not stop_timer.is_set():
            mins, secs = divmod(remaining, 60)
            timer_label.config(text=f"Time left: {mins:02d}:{secs:02d}")
            timer_window.update()
            time.sleep(1)
            remaining -= 1

        if not stop_timer.is_set():
            timer_label.config(text="Your egg is ready! 🥚🍳")
            play_alarm(alarm_playing)
            cancel_button.pack_forget()  #hide "I changed my mind" button when timer ends
            show_stop_alarm_button()
            hourglass_label.pack_forget()  #stop showing the hourglass once the timer ends

    def stop_alarm():
        stop_alarm_sound(alarm_playing)
        timer_window.destroy()

    def cancel_timer():
        stop_timer.set()
        timer_window.destroy()

    def show_stop_alarm_button():
        stop_button = tk.Button(timer_window, text="Stop Alarm", command=stop_alarm, bg="#FF4C4C", fg="white", font=("Arial", 12, "bold"), relief="raised", width=15, height=2)
        stop_button.pack(pady=10)

    # cancel button
    cancel_button = tk.Button(timer_window, text="I changed my mind", command=cancel_timer, bg="#FF9F00", fg="white", font=("Arial", 12, "bold"), relief="raised", width=15, height=2)
    cancel_button.pack(pady=10)

    #start the countdown thread
    threading.Thread(target=countdown).start()

    #start the hourglass animation
    update_hourglass(0)


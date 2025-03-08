from playsound import playsound
import threading
import time

import winsound


def play_alarm(alarm_playing):
    def sound_loop():
        while not alarm_playing.is_set():
            winsound.PlaySound(r"sounds\alarm.wav", winsound.SND_FILENAME)
            time.sleep(0.5)

    threading.Thread(target=sound_loop).start()


def stop_alarm_sound(alarm_playing):
    alarm_playing.set()

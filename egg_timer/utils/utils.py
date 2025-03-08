def format_time(seconds):
    mins, secs = divmod(seconds, 60)
    return f"{mins:02d}:{secs:02d}"

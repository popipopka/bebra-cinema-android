package it.bebra.cinema.common.util

fun formatDuration(duration: Int): String {
    val minutes: Int = duration % 60
    val hours: Int = (duration - minutes) / 60;

    if (hours == 0) {
        return "$minutes min"
    }

    if (minutes == 0) {
        return "$hours h"
    }

    return "$hours h $minutes min"
}
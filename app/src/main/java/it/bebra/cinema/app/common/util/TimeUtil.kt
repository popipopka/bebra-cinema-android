package it.bebra.cinema.app.common.util

import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale

fun formatDateTime(str: String, pattern: String): String? {
    val zonedDateTimeSystem = getSystemZonedDateTime(str)

    return zonedDateTimeSystem.format(DateTimeFormatter.ofPattern(pattern, Locale("ru", "RU")))
}

fun getSystemZonedDateTime(str: String): ZonedDateTime {
    val zonedDateTimeUtc = ZonedDateTime.parse(str, DateTimeFormatter.ISO_DATE_TIME)
    return zonedDateTimeUtc.withZoneSameInstant(ZoneId.systemDefault())
}

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

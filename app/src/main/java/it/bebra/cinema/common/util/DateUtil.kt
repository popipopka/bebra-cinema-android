package it.bebra.cinema.common.util

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.temporal.Temporal

fun formatDate(dateTime: LocalDateTime, pattern: String ): String? {
    return dateTime.format(DateTimeFormatter.ofPattern(pattern))
}
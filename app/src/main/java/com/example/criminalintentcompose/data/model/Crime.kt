package com.example.criminalintentcompose.data.model

import java.util.Date
import java.util.UUID

data class Crime(
  val id: UUID = UUID.randomUUID(),
  val description: String = "",
  val date: Date = Date(),
  val isSolved: Boolean = false
) {
  fun isNew(): Boolean {
    return description.isEmpty()
  }
}
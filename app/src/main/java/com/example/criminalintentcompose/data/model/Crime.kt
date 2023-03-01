package com.example.criminalintentcompose.data.model

import java.util.Date
import java.util.UUID

data class Crime(
  val id: UUID = UUID.fromString(NEW_CRIME_ID),
  val title: String = "",
  val date: Date = Date(),
  val isSolved: Boolean = false
)


const val NEW_CRIME_ID = "new crime"
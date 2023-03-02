package com.example.criminalintentcompose

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue


sealed class Screen {
  object List: Screen()
  object Detail: Screen()
  object History: Screen()
}

object CrimeRouter {
  var currentScreen: Screen by mutableStateOf(Screen.List)

  fun navigateTo(destination: Screen) {
    currentScreen = destination
  }
}
package com.example.criminalintentcompose

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.criminalintentcompose.data.model.Crime
import java.util.*

class MainVM: ViewModel() {
  val crimes: LiveData<List<Crime>> by lazy {
    generateCrimes()
  }


  fun onCrimeClicked(crime: Crime) {}
}


private fun generateCrimes(): LiveData<List<Crime>> {
  val crimes = mutableListOf<Crime>()
  for(i in 0 until 100) {
    crimes.add(
      Crime(
        id = UUID.randomUUID(),
        title = i.toString(),
        date = Date(),
        isSolved = Random().nextBoolean()
      )
    )
  }

  return MutableLiveData(crimes)
}
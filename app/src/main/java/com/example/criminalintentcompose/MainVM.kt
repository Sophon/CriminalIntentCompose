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
  private var _crimeEntry = MutableLiveData(Crime())
  var crimeEntry: LiveData<Crime> = _crimeEntry

  fun onCrimeClicked(crime: Crime) {
    _crimeEntry.value = crime
    //TODO: navigate to the detail screen
  }

  fun onCreateNewCrime() {
    _crimeEntry.value = Crime()
    //TODO: navigate to the detail screen
  }

  fun onCrimeChanged(crime: Crime) {
    //TODO: save the note to the DB
  }
}


private fun generateCrimes(): LiveData<List<Crime>> {
  val crimes = mutableListOf<Crime>()
  for(i in 0 until 100) {
    crimes.add(
      Crime(
        id = UUID.randomUUID(),
        description = i.toString(),
        date = Date(),
        isSolved = Random().nextBoolean()
      )
    )
  }

  return MutableLiveData(crimes)
}
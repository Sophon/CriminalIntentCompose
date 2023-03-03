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

  fun goToCrimeDetail(crime: Crime) {
    _crimeEntry.value = crime
    CrimeRouter.navigateTo(Screen.Detail)
  }

  fun createNewCrime() {
    _crimeEntry.value = Crime()
    CrimeRouter.navigateTo(Screen.Detail)
  }

  fun crimeEntryChanged(crime: Crime) {
    _crimeEntry.value = crime
  }

  fun saveCrime(crime: Crime) {
    //TODO: save to the DB
  }

  fun deleteCrime(crime: Crime) {
    //TODO: delete from the DB
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
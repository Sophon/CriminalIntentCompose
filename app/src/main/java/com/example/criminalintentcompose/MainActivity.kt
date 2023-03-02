package com.example.criminalintentcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.criminalintentcompose.ui.screens.CrimeDetailScreen
import com.example.criminalintentcompose.ui.screens.CrimeListScreen
import com.example.criminalintentcompose.ui.theme.CriminalIntentComposeTheme

class MainActivity : ComponentActivity() {
  private val vm: MainVM by viewModels()

  @OptIn(ExperimentalMaterialApi::class)
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
      CriminalIntentComposeTheme {
        MainActivityScreen(vm)
      }
    }
  }
}


@Composable
@ExperimentalMaterialApi
private fun MainActivityScreen(vm: MainVM) {
  Surface {
    when(CrimeRouter.currentScreen) {
      is Screen.List -> CrimeListScreen(vm)
      is Screen.Detail -> CrimeDetailScreen(vm)
      is Screen.History -> CrimeListScreen(vm) //TODO: implement
    }
  }
}
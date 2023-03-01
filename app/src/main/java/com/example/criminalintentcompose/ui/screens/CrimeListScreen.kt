package com.example.criminalintentcompose.ui.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.criminalintentcompose.MainVM
import com.example.criminalintentcompose.R
import com.example.criminalintentcompose.data.model.Crime
import com.example.criminalintentcompose.ui.components.CrimeItem
import java.util.*

@Composable
fun CrimeListScreen(vm: MainVM) {
  val crimes: List<Crime> by vm
    .crimes
    .observeAsState(listOf())

  Scaffold(
    topBar = {
      TopAppBar(title = { Text(text = stringResource(R.string.app_name))})
    },
    floatingActionButton = {
      FloatingActionButton(
        onClick = {},
        contentColor = MaterialTheme.colors.background
      ) {
        Icon(imageVector = Icons.Filled.Add, contentDescription = "Create crime button")
      }
    },
    floatingActionButtonPosition = FabPosition.End
  ) { padding ->
    CrimeList(
      crimes = crimes,
      onCrimeClicked = { vm.onCrimeClicked(it) },
      modifier = Modifier.padding(padding)
    )
  }
}


@Composable
private fun CrimeList(
  crimes: List<Crime>,
  onCrimeClicked: (Crime) -> Unit,
  modifier: Modifier = Modifier
) {
  LazyColumn(modifier = modifier) {
    items(count = crimes.size) { index ->
      CrimeItem(crime = crimes[index], onCrimeClicked = onCrimeClicked)
    }
  }
}


@Preview
@Composable
fun CrimeListPreview() {
  CrimeList(
    crimes = listOf(
      Crime(UUID.randomUUID(), "Title", Date(), true),
      Crime(UUID.randomUUID(), "Title", Date(), false),
      Crime(UUID.randomUUID(), "Title", Date(), true),
      Crime(UUID.randomUUID(), "Title", Date(), false),
    ),
    onCrimeClicked = {}
  )
}
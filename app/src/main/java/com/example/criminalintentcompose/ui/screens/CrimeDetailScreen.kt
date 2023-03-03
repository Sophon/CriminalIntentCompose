package com.example.criminalintentcompose.ui.screens

import android.text.format.DateFormat
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.criminalintentcompose.CrimeRouter
import com.example.criminalintentcompose.MainVM
import com.example.criminalintentcompose.R
import com.example.criminalintentcompose.Screen
import com.example.criminalintentcompose.data.model.Crime
import java.util.*

@Composable
fun CrimeDetailScreen(vm: MainVM) {

  val crimeEntry: Crime by vm.crimeEntry.observeAsState(Crime())
  val isInEditingMode = crimeEntry.isNew().not()

  BackHandler {
    CrimeRouter.navigateTo(Screen.List)
  }

  Scaffold(
    topBar = {
      AppBar(
        isInEditingMode = isInEditingMode,
        onBackClick = { CrimeRouter.navigateTo(Screen.List) },
        onSaveClick = { vm.saveCrime(crimeEntry) },
        onDeleteClick = { vm.deleteCrime(crimeEntry) }
      )
    }
  ) { padding ->
    CrimeDetail(
      crime = crimeEntry,
      onCrimeChanged = { newCrime ->
        vm.onCrimeEntryChanged(newCrime)
      },
      modifier = Modifier.padding(padding)
    )
  }
}


@Composable
private fun CrimeDetail(
  crime: Crime,
  onCrimeChanged: (Crime) -> Unit,
  modifier: Modifier = Modifier
) {
  Column(modifier = modifier.fillMaxSize()) {
    CrimeTextField(
      text = crime.description,
      label ="Title",
      onTextChange = { onCrimeChanged(crime.copy(description = it)) }
    )
    Button(
      onClick = { /*TODO*/ },
      modifier = Modifier
        .fillMaxWidth()
        .padding(8.dp)
    ) {
      val formattedDate = DateFormat.format(
        stringResource(id = R.string.full_date_format),
        crime.date
      )
      Text(text = formattedDate.toString())
    }
    Checkbox(
      checked = crime.isSolved,
      onCheckedChange = { onCrimeChanged(crime.copy(isSolved = it)) },
    )
  }
}

@Composable
private fun CrimeTextField(
  text: String,
  label: String,
  onTextChange: (String) -> Unit,
  modifier: Modifier = Modifier
) {
  TextField(
    value = text,
    onValueChange = onTextChange,
    label = { Text(text = label) },
    colors = TextFieldDefaults.textFieldColors(
      backgroundColor = MaterialTheme.colors.surface
    ),
    modifier = modifier
      .fillMaxWidth()
      .padding(horizontal = 8.dp)
  )
}

@Composable
private fun AppBar(
  isInEditingMode: Boolean,
  onBackClick: () -> Unit,
  onSaveClick: () -> Unit,
  onDeleteClick: () -> Unit
) {
  TopAppBar(
    title = {
      Text(stringResource(R.string.app_name))
    },
    navigationIcon = {
      IconButton(onClick = onBackClick) {
        Icon(
          imageVector = Icons.Default.ArrowBack,
          contentDescription = "Go back button"
        )
      }
    },
    actions = {
      IconButton(onClick = onSaveClick, enabled = isInEditingMode) {
        Icon(
          imageVector = Icons.Default.Check,
          contentDescription = "Save button"
        )
      }
      IconButton(onClick = onDeleteClick) {
        Icon(
          imageVector = Icons.Default.Delete,
          contentDescription = "Delete button"
        )
      }
    }
  )
}


//region PREVIEW
@Preview
@Composable
fun CrimeDetailPreview() {
  CrimeDetail(
    crime = Crime(UUID.randomUUID(), "Title", Date(), false),
    onCrimeChanged = {}
  )
}

@Preview
@Composable
fun ContentTextFieldPreview() {
  CrimeTextField(text = "text", label = "label", onTextChange = {})
}

@Preview
@Composable
fun AppBarPreview() {
  AppBar(false, {}, {}, {})
}
//endregion
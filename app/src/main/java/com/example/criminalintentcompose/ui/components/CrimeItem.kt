package com.example.criminalintentcompose.ui.components

import android.text.format.DateFormat
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.criminalintentcompose.R
import com.example.criminalintentcompose.data.model.Crime
import java.util.*

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CrimeItem(
  modifier: Modifier = Modifier,
  crime: Crime,
  onCrimeClicked: (Crime) -> Unit = {}
) {
  Card(
    shape = RoundedCornerShape(4.dp),
    modifier = modifier
      .padding(8.dp)
      .fillMaxWidth()
  ) {
    ListItem(
      text = { Text(text = crime.description, maxLines = 1) },
      secondaryText = {
        val formattedDate = DateFormat.format(
          stringResource(id = R.string.full_date_format),
          crime.date
        )
        Text(
          text = formattedDate.toString(),
          maxLines = 1
        )
      },
      trailing = {
        if(crime.isSolved) {
          Icon(painterResource(id = R.drawable.ic_solved), "solved icon")
        }
      },
      modifier = Modifier.clickable { onCrimeClicked(crime) }
    )
  }
}


//region PREVIEW
@Preview
@Composable
fun CrimeItemPreview() {
  CrimeItem(crime = Crime(UUID.randomUUID(), "Title", Date(), true))
}
//endregion
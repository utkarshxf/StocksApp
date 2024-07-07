import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.ArrowDownward
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun DisplayBar(leftText: String? = "null", middleText: String = "null", rightText: String = "null") {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.padding(16.dp)
    ) {
        Text(
            text = middleText,
            fontWeight = FontWeight.SemiBold,
            style = MaterialTheme.typography.bodyMedium
        )
        Icon(
            imageVector = Icons.Sharp.ArrowDownward,
            contentDescription = null,
            modifier = Modifier.size(16.dp),
        )
        Spacer(modifier = Modifier.height(8.dp)) // Add spacing between middle text and bar

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = leftText?:"", style = MaterialTheme.typography.bodySmall
            )
            Spacer(
                modifier = Modifier
                    .weight(1f) // Use weight to fill available space
                    .height(4.dp) // Adjust height as needed
                    .background(color = MaterialTheme.colorScheme.primary)
            )
            Text(
                text = rightText, style = MaterialTheme.typography.bodySmall
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DisplayBarPreview() {
    DisplayBar(leftText = "Left", middleText = "Middle", rightText = "Right")
}

package digital.dutton.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


data class Message(val name: String, val content: String, val time: String)

val mockMessages =
  listOf(
    Message("Alice", "Hey, how's it going?", "10:12 AM"),
    Message("Bob", "Wanna catch up later?", "9:58 AM"),
    Message("Charlie", "Sent you the docs", "Yesterday"),
  )

class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
      MaterialTheme(
        colorScheme = dynamicDarkColorScheme(LocalContext.current),
      ) { Surface(modifier = Modifier.fillMaxSize()) { MessageListScreen() } }
    }
  }
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun MessageListScreen() {
  Scaffold(topBar = { TopAppBar(title = { Text("Messages") }) }) { padding ->
    LazyColumn(contentPadding = padding) {
      items(mockMessages) { msg ->
        MessageListItem(msg)
        Divider()
      }
    }
  }
}

@Composable
fun MessageListItem(message: Message) {
  Row(
    modifier = Modifier.fillMaxWidth().clickable {}.padding(16.dp),
    verticalAlignment = Alignment.CenterVertically
  ) {
    Box(
      contentAlignment = Alignment.Center,
      modifier =
        Modifier.size(48.dp)
          .background(
            MaterialTheme.colorScheme.primary,
            shape = MaterialTheme.shapes.medium
          )
    ) { Text(message.name.first().toString(), color = MaterialTheme.colorScheme.onBackground) }
    Spacer(modifier = Modifier.width(12.dp))
    Column(modifier = Modifier.weight(1f)) {
      Text(message.name, fontWeight = FontWeight.Bold, fontSize = 16.sp)
      Text(message.content, color = Color.Gray, maxLines = 1)
    }
    Text(message.time, color = Color.Gray, fontSize = 12.sp)
  }
}

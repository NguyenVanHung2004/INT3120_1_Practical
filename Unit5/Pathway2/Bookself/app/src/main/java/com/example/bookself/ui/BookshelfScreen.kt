package com.example.bookself.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.bookself.model.Volume
import com.example.bookself.model.bestHttps

@Composable
fun BookshelfApp(vm: BookshelfViewModel = viewModel()) {
    val state = vm.uiState
    when (state) {
        is UiState.Loading -> Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
        is UiState.Error -> Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text("Lỗi: ${state.message}")
        }
        is UiState.Success -> Column(Modifier.fillMaxSize()) {
            SearchBar(
                text = vm.query,
                onTextChange = vm::updateQuery,
                onSearch = { vm.load() } // dùng vm.query hiện tại
            )
            BooksGrid(state.books)
        }
    }
}

@Composable
fun SearchBar(text: String, onTextChange: (String) -> Unit, onSearch: () -> Unit) {
    Row(
        Modifier.fillMaxWidth().padding(12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        OutlinedTextField(
            value = text,
            onValueChange = onTextChange,
            modifier = Modifier.weight(1f),
            singleLine = true,
            label = { Text("Tìm sách (Google Books)") }
        )
        Spacer(Modifier.width(8.dp))
        Button(onClick = onSearch) { Text("Search") }
    }
}

@Composable
private fun BooksGrid(books: List<Volume>) {
    LazyVerticalGrid(
        modifier = Modifier.fillMaxSize(),
        columns = GridCells.Adaptive(minSize = 140.dp),
        contentPadding = PaddingValues(12.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(books, key = { it.id }) { v ->
            ElevatedCard(  modifier = Modifier
                .fillMaxWidth()
                .height(260.dp)) {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(v.volumeInfo.imageLinks.bestHttps())
                        .crossfade(true)
                        .build(),
                    contentDescription = v.volumeInfo.title,
                    modifier = Modifier.fillMaxWidth().height(180.dp),
                    contentScale = ContentScale.FillWidth,
                )
                Text(
                    v.volumeInfo.title,
                    modifier = Modifier.padding(8.dp),
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}

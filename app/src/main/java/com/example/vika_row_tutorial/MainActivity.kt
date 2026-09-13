package com.example.vika_row_tutorial

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.vika_row_tutorial.ui.theme.VikaRowTutorialTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            VikaRowTutorialTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    SearchRowExample(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

/**
 * Пример Row, содержащего три разных типа контролов одновременно:
 *  1. OutlinedTextField — поле ввода текста (с иконкой поиска внутри)
 *  2. IconButton        — кнопка-иконка для очистки поля (появляется, когда есть текст)
 *  3. Button            — обычная кнопка действия "Найти"
 *
 * Расположение: [TextField][IconButton][Button]
 * TextField растягивается на всё доступное место (weight),
 * а IconButton и Button занимают свою естественную ширину.
 */
@Composable
fun SearchRowExample(modifier: Modifier = Modifier) {
    // Состояние (State) — текст, введённый пользователем.
    // remember { mutableStateOf("") } означает: хранить значение между рекомпозициями,
    // начальное значение — пустая строка.
    var query by remember { mutableStateOf("") }

    // Дополнительное состояние — показывать ли результат поиска.
    var lastSearch by remember { mutableStateOf("") }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = "Поиск: Row с TextField + IconButton + Button",
            style = MaterialTheme.typography.titleMedium
        )

        // ===== Наш Row с тремя контролами =====
        Row(
            modifier = Modifier.fillMaxWidth(),
            // Центрируем все элементы по вертикали — иначе TextField
            // и Button будут висеть на разной высоте.
            verticalAlignment = Alignment.CenterVertically
        ) {
            // --- 1. TextField ---
            OutlinedTextField(
                value = query,
                onValueChange = { query = it },
                modifier = Modifier
                    // weight(1f) заставляет поле занять всё свободное место,
                    // оставшееся после IconButton и Button.
                    .weight(1f),
                label = { Text("Что найти?") },
                singleLine = true,  // запрещаем перенос строки — поле однострочное
                leadingIcon = {
                    // Иконка поиска внутри поля (слева от текста)
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "Поиск"
                    )
                }
            )

            Spacer(modifier = Modifier.width(8.dp))

            // --- 2. IconButton (кнопка очистки) ---
            // Показываем её только если пользователь что-то ввёл.
            if (query.isNotEmpty()) {
                IconButton(onClick = { query = "" }) {
                    Icon(
                        imageVector = Icons.Default.Clear,
                        contentDescription = "Очистить",
                        tint = MaterialTheme.colorScheme.error
                    )
                }
                Spacer(modifier = Modifier.width(8.dp))
            }

            // --- 3. Button (кнопка действия) ---
            Button(
                onClick = { lastSearch = query },
                enabled = query.isNotBlank()  // кнопка неактивна, пока поле пустое
            ) {
                Text("Найти")
            }
        }

        // ===== Отображение результата =====
        if (lastSearch.isNotEmpty()) {
            Text(
                text = "Вы искали: «$lastSearch»",
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.primary
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SearchRowExamplePreview() {
    VikaRowTutorialTheme {
        SearchRowExample()
    }
}
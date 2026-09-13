package com.example.vika_row_tutorial

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
                    // Вызываем наш обучающий пример вместо Greeting
                    RowTutorial(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

/**
 * Обучающий пример использования компонента Row.
 *
 * Row — это контейнер, который размещает дочерние элементы
 * ГОРИЗОНТАЛЬНО (слева направо).
 *
 * Аналог в старом XML: LinearLayout с android:orientation="horizontal".
 *
 * Ключевые параметры Row:
 *  - modifier            — настройка самого контейнера (размер, отступы, фон)
 *  - horizontalArrangement — как расположить детей по горизонтали
 *  - verticalAlignment     — как выровнять детей по вертикали
 */
@Composable
fun RowTutorial(modifier: Modifier = Modifier) {
    // Column здесь не нужен — мы хотим показать несколько Row друг под другом,
    // поэтому используем вертикальный контейнер через простой Column.
    androidx.compose.foundation.layout.Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        // ===== Пример 1: базовый Row =====
        // Три цветных квадрата идут друг за другом слева направо.
        Text(
            text = "1. Базовый Row (по умолчанию — прижат к началу)",
            style = MaterialTheme.typography.titleMedium
        )
        Row {
            Box(
                modifier = Modifier
                    .size(60.dp)
                    .background(Color.Red, RoundedCornerShape(8.dp))
            )
            Spacer(modifier = Modifier.width(8.dp))
            Box(
                modifier = Modifier
                    .size(60.dp)
                    .background(Color.Green, RoundedCornerShape(8.dp))
            )
            Spacer(modifier = Modifier.width(8.dp))
            Box(
                modifier = Modifier
                    .size(60.dp)
                    .background(Color.Blue, RoundedCornerShape(8.dp))
            )
        }

        // ===== Пример 2: SpaceEvenly =====
        // Равные промежутки между элементами и по краям.
        Text(
            text = "2. Arrangement.SpaceEvenly (равные промежутки везде)",
            style = MaterialTheme.typography.titleMedium
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Box(
                modifier = Modifier
                    .size(60.dp)
                    .background(Color.Red, RoundedCornerShape(8.dp))
            )
            Box(
                modifier = Modifier
                    .size(60.dp)
                    .background(Color.Green, RoundedCornerShape(8.dp))
            )
            Box(
                modifier = Modifier
                    .size(60.dp)
                    .background(Color.Blue, RoundedCornerShape(8.dp))
            )
        }

        // ===== Пример 3: SpaceBetween =====
        // Первый у начала, последний у конца, равные промежутки между.
        Text(
            text = "3. Arrangement.SpaceBetween (крайние — по краям)",
            style = MaterialTheme.typography.titleMedium
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Box(
                modifier = Modifier
                    .size(60.dp)
                    .background(Color.Red, RoundedCornerShape(8.dp))
            )
            Box(
                modifier = Modifier
                    .size(60.dp)
                    .background(Color.Green, RoundedCornerShape(8.dp))
            )
            Box(
                modifier = Modifier
                    .size(60.dp)
                    .background(Color.Blue, RoundedCornerShape(8.dp))
            )
        }

        // ===== Пример 4: выравнивание по вертикали =====
        // Разные по высоте элементы выравниваются по центру по вертикали.
        Text(
            text = "4. verticalAlignment = CenterVertically",
            style = MaterialTheme.typography.titleMedium
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .background(Color.Red, RoundedCornerShape(8.dp))
            )
            Spacer(modifier = Modifier.width(8.dp))
            Box(
                modifier = Modifier
                    .size(80.dp)
                    .background(Color.Green, RoundedCornerShape(8.dp))
            )
            Spacer(modifier = Modifier.width(8.dp))
            Box(
                modifier = Modifier
                    .size(60.dp)
                    .background(Color.Blue, RoundedCornerShape(8.dp))
            )
        }

        // ===== Пример 5: weight — пропорциональное распределение =====
        // Первый элемент занимает 2/3 ширины, второй — 1/3.
        Text(
            text = "5. Modifier.weight() — пропорции 2 : 1",
            style = MaterialTheme.typography.titleMedium
        )
        Row(modifier = Modifier.fillMaxWidth()) {
            Box(
                modifier = Modifier
                    .weight(2f)
                    .height(60.dp)
                    .background(Color.Red, RoundedCornerShape(8.dp))
            )
            Spacer(modifier = Modifier.width(8.dp))
            Box(
                modifier = Modifier
                    .weight(1f)
                    .height(60.dp)
                    .background(Color.Blue, RoundedCornerShape(8.dp))
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RowTutorialPreview() {
    VikaRowTutorialTheme {
        RowTutorial()
    }
}
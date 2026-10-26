package com.rosto.photopro.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TextToolScreen(onApply: () -> Unit, onCancel: () -> Unit) {
    var text by remember { mutableStateOf("أضف نصك هنا") }
    var textSize by remember { mutableStateOf(32f) }
    var textColor by remember { mutableStateOf(Color.White) }
    var isBold by remember { mutableStateOf(false) }
    var isItalic by remember { mutableStateOf(false) }
    var selectedFont by remember { mutableStateOf(0) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(16.dp)
    ) {
        // Header
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("أداة النصوص", fontSize = 20.sp, fontWeight = FontWeight.Bold)
            IconButton(onClick = onCancel) {
                Icon(Icons.Default.Close, contentDescription = "إغلاق")
            }
        }

        // Text Input
        TextField(
            value = text,
            onValueChange = { text = it },
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .padding(bottom = 16.dp),
            placeholder = { Text("اكتب نصك هنا") },
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = MaterialTheme.colorScheme.surface
            )
        )

        // Text Size Control
        Text("حجم النص", fontSize = 14.sp)
        Slider(
            value = textSize,
            onValueChange = { textSize = it },
            valueRange = 10f..100f,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 12.dp)
        )
        Text("${textSize.toInt()}px", fontSize = 12.sp, color = MaterialTheme.colorScheme.primary)

        Spacer(modifier = Modifier.height(16.dp))

        // Text Styling
        Text("الأسلوب", fontSize = 14.sp, modifier = Modifier.padding(bottom = 8.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            FilterChip(
                selected = isBold,
                onClick = { isBold = !isBold },
                label = { 
                    Text(
                        "غامق",
                        fontWeight = if (isBold) FontWeight.Bold else FontWeight.Normal
                    )
                }
            )
            FilterChip(
                selected = isItalic,
                onClick = { isItalic = !isItalic },
                label = { 
                    Text(
                        "مائل",
                        fontStyle = if (isItalic) FontStyle.Italic else FontStyle.Normal
                    )
                }
            )
        }

        // Color Selection
        Text("اللون", fontSize = 14.sp, modifier = Modifier.padding(bottom = 8.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            listOf(Color.White, Color.Black, Color.Red, Color.Blue, Color.Yellow).forEach { color ->
                Box(
                    modifier = Modifier
                        .size(40.dp)
                        .background(color)
                        .padding(2.dp)
                        .background(if (textColor == color) Color.Gray else Color.Transparent),
                    contentAlignment = Alignment.Center
                ) {
                    if (textColor == color) {
                        Icon(
                            Icons.Default.Check,
                            contentDescription = null,
                            modifier = Modifier.size(20.dp)
                        )
                    }
                }
            }
        }

        // Font Selection
        Text("الخط", fontSize = 14.sp, modifier = Modifier.padding(bottom = 8.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            listOf("Default", "Monospace", "Serif").forEachIndexed { index, fontName ->
                FilterChip(
                    selected = selectedFont == index,
                    onClick = { selectedFont = index },
                    label = { Text(fontName) }
                )
            }
        }

        Spacer(modifier = Modifier.weight(1f))

        // Preview
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surface
            )
        ) {
            Text(
                text = text,
                modifier = Modifier.padding(16.dp),
                fontSize = textSize.sp,
                fontWeight = if (isBold) FontWeight.Bold else FontWeight.Normal,
                fontStyle = if (isItalic) FontStyle.Italic else FontStyle.Normal,
                color = textColor
            )
        }

        // Action Buttons
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            OutlinedButton(
                onClick = onCancel,
                modifier = Modifier.weight(1f)
            ) {
                Text("إلغاء")
            }

            Button(
                onClick = onApply,
                modifier = Modifier.weight(1f)
            ) {
                Icon(Icons.Default.Check, contentDescription = null, modifier = Modifier.size(18.dp))
                Spacer(modifier = Modifier.width(8.dp))
                Text("تطبيق")
            }
        }
    }
}

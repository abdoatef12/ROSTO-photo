package com.rosto.photopro.presentation.screens

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.ColorMatrix
import android.graphics.ColorMatrixColorFilter
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun EditorScreen(imageUri: String, navController: NavController) {
    val context = LocalContext.current
    var brightness by remember { mutableStateOf(0f) }
    var contrast by remember { mutableStateOf(1f) }
    var saturation by remember { mutableStateOf(1f) }
    var selectedFilter by remember { mutableStateOf(0) }
    var showAdvanced by remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        // Top Bar
        TopAppBar(
            title = { Text("محرر الصور") },
            navigationIcon = {
                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(Icons.Default.ArrowBack, contentDescription = "رجوع")
                }
            },
            actions = {
                IconButton(onClick = { /* Export */ }) {
                    Icon(Icons.Default.Download, contentDescription = "تنزيل")
                }
                IconButton(onClick = { /* Share */ }) {
                    Icon(Icons.Default.Share, contentDescription = "مشاركة")
                }
            },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = MaterialTheme.colorScheme.surface
            )
        )

        // Image Preview
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(400.dp)
                .background(MaterialTheme.colorScheme.background),
            contentAlignment = Alignment.Center
        ) {
            AsyncImage(
                model = imageUri,
                contentDescription = "Selected Image",
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                contentScale = ContentScale.Fit
            )
        }

        // Edit Controls
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            // Filter Tabs
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .horizontalScroll(rememberScrollState())
                    .padding(bottom = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                repeat(12) { index ->
                    FilterChip(
                        selected = selectedFilter == index,
                        onClick = { selectedFilter = index },
                        label = { Text(getFilterName(index)) }
                    )
                }
            }

            // Brightness Control
            ControlSlider(
                label = "السطوع",
                value = brightness,
                onValueChange = { brightness = it },
                range = -100f..100f
            )

            // Contrast Control
            ControlSlider(
                label = "التباين",
                value = contrast,
                onValueChange = { contrast = it },
                range = 0.5f..2f
            )

            // Saturation Control
            ControlSlider(
                label = "التشبع",
                value = saturation,
                onValueChange = { saturation = it },
                range = 0f..2f
            )

            // Advanced Controls
            Button(
                onClick = { showAdvanced = !showAdvanced },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.surface
                )
            ) {
                Icon(
                    imageVector = if (showAdvanced) Icons.Default.ExpandLess else Icons.Default.ExpandMore,
                    contentDescription = null,
                    modifier = Modifier.size(20.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text("أدوات متقدمة")
            }

            if (showAdvanced) {
                Spacer(modifier = Modifier.height(8.dp))
                
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .horizontalScroll(rememberScrollState()),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    AdvancedToolButton("درجة الحرارة")
                    AdvancedToolButton("الصبغة")
                    AdvancedToolButton("الوضوح")
                    AdvancedToolButton("الظلال")
                    AdvancedToolButton("الإضاءات")
                    AdvancedToolButton("منحنيات")
                }
            }
        }

        // Bottom Action Buttons
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            OutlinedButton(
                onClick = { /* Undo */ },
                modifier = Modifier.weight(1f)
            ) {
                Icon(Icons.Default.Undo, contentDescription = null, modifier = Modifier.size(18.dp))
                Spacer(modifier = Modifier.width(4.dp))
                Text("تراجع")
            }

            OutlinedButton(
                onClick = { /* Redo */ },
                modifier = Modifier.weight(1f)
            ) {
                Icon(Icons.Default.Redo, contentDescription = null, modifier = Modifier.size(18.dp))
                Spacer(modifier = Modifier.width(4.dp))
                Text("إعادة")
            }

            Button(
                onClick = { /* Save */ },
                modifier = Modifier.weight(1f)
            ) {
                Icon(Icons.Default.SaveAlt, contentDescription = null, modifier = Modifier.size(18.dp))
                Spacer(modifier = Modifier.width(4.dp))
                Text("حفظ")
            }
        }
    }
}

@Composable
fun ControlSlider(
    label: String,
    value: Float,
    onValueChange: (Float) -> Unit,
    range: ClosedFloatingPointRange<Float>,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(label, color = MaterialTheme.colorScheme.onBackground)
            Text(
                "%.1f".format(value),
                color = MaterialTheme.colorScheme.primary,
                style = MaterialTheme.typography.labelSmall
            )
        }
        
        Slider(
            value = value,
            onValueChange = onValueChange,
            valueRange = range,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Composable
fun AdvancedToolButton(label: String) {
    OutlinedButton(
        onClick = { /* Handle tool */ },
        modifier = Modifier
            .width(120.dp)
            .height(48.dp),
        contentPadding = PaddingValues(0.dp)
    ) {
        Text(label, fontSize = MaterialTheme.typography.labelSmall.fontSize)
    }
}

fun getFilterName(index: Int): String = when (index) {
    0 -> "بدون فلتر"
    1 -> "رمادي"
    2 -> "دافئ"
    3 -> "بارد"
    4 -> "حاد"
    5 -> "ناعم"
    6 -> "Vintage"
    7 -> "Neon"
    8 -> "Cyberpunk"
    9 -> "Dreamy"
    10 -> "HDR"
    11 -> "Glow"
    else -> "فلتر"
}

package com.example.photogallery

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.example.photogallery.ui.theme.PhotoGalleryTheme
import androidx.compose.ui.unit.dp
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.foundation.Image
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource



class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PhotoGalleryTheme {
                    PhotoGallery()
                }
            }
        }
    }


@Composable
fun PhotoGallery() {
    val photos: List<Int> = listOf(
        R.drawable.eevee,
        R.drawable.espeon,
        R.drawable.umbreon,
        R.drawable.pawmi,
        R.drawable.steelix,
        R.drawable.teddiursa
    )
    LazyVerticalStaggeredGrid(
        columns = StaggeredGridCells.Adaptive(minSize = 120.dp),
        modifier = Modifier.fillMaxSize(),
    ) {
        items(photos) { imgRes: Int ->
            ZoomableImage(imgRes)
        }
    }
}
@Composable
fun ZoomableImage(@DrawableRes imageRes: Int) {
    var isZoomed by remember { mutableStateOf(false) }
    val scale by animateFloatAsState(
        targetValue = if (isZoomed) 1.2f else 1f
    )
    Box(
        modifier = Modifier
            .padding(4.dp)
            .clickable { isZoomed = !isZoomed }
            .graphicsLayer {
                scaleX = scale
                scaleY = scale
            }
    ) {
        Image(
            painter = painterResource(id = imageRes),
            contentDescription = null,
            contentScale = ContentScale.Crop
        )
    }
}
@Preview(showBackground = true)
@Composable
fun GalleryPreview() {
    PhotoGalleryTheme {
        PhotoGallery()
    }
}
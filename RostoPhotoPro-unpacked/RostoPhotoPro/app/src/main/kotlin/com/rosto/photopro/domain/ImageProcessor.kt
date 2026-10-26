package com.rosto.photopro.domain

import android.graphics.*
import kotlin.math.cos
import kotlin.math.sin

object ImageProcessor {
    
    fun adjustBrightness(bitmap: Bitmap, brightness: Float): Bitmap {
        val result = bitmap.copy(bitmap.config, true)
        val canvas = Canvas(result)
        val paint = Paint()
        
        val colorMatrix = ColorMatrix()
        colorMatrix.setScale(1f, 1f, 1f, 1f)
        
        // Adjust brightness
        val brightValue = (brightness * 255) / 100
        colorMatrix.postTranslate(brightValue, brightValue, brightValue, 0f)
        
        paint.colorFilter = ColorMatrixColorFilter(colorMatrix)
        canvas.drawBitmap(bitmap, 0f, 0f, paint)
        
        return result
    }
    
    fun adjustContrast(bitmap: Bitmap, contrast: Float): Bitmap {
        val result = bitmap.copy(bitmap.config, true)
        val canvas = Canvas(result)
        val paint = Paint()
        
        val colorMatrix = ColorMatrix()
        val scale = contrast
        val translate = (1 - scale) / 2
        
        colorMatrix.set(floatArrayOf(
            scale, 0f, 0f, 0f, translate * 255,
            0f, scale, 0f, 0f, translate * 255,
            0f, 0f, scale, 0f, translate * 255,
            0f, 0f, 0f, 1f, 0f
        ))
        
        paint.colorFilter = ColorMatrixColorFilter(colorMatrix)
        canvas.drawBitmap(bitmap, 0f, 0f, paint)
        
        return result
    }
    
    fun adjustSaturation(bitmap: Bitmap, saturation: Float): Bitmap {
        val result = bitmap.copy(bitmap.config, true)
        val canvas = Canvas(result)
        val paint = Paint()
        
        val colorMatrix = ColorMatrix()
        colorMatrix.setSaturation(saturation)
        
        paint.colorFilter = ColorMatrixColorFilter(colorMatrix)
        canvas.drawBitmap(bitmap, 0f, 0f, paint)
        
        return result
    }
    
    fun cropImage(bitmap: Bitmap, x: Int, y: Int, width: Int, height: Int): Bitmap {
        return Bitmap.createBitmap(bitmap, x, y, width, height)
    }
    
    fun rotateImage(bitmap: Bitmap, degrees: Float): Bitmap {
        val matrix = Matrix()
        matrix.postRotate(degrees)
        return Bitmap.createBitmap(bitmap, 0, 0, bitmap.width, bitmap.height, matrix, true)
    }
    
    fun flipImageHorizontal(bitmap: Bitmap): Bitmap {
        val matrix = Matrix()
        matrix.preScale(-1f, 1f)
        return Bitmap.createBitmap(bitmap, 0, 0, bitmap.width, bitmap.height, matrix, true)
    }
    
    fun flipImageVertical(bitmap: Bitmap): Bitmap {
        val matrix = Matrix()
        matrix.preScale(1f, -1f)
        return Bitmap.createBitmap(bitmap, 0, 0, bitmap.width, bitmap.height, matrix, true)
    }
    
    fun resizeImage(bitmap: Bitmap, width: Int, height: Int): Bitmap {
        return Bitmap.createScaledBitmap(bitmap, width, height, true)
    }
    
    // Filter: Grayscale
    fun applyGrayscale(bitmap: Bitmap): Bitmap {
        val result = bitmap.copy(bitmap.config, true)
        val canvas = Canvas(result)
        val paint = Paint()
        
        val colorMatrix = ColorMatrix()
        colorMatrix.setSaturation(0f)
        
        paint.colorFilter = ColorMatrixColorFilter(colorMatrix)
        canvas.drawBitmap(bitmap, 0f, 0f, paint)
        
        return result
    }
    
    // Filter: Warm
    fun applyWarmFilter(bitmap: Bitmap): Bitmap {
        val result = bitmap.copy(bitmap.config, true)
        val pixels = IntArray(result.width * result.height)
        result.getPixels(pixels, 0, result.width, 0, 0, result.width, result.height)
        
        for (i in pixels.indices) {
            val pixel = pixels[i]
            val a = (pixel shr 24) and 0xff
            var r = (pixel shr 16) and 0xff
            var g = (pixel shr 8) and 0xff
            var b = pixel and 0xff
            
            r = (r * 1.1).toInt().coerceIn(0, 255)
            g = (g * 1.05).toInt().coerceIn(0, 255)
            
            pixels[i] = (a shl 24) or (r shl 16) or (g shl 8) or b
        }
        
        result.setPixels(pixels, 0, result.width, 0, 0, result.width, result.height)
        return result
    }
    
    // Filter: Cold
    fun applyColdFilter(bitmap: Bitmap): Bitmap {
        val result = bitmap.copy(bitmap.config, true)
        val pixels = IntArray(result.width * result.height)
        result.getPixels(pixels, 0, result.width, 0, 0, result.width, result.height)
        
        for (i in pixels.indices) {
            val pixel = pixels[i]
            val a = (pixel shr 24) and 0xff
            var r = (pixel shr 16) and 0xff
            var g = (pixel shr 8) and 0xff
            var b = pixel and 0xff
            
            r = (r * 0.9).toInt().coerceIn(0, 255)
            b = (b * 1.1).toInt().coerceIn(0, 255)
            
            pixels[i] = (a shl 24) or (r shl 16) or (g shl 8) or b
        }
        
        result.setPixels(pixels, 0, result.width, 0, 0, result.width, result.height)
        return result
    }
    
    // Filter: Sepia
    fun applySepiaFilter(bitmap: Bitmap): Bitmap {
        val result = bitmap.copy(bitmap.config, true)
        val canvas = Canvas(result)
        val paint = Paint()
        
        val colorMatrix = ColorMatrix(floatArrayOf(
            0.272f, 0.534f, 0.131f, 0f, 0f,
            0.349f, 0.686f, 0.168f, 0f, 0f,
            0.393f, 0.769f, 0.189f, 0f, 0f,
            0f, 0f, 0f, 1f, 0f
        ))
        
        paint.colorFilter = ColorMatrixColorFilter(colorMatrix)
        canvas.drawBitmap(bitmap, 0f, 0f, paint)
        
        return result
    }
    
    // Filter: Invert
    fun applyInvertFilter(bitmap: Bitmap): Bitmap {
        val result = bitmap.copy(bitmap.config, true)
        val pixels = IntArray(result.width * result.height)
        result.getPixels(pixels, 0, result.width, 0, 0, result.width, result.height)
        
        for (i in pixels.indices) {
            val pixel = pixels[i]
            val a = (pixel shr 24) and 0xff
            val r = 255 - ((pixel shr 16) and 0xff)
            val g = 255 - ((pixel shr 8) and 0xff)
            val b = 255 - (pixel and 0xff)
            
            pixels[i] = (a shl 24) or (r shl 16) or (g shl 8) or b
        }
        
        result.setPixels(pixels, 0, result.width, 0, 0, result.width, result.height)
        return result
    }
    
    // Filter: Sharpen
    fun applySharpenFilter(bitmap: Bitmap): Bitmap {
        val result = bitmap.copy(bitmap.config, true)
        val kernel = floatArrayOf(
            0f, -1f, 0f,
            -1f, 5f, -1f,
            0f, -1f, 0f
        )
        
        // Apply convolution kernel
        return applyConvolutionKernel(result, kernel, 3, 3)
    }
    
    // Filter: Blur
    fun applyBlurFilter(bitmap: Bitmap, radius: Int = 10): Bitmap {
        val result = bitmap.copy(bitmap.config, true)
        val pixels = IntArray(result.width * result.height)
        result.getPixels(pixels, 0, result.width, 0, 0, result.width, result.height)
        
        val newPixels = IntArray(pixels.size)
        val width = result.width
        val height = result.height
        
        for (y in 0 until height) {
            for (x in 0 until width) {
                var r = 0
                var g = 0
                var b = 0
                var count = 0
                
                for (ky in -radius..radius) {
                    for (kx in -radius..radius) {
                        val nx = (x + kx).coerceIn(0, width - 1)
                        val ny = (y + ky).coerceIn(0, height - 1)
                        val pixel = pixels[ny * width + nx]
                        
                        r += (pixel shr 16) and 0xff
                        g += (pixel shr 8) and 0xff
                        b += pixel and 0xff
                        count++
                    }
                }
                
                val a = (pixels[y * width + x] shr 24) and 0xff
                r /= count
                g /= count
                b /= count
                
                newPixels[y * width + x] = (a shl 24) or (r shl 16) or (g shl 8) or b
            }
        }
        
        result.setPixels(newPixels, 0, width, 0, 0, width, height)
        return result
    }
    
    private fun applyConvolutionKernel(
        bitmap: Bitmap,
        kernel: FloatArray,
        kernelWidth: Int,
        kernelHeight: Int
    ): Bitmap {
        val result = bitmap.copy(bitmap.config, true)
        val pixels = IntArray(result.width * result.height)
        result.getPixels(pixels, 0, result.width, 0, 0, result.width, result.height)
        
        val newPixels = IntArray(pixels.size)
        val width = result.width
        val height = result.height
        val kernelOffset = kernelHeight / 2
        
        for (y in 0 until height) {
            for (x in 0 until width) {
                var r = 0f
                var g = 0f
                var b = 0f
                
                for (ky in 0 until kernelHeight) {
                    for (kx in 0 until kernelWidth) {
                        val nx = (x + kx - kernelOffset).coerceIn(0, width - 1)
                        val ny = (y + ky - kernelOffset).coerceIn(0, height - 1)
                        val pixel = pixels[ny * width + nx]
                        val kernelValue = kernel[ky * kernelWidth + kx]
                        
                        r += ((pixel shr 16) and 0xff) * kernelValue
                        g += ((pixel shr 8) and 0xff) * kernelValue
                        b += (pixel and 0xff) * kernelValue
                    }
                }
                
                val a = (pixels[y * width + x] shr 24) and 0xff
                val rr = r.toInt().coerceIn(0, 255)
                val gg = g.toInt().coerceIn(0, 255)
                val bb = b.toInt().coerceIn(0, 255)
                
                newPixels[y * width + x] = (a shl 24) or (rr shl 16) or (gg shl 8) or bb
            }
        }
        
        result.setPixels(newPixels, 0, width, 0, 0, width, height)
        return result
    }
}

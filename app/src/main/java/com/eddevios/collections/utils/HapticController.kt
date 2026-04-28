package com.eddevios.collections.utils

import android.content.Context
import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator

object HapticController {

    /* ---------- helpers ---------- */

    private fun vibrator(context: Context): Vibrator? =
        context.getSystemService(Context.VIBRATOR_SERVICE) as? Vibrator

    fun isSupported(context: Context): Boolean =
        vibrator(context)?.hasVibrator() == true

    /* ---------- disparo simple ---------- */

    fun oneShot(
        context: Context,
        duration: Long = 70L,
        amplitude: Int = VibrationEffect.DEFAULT_AMPLITUDE
    ) {
        vibrator(context)?.let { v ->
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                v.vibrate(VibrationEffect.createOneShot(duration, amplitude))
            } else {
                @Suppress("DEPRECATION")
                v.vibrate(duration)
            }
        }
    }

    /* ---------- patrón genérico ---------- */

    fun vibratePattern(
        context: Context,
        pattern: LongArray,
        repeat: Int = -1
    ) {
        vibrator(context)?.let { v ->
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                v.vibrate(VibrationEffect.createWaveform(pattern, repeat))
            } else {
                @Suppress("DEPRECATION")
                v.vibrate(pattern, repeat)
            }
        }
    }

    /* ---------- patrones enriquecidos ---------- */

    fun rampAndHit(context: Context) {
        val timings = longArrayOf(0,10,20,30,40,50,60,70,80,90,100, 300, 1000)
        val amplitudes = intArrayOf(0,25,50,75,100,125,150,175,200,225,255, 255,0)
        vibrator(context)?.let { v ->
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                v.vibrate(VibrationEffect.createWaveform(timings, amplitudes, -1))
            } else {
                @Suppress("DEPRECATION") v.vibrate(timings, -1)
            }
        }
    }

    fun slowRiseAndClick(context: Context) {
        vibrator(context)?.let { v ->
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                val effect = VibrationEffect
                    .startComposition()
                    .addPrimitive(VibrationEffect.Composition.PRIMITIVE_SLOW_RISE, 1f)
                    .addPrimitive(VibrationEffect.Composition.PRIMITIVE_CLICK, 1f)
                    .compose()
                v.vibrate(effect)
            } else {
                rampAndHit(context)              // fallback pre-API 30
            }
        }
    }
}

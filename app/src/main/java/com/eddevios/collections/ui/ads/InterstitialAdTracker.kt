package com.eddevios.collections.ads
import com.eddevios.collections.utils.AppConfig

object InterstitialAdTracker {
    private var interstitialCounter = 0
    private var lastInterstitialTime = 0L

    fun shouldShowAd(): Boolean {
        interstitialCounter++
        val currentTime = System.currentTimeMillis()
        val timeSinceLastAd = currentTime - lastInterstitialTime
        val shouldShow = interstitialCounter % 3 == 0 && timeSinceLastAd >= AppConfig.INTERSTITIAL_TIME_INTERVAL
        if (shouldShow) {
            lastInterstitialTime = currentTime
        }
        return shouldShow
    }

    fun reset() {
        interstitialCounter = 0
        lastInterstitialTime = 0L
    }
}

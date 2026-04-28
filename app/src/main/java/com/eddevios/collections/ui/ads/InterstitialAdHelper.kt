package com.eddevios.collections.ads

import android.app.Activity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import com.eddevios.collections.utils.AppConfig

@Composable
fun ShowInterstitialIfNeeded() {
    val context = LocalContext.current
    val activity = context as? Activity

    LaunchedEffect(Unit) {
        if (InterstitialAdTracker.shouldShowAd()) {
            AdMobManager.loadInterstitial(
                context = context,
                adUnitId = AppConfig.INTERSTITIAL_AD_ID
            ) {
                activity?.let {
                    AdMobManager.showInterstitial(it)
                }
            }
        }
    }
}
package com.eddevios.collections.ads

import android.app.Activity
import android.content.Context
import android.util.Log
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback
import androidx.core.view.WindowCompat
import com.google.android.gms.ads.AdError
import com.google.android.gms.ads.FullScreenContentCallback

object AdMobManager {

    private var interstitialAd: InterstitialAd? = null

    fun loadInterstitial(context: Context, adUnitId: String, onAdLoaded: (() -> Unit)? = null) {
        val adRequest = AdRequest.Builder().build()

        InterstitialAd.load(context, adUnitId, adRequest, object : InterstitialAdLoadCallback() {
            override fun onAdLoaded(ad: InterstitialAd) {
                interstitialAd = ad
                onAdLoaded?.invoke()
            }

            override fun onAdFailedToLoad(error: com.google.android.gms.ads.LoadAdError) {
                Log.d("AdMob", "Error cargando interstitial: ${error.message}")
                interstitialAd = null
            }
        })
    }
/*
    fun showInterstitial(activity: Activity, onAdClosed: (() -> Unit)? = null) {
        interstitialAd?.let { ad ->
            ad.fullScreenContentCallback = object : com.google.android.gms.ads.FullScreenContentCallback() {
                override fun onAdDismissedFullScreenContent() {
                    interstitialAd = null
                    onAdClosed?.invoke()
                }

                override fun onAdFailedToShowFullScreenContent(adError: com.google.android.gms.ads.AdError) {
                    Log.d("AdMob", "Error mostrando interstitial: ${adError.message}")
                    interstitialAd = null
                    onAdClosed?.invoke()
                }
            }

            ad.show(activity)
        } ?: onAdClosed?.invoke()
    }
*/
    fun showInterstitial(activity: Activity, onAdClosed: (() -> Unit)? = null) {
        interstitialAd?.let { ad ->
            // 1. Ajustar decor fitsSystemWindows para que el close button no quede oculto
            WindowCompat.setDecorFitsSystemWindows(activity.window, true)

            ad.fullScreenContentCallback = object : FullScreenContentCallback() {
                override fun onAdDismissedFullScreenContent() {
                    // 3. Restaurar edge-to-edge tras cerrar el interstitial
                    WindowCompat.setDecorFitsSystemWindows(activity.window, false)
                    interstitialAd = null
                    onAdClosed?.invoke()
                }

                override fun onAdFailedToShowFullScreenContent(adError: AdError) {
                    WindowCompat.setDecorFitsSystemWindows(activity.window, false)
                    interstitialAd = null
                    onAdClosed?.invoke()
                }

                override fun onAdShowedFullScreenContent() {
                    // 2. Aquí podría ocultar barra de estado si además quieres inmersión completa
                    //activity.window.insetsController?.hide(WindowInsets.Type.statusBars())
                }
            }

            ad.show(activity)
        } ?: onAdClosed?.invoke()
    }

}

package com.eddevios.collections.utils

import com.eddevios.collections.BuildConfig

object AppConfig {
    // URL base de la API
    const val API_BASE_URL = "https://api.eddevios.com/"
    const val WEB_URL = "https://eddevios.com/"
    
    // Estos valores se inyectan desde local.properties vía Gradle para seguridad
    val ONESIGNAL_ID = BuildConfig.ONESIGNAL_ID
    val BANNER_AD_ID = BuildConfig.BANNER_AD_ID
    val INTERSTITIAL_AD_ID = BuildConfig.INTERSTITIAL_AD_ID
    val API_KEY = BuildConfig.API_KEY

    // Timeouts para las llamadas a la API (en segundos)
    const val API_CONNECT_TIMEOUT = 30L
    const val API_READ_TIMEOUT = 30L
    const val API_WRITE_TIMEOUT = 30L

    // Configuración de Publicidad
    const val INTERSTITIAL_TIME_INTERVAL = 120_000
}
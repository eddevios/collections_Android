package com.eddevios.collections.utils

import com.google.firebase.crashlytics.FirebaseCrashlytics
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CrashlyticsLogger @Inject constructor() {

    private val crashlytics = FirebaseCrashlytics.getInstance()

    /**
     * Registra una excepción no fatal. Útil para errores controlados
     * en bloques try-catch que no deben cerrar la app.
     */
    fun logNonFatal(exception: Throwable) {
        crashlytics.recordException(exception)
    }

    /**
     * Añade una clave-valor personalizada al siguiente reporte de crash.
     * Útil para saber el estado de la app en el momento del error.
     */
    fun setCustomKey(key: String, value: String) {
        crashlytics.setCustomKey(key, value)
    }

    /**
     * Asocia un identificador de usuario a los reportes de crash.
     * Ayuda a saber cuántos usuarios afecta un mismo error.
     */
    fun setUserId(id: String) {
        crashlytics.setUserId(id)
    }
}

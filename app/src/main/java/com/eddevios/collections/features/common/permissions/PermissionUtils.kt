package com.eddevios.collections.features.common.permissions

import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

object PermissionUtils {

    /**
     * Comprueba si se han concedido todos los permisos especificados.
     *
     * @param context Contexto de la app.
     * @param permissions Array de permisos a verificar.
     * @return `true` si todos los permisos han sido concedidos, `false` en caso contrario.
     */
    fun hasPermissions(context: Context, permissions: Array<String>): Boolean {
        return permissions.all {
            ContextCompat.checkSelfPermission(context, it) == PackageManager.PERMISSION_GRANTED
        }
    }

    /**
     * Solicita permisos al usuario.
     *
     * @param activity Actividad desde la que se solicita el permiso.
     * @param permissions Array de permisos a solicitar.
     * @param requestCode Código único para identificar la solicitud.
     */
    fun requestPermissions(activity: Activity, permissions: Array<String>, requestCode: Int) {
        ActivityCompat.requestPermissions(activity, permissions, requestCode)
    }

    /**
     * Comprueba si el usuario ha denegado algún permiso y seleccionado "No volver a preguntar".
     *
     * @param activity Actividad desde la que se verifica el permiso.
     * @param permission Permiso específico a verificar.
     * @return `true` si se ha denegado con "No volver a preguntar", `false` en caso contrario.
     */
    fun shouldShowRationale(activity: Activity, permission: String): Boolean {
        return ActivityCompat.shouldShowRequestPermissionRationale(activity, permission)
    }
}

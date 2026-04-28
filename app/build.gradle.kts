import java.util.Properties

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.ksp)
    alias(libs.plugins.google.services)
    alias(libs.plugins.firebase.crashlytics)
    alias(libs.plugins.hilt)
}

val localProperties = Properties()
val localPropertiesFile = rootProject.file("local.properties")
if (localPropertiesFile.exists()) {
    localProperties.load(localPropertiesFile.inputStream())
}

android {
    namespace = "com.eddevios.collections"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.eddevios.collections"
        minSdk = 24
        targetSdk = 36
        versionCode = 18
        versionName = "1.0.6"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        // Placeholder por defecto (puedes poner el de test aquí también)
        manifestPlaceholders["admobAppId"] = localProperties.getProperty("ADMOB_APPLICATION_ID") ?: "ca-app-pub-3940256099942544~3347511713"
    }

    buildTypes {
        debug {
            // IDs de TEST oficiales de Google
            buildConfigField("String", "BANNER_AD_ID", "\"ca-app-pub-3940256099942544/6300978111\"")
            buildConfigField("String", "INTERSTITIAL_AD_ID", "\"ca-app-pub-3940256099942544/1033173712\"")
            buildConfigField("String", "ONESIGNAL_ID", "\"xxxxxxxx-xxxx-xxxx-xxxx-xxxxxxxxxxxx\"") // ID de prueba
            buildConfigField("String", "API_KEY", "\"debug_api_key\"")
            
            // En debug usamos el ID de test en el manifest también
            manifestPlaceholders["admobAppId"] = "ca-app-pub-3940256099942544~3347511713"
        }
        release {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            ndk {
                debugSymbolLevel = "FULL"
            }

            // IDs REALES leídos de local.properties
            buildConfigField("String", "BANNER_AD_ID", "\"${localProperties.getProperty("ADMOB_BANNER_ID")}\"")
            buildConfigField("String", "INTERSTITIAL_AD_ID", "\"${localProperties.getProperty("ADMOB_INTERSTITIAL_ID")}\"")
            buildConfigField("String", "ONESIGNAL_ID", "\"${localProperties.getProperty("ONESIGNAL_ID")}\"")
            buildConfigField("String", "API_KEY", "\"${localProperties.getProperty("API_KEY")}\"")
            
            manifestPlaceholders["admobAppId"] = localProperties.getProperty("ADMOB_APPLICATION_ID") ?: ""
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    kotlinOptions {
        jvmTarget = "11"
    }

    buildFeatures {
        buildConfig = true
        compose = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.material.icons.core)
    implementation(libs.androidx.material.icons.extended)
    implementation(libs.navigation.compose)
    implementation(libs.onesignal)
    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.analytics)
    implementation(libs.firebase.crashlytics)
    implementation(libs.play.services.ads)
    implementation(libs.reorderable)
    implementation(libs.room.runtime)
    implementation(libs.room.ktx)
    implementation(libs.androidx.core)
    ksp(libs.room.compiler)
    implementation(libs.postgrest.kt)
    implementation(libs.gotrue.kt)
    implementation(libs.ktor.client.android)
    // ►►► INYECCIÓN DE DEPENDENCIAS: (tipo: Dependency)
    implementation(libs.hilt.android)
    implementation(libs.hilt.navigation.compose)
    ksp(libs.hilt.compiler)
    implementation(libs.glide.compose)
    implementation(libs.coil.compose)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}
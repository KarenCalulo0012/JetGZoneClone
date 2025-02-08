plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "com.kaecals.jetgzoneclone.di"
    compileSdk = 35

    defaultConfig {
        minSdk = 24
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    kotlinOptions {
        jvmTarget = "11"
    }
}
dependencies {
    implementation(libs.kotlin.stdlib)
    // Koin core library for dependency injection
    implementation(libs.koin.core)
    implementation(libs.koin.android)

    // Koin for AndroidX ViewModel if you're using ViewModel
    implementation(libs.koin.androidx.navigation)

    // (Optional) If you are using Jetpack Compose with Koin
    implementation(libs.koin.androidx.compose)
}
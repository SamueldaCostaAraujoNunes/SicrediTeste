plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    kotlin("kapt")
    id("com.google.dagger.hilt.android")
    id("androidx.navigation.safeargs.kotlin")
}

android {
    namespace = "samuelnunes.com.sicrediteste"
    compileSdk = 34

    defaultConfig {
        applicationId = "samuelnunes.com.sicrediteste"
        minSdk = 21
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        debug {
            buildConfigField("String", "EVENTS_API_BASE_URL", "\"https://5f5a8f24d44d640016169133.mockapi.io/api/\"")

        }

        release {

            buildConfigField("String", "EVENTS_API_BASE_URL", "\"https://5f5a8f24d44d640016169133.mockapi.io/api/\"")

            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        buildConfig = true
        viewBinding = true
    }
}

dependencies {
    implementation ("com.github.SamueldaCostaAraujoNunes:utility-tools-kit-android:1.8")

// Swiper Refresh Layout
    implementation("androidx.swiperefreshlayout:swiperefreshlayout:1.1.0")

// Hilt
    val hilt_dagger_version = "2.48"
    implementation("com.google.dagger:hilt-android:$hilt_dagger_version")
    kapt("com.google.dagger:hilt-android-compiler:$hilt_dagger_version")

// Coil
    val coil_version = "2.5.0"
    implementation("io.coil-kt:coil:$coil_version")
    implementation("io.coil-kt:coil-gif:$coil_version")

// Room
    val room_version = "2.6.1"
    implementation("androidx.room:room-runtime:$room_version")
    kapt("androidx.room:room-compiler:$room_version")
    implementation("androidx.room:room-ktx:$room_version")

// Timber
    val timber_version = "5.0.1"
    implementation("com.jakewharton.timber:timber:$timber_version")

// Lifecycle
    val lifecycle_version = "2.7.0"
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:$lifecycle_version")

// Retrofit
    val retrofit_version = "2.9.0"
    implementation("com.squareup.retrofit2:retrofit:$retrofit_version")
    implementation("com.squareup.retrofit2:converter-gson:$retrofit_version")

// OkHttp
    val ok_http_version = "5.0.0-alpha.2"
    implementation("com.squareup.okhttp3:okhttp:$ok_http_version")

// Navigation
    val navigation_version = "2.7.7"
    implementation("androidx.navigation:navigation-fragment-ktx:$navigation_version")
    implementation("androidx.navigation:navigation-ui-ktx:$navigation_version")

    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
}
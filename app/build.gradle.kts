plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "com.example.myapplicationfinal"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.example.myapplicationfinal"
        minSdk = 24
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    // 🔹 Java / Kotlin settings
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    kotlinOptions {
        jvmTarget = "11"
    }

    // 🔹 ViewBinding (აუცილებელია დავალებისთვის)
    buildFeatures {
        viewBinding = true
    }
}

dependencies {

    // Core Android
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)

    // 🔹 RecyclerView
    implementation("androidx.recyclerview:recyclerview:1.3.2")

    // 🔹 Retrofit (API)
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")

    // 🔹 Glide (Image Loading)
    implementation("com.github.bumptech.glide:glide:4.16.0")

    // 🔹 Lifecycle / ViewModel
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.8.6")

    // 🔹 Navigation Component
    implementation("androidx.navigation:navigation-fragment-ktx:2.8.4")
    implementation("androidx.navigation:navigation-ui-ktx:2.8.4")

    // Tests
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}

plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    id("com.google.gms.google-services")
}

android {
    namespace = "com.example.knowyourproduct"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.knowyourproduct"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    buildFeatures {

        viewBinding = true

    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
        dataBinding{
            enable = true
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.firebase.auth)
    implementation(libs.firebase.storage)
    implementation(libs.firebase.firestore.ktx)
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)
    implementation(libs.firebase.firestore)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    //firebase
    // https://mvnrepository.com/artifact/com.google.firebase/firebase-bom
    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.database)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    //Glide
    implementation (libs.com.github.bumptech.glide.glide)
    //Life Cycle
    implementation (libs.androidx.lifecycle.extensions)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    implementation(libs.androidx.lifecycle.livedata.core.ktx)
    //View Model
    implementation(libs.androidx.activity.ktx)
    implementation(libs.com.github.bumptech.glide.glide)
    implementation(libs.gson)
    implementation(libs.dotsindicator)
    //gms_play_services
    implementation(libs.play.services.auth)
    //Corountines
    implementation (libs.kotlinx.coroutines.android)
    //picasso
    implementation(libs.picasso)
    //Autoimage
    implementation (libs.autoimageslider)
    //Glide
    implementation (libs.com.github.bumptech.glide.glide)
    //Retrofit
    implementation(libs.retrofit)
    //gson converter
    implementation(libs.converter.gson)
    //lib_to_change_hexacodeIntoTime
    implementation(libs.timeago)


}
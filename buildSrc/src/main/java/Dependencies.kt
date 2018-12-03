object Version {
    //Android
    const val androidCompileSdk = 28
    const val androidMinSdk = 16
    const val androidPlayService = "11.6.0"
    const val androidTargetSdk = 28
    const val androidVersionCode = 1
    const val androidVersionName = "1.0.0"
    const val gradleBuildTool = "3.2.1"
    const val fabricTool = "1.26.1"

    //Libraries
    const val appCompat = "28.0.0"
    const val coroutines = "1.0.1"
    const val constraintLayout = "1.1.3"
    const val fabric = "2.9.5@aar"
    const val kodein = "5.3.0"
    const val kodeinFramework = "5.0.0"
    const val kotlin = "1.3.0"
    const val okHttp = "3.10.0"
    const val picasso = "2.71828"
    const val retrofit = "2.4.0"
    const val room = "1.1.1"
    const val timber = "4.7.1"

    //Testing
    const val espressoRunner = "1.0.2"
    const val espresso = "3.0.2"
    const val jUnit = "5.3.1"
    const val mockk = "1.8.11"
    const val mockito = "2.18.3"
}

object Lib {
    const val androidPlayService = "com.google.android.gms:play-services-location:${Version.androidPlayService}"
    const val constraintLayout = "com.android.support.constraint:constraint-layout:${Version.constraintLayout}"
    const val coroutinesAndroid = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Version.coroutines}"
    const val coroutinesCore = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Version.coroutines}"
    const val kodein = "org.kodein.di:kodein-di-generic-jvm:${Version.kodein}"
    const val kodeinFramework = "org.kodein.di:kodein-di-framework-android:${Version.kodeinFramework}"
    const val kotlin = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Version.kotlin}"
    const val okHttpLogger = "com.squareup.okhttp3:logging-interceptor:${Version.okHttp}"
    const val picasso = "com.squareup.picasso:picasso:${Version.picasso}"
    const val retrofit = "com.squareup.retrofit2:retrofit:${Version.retrofit}"
    const val retrofitConverter = "com.squareup.retrofit2:converter-gson:${Version.retrofit}"
    const val room = "android.arch.persistence.room:runtime:${Version.room}"
    const val roomCompiler = "android.arch.persistence.room:compiler:${Version.room}"
    const val supportDesign = "com.android.support:design:${Version.appCompat}"
    const val supportLib = "com.android.support:appcompat-v7:${Version.appCompat}"
    const val timber = "com.jakewharton.timber:timber:${Version.timber}"

    const val espresso = "com.android.support.test.espresso:espresso-core:${Version.espresso}"
    const val espressoRunner = "com.android.support.test:runner:${Version.espressoRunner}"
    const val junit = "org.junit.jupiter:junit-jupiter-api:${Version.jUnit}"
    const val mockk = "io.mockk:mockk:${Version.mockk}"
    const val roomTesting = "android.arch.persistence.room:testing:${Version.room}"

    const val testInstrumentationRunner = "android.support.test.runner.AndroidJUnitRunner"
}
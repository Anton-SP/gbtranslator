import org.gradle.api.JavaVersion

object Config {
    const val applicationId = "com.example.gbtranslator"
    const val compileSdk = 33
    const val minSdk = 24
    const val targetSdk = 33
    val javaVersion = JavaVersion.VERSION_1_8
}

object Releases {
    const val versionCode = 1
    const val versionName = "1.0"
}

object Modules {
    const val app = ":app"
    const val featureTranslator = ":featureTranslator"
    const val featureTimer = ":featureTimer"
}

object Versions {
    //Design
    const val appcompat = "1.6.1"
    const val material = "1.8.0"
    const val swipe = "1.1.0"

    //Kotlin
    const val core = "1.7.0"

    //Koin
    const val koinCore = "3.3.3"
    const val koinAndroid = "3.3.3"

    //Room
    const val roomKtx = "2.5.0"
    const val runtime = "2.5.0"
    const val roomCompiler = "2.5.0"

    //Retrofit 2
    const val retrofit = "2.9.0"
    const val converterJson = "2.9.0"
    const val interceptor = "3.12.1"

    //Navigation
    const val navigation = "2.5.3"

    //Fragment
    const val fragmentKtx = "1.5.5"

    //Coil
    const val coil = "2.1.0"

    //ViewBinding
    const val viewBinding = "1.5.6"

    //Test
    const val jUnit = "4.13.2"
    const val runner = "1.2.0"
    const val espressoCore = "3.4.0"
    const val testExt = "1.1.3"

    //ViewModel
    const val viewModelLifeCycle = "2.6.0"
}

object Design {
    const val appcompat = "androidx.appcompat:appcompat:${Versions.appcompat}"
    const val material = "com.google.android.material:material:${Versions.material}"
    const val swipe = "androidx.swiperefreshlayout:swiperefreshlayout:${Versions.swipe}"
}

object Kotlin {
    const val core = "androidx.core:core-ktx:${Versions.core}"
}

object Coil {
    const val coil = "io.coil-kt:coil:${Versions.coil}"
}

object Room {
    const val runtime = "androidx.room:room-runtime:${Versions.runtime}"
    const val compiler = "androidx.room:room-compiler:${Versions.roomCompiler}"
    const val roomKtx = "androidx.room:room-ktx:${Versions.roomKtx}"
}

object Retrofit2 {
    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    const val converterJson = "com.squareup.retrofit2:converter-gson:${Versions.converterJson}"
    const val interceptor = "com.squareup.okhttp3:logging-interceptor:${Versions.interceptor}"
}

object ViewBinding {
    const val viewBinding = "com.github.kirich1409:viewbindingpropertydelegate:${
        Versions.viewBinding
    }"
}

object TestImpl {
    const val junit = "junit:junit:${Versions.jUnit}"
    const val runner = "androidx.test:runner:${Versions.runner}"
    const val espresso = "androidx.test.espresso:espresso-core:${Versions.espressoCore}"
    const val testExt = "androidx.test.ext:junit:${Versions.testExt}"
}

object NavigationComponent {
    const val navigationFragment =
        "androidx.navigation:navigation-fragment-ktx:${Versions.navigation}"
    const val navigationUi = "androidx.navigation:navigation-ui-ktx:${Versions.navigation}"
}

object Fragment {
    const val fragmentKtx = "androidx.fragment:fragment-ktx:${Versions.fragmentKtx}"
}

object Koin {
    const val core = "io.insert-koin:koin-core:${Versions.koinCore}"
    const val android = "io.insert-koin:koin-android:${Versions.koinAndroid}"
}

object ViewModel {
    const val lifecycle = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.viewModelLifeCycle}"
}


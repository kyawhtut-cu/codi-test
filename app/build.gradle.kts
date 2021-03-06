import java.util.*

plugins {
    androidApp()
    kotlinAndroid()
    kotlinKapt()
    dagger()
    navigationSafeArgsKtx()
    androidGitVersion()
}

val configProperties = Properties()
configProperties.load(file("${rootDir}/local.properties").inputStream())
val API_BASE_URL: String = configProperties.getProperty("API_BASE_URL", "")
val IMAGE_BASE_URL: String = configProperties.getProperty("IMAGE_BASE_URL", "")
val API_BEARER_TOKEN: String = configProperties.getProperty("API_BEARER_TOKEN", "")

val appName = hashMapOf(
    "debug" to "app-debug.apk",
    "release" to "app-release.apk"
)

android {

    compileSdkVersion(Versions.compileSdkVersion)
    buildToolsVersion(Versions.buildToolsVersion)

    defaultConfig {

        applicationId = "com.kyawhut.codetest"

        minSdkVersion(Versions.minSdkVersion)
        targetSdkVersion(Versions.targetSdkVersion)

        versionCode = androidGitVersion.code()
        versionName = androidGitVersion.name()

        multiDexEnabled = true

        vectorDrawables {
            useSupportLibrary = true
        }

        buildConfigString("API_BASE_URL", API_BASE_URL)

        buildConfigString("IMAGE_BASE_URL", IMAGE_BASE_URL)

        buildConfigString("API_BEARER_TOKEN", API_BEARER_TOKEN)

    }

    buildTypes {

        getByName("release") {
            debuggable(false)
            jniDebuggable(false)
            renderscriptDebuggable(false)

            minifyEnabled(true)
            isShrinkResources = true

            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }

    }

    compileOptions {
        sourceCompatibility(JavaVersion.VERSION_1_8)
        targetCompatibility(JavaVersion.VERSION_1_8)
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }

    buildFeatures {
        dataBinding = true
        viewBinding = true
    }

    android.applicationVariants.all {
        val variant = this
        variant.outputs.map { it as com.android.build.gradle.internal.api.BaseVariantOutputImpl }
            .forEach { output ->
                val buildOutputPath = "../../release/${variant.versionName}/%s"
                output.outputFileName = String.format(
                    buildOutputPath,
                    appName[variant.buildType.name]
                )
            }
    }
}

dependencies {

    testImplementation(Libs.junit)
    androidTestImplementation(Libs.testJunit)
    androidTestImplementation(Libs.espresso)

    implementation(Libs.kotlinLib)
    implementation(Libs.coreKtx)
    implementation(Libs.appCompact)
    implementation(Libs.material)
    implementation(Libs.constraintLayout)
    implementation(Libs.navigationFragmentKtx)
    implementation(Libs.navigationUI)
    implementation(Libs.vectorDrawable)
    implementation(Libs.swipeRefresh)

    // dependency injection
    implementation(Libs.hiltAndroid)
    kapt(Libs.hiltAndroidCompiler)

    // ViewModel and LiveData
    api(Libs.lifeCycleExt)
    api(Libs.fragmentKtx)

    implementation(Libs.retrofit)
    implementation(Libs.okhttp)
    implementation(Libs.retrofitGson)
    implementation(Libs.loggingInterceptor)
    implementation(Libs.coroutineKotlin)

    implementation(Libs.glide)
    implementation(Libs.glideOkHttp)
    kapt(Libs.glideCompiler)

    //Timber(Logging)
    implementation(Libs.timber)

    // Room
    implementation(Libs.roomKtx)
    implementation(Libs.roomRuntime)
    kapt(Libs.roomCompiler)
    // RxJava support for Room
    implementation(Libs.roomRxJava)

}

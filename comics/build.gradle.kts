plugins {
    id("com.android.library")
    id("kotlin-android")
    id("kotlin-android-extensions")
    id("kotlin-kapt")
}

android {
    dataBinding{
        isEnabled = true
    }
    compileSdkVersion(Versions.compileSdkVersion)
    buildToolsVersion(Versions.buildToolsVersion)

    defaultConfig {
        minSdkVersion(Versions.minSdkVersion)
        targetSdkVersion(Versions.targetSdkVersion)
        versionCode = Versions.versionCode
        versionName = Versions.versionName

        testInstrumentationRunner = Depends.runnerPackage

        vectorDrawables.useSupportLibrary = true
        multiDexEnabled = true
    }
}

dependencies {
    implementation(project(Depends.Module.di))
    implementation(project(Depends.Module.base))
    implementation(project(Depends.Module.resources))
    implementation(project(Depends.Module.network))
    implementation(project(Depends.Module.comicData))
    implementation(project(Depends.Module.components))

    implementation(Depends.Picasso.picasso)
    Depends.processorDaggerArray.forEach { kapt(it) }
    Depends.daggerArray.forEach { implementation(it) }
    Depends.rxArray.forEach { implementation(it) }
    Depends.viewModelArray.forEach { implementation(it) }

    Depends.kotlinArray.forEach { implementation(it) }
    Depends.supportArray.forEach { implementation(it) }
    Depends.unitTestArray.forEach { testImplementation(it) }
    Depends.androidTestArray.forEach { androidTestImplementation(it) }
}

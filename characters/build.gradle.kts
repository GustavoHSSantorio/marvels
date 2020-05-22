plugins {
    id("com.android.library")
    id("kotlin-android")
    id("kotlin-android-extensions")
    id("kotlin-kapt")
}

android {
    dataBinding {
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
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))

    implementation(project(Depends.Module.base))
    implementation(project(Depends.Module.di))
    implementation(project(Depends.Module.resources))
    implementation(project(Depends.Module.network))
    implementation(project(Depends.Module.dayNight))
    implementation(project(Depends.Module.characterData))
    implementation(project(Depends.Module.comicData))
    implementation(project(Depends.Module.components))
    implementation(project(Depends.Module.charactersProfile))

    implementation(Depends.Picasso.picasso)
    Depends.daggerArray.forEach { implementation(it) }
    Depends.processorDaggerArray.forEach { kapt(it) }
    Depends.rxArray.forEach { implementation(it) }
    Depends.navigationArray.forEach { implementation(it) }
    Depends.retrofitArray.forEach { implementation(it) }

    Depends.kotlinArray.forEach { implementation(it) }
    Depends.supportArray.forEach { implementation(it) }
    Depends.unitTestArray.forEach { testImplementation(it) }
    Depends.androidTestArray.forEach { androidTestImplementation(it) }
}

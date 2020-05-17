buildscript {
    repositories {
        google()
        jcenter()
    }
    dependencies {
        classpath(Depends.Kotlin.gradle)
        classpath(Depends.Android.gradle_plugin)
        classpath(kotlin("gradle-plugin", Versions.kotlin_version))
    }
}

allprojects {
    repositories {
        google()
        jcenter()
        flatDir {
            dirs("libs")
        }
    }
}

tasks.register("clean").configure {
    delete("build")
}

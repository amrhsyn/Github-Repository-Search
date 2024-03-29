buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath(Build.androidBuildTools)
        classpath(Build.hiltAndroidGradlePlugin)
        classpath(Kotlin.kotlinGradlePlugin)
        classpath(Testing.kotlinxCoroutinesTest)
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.6.10")
    }
}
tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}

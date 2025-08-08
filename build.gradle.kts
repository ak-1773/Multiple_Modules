import org.gradle.internal.impldep.org.apache.ivy.util.url.IvyAuthenticator.install

// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlin.compose) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.dagger.hilt.android) apply false
    alias(libs.plugins.cn.therouter) apply false
}

tasks.register<tasks.ModuleGeneratorTask>("gm") {
    modName.set(project.findProperty("modName")?.toString() ?: "defalt")
    packName.set(project.findProperty("packName")?.toString() ?: "")
    hasViewModel.set(project.findProperty("hasViewModel")?.toString()?.toBoolean() ?: true)
}

subprojects {
    apply(plugin = "kotlin-kapt")
    afterEvaluate {
        val configure = {
            dependencies {
                add("implementation", libs.multidex)
            }
        }

        plugins.withId("com.android.application") {
            configure()
        }
        plugins.withId("com.android.library") {
            configure()
        }
    }

}

project.ext.apply {
    set("compileSdk", 34)
    set("minSdk", 23)
    set("targetSdk", 34)
    set("versionCode", 1)
    set("versionName", "1.0.0")
}

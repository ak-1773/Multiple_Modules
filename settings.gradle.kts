pluginManagement {
    repositories {
        maven { url = uri("https://maven.aliyun.com/repository/google") }
        maven { url = uri("https://maven.aliyun.com/repository/public") }
        maven { url = uri("https://maven.aliyun.com/nexus/content/repositories/jcenter") }
        gradlePluginPortal()
        google()
        mavenCentral()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.PREFER_SETTINGS)
    repositories {
        maven { url = uri("https://maven.aliyun.com/repository/google") }
        maven { url = uri("https://maven.aliyun.com/nexus/content/groups/public/") }
        maven { url = uri("https://maven.aliyun.com/nexus/content/repositories/jcenter") }
        maven { url = uri("https://oss.sonatype.org/content/repositories/snapshots/") }
        google()
        mavenCentral()
        flatDir {
            dirs("libs") // 指定所有模块的libs目录
        }
    }
}

rootProject.name = "Example2025"
//app壳
include(":app")
//核心包
file("core").listFiles()?.forEach { directory ->
    if (directory.isDirectory) {
        include(":core:${directory.name}")
    }
}
//基础包
file("lib").listFiles()?.forEach { directory ->
    if (directory.isDirectory) {
        include(":lib:${directory.name}")
    }
}
//业务包
file("business").listFiles()?.forEach { directory ->
    if (directory.isDirectory) {
        include(":business:${directory.name}")
    }
}
/**
 * 解释：
 * 1. app壳  仅发布app作为一个壳, 集成所有业务包, 不要在这里做业务
 * 2. 核心包 为最低层, 向上提供服务, 平级包可以依赖, 尽量不要依赖, 不要向上依赖基础包, 更不能依赖业务包。
 * 3. 基础包 为中间层, 向上提供服务, 平级包可以依赖, 向下可以依赖核心包, 但不能依赖业务包。
 * 4. 业务包 为最上层, 各个业务在这里完成, 平级包不能依赖, 向下可以依赖基础包或核心包, 可以单独调试每个业务。
 */
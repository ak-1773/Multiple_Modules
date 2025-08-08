package tasks

import org.gradle.api.DefaultTask
import org.gradle.api.provider.Property
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.TaskAction
import java.io.File
import java.util.concurrent.Executors

abstract class ModuleGeneratorTask : DefaultTask() {
    @get:Input
    abstract val modName: Property<String>

    @get:Input
    abstract val packName: Property<String>

    @get:Input
    abstract val hasViewModel: Property<Boolean>

    @get:Input
    abstract val corporateName: Property<String>

    init {
        corporateName.set("example")
    }


    @TaskAction
    fun generate() {
        logger.lifecycle(
            "\n" +
                    " -------- __@  \n" +
                    " ----- _`\\<,_  \n" +
                    " ---- (*)/ (*)  \nstart generating module ..."
        )

        val executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors())
        executor.execute {
            asyncGenerate()
        }


    }

    fun asyncGenerate() {
        //模块名, 如:home
        val moduleName = modName.get()
        if (moduleName.isEmpty()) {
            logger.lifecycle("❌ 模块名称不能为空")
            return
        }

        //business
        val businessDir = project.rootProject.file("business")
        if (!businessDir.exists()) {
            businessDir.mkdirs()
            logger.lifecycle("✅ 创建业务目录")
        }
        //module目录
        val moduleDir = project.rootProject.file("business/module_${moduleName.lowercase()}")
        if (moduleDir.exists()) {
            logger.lifecycle("✅ 模块 ${moduleName} 已存在")
        } else {
            moduleDir.mkdirs()
            logger.lifecycle(
                "\n" +
                        "          __       \n" +
                        "H________H__]\\____.  \n" +
                        "|________|'    _ _:  \n" +
                        "'  /(_)   \"\"\"\"/(_)  \nCreate Module: ${moduleName}"
            )
        }

        //创建目录
        val srcDir = moduleDir.resolve("src/main/java")
        if (!srcDir.exists()) {
            srcDir.mkdirs()
        }
        val resDir = moduleDir.resolve("src/main/res")
        if (!resDir.exists()) {
            resDir.mkdirs()
        }
        val debugDir = moduleDir.resolve("src/main/debug")
        if (!debugDir.exists()) {
            debugDir.mkdirs()
        }

        val drawableDir = resDir.resolve("drawable")
        if (!drawableDir.exists()) {
            drawableDir.mkdirs()
        }

        val drawable_v24Dir = resDir.resolve("drawable-v24")
        if (!drawable_v24Dir.exists()) {
            drawable_v24Dir.mkdirs()
        }
        val mipmapDir = resDir.resolve("mipmap-anydpi-v26")
        if (!mipmapDir.exists()) {
            mipmapDir.mkdirs()
        }
        val valuesDir = resDir.resolve("values")
        if (!valuesDir.exists()) {
            valuesDir.mkdirs()
        }
        //包名
        var packageName = packName.get()
        if (packageName.isEmpty()) {
            packageName = "com.${corporateName.get()}.module_${moduleName.lowercase()}"
        } else {
            packageName = packageName.lowercase()
        }

        val packageDir = srcDir.resolve(packageName.lowercase().replace(".", "/"))
        if (!packageDir.exists()) {
            packageDir.mkdirs()
        }

        //创建包目录
        val activityDir = packageDir.resolve("activity")
        if (!activityDir.exists()) {
            activityDir.mkdirs()
        }
        val modelDir = packageDir.resolve("model")
        if (!modelDir.exists()) {
            modelDir.mkdirs()
        }
        val uiDir = packageDir.resolve("ui")
        if (!uiDir.exists()) {
            uiDir.mkdirs()
        }
        val viewModelDir = packageDir.resolve("vm")
        if (!viewModelDir.exists()) {
            viewModelDir.mkdirs()
        }

        //生成文件
        val templateDir = File(project.rootDir, "templates/module_template")
        var formatName = ""
        if (moduleName.contains("_")) {
            val split = moduleName.split("_")
            for (s in split) {
                formatName += s.lowercase().replaceFirstChar { it.uppercase() }
            }
        } else {
            formatName = moduleName.lowercase().replaceFirstChar { it.uppercase() }
        }


        val activityName = "${formatName}Activity"
        val viewModelName = "${formatName}ViewModel"
        val activityFile = activityDir.resolve("${activityName}.kt")
        if (!activityFile.exists()) {
            activityFile.createNewFile()
            activityFile.writeText(
                templateDir.resolve("TemplateActivity.kt").readText()
                    .replace("{{packageName}}", packageName)
                    .replace("{{name}}", moduleName.lowercase())
                    .replace("{{activityName}}", activityName)
                    .replace("{{hasViewModel}}", hasViewModel.get().toString())
                    .replace("{{viewModelName}}", viewModelName)
                    .replace("{{formatName}}", formatName)
            )
        }
        val viewModelFile = viewModelDir.resolve("${viewModelName}.kt")
        if (!viewModelFile.exists()) {
            viewModelFile.createNewFile()
            viewModelFile.writeText(
                templateDir.resolve("TemplateViewModel.kt").readText()
                    .replace("{{packageName}}", packageName)
                    .replace("{{viewModelName}}", viewModelName)
            )
        }
        val uiFile =
            uiDir.resolve("${formatName}UI.kt")
        if (!uiFile.exists()) {
            uiFile.createNewFile()
            uiFile.writeText(
                templateDir.resolve("TemplateUI.kt").readText()
                    .replace("{{packageName}}", packageName)
                    .replace("{{formatName}}", formatName)
            )
        }

        val manifest = moduleDir.resolve("src/main/AndroidManifest.xml")
        if (!manifest.exists()) {
            manifest.createNewFile()
            manifest.writeText(
                templateDir.resolve("AndroidManifest.xml").readText()
                    .replace("{{packageName}}", packageName)
                    .replace("{{activityName}}", activityName)
            )
        }

        val debugPackDir = debugDir.resolve("java/${packageName.lowercase().replace(".", "/")}")
        if (!debugPackDir.exists()) {
            debugPackDir.mkdirs()
        }
        //创建app目录
        val appDir = debugPackDir.resolve("app")
        if (!appDir.exists()) {
            appDir.mkdirs()
        }
        val appName = "${formatName}Application"
        val appFile = appDir.resolve("${appName}.kt")
        if (!appFile.exists()) {
            appFile.createNewFile()
            appFile.writeText(
                templateDir.resolve("TemplateApplication.kt").readText()
                    .replace("{{packageName}}", packageName)
                    .replace("{{formatName}}", formatName)
            )

        }
        val debugManifest = debugDir.resolve("AndroidManifest.xml")
        if (!debugManifest.exists()) {
            debugManifest.createNewFile()
            debugManifest.writeText(
                templateDir.resolve("debug/AndroidManifest.xml").readText()
                    .replace("{{packageName}}", packageName)
                    .replace("{{activityName}}", activityName)
                    .replace("{{formatName}}", formatName)
            )
        }
        val stringsFile = valuesDir.resolve("strings.xml")
        if (!stringsFile.exists()) {
            stringsFile.createNewFile()
            stringsFile.writeText(
                templateDir.resolve("res/values/strings.xml").readText()
                    .replace("{{name}}", moduleName.lowercase())
            )
        }
        val buildFile = moduleDir.resolve("build.gradle.kts")
        if (!buildFile.exists()) {
            buildFile.createNewFile()
            buildFile.writeText(
                templateDir.resolve("build.gradle.kts").readText()
                    .replace("{{packageName}}", packageName)
            )
        }

        //拷贝文件
        if (!drawableDir.resolve("ic_launcher_background.xml").exists()) {
            templateDir.resolve("res/drawable/ic_launcher_background.xml")
                .copyTo(drawableDir.resolve("ic_launcher_background.xml"))
        }
        if (!drawable_v24Dir.resolve("${moduleName}_ic_launcher_foreground.xml").exists()) {
            templateDir.resolve("res/drawable-v24/ic_launcher_foreground.xml")
                .copyTo(drawable_v24Dir.resolve("${moduleName}_ic_launcher_foreground.xml"))
        }

        val launcherFile = mipmapDir.resolve("ic_launcher.xml")
        if (!launcherFile.exists()) {
            launcherFile.exists()
            launcherFile.writeText(
                templateDir.resolve("res/mipmap-anydpi-v26/ic_launcher.xml")
                    .readText().replace("{{moduleName}}", moduleName.lowercase())
            )
        }
        val launcherRoundFile = mipmapDir.resolve("ic_launcher_round.xml")
        if (!launcherRoundFile.exists()) {
            launcherRoundFile.createNewFile()
            launcherRoundFile.writeText(
                templateDir.resolve("res/mipmap-anydpi-v26/ic_launcher_round.xml")
                    .readText().replace("{{moduleName}}", moduleName.lowercase())
            )
        }
        if (!valuesDir.resolve("themes.xml").exists()) {
            templateDir.resolve("res/values/themes.xml").copyTo(valuesDir.resolve("themes.xml"))
        }
        logger.lifecycle(
            "\n\n" +
                    " -------- __@      __@       __@       __@      __~@\n" +
                    " ----- _`\\<,_    _`\\<,_    _`\\<,_     _`\\<,_    _`\\<,_\n" +
                    " ---- (*)/ (*)  (*)/ (*)  (*)/ (*)  (*)/ (*)  (*)/ (*)  \nModule: ${moduleName} creation completed"
        )
    }
}
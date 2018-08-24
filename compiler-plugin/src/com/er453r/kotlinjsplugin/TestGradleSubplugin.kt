package com.er453r.kotlinjsplugin

import com.squareup.kotlinpoet.*
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.tooling.provider.model.ToolingModelBuilderRegistry
import java.io.File
import java.nio.charset.Charset
import javax.inject.Inject

class TestGradleSubplugin @Inject internal constructor(private val registry: ToolingModelBuilderRegistry) : Plugin<Project> {
    override fun apply(pro: Project?) {
        println("[kotlinjs] Gradle plugin apply $pro")

        pro?.let { project ->
            println("Build path ${project.projectDir}")

            walkRecursive(project.projectDir) {
                println("Found ${it.name}")

                val contents = it.readText(Charset.defaultCharset())

                val packageRegex = "package (.+)".toRegex()
                val classRegex = "class (\\w+)".toRegex()

                packageRegex.find(contents)?.let { matchResult ->
                    val (packageName) = matchResult.destructured
                    println("package $packageName")

                    classRegex.find(contents)?.let { classMatchResult ->
                        var result:MatchResult? = classMatchResult

                        while(result != null){
                            val (className) = classMatchResult.destructured
                            println("class $className")

                            val greeterClass = ClassName("", "Greeter")

                            val file = FileSpec.builder("", "HelloWorld")
                                    .addType(TypeSpec.classBuilder("Greeter")
                                            .primaryConstructor(FunSpec.constructorBuilder()
                                                    .addParameter("name", String::class)
                                                    .build())
                                            .addProperty(PropertySpec.builder("name", String::class)
                                                    .initializer("name")
                                                    .build())
                                            .addFunction(FunSpec.builder("greet")
                                                    .addStatement("println(%S)", "Hello, \$name")
                                                    .build())
                                            .build())
                                    .addFunction(FunSpec.builder("main")
                                            .addParameter("args", String::class, KModifier.VARARG)
                                            .addStatement("%T(args[0]).greet()", greeterClass)
                                            .build())
                                    .build()

                            file.writeTo(System.out)

                            result = result.next()
                        }
                    }
                }

                File("${project.projectDir}/generated").mkdirs()
                File("${project.projectDir}/generated/Extensions.kt").createNewFile()
            }
        }
    }

    private fun walkRecursive(directory:File, onFound:(File)->Unit){
        if(!directory.isDirectory)
            return

        directory.listFiles().forEach {
            if(it.isDirectory)
                walkRecursive(it, onFound)
            else if(it.name.endsWith(".kt"))
                onFound(it)
        }
    }
}

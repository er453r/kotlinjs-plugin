package com.er453r.kotlinjsplugin

import com.squareup.kotlinpoet.ClassName
import com.squareup.kotlinpoet.FileSpec
import com.squareup.kotlinpoet.FunSpec
import com.squareup.kotlinpoet.PropertySpec
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

            val found: MutableList<Match> = mutableListOf()

            walkRecursive(project.projectDir) {
                println("Found ${it.name}")

                val contents = it.readText(Charset.defaultCharset())

                val packageRegex = "package (.+)".toRegex()
                val classRegex = "class (\\w+)".toRegex()

                packageRegex.find(contents)?.let { matchResult ->
                    val (packageName) = matchResult.destructured
                    println("package $packageName")

                    classRegex.find(contents)?.let { classMatchResult ->
                        var result: MatchResult? = classMatchResult

                        while (result != null) {
                            val (className) = classMatchResult.destructured
                            println("class $className")

                            found.add(Match(packageName, className))

                            result = result.next()
                        }
                    }
                }
            }

            val dir = File("${project.projectDir}/generated")

            dir.mkdirs()

            val builder = FileSpec.builder("com.er453r.plugin.generated", "extensions")

            found.forEach {
                val type = ClassName(it.packageName, it.className)

                builder.addProperty(PropertySpec.builder("derp", String::class)
                        .receiver(type)
                        .getter(
                            FunSpec.getterBuilder().addStatement("return %S", "LOLZ").build()
                        ).build())
            }

            builder.build().writeTo(dir)
        }
    }

    private fun walkRecursive(directory: File, onFound: (File) -> Unit) {
        if (!directory.isDirectory)
            return

        directory.listFiles().forEach {
            if (it.isDirectory)
                walkRecursive(it, onFound)
            else if (it.name.endsWith(".kt"))
                onFound(it)
        }
    }
}

class Match(val packageName: String, val className: String)

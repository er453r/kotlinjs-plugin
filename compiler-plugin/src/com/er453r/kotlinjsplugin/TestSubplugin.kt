package com.er453r.kotlinjsplugin

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.tasks.SourceSet
import org.gradle.api.tasks.compile.AbstractCompile
import org.gradle.tooling.provider.model.ToolingModelBuilderRegistry
import org.jetbrains.kotlin.gradle.plugin.KotlinGradleSubplugin
import org.jetbrains.kotlin.gradle.plugin.SubpluginArtifact
import org.jetbrains.kotlin.gradle.plugin.SubpluginOption
import javax.inject.Inject

class TestGradleSubplugin @Inject internal constructor(private val registry: ToolingModelBuilderRegistry) : Plugin<Project> {
    override fun apply(p0: Project?) {
        println("TestSubplugin apply ${TestProcessor.PLUGIN_ID}")
    }
}

class TestSubplugin : KotlinGradleSubplugin<AbstractCompile> {
    override fun apply(project: Project, kotlinCompile: AbstractCompile, javaCompile: AbstractCompile, variantData: Any?, androidProjectHandler: Any?, javaSourceSet: SourceSet?): List<SubpluginOption> {
        return emptyList()
    }

    override fun getCompilerPluginId() = "com.er453r.kotlinjsplugin"

    override fun getPluginArtifact() = SubpluginArtifact(
            groupId = "com.er453r",
            artifactId = TestProcessor.PLUGIN_ID
    )

    override fun isApplicable(project: Project, task: AbstractCompile) = true
}

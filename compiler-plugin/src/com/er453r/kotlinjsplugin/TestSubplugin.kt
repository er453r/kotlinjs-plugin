package com.er453r.kotlinjsplugin

import org.gradle.api.Project
import org.gradle.api.tasks.SourceSet
import org.gradle.api.tasks.compile.AbstractCompile
import org.jetbrains.kotlin.cli.common.messages.CompilerMessageLocation
import org.jetbrains.kotlin.cli.common.messages.CompilerMessageSeverity
import org.jetbrains.kotlin.cli.common.messages.MessageCollector
import org.jetbrains.kotlin.gradle.plugin.KotlinGradleSubplugin
import org.jetbrains.kotlin.gradle.plugin.SubpluginArtifact
import org.jetbrains.kotlin.gradle.plugin.SubpluginOption

class TestSubplugin : KotlinGradleSubplugin<AbstractCompile> {
    companion object {
        const val ID = "kotlinjsplugin"
    }

    override fun apply(project: Project, kotlinCompile: AbstractCompile, javaCompile: AbstractCompile, variantData: Any?, androidProjectHandler: Any?, javaSourceSet: SourceSet?): List<SubpluginOption> {
        return emptyList()
    }

    override fun getCompilerPluginId() = "com.er453r.kotlinjsplugin"

    override fun getPluginArtifact() = SubpluginArtifact(
            groupId = "com.er453r",
            artifactId = TestProcessor.PLUGIN_ID,
            version = "0.0.1"
    )

    override fun isApplicable(project: Project, task: AbstractCompile) = true

}

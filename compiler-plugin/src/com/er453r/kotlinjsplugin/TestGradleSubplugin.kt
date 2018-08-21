package com.er453r.kotlinjsplugin

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.tooling.provider.model.ToolingModelBuilderRegistry
import javax.inject.Inject

class TestGradleSubplugin @Inject internal constructor(private val registry: ToolingModelBuilderRegistry) : Plugin<Project> {
    override fun apply(p0: Project?) {
        println("TestSubplugin apply ${TestProcessor.PLUGIN_ID}")
    }
}

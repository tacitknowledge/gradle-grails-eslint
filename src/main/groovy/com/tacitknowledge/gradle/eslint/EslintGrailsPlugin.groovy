package com.tacitknowledge.gradle.eslint

import com.moowork.gradle.node.NodePlugin
import org.gradle.api.Plugin
import org.gradle.api.Project

class EslintGrailsPlugin implements Plugin<Project> {
  void apply(Project project) {
    project.plugins.apply(NodePlugin)

    project.extensions.create('eslint', EslintGrailsExtension)

    project.tasks.with {
      def installEslint = create(
              name: 'installEslint',
              type: InstallEslint)

      def eslintPrepare = create(
              name: 'eslintPrepare',
              type: EslintPrepareTask,
              dependsOn: ['installEslint'])

      def eslintRun = create(
              name: 'eslintRun',
              type: EslintRunTask,
              dependsOn: ['eslintPrepare'])

      findByName('verify')?.dependsOn eslintRun
    }
  }
}

package com.tacitknowledge.gradle.eslint

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction

class EslintPrepareTask extends DefaultTask {

  EslintPrepareTask() {
    group = 'Eslint'
    description = 'Prepares eslint configuration '
  }

  @TaskAction
  void prepare() {
    new File(project.buildDir, '.eslintrc').text = project.eslint.configFile
  }

}

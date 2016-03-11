package com.tacitknowledge.gradle.eslint

import org.gradle.api.DefaultTask
import groovy.json.JsonBuilder
import groovy.json.JsonOutput
import groovy.json.JsonSlurper
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction

class EslintPrepareTask extends DefaultTask
{

	EslintPrepareTask() {
    group = 'Eslint'
    description = 'Prepares eslint configuration '
	}

  @TaskAction
  void prepare()
  {
    def config = new JsonSlurper().parseText(new JsonBuilder(project.eslint.config).toString())
    new File(project.buildDir, '.eslintrc').text = JsonOutput.toJson(config)

  }

}

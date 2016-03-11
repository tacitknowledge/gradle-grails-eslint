package com.tacitknowledge.gradle.eslint

import com.moowork.gradle.node.task.NodeTask

class EslintRunTask extends NodeTask
{

	EslintRunTask() {
		group = 'Eslint'
	  description = 'Runs eslint'
	}

  @Override
  void exec()
  {
    script = new File(project.node.nodeModulesDir, 'node_modules/eslint/bin/eslint.js')
		println "~~~~~~~~~~~~~~~~~"
		println config.reporter.output
    args = ['-f', 'html',
					  '-o', new File(project.buildDir, config.reporter.output),
					  '-c', new File(project.buildDir, '.eslintrc'),
						new File(project.projectDir, 'grails-app/assets/javascripts/')]
    super.exec()
  }
}

package com.tacitknowledge.gradle.eslint

import com.moowork.gradle.node.task.NodeTask

class RunEslint extends NodeTask
{

	RunEslint() {
		inputs.dir new File(project.projectDir, "grails-app/assets/javascripts/")
		outputs.dir new File(project.buildDir, 'reports/eslint/eslint-report.html')
	}

  @Override
  void exec()
  {
    script = new File(project.node.nodeModulesDir, 'node_modules/eslint/bin/eslint.js')

    args = ['-f', 'html', '-o', 'build/reports/eslint/eslint-report.html', '-c', new File(project.projectDir, '.eslintrc'), new File(project.projectDir, 'grails-app/assets/javascripts/')]
    super.exec()
  }
}

package com.tacitknowledge.gradle.eslint

import com.moowork.gradle.node.task.NodeTask

class RunEslint extends NodeTask
{
  @Override
  void exec()
  {
    script = new File(project.node.nodeModulesDir, 'node_modules/eslint/bin/eslint.js')

    args = ['-f', 'html', '-o', 'eslint-results.html', new File(project.projectDir, 'grails-app/assets/javascripts/')]
    super.exec()
  }
}

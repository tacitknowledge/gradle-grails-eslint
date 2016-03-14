package com.tacitknowledge.gradle.eslint

import com.moowork.gradle.node.task.NodeTask

class EslintRunTask extends NodeTask {

  EslintRunTask() {
    group = 'Eslint'
    description = 'Runs eslint'
  }

  @Override
  void exec() {
    script = new File(project.node.nodeModulesDir, 'node_modules/eslint/bin/eslint.js')
    args = ['-c', new File(project.buildDir, '.eslintrc')]

    if (project.eslint.outputPath) {
      args << '-o' << new File(project.buildDir, project.eslint.outputPath)
    }

    if (project.eslint.format){
      args << '-f' << project.eslint.format
    }

    project.eslint.sourceDirs.each {
      args << new File(project.projectDir, it)
    }


    super.exec()
  }
}

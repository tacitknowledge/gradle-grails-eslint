package com.tacitknowledge.gradle.eslint

import com.moowork.gradle.node.task.NpmTask

class InstallEslint extends NpmTask {
  InstallEslint() {
    group = 'Eslint'
    description = 'Installs eslint bin (node) into project'

    project.afterEvaluate {
      workingDir = project.node.nodeModulesDir

      def pkgName = project.eslint.version ? "eslint@${project.eslint.version}" : 'eslint'
      args = ['install', pkgName]
      args += project.eslint.additionalNodeDependencies?.collect { k, v -> "$k@$v" } ?: [:]

      List<File> moduleDirs = (['eslint'] + project.eslint.additionalNodeDependencies?.keySet()).
          collect { new File(project.node.nodeModulesDir, "node_modules/$it") }
      moduleDirs.each { outputs.dir it }
      enabled = !moduleDirs.every { it.exists() }
    }
  }

  @Override
  void exec() {
    executeExclusively {
      super.exec()
    }
  }

  void executeExclusively(Closure closure) {
    def npmUserFolder = new File(System.properties['user.home'], '.npm')
    npmUserFolder.mkdirs()
    def random = new RandomAccessFile(new File(npmUserFolder, 'node_modules_lock'), 'rw')
    def lock = null

    for (i in (1..5)) {
      try {
        logger.info("Trying to acquire lock to install eslint. Attempt nr $i.")
        lock = random.channel.tryLock()
      } catch (ignore) { /*noop*/
      }
      if (lock) {
        break
      } else {
        logger.warn("Attempt nr $i was unsuccessful. Sleeping 1min before the next attempt")
        sleep(60000)
      }
    }

    if (!lock) {
      throw new IllegalStateException("Can't acquire exclusive lock to setup eslint")
    }

    try {
      closure.run()
    } finally {
      if (lock) {
        lock.release()
        random.close()
      }
    }
  }
}
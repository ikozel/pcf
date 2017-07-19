//
package com.sample.pipeline

import org.jenkinsci.plugins.workflow.steps.FlowInterruptedException
import org.jenkinsci.plugins.workflow.support.steps.input.Rejection
//import org.junit.Assert
import groovy.json.*

class ComponentPipeline implements Serializable {
  def stages, args
  
  ComponentPipeline(Map args=[:], stages) {
    this.args                  = args
    this.stages                = stages
  }
  
  def main() {
    stages.ansiColor('xterm') {
      stages.node('master') {
        stages.stage('PWD') {
          shStage ('pwd')
        }
      }
    }
  }

def shStage(_cmd) {
    stages.stage("Test ${capitalizedEnvironment}") {
      stages.sh "${_cmd}"
    }
}
  
}


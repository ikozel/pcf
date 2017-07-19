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
  
def invoke() {
  stages.ansiColor('xterm') {
    stages.node('master') {
      stages.stage('PWD') {
        checkOutStage ('pwd; ls -la')
      }    
      stages.stage('PWD') {
        gradleStage ('pwd; ls -la')
      }
      
    }
  }
}

def checkOutStage() {
    stages.stage ('Checkout') {
      stages.checkout([
         $class: 'GitSCM' //,
         //branches: stages.scm.branches
      ])
    }
  }

  
def gradleStage(_cmd) {
    stages.stage('Checkout') {
      checkout scm
    }
    stages.stage("Scope ${_cmd}") {
      stages.sh "${_cmd}"
    }
    stages.stage('Build') {
      stages.sh 'gradle clean build'
    }
    stages.stage('Test') {
      shstages.sh'gradle test'
    }
    stages.stage('Package') {
      stages.sh 'gradle jar'
    }
}
  
}


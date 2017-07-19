package com.sample.pipeline

import org.jenkinsci.plugins.workflow.steps.FlowInterruptedException
import org.jenkinsci.plugins.workflow.support.steps.input.Rejection
import org.junit.Assert
import groovy.json.*

class ComponentPipeline implements Serializable {
  def stages, env, options, environments, aws_profile, args
  def componentName, release_version, environment_version
  def deployed_environments, repoPath, cookbook_version
  PipelineUtilities pipelineUtilities
  ProvisioningComponentPipeline(Map args=[:], stages, env) {
    this.args                  = args
    this.stages                = stages
    this.env                   = env
    this.options               = options
    this.componentName         = env.component_name
    this.environment_version   = env.environment_version
    this.deployed_environments = ""
    this.repoPath              = ""
    this.cookbook_version      = ""
  }
}

folder("PCF")
folder("PCF/Infra")

pipelineJob("PCF/Infra/environment") {

  label('Chef Provisioner Prod')

  parameters {
    def services = ['AppX','ServiceY', 'ComponentZ']
    def environments = ['cicd','dev','qa','stg','prod']
    choiceParam('service', services)
    choiceParam('environment', environments)
  }

  definition {

    cpsScm {

      scm {
        git {
          remote {
            credentials("")
            url("ps://github.com/ikozel/pcf.git")
            branch("*/master")
          }
        }
      }
      scriptPath('jenkins/Jenkinsfile')
    }
  }

}

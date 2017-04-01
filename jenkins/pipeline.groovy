folder("PCF")
folder("PCF/Infra")

pipelineJob("PCF/Infra/PCF-Environment") {

  label('Chef Provisioner Prod')

  parameters {
    def services = ['AppX','ServiceY', 'ComponentZ']
    def environments = ['cicd','dev','qa','stg','prod']
    def instance_memory = ['1G','2G']
    def total_space_memory = ['2G','3G']
    def space_routs = ['2','4']
    def service_instances = ['2','4']
    choiceParam('org_name', services)
    choiceParam('env_name', environments)
    choiceParam('instance_memory', instance_memory)
    choiceParam('total_space_memory', total_space_memory)
    choiceParam('space_routs', space_routs)
    choiceParam('service_instances', service_instances)
  }

  definition {

    cpsScm {

      scm {
        git {
          remote {
            credentials("")
            url("https://github.com/ikozel/pcf.git")
            branch("*/master")
          }
        }
      }
      scriptPath('jenkins/Jenkinsfile')
    }
  }

}

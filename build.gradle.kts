group = "ch.peters.daniel"
version = "1.0"

plugins {
  java
  application
  groovy
}

application {
  applicationName = "pidcontroller"
  mainClassName = "${project.group}.$applicationName.App"
}

dependencies {
  testImplementation("org.codehaus.groovy:groovy-all:2.5.5")
  testImplementation("org.spockframework:spock-core:1.2-groovy-2.5")
  testImplementation("junit:junit:4.12")
}

repositories {
  jcenter()
}

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
  implementation("com.google.guava:guava:23.0")
  testImplementation("org.codehaus.groovy:groovy-all:2.4.14")
  testImplementation("org.spockframework:spock-core:1.0-groovy-2.4")
  testImplementation("junit:junit:4.12")
}

repositories {
  jcenter()
}

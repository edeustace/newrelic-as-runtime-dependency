import sbt._
import play._
import sbt.Keys._
import play.Project._


object Build extends sbt.Build{

  val newRelicConfig =  config("new-relic").hide
  val newRelicAgent = "com.newrelic.agent.java" % "newrelic-agent" % "3.10.0" % newRelicConfig
  
  val root = play.Project( "root", "1.0").settings(
    ivyConfigurations += newRelicConfig,
    libraryDependencies ++= Seq(newRelicAgent),
    unmanagedClasspath in Runtime ++= update.value.select(configurationFilter("new-relic"))
  )
}
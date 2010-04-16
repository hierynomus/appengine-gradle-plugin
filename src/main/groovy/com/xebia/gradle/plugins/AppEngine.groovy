package com.xebia.gradle.plugins

import com.google.appengine.tools.admin.AppCfg
import org.apache.ivy.plugins.resolver.URLResolver
import org.gradle.api.InvalidUserDataException
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.plugins.ProjectPluginsContainer

class AppEngine implements Plugin {
  void use(Project project, ProjectPluginsContainer pluginContainer) {
    configureWarPlugin(project)

    project.convention.plugins.appengine = new AppEnginePluginConvention(project)

    project.configurations {
      appengine
    }
    
    addDependencies(project)

    project.task('upload') << {
      AppCfg.main("update", project.convention.plugins.appengine.exploded.toString())
    }

    project.upload.dependsOn project.war
  }

  def addDependencies(org.gradle.api.Project project) {
    project.dependencies {
      appengine "appengine:tools-api:1.3.2@jar"
    }
    project.repositories {
      add(new URLResolver()) {
        name = "GitHub AppEngine Gradle Plugin"
        addArtifactPattern 'http://github.com/hierynomus/appengine-gradle-plugin/downloads/[organization]-[module]-[revision].[ext]'
      }
    }
  }

  def configureWarPlugin(Project project) {
    if (!project.plugins.hasPlugin('war')) {
      throw new InvalidUserDataException("For AppEngine plugin, the war plugin should be enabled!")
    }

    // Add the exploded-war to the war task
    project.war.doLast {
      ant.unzip(src: project.war.archivePath, dest: project.convention.plugins.appengine.exploded)
    }
  }
}

class AppEnginePluginConvention {
  Properties props = new Properties()
  Project project
  File exploded

  def AppEnginePluginConvention(project) {
    this.project = project
    if (!new File("appengine.properties").exists()) {
      throw new InvalidUserDataException("appengine.properties should exist in build root dir")
    }
    props.load(new FileInputStream("appengine.properties"))
    init()
  }

  def init() {
    exploded = new File(project.buildDir, "exploded-war")
    System.setProperty("appengine.sdk.root", appEngineSdkRoot)
  }

  def propertyMissing(String name) { props[name] }
}
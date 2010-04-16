# Google AppEngine Gradle Plugin
This plugin can be used to upload an application built with Gradle to AppEngine

## Usage
In your _build.gradle_ file add:

    usePlugin nl.javadude.gradle.plugins.AppEngine

    repositories {
      add(new org.apache.ivy.plugins.resolver.URLResolver()) {
        name = "GitHub"
        addArtifactPattern 'http://github.com/hierynomus/adoptimizer/downloads/[organization]-[module]-[revision].[ext]'
      }
    }

    dependencies {
      compile 'javadude:appengine-gradle-plugin:0.1@jar"
    }


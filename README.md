# Google AppEngine Gradle Plugin
This plugin can be used to upload an application built with Gradle to AppEngine

## Usage
In your _build.gradle_ file add:

	buildscript {
	  repositories {
	    add(new org.apache.ivy.plugins.resolver.URLResolver()) {
	      name = "GitHub"
	      addArtifactPattern 'http://github.com/hierynomus/appengine-gradle-plugin/downloads/[organization]-[module]-[revision].[ext]'
	    }
	  }
	
	  dependencies {
	    classpath 'nl.javadude:appengine-gradle-plugin:0.2@jar'
	  }
	}

    usePlugin nl.javadude.gradle.plugins.AppEngine

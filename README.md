# Google AppEngine Gradle Plugin
This plugin can be used to upload an application built with Gradle to AppEngine

## Configuring
In your _build.gradle_ file add:

	buildscript {
	  repositories {
	    add(new org.apache.ivy.plugins.resolver.URLResolver()) {
	      name = "GitHub"
	      addArtifactPattern 'http://github.com/hierynomus/appengine-gradle-plugin/downloads/[organization]-[module]-[revision].[ext]'
	    }
	  }
	
	  dependencies {
	    classpath 'com.xebia:appengine-gradle-plugin:0.2@jar'
	    classpath 'appengine:tools-api:1.3.2@jar'
	  }
	}

    usePlugin com.xebia.gradle.plugins.AppEngine

## Usage
### Deploy
In order to deploy your application to Google AppEngine you can simply execute the following command:
	gradle upload


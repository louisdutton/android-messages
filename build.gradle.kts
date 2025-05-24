buildscript { repositories { google() } }

plugins {
  kotlin("android")                   version "1.9.24" apply false
  id("com.android.application")       version "8.5.0"  apply false
  id("com.android.library")           version "8.5.0"  apply false
  id("com.github.ben-manes.versions") version "0.51.0" apply false
}

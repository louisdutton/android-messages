plugins {
  id("com.android.application")
  id("org.jetbrains.kotlin.android")
}

val versionMajor = 1
val versionMinor = 0
val versionPatch = 0
val versionBuild = 0 // Bump for dogfood builds, public betas, etc.

val bundleId = "digital.dutton.example"

android {
  namespace = bundleId
  compileSdk = 34

  defaultConfig {
    applicationId = bundleId
    minSdk = 31
    targetSdk = 34
    versionCode = versionMajor * 10000 + versionMinor * 1000 + versionPatch * 100 + versionBuild
    versionName = "${versionMajor}.${versionMinor}.${versionPatch}"

    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    vectorDrawables { useSupportLibrary = true }
  }

  buildTypes {
    release {
      isMinifyEnabled = true
      isShrinkResources = true

      // Includes the default ProGuard rules files that are packaged with
      // the Android Gradle plugin. To learn more, go to the section about
      // R8 configuration files.
      proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
    }
  }

  compileOptions {
    sourceCompatibility = JavaVersion.VERSION_21
    targetCompatibility = JavaVersion.VERSION_21
  }
  kotlinOptions { jvmTarget = "21" }
  buildFeatures { compose = true }
  composeOptions { kotlinCompilerExtensionVersion = "1.5.14" }
  packaging { resources { excludes += "/META-INF/{AL2.0,LGPL2.1}" } }
}

dependencies {
  implementation("androidx.core:core-ktx:1.13.1")
  implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.8.4")
  implementation("androidx.activity:activity-compose:1.9.1")
  implementation(platform("androidx.compose:compose-bom:2024.06.00"))
  implementation("androidx.compose.ui:ui")
  implementation("androidx.compose.ui:ui-graphics")
  implementation("androidx.compose.ui:ui-tooling-preview")
  implementation("androidx.compose.material3:material3")
  testImplementation(kotlin("test"))
  androidTestImplementation("androidx.test.ext:junit:1.1.5")
  androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
  androidTestImplementation(platform("androidx.compose:compose-bom:2024.06.00"))
  androidTestImplementation("androidx.compose.ui:ui-test-junit4")
  debugImplementation("androidx.compose.ui:ui-tooling")
  debugImplementation("androidx.compose.ui:ui-test-manifest")
  // GSON
  implementation("com.google.code.gson:gson:2.8.9")
  // Constraint Layout
  implementation("androidx.constraintlayout:constraintlayout-compose:1.0.1")
  // Material Symbols
  implementation("androidx.compose.material:material-icons-extended:1.4.3")
  // Datastore
  implementation("androidx.datastore:datastore-preferences:1.1.1")
  implementation("androidx.datastore:datastore-preferences-rxjava2:1.1.1")
  implementation("androidx.datastore:datastore-preferences-rxjava3:1.1.1")
}

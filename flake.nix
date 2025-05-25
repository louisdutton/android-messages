{
  description = "My Android project";

  inputs = {
    nixpkgs.url = "github:NixOS/nixpkgs";
    flake-utils.url = "github:numtide/flake-utils";
  };

  outputs = {
    self,
    nixpkgs,
    flake-utils,
  }:
    flake-utils.lib.eachDefaultSystem (
      system: let
        pkgs = import nixpkgs {
          inherit system;
          config = {
            allowUnfree = true;
            android_sdk.accept_license = true;
          };
        };

        platformVersion = "35";
        buildToolsVersion = "35.0.0";
        systemImageType = "default";
        androidPkgs = pkgs.androidenv.composeAndroidPackages {
          buildToolsVersions = [buildToolsVersion];
          platformVersions = [platformVersion];
          systemImageTypes = [systemImageType];
          useGoogleAPIs = false;
          includeSystemImages = true;
        };
        androidSdk = androidPkgs.androidsdk;
      in
        with pkgs; {
          devShells.default = mkShell rec {
            buildInputs = [
              androidSdk
              gradle
              jdk
            ];

            ANDROID_HOME = "${androidSdk}/libexec/android-sdk";
            ANDROID_NDK_ROOT = "${ANDROID_HOME}/ndk-bundle";
            GRADLE_OPTS = "-Dorg.gradle.project.android.aapt2FromMavenOverride=${ANDROID_HOME}/build-tools/${buildToolsVersion}/aapt2";
          };

          packages.emulator = androidenv.emulateApp {
            inherit systemImageType platformVersion;
            name = "example";
            abiVersion = "arm64-v8a"; # arm64-v8a or x86_64
            # TODO: install app (difficult as the apk isn't tracked in git)
            # app = ./example.apk;
            # package = "digital.dutton.exampleapp";
            # activity = "digital.dutton.exampleapp.App";
          };
        }
    );
}

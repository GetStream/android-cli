#!/usr/bin/env bash
./gradlew --quiet "installDist" && "./build/install/android-cli/bin/android-cli" "$@"

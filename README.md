# Android-cli

**Commands to help out in the release.**

## Install

You can generate the CLI by running the command:

```
./gradlew installDist
```

And you'll get a nice CLI in the path: `./build/install/release-cli/bin/release-cli` 

put the bin file in your path to be able to use `android-cli` command:

```
export PATH="$PATH:$HOME/stream/android-cli/build/install/android-cli/bin"
```

Then run `android-cli`. You should see this output:

```
Usage: android-cli [OPTIONS] COMMAND [ARGS]...

Options:
  -h, --help  Show this message and exit

Commands:
  parse-unrelead-changelog  Parses the unreleased changeg .md file
  ktlint-selected           Applies ktlint only in the selected modules
  unit-test-selected        Applies unit tests only in the selected modules
```

## Commands

### parse-unreleased-changelog
This commands removes the unwanted information of `UNRELEASED_CHANGELOG.md` and prints it in the terminal stdout.

Example: 

```
android-cli ./path/to/UNRELEASED_CHANGELOG.md
```

### ktlint-selected
This commands applies **ktlint** in all the modules selected in a file. It should be used with [dag-command](https://github.com/leandroBorgesFerreira/dag-command). First create the `affected-modules.json` file: 

```
./gradlew dag-command
```

and then run the command with the path to `affected-modules.json` as the parameter: 

```
android-cli ktlint-selected  build/dag-command/affected-modules.json
```

### unit-test-selected

The command works just like `ktlint-selected`, but it applies unit tests for the affected modules. 

## Developing commands
If you are developing new commands for this CLI, you can iterate in your commands using `run.sh`. As the exaple: 

```
./run.sh [COMMAND] [PARAMTERS]
```



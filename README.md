# release-cli

**Commands to help out in the release.**

## Install

You can generate the CLI by running the command:

```
./gradlew installDist
```

And you'll get a nice CLI in the path: `./build/install/release-cli/bin/release-cli` 

## Usage

Currently it has only the command to parse `UNRELEASED_CHANGELOG`, but more commands can be added. 

If you would like to try it out, run: 

```
./build/install/release-cli/bin/release-cli UNRELEASED_CHANGELOG.md
```

After creating the CLI and you'll have the release common.output in your STDOUT. 

You can also use the `run.sh` script:

```
./run.sh UNRELEASED_CHANGELOG.md
```


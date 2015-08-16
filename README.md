# cat-cli

A command line utility to do cat-related things.

Uses [gradle](http://gradle.org/) as a build automation tool but there
is no dependency on having it installed on your system as the project
includes the [gradle wrapper](https://docs.gradle.org/current/userguide/gradle_wrapper.html).

Developed and tested in Linux. 

## Build

The project uses the gradle `fatJar` plugin to bundle all of the dependencies on a single
executable jar file.

`$ ./gradlew [clean] fatJar`

## Test

`$ ./gradlew [clean] test`

Test results will be available under in `cat-cli/build/reports/tests/index.html`.

## Usage

`$ java -jar cat-cli-1.0.jar <option>`

### Options

* `help` (or something other than a valid option) list options
* `file` downloads and saves cat picture in desktop
* `fact` show interesting cat fact
* `browser` open a cat picture in the default browser (if possible)
* `ascii` display cute cat ascii art

If no option is provided, the default one (`browser`) will be used.
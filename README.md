# AppiumTestProject

Requirements

JDK 8
Requirements for contributors

Git
Node
Yarn
To run tests in local machine

Appium Server
Android/iOS Device/Simulators
Usage

Runs the discovery plus app tests with given application file
Running the testsuites from command line, use the -t parameter in the command eg: -t=smoketest
Usage: run_tests -e=<local|remote> -p=<ios|android> -a=<application_file> -t=<testsuitename>
-e, --env             Environment: local|remote
-p, --platform        Platform name: ios|android
-a, --app-file        Application file: Optional for remote tests, if the application is already uploaded
-n, --app-name        Custom name for the uploaded application in remote server
-b, --build-name      Custom Build name to be shown in remote server. Optional; Auto populated with repository details
-t, --tag             Tag name: Optional, to run a set of tests that have a unique tag name eg: -t=smoketest"
-u, --testrail-upload User can able to upload the test execution results in TestRails using the testrail-upload status based on the
                       testsuites (eg:--testrail-upload = smoketest) . TestRail upload will happen only for suite based execution.
-q, --build-type      User can able to choose the build type as uat,dev,prod from the browser stack (eg --build-type=uat)
-h, --help            Help


> Run Tests with the application specifying the uploaded <application_name>
> ./run_tests.sh --env=remote --platform=android

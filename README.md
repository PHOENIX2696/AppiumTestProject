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

Runs the Appium app with given application file
-e, --env             Environment: local|remote
-p, --platform        Platform name: ios|android
-a, --app-file        Application file: Optional for remote tests, if the application is already uploaded
-n, --app-name        Custom name for the uploaded application in remote server
-b, --build-name      Custom Build name to be shown in remote server. Optional; Auto populated with repository details
-t, --tag             Tag name: Optional, to run a set of tests that have a unique tag name eg: -t=smoketest"
-h, --help            Help


> Run Tests with the application specifying the uploaded <application_name>
> ./run_tests.sh --env=remote --platform=android

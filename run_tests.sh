function help_text()
{
    echo "Runs the test app with given application file"
    echo "-e, --env             Environment: local|remote"
    echo "-p, --platform        Platform name: ios|android"
    echo "-d, --device          Device name"
    echo "-o, --device-os       Device Os Version"
    echo "-h, --help            Help"
}

# Get command line arguments
for i in "$@"
do
case $i in
    -e=*|--env=*)
        env="${i#*=}"
        shift
        ;;
    -p=*|--platform=*)
        platform="${i#*=}"
        shift
        ;;
    -d=*|--device=*)
        device="${i#*=}"
        shift
        ;;
    -o=*|--device-os=*)
        device_os_version="${i#*=}"
        shift
        ;;
      esac
done

#If parameter does NOT exist with set Environment Variable
if [ -z "$device" ]; then
    device=$DEVICE_NAME
fi

#If Device OS Version parameter does NOT exist with set Environment Variable
if [ -z "$device_os_version" ]; then
    device_os_version=$DEVICE_OS_VERSION
fi

#If parameter does NOT exist with set Environment Variable
if [ -z "$platform" ]; then
    platform=$DEVICE_PLATFORM
fi

#Check env
if [ "$env" != "local" -a "$env" != "remote" ]; then
    echo "Invalid test environment"
    # shellcheck disable=SC2242
    exit -1
fi

#Check platform
if [ -z "$run_tag" ]; then
    run_tag=""
    if [ "$platform" == "android" ]; then
        run_tag="@Android"

    elif [ "$platform" == "ios" ]; then
        run_tag="@iOS"

    elif [ "$platform" == "api" ]; then
        run_tag="@api"

    else
        echo "Invalid platform"
        # shellcheck disable=SC2242
        exit -1
    fi
fi

echo "Running Tests with the Cucumber Options Tag : ${run_tag}"

#Check input file
if [[ -z ${app_file}  &&  -f "$app_file" ]]; then
    echo "File '$app_file' doesn't exist"
    # shellcheck disable=SC2242
    exit -1
fi

if [ "$env" == "local" ]; then
    mvn test -q -Dtest.env=${env} -P${platform} -Ddevice.name=${device} -Ddevice.os.version=${device_os_version} -Dapplication.path=${app_file} "-Dcucumber.options=--tags ${run_tag} --tags ~@ignore"
fi

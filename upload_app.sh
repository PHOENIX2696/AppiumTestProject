#!/usr/bin/env bash

#Serer authentication details
AUTO_BS_SERVER_URL="https://api-cloud.browserstack.com/app-automate/upload"
AUTO_BS_USER_NAME="pprag_9yLUNK"
AUTO_BS_ACCESS_KEY="gSLkqd6z229wfymzFnrC"

#Check input file
if [[ -f "$1" ]]
then
    app_file=$1
else
    echo "File '$1' does not exist"
#    exit -1
fi

#Check input file
app_name="$2"
if [ -z $app_name ]; then
    if [ -n "$CIRCLE_PROJECT_REPONAME" ]; then
        app_name="${CIRCLE_PROJECT_REPONAME}-${CIRCLE_BUILD_NUM}"
    else
        echo "Application name in remote server needs to be provided"
#        exit -1
    fi
fi

echo "Uploading file: '$app_file'"
res=`curl -u "${AUTO_BS_USER_NAME}:${AUTO_BS_ACCESS_KEY}" -X POST ${AUTO_BS_SERVER_URL} -F file=@${app_file} -F data={\"custom_id\":\"${app_name}\"}`
echo "$res"

#Check whether the returned result has the custom id specified in the command
if [[ "$res" =~ "$app_name" ]];
then
    echo "Application is uploaded successfully with custom name: '${app_name}'"
else
    echo "Failed to upload the application file"
#    exit -1
fi

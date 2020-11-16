

#export TARGET_URL="https://api.github.com/repos/$REPOSITORY/releases?access_token=$GITHUB_TOKEN"
echo "Tagging build with $BUILD_NAME"
echo hhhhh
echo kkkkk
echo "$BUILD_NAME"
echo $EXAMPLE
echo $GITHUB_TOKEN
echo lllll


#body="{
#  \"tag__name\": \"$BUILD_NAME\",
#  \"target_commitish\": \"develop\",
#  \"name\": \"$BUILD_NAME\",
#  \"body\": \"Release of version $BUILD_NAME\",
#  \"draft\": true,
#  \"prerelease\": true
#}"

#echo $TARGET_URL

#GITHUB_TOKEN =
#curl -k -X POST \
#  -H "Content-Type: application/json" \
#  -d "$body" \
#  "https://api.github.com/repos/beyond-eyesight/chat/releases?access_token=$GITHUB_TOKEN"

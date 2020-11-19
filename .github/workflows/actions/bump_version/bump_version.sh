#!/bin/sh -l

last_version=$(git describe --tags `git rev-list --tags --max-count=1`)
echo "haha"
echo "$last_version"



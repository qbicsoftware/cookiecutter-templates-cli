#!/usr/bin/env bash

# generates javadocs using sphinx/javasphinx, pushes documentation to GitHub,
# readthedocs.org takes care of the rest

# clone the current repository using an access key as credentials
CLONED_REPO_FOLDER=".cloned_repo_$TRAVIS_BUILD_ID"
mkdir $CLONED_REPO_FOLDER
cd $CLONED_REPO_FOLDER
git clone https://github.com/$TRAVIS_REPO_SLUG

# read version from pom.xml
ARTIFACT_VERSION=$(mvn help:evaluate --quiet -DforceStdout=true -Dexpression=project.version)

# update docs/conf.py with the current version
sed -i "s/^version[[:space:]]*=[[:space:]]*.*$/version = $ARTIFACT_VERSION/g" docs/conf.py
sed -i "s/^release[[:space:]]*=[[:space:]]*.*$/release = $ARTIFACT_VERSION/g" docs/conf.py

# use javasphinx to generate javadocs
javasphinx-apidoc -o docs/. src/main

# add, commit, push, but make sure that Travis doesn't start a build!
# (see: https://docs.travis-ci.com/user/customizing-the-build/#skipping-a-build)
git add docs
git commit -m "[skip travis] Updated javadocs" -m "Travis build ID: $TRAVIS_BUILD_ID"
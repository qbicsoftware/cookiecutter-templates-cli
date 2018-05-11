#!/bin/bash

# rules of our build:
# - build/test everything on master/development, don't use any profile
# - use code coverage plug-in on everything from master/development
# - NEVER deploy pull requests to maven
# - deploy to maven using profile "release-build" if pushed to master branch 
# - deploy to maven using profile "development-build" if pushed to development branch

echo "[INFO] installing repository (compile, test, verify (integration tests), install)"
mvn --activate-profiles !development-build,!release-build --settings .travis.settings.xml clean cobertura:cobertura install
if [ "$TRAVIS_EVENT_TYPE" = "push" ]; then
    if [ "$TRAVIS_BRANCH" = "master" ]; then
        echo "[INFO] deploying release to maven repository (version string will be validated)"
        mvn --activate-profiles !development-build,release-build --settings .travis.settings.xml deploy
    elif [ "$TRAVIS_BRANCH" = "development" ]; then
        echo "[INFO] deploying SNAPSHOT to maven repository (version string will be validated)"
        mvn --activate-profiles development-build,!release-build --settings .travis.settings.xml deploy
    else
        echo "[WARN] Deploying only 'push' events from master/development branches (TRAVIS_EVENT_TYPE: '$TRAVIS_EVENT_TYPE'; TRAVIS_BRANCH: '$TRAVIS_BRANCH')"
    fi
else
    echo "[WARN] Non-push events (e.g., pull requests) are not deployed (TRAVIS_EVENT_TYPE: '$TRAVIS_EVENT_TYPE'; TRAVIS_BRANCH: '$TRAVIS_BRANCH')"
fi
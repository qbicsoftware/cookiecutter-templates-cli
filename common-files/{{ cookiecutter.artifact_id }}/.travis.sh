#!/usr/bin/env bash

# rules of our build:
# 1. build/test everything on any branch without using any profile
# 2. deploy ONLY push events to maven repo from both branches (release and snapshot branches)
# 3. deploy to maven using profile "release-build" if pushed to the release branch 
# 4. deploy to maven using profile "development-build" if pushed to snapshot branch

export RELEASE_BRANCH="master"
export SNAPSHOT_BRANCH="testing"

# if no argument is provided, default to pom.xml
if [ -z "$1" ]; then
    export POM_FILE="pom.xml"
else
    export POM_FILE=$1
fi

# 1) this applies to every branch, every event
echo "[INFO] installing in local repository (compile, test, verify (integration tests), install)"
mvn --activate-profiles !development-build,!release-build --settings .travis.settings.xml --file $POM_FILE clean cobertura:cobertura install
# 2.1) check if this is a push event
if [ "$TRAVIS_EVENT_TYPE" = "push" ]; then
    if [ "$TRAVIS_BRANCH" = "$RELEASE_BRANCH" ]; then
        # 3) push to release branches are deployed
        echo "[INFO] deploying release to maven repository (version string will be validated)"
        mvn --activate-profiles !development-build,release-build --settings .travis.settings.xml --file $POM_FILE deploy
    elif [ "$TRAVIS_BRANCH" = "$SNAPSHOT_BRANCH" ]; then
        # 4) snapshots as well
        echo "[INFO] deploying SNAPSHOT version to maven repository (version string will be validated)"
        mvn --activate-profiles development-build,!release-build --settings .travis.settings.xml --file $POM_FILE deploy
    else
        # 2.2) we only deploy push events
        echo "[WARN] Only 'push' events from $RELEASE_BRANCH/$SNAPSHOT_BRANCH branches are deployed (TRAVIS_EVENT_TYPE: '$TRAVIS_EVENT_TYPE'; TRAVIS_BRANCH: '$TRAVIS_BRANCH')"
    fi
else
    # 2.3) it's not a push event, so it won't be deployed
    echo "[WARN] Non-push events (e.g., pull requests) are not deployed (TRAVIS_EVENT_TYPE: '$TRAVIS_EVENT_TYPE'; TRAVIS_BRANCH: '$TRAVIS_BRANCH')"
fi
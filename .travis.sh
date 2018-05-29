#!/usr/bin/env bash

# we override the artifact_id variable; the tests in (test_template.py) depend on this value, so be careful when you change it

# create all projects (just four, for now... maybe later we need to do this using for loops)
rm -Rf template-test && ./generate.py --no-input --output-dir . --type=$TYPE \
                artifact_id=template-test \
                use_openbis_client=$USE_OPENBIS_CLIENT \
                use_openbis_raw_api=$USE_OPENBIS_CLIENT \
                use_qbic_databases=$USE_QBIC_DATABASES \
                && pytest --cov=./ \
                && mvn --file template-test/pom.xml --settings template-test/.travis.settings.xml cobertura:cobertura

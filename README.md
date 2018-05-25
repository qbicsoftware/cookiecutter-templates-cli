# Cookiecutter templates CLI tool
[![Build Status](https://travis-ci.org/qbicsoftware/cookiecutter-templates-cli.svg?branch=master)](https://travis-ci.org/qbicsoftware/cookiecutter-templates-cli)[![Code Coverage]( https://codecov.io/gh/qbicsoftwarecookiecutter-templates-cli}/branch/master/graph/badge.svg)](https://codecov.io/gh/qbicsoftware/cookiecutter-templates-cli)

## Table of contents
- [Cookiecutter templates CLI tool](#cookiecutter-templates-cli-tool)
    - [Table of contents](#table-of-contents)
    - [Motivation](#motivation)
    - [Available templates](#available-templates)
    - [Requirements](#requirements)
    - [Repository structure](#repository-structure)
    - [Usage](#usage)
        - [Introduction](#introduction)
        - [Location of generated code](#location-of-generated-code)
        - [Change output folder](#change-output-folder)
        - [Provide values without using prompts](#provide-values-without-using-prompts)
        - [Provide values without editing files](#provide-values-without-editing-files)
    - [Layout of the generated projects](#layout-of-the-generated-projects)
    - [What to do once you've generated your project?](#what-to-do-once-youve-generated-your-project)
        - [Write tests, check code coverage](#write-tests--check-code-coverage)
        - [Test your code locally](#test-your-code-locally)
            - [Testing a portlet locally using Jetty](#testing-a-portlet-locally-using-jetty)
            - [Testing CLI tools locally](#testing-cli-tools-locally)
        - [Create a new GitHub repository for your new project](#create-a-new-github-repository-for-your-new-project)
        - [Enable your GitHub repository on Travis CI](#enable-your-github-repository-on-travis-ci)
        - [Deploying your project as a Maven artifact](#deploying-your-project-as-a-maven-artifact)
        - [Pushing your first version](#pushing-your-first-version)
        - [Change default branch](#change-default-branch)
        - [Getting slack notifications from Travis CI (optional)](#getting-slack-notifications-from-travis-ci-optional)

## Motivation
There is a lot of boilerplate code associated to building Vaadin portlets for Liferay portals, so it makes sense to automate their creation. We first started by using [cookiecutter templates][cookiecutter] to generate a sample Liferay/Vaadin portlet based on [Maven][maven], but we have now created templates for other kinds of projects: command-line tools, portal and generic libraries.

## Available templates
* Vaadin Portlet running on Liferay: this is your common, run-of-the-mill portlet.
* Command-line tools: tools to be used using a console (e.g., [qpostman-cli](https://github.com/qbicsoftware/postman-cli)).
* Portlet libraries: libraries using Liferay and Vaadin dependencies (e.g., [portal-utils-lib](https://github.com/qbicsoftware/portal-utils-lib)).
* Generic Java libraries: libraries that don't have Vaadin or Liferay as dependencies (e.g., [openbis-client-lib](https://github.com/qbicsoftware/openbis-client-lib)).

## Requirements
You will need the following:

* Python, version 2.7 or more recent.
* [Cookiecutter][cookiecutter], version 1.5 or more recent.
* A Java 1.8 compatible SDK.
* [Apache Maven][maven].
* [Travis CI Client][travis-console].
* Access to the [QBiC Software GitHub organization](https://github.com/qbicsoftware) so you can create your own GitHub repository.
* [portal-utils-lib](https://github.com/qbicsoftware/portal-utils-lib)

## Repository structure
This is a simplified view of the structure of this repository:

```bash
.
├── common-files
│   
└── cookiecutters
    ├── cli
    │   ├── {{ cookiecutter.artifact_id }}
    │   │   ├── pom.xml
    │   │   └── src
    │   └── cookiecutter.json
    ├── generic-lib
    │   ├── {{ cookiecutter.artifact_id }}
    │   │   ├── pom.xml
    │   │   └── src
    │   └── cookiecutter.json
    ├── portal-lib
    │   ├── {{ cookiecutter.artifact_id }}
    │   │   ├── pom.xml
    │   │   └── src
    │   └── cookiecutter.json
    └── portlet
        ├── {{ cookiecutter.artifact_id }}
        │   ├── pom.xml
        │   └── src
        └── cookiecutter.json
 ```

The first thing you will probably notice is the strange name (`{{ cookiecutter.artifact_id }}`) given to some folders. [Cookiecutter][cookiecutter] is able to dynamically resolve variables found in text files and file names. This is a great feature, because the name of the *root folder* of our projects should match the artifact ID, as defined in the `pom.xml` file. 

Notice how each of available templates has its own sub-folder under `cookiecutters` (e.g., the `cookiecutters/generic-lib` folder contains code specific for generic Java libraries). However, since our projects have a few files in common, we have also created a `common-files` folder.

## Usage
### Introduction
`generate.py` is a wrapper Python script that helps you automate the task of creating a new project. It uses [Cookiecutter's][cookiecutter] Python API internally to use templates. You can display its usage like so:

```bash
$ ./generate.py --help
```

To generate a specific template, you use the `-t`/`--type` parameter:

```bash
$ ./generate.py --type <type>
```

Supported types are `portlet`, `cli`, `portal-lib` and `generic-lib`, so if you want to generate a Vaadin portlet for Liferay, you would run the following command:

```bash
$ ./generate.py --type portlet
```

Regardless of which template type you are using, you will immediately see that you are prompted to enter some values, as shown here:

```bash
author [Winnie Pooh]: Homer Simpson
email [winnie.the.pooh@qbic.uni-tuebingen.de]: simpson@burns.com
artifact_id [sample-portlet]: donut-portlet
display_name [Sample Portlet]: Donut Portlet
version [0.0.1-SNAPSHOT]: 
short_description [Will never portLET you go.]: Mmm donuts
main_ui [SamplePortletUI]: DonutPortletUI
copyright_holder [QBiC]: Mr. Burns
Select use_openbis_client:
1 - yes
2 - no
Choose from 1, 2 [1]: 1
Select use_openbis_raw_api:
1 - yes
2 - no
Choose from 1, 2 [1]: 2
Select use_qbic_databases:
1 - yes
2 - no
Choose from 1, 2 [1]: 1
```

The values shown between brackets are the defaults. To use the default value (as Homer did here for `version`), simply press `ENTER` without entering any other text. Default values are provided in `cookiecutter.json` files (there's one for each template type). In any case, **make sure to consult our naming and versioning conventions guide**.

### Location of generated code
`generate.py` will create your project in the `generated` folder. All templates generate a very simple sample project.

The name of the generated folder is determined by the value of the ``{{ cookiecutter.artifact_id }}`` variable (i.e., ``donut-portlet`` in our example). So in this case, you will see your generated sample porlet in the folder `generated/donut-portlet`.

### Change output folder
If you want your created projects to be placed somewhere else, you can use the `-o`/`--output-dir` argument to tell `generate.py` where to place its output:

```bash
$ ./generate.py --type portal-lib --output-dir /tmp/generated-projects
```

### Provide values without using prompts
If you do not want to be prompted for values, you can use the `--no-input` flag:

```bash
$ ./generate.py --type portal-lib --no-input
```

This will use whichever values are stored in the corresponding `cookiecutter.json` file. In this case, the defaults values will be loaded from `cookiecutters/portal-lib/cookiecutter.json`. 

### Provide values without editing files
But what if you do not want to edit `cookiecutter.json` files everytime? You can use positional arguments in the form of `name=value`, like so:

```bash
$ ./generate.py --type cli --no-input artifact_id=sample-cli version=1.1-SNAPSHOT
```

## Layout of the generated projects
`generate.py` will generate a folder which you can immediately use for development. The contents differ across template types. Here, for the sake of clarity, we will keep using our sample portlet, `donut-portlet`. Portlets are, by far, the most intricate template types because there are many files you have to configure properly for your portlet to work properly. Have a look at the generated structure:

```bash
donut-portlet/
├── CODE_OF_CONDUCT.md
├── LICENSE
├── pom.xml
├── README.md
└── src
    ├── main
    │   ├── java
    │   │   └── life
    │   │       └── qbic
    │   │           └── portal
    │   │               └── portlet
    │   │                   └── DonutPortletUI.java
    │   ├── resources
    │   │   ├── life
    │   │   │   └── qbic
    │   │   │       └── portlet
    │   │   │           └── AppWidgetSet.gwt.xml
    │   │   ├── log4j2.xml
    │   │   └── portlet.properties
    │   └── webapp
    │       ├── VAADIN
    │       │   └── themes
    │       │       └── mytheme
    │       │           ├── addons.scss
    │       │           ├── mytheme.scss
    │       │           ├── styles.css
    │       │           └── styles.scss
    │       └── WEB-INF
    │           ├── liferay-display.xml
    │           ├── liferay-plugin-package.properties
    │           ├── liferay-portlet.xml
    │           ├── portlet.xml
    │           └── web.xml
    └── test
        └── java
            └── life
                └── qbic
                    └── portal
                        └── portlet
                            └── DonutPortletUITest.java
```

## What to do once you've generated your project?
`generate.py` creates just a sample project. Sadly, you will still have to write your own code.

### Write tests, check code coverage
The generated folder already contains simple [jUnit](junit) tests (i.e., in `src/test/java/life/qbic/portal/portlet/DonutPortletUITest.java`). Writing code that tests your code is an important part of the development lifecycle (see: https://makeameme.org/meme/Yo-dawg-I-wgn8jg).

As a general guideline, try to code the _logic_ of your portlet independent of the user interface so you can easily write code that tests your portlet.

[Maven][maven] has been configured to execute unit tests under the `src/test` folder that match the _*Test_ name (e.g., `DonutPortletUITest`). To locally run the unit tests and generate a code coverage report, use the following command:

```bash
$ mvn cobertura:cobertura
```

Similarly, we have configured the [Maven][maven] plug-ins to run integration tests. These tests are also under the `src/test` folder, but their names must end with _*IntegrationTest_, such as `DonutPortletUIIntegrationTest`.

### Test your code locally
You can easily run the unit and integration tests for libraries you have written. There is no direct interaction with a user. This is different for portlets and CLI tools.

#### Testing a portlet locally using Jetty
Go to the generated folder (i.e., `generated/donut-portlet` in our case) and run:

```bash
$ mvn jetty:run
```

You should see an output similar to:

```bash
[INFO] Started ServerConnector@67c06a9e{HTTP/1.1,[http/1.1]}{0.0.0.0:8080}
[INFO] Started @30116ms
[INFO] Started Jetty Server
```

Direct your browser to [localhost:8080](http://localhost:8080). If everything went fine, you will see a portlet with several controls. So far so good, congratulations!

Interact with the UI and, if this is your first portlet, we strongly suggest you to try to change a few things in the code and see what happens after you test again. 

#### Testing CLI tools locally
We configured a [Maven][maven] plug-in to generate *stand-alone* JAR files. That is, [Maven][maven] will package all of the needed dependencies inside one single JAR file. Let's assume that you used the `cli` template as such:

```bash
$ ./generate.py --type cli --no-input artifactId=donut-cli version=1.0-SNAPSHOT
```

This will generate your new CLI tool in the `generated/donut-cli` folder. To test your CLI tool locally, you first need to *package* your artifact using [Maven][maven] in the `generated/donut-cli` folder:

```bash
$ mvn package
```

You then need to use the following command:

```bash
$ java -jar target/<artifactId>-<version>-jar-with-dependencies.jar
```

That is:

```bash
$ java -jar target/donut-cli-1.0-SNAPSHOT-jar-with-dependencies.jar
```

### Create a new GitHub repository for your new project
You now have a new QBiC project with all the required dependencies and configuration files. You still need to create a remote repository for it, though, so it's available for everyone. Follow [this guide](https://help.github.com/articles/create-a-repo/) to create a repository on GitHub. For this example, we will still use `donut-portlet` as the name of our repository. You need to create your GitHub repository under the [QBiC's GitHub organization](https://github.com/qbicsoftware), so you need writing access.

Navigate to your new repository's website in GitHub (i.e., [github.com/qbicsoftware/donut-portlet](https://github.com/qbicsoftware/donut-portlet)) and click on _Settings_. On the left side, click on _Integrations & Services_ and add the _Travis CI_ service. Leave all fields as they are and click on the _Add Service_ button.

### Enable your GitHub repository on Travis CI
The generated `donut-portlet` folder contains a `.travis.yml` file that will help you integrate your GitHub repository with [Travis CI][travis], our continuous integration service. Broadly speaking, everytime you _push_ a change into your GitHub repository, [Travis CI][travis] will download the code from your repository, compile it, run the unit tests and generate a code coverage report. Follow [this guide](https://docs.travis-ci.com/user/getting-started/#To-get-started-with-Travis-CI) to enable your new GitHub repository in Travis CI.

### Deploying your project as a Maven artifact
Even though our [Maven][maven] repository is visible to everyone publicly as read-only, you need to provide [Travis CI][travis] some credentials so it can _deploy_ artifacts into it. You will need to modify your `.travis.yml` file to add the encrypted username and password of our [Maven][maven] repository. In your local GitHub repository directory (i.e., `donut-portlet`) run the following commands using [the Travis console][travis-console]:

```bash
$ travis encrypt "MAVEN_REPO_USERNAME=<username>" --add env.global
$ travis encrypt "MAVEN_REPO_PASSWORD=<password>" --add env.global
```

Ask the people who wrote this guide about the proper values of `<username>` and `<password>`. Encryption and decryption keys in [Travis CI][travis] are bound to their GitHub repository, so you cannot simply copy them from other places.

### Pushing your first version
In your local GitHub repository directory (i.e., `donut-portlet`) run the following commands:

```bash
$ git init
$ git add .
$ git commit -m "Initial commit before pressing the 'flush radioactive material' button"
$ git remote add origin https://github.com/qbicsoftware/donut-portlet
$ git push origin master
$ git checkout -b development
$ git push origin development
```

Of course, you must replace `donut-portlet` with the real name of your repository. You can now start using your repository containing your brand new portlet.

### Change default branch
We strongly recommend you to set the `development` branch as your default branch by following [this guide](https://help.github.com/articles/setting-the-default-branch/). 

### Getting slack notifications from Travis CI (optional)
You can edit the `.travis.yml` file to tell Travis to send slack notifications. In your GitHub local repository folder execute:

```bash
$ travis encrypt "<your GitHub Account>:<token>" --add notifications.slack.rooms
```

Where `<token>` can be obtained by clicking on the "Edit configuration" icon (it looks like a pencil) [in this page](https://qbictalk.slack.com/apps/A0F81FP4N-travis-ci).

[maven]: https://maven.apache.org/
[cookiecutter]: https://cookiecutter.readthedocs.io
[junit]: https://junit.org
[travis]: https://travis-ci.org/
[travis-qbic]: https://travis-ci.org/profile/qbicsoftware
[travis-console]: https://github.com/travis-ci/travis.rb

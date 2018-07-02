# Cookiecutter templates CLI tool
[![Build Status](https://travis-ci.com/qbicsoftware/cookiecutter-templates-cli.svg?branch=development)](https://travis-ci.com/qbicsoftware/cookiecutter-templates-cli)[![Code Coverage](https://codecov.io/gh/qbicsoftware/cookiecutter-templates-cli/branch/development/graph/badge.svg)](https://codecov.io/gh/qbicsoftware/cookiecutter-templates-cli)

## Table of contents
  * [Motivation](#motivation)
  * [Available templates](#available-templates)
  * [Structure of this repository](#structure-of-this-repository)
  * [Requirements](#requirements)
    + [Conda](#conda)
  * [Making sure it all went fine](#making-sure-it-all-went-fine)
  * [Usage](#usage)
    + [Generating a new proejct](#generating-a-new-proejct)
    + [How to change default values](#how-to-change-default-values)
      - [1. Providing defaults using a `.cookiecutterrc` file](#1-providing-defaults-using-a--cookiecutterrc--file)
      - [2. Providing defaults via the command-line](#2-providing-defaults-via-the-command-line)
    + [Avoid using prompts](#avoid-using-prompts)
    + [Location of generated code](#location-of-generated-code)
    + [Change output folder](#change-output-folder)

## Motivation
There is a lot of boilerplate code associated to building Vaadin portlets for Liferay portals, so it makes sense to automate their creation by using templates. 

We first started by using a [cookiecutter template][cookiecutter] to generate a sample Liferay/Vaadin portlet based on [Maven][maven], but we have now created templates for other kinds of Java projects as well: services, JavaFX applications, command-line tools, portal and generic libraries. We have also added detailed documentation for developers.

This guide covers information regarding the usage of this tool. If you are looking for information about portlet development or other software development tasks, you might want to:
  - Take a look at [this guide](https://github.com/qbicsoftware/cookiecutter-templates-cli/blob/development/README_DEVELOPMENT.md).
  - Check out the `sample-code` folder. We created sample projects for each supported project type.

## Available templates
This tool is able to generate boilerplate code for the following type of projects:
* Vaadin portlet running on Liferay: this is your common, run-of-the-mill portlet.
* Command-line tools: tools to be used using a console (e.g., [qpostman-cli](https://github.com/qbicsoftware/postman-cli)).
* Graphical user interface applications: stand-alone JavaFX applications, such as [experiment-graph-gui](https://github.com/qbicsoftware/experiment-graph-gui).
* Services: these are similar to command-line tools in their structure, but once a service has been started, it stays "active" until shutdown, like our [portlet deployer service](https://github.com/qbicsoftware/portlet-deployer-service).
* Portlet libraries: libraries using Liferay and Vaadin dependencies (e.g., [portal-utils-lib](https://github.com/qbicsoftware/portal-utils-lib)).
* Generic Java libraries: libraries that don't have Vaadin or Liferay as dependencies (e.g., [openbis-client-lib](https://github.com/qbicsoftware/openbis-client-lib)).

## Structure of this repository
This is a simplified view of the structure of this repository:

```bash
.
├── common-files
│   └── {{ cookiecutter.artifact_id }}
|
├── cookiecutters
│   ├── cli
│   │   └── {{ cookiecutter.artifact_id }}
│   ├── generic-lib
│   │   └── {{ cookiecutter.artifact_id }}
│   ├── gui
│   │   └── {{ cookiecutter.artifact_id }}
│   ├── portal-lib
│   │   └── {{ cookiecutter.artifact_id }}
│   ├── portlet
│   │   └── {{ cookiecutter.artifact_id }}
│   └── service
│       └── {{ cookiecutter.artifact_id }}
|
└── sample-code
    ├── cli
    ├── generic-lib
    ├── gui
    ├── portal-lib
    ├── portlet
    └── service
 ```

The first thing you will probably notice is the strange name, `{{ cookiecutter.artifact_id }}`, given to some folders and files. [Cookiecutter][cookiecutter] is able to dynamically resolve variables found in text files and file/folder names. The name of the *root folder* of our projects should match the artifact ID, as defined in the `pom.xml` file. 

Each of the available templates has its own sub-folder under `cookiecutters` (e.g., the `cookiecutters/generic-lib` folder contains code specific for generic Java libraries). However, since our projects have a few files in common, we have also created a `common-files` folder.

The `sample-code` folder contains sample code for each type of supported project. Each sample was generated using this tool.

## Requirements
Here we are just covering the usage of this tool. You will need Python because generation of projects is based in [Cookiecutter][cookiecutter], which is a Python-based tool. It really doesn't matter whether you use `pip` or install Python directly and then you install dependencies on your own. That's up to you. That being said, you will only get support from us if you follow this guide.

### Conda
We use [Cookiecutter][cookiecutter] to create new QBiC software projects using templates. QBiC also maintains and develops a collection of Python tools for non-portal development (e.g., https://github.com/qbicsoftware/etl-scripts, https://github.com/qbicsoftware/barcode-creation), so it makes sense to use a package manager to distribute and use our code.

The official way to use these templates is to use the [Conda][conda] package manager. We will soon release this and other QBiC tools as [Conda][conda] packages (see: https://github.com/qbicsoftware/cookiecutter-templates-cli/issues/7). 

Once you have installed [Conda][conda], create a [Conda environment](https://conda.io/docs/user-guide/tasks/manage-environments.html) on which you will install all required Python dependencies:
```bash
conda create --channel conda-forge --name qbic cookiecutter python=3
```

Let's break down this command:
  - `conda create`: here you are creating a new [Conda][conda] environment.
  - `--channel conda-forge`: instruct [Conda][conda] to also search for packages in the  _channel_ called `conda-forge`. You can think of channels as repositories. In any case, [Cookiecutter][cookiecutter] is available in the `conda-forge` channel.
  - `--name qbic`: this is the name of the new [Conda][conda] environment you are creating.
  - `cookiecutter python=3`: dependencies are given as positional arguments. This means that your `qbic` Python environment depends on Python 3 and [Cookiecutter][cookiecutter].

## Making sure it all went fine
This is a fairly new tool, so, for now, you will first need to clone this repository. **Make sure to update your local copy regularly** (i.e., by using `git pull`) so you won't miss the latest features and bug fixes. This is a command-line tool, so having a terminal open while you read this guide would be useful.

You can now *activate* your [Conda environment](https://conda.io/docs/user-guide/tasks/manage-environments.html):

```bash
conda activate qbic
```

If this fails, the printed warnings/errors are pretty self-explanatory, but have in mind that the `conda activate` command [has been made available in version 4.4](https://conda.io/docs/release-notes.html#new-feature-highlights). Make sure you installed the latest version of [Conda][conda].

To test that you have installed all required tools to use these templates, invoke the main script of this project:

```bash
./generate.py --help
```

Note: `generate.py` is an executable Python script. However, if you are using Windows, you might need to invoke the script as shown here:

```bash
python generate.py --help
```

`generate.py` helps you automate the task of creating a new project. It is located at the root folder of this repository. 

## Usage
### Generating a new proejct
To generate a specific template, use the `-t`/`--type` parameter:

```bash
./generate.py --type <type>
```

Supported types are `portlet`, `cli`, `portal-lib`, `generic-lib`, `service` and `gui`, so if you want to generate a Vaadin portlet for Liferay, you would run the following command:

```bash
./generate.py --type portlet
```

Regardless of which template type you are using, you will immediately see that you are prompted to enter some values, as shown here:

```bash
author [Winnie Pooh]: Homer Simpson
email [winnie.the.pooh@honey.uni-tuebingen.de]: simpson@burns.com
artifact_id [sample-portlet]: donut-portlet
display_name [Sample Portlet]: Donut Portlet
version [1.0.0-SNAPSHOT]: 
...
```
Our [unofficial development guide](https://github.com/qbicsoftware/cookiecutter-templates-cli/blob/development/README_DEVELOPMENT.md) contains a detailed explanation of each of the variables.

Values shown between brackets are the defaults. To use the default value (as Homer did here for `version`), simply press `ENTER` without entering any other text. Default values are found in `cookiecutter.json` files (there's one for each template type) and in [Cookiecutter's user configuration file](http://cookiecutter.readthedocs.io/en/latest/advanced/user_config.html).


### How to change default values
There are two recommended ways to change default values without having to edit those pesky `cookiecutter.json` files. Of course, you can edit `cookiecutter.json` files, but that's not the most elegant solution.

#### 1. Providing defaults using a `.cookiecutterrc` file
After repeatedly using this tool, you will surely become annoyed at the fact that you have to type your name and your email address over and over, even though they haven't changed in the last couple of years. 

Luckily, [Cookiecutter offers a global configuration file](http://cookiecutter.readthedocs.io/en/latest/advanced/user_config.html). Create a file named `.cookiecutterrc` in your home folder (this varies across operating systems) and include your *global defaults* in it as shown here:

```bash
default_context:
  author: "Homer Simpson"
  email: "simpson@burns.com"
  copyright_holder: "Mr. Burns"
  # ... other variables ...
```

Now, every time Homer uses this tool, his global default values will be used and he will be able to simply press `ENTER` and use them:

```bash
./generate.py ...

author [Homer Simpson]:
email [simpson@burns.com]:
copyright_holder[Mr. Burns]:
# ... and so on...
```

Values found in your `.cookiecutterrc` file **will only override** values provided in `cookiecutter.json` files.

#### 2. Providing defaults via the command-line
You can use positional arguments in the form of `name=value` to provide defaults:

```bash
./generate.py --type cli artifact_id=sample-cli version=1.1.0-SNAPSHOT
```

Values provided via the command-line **will always override** values provided in `cookiecutter.json` files and in [your Cookiecutter user configuration file](http://cookiecutter.readthedocs.io/en/latest/advanced/user_config.html).

### Avoid using prompts
If you do not want to be asked for values, you can use the `--no-input` flag:

```bash
./generate.py --type portal-lib --no-input
```

You can also use the `--no-input` flag while providing command-line defaults:

```bash
./generate.py --type portlet --no-input use_openbis_client=no version=2.1.1
```

### Location of generated code
`generate.py` will create your project folder in the `generated` folder. All templates generate an empty project and each project will get its own folder. The name of the generated folder is determined by the value of the `artifact_id` variable (i.e., `donut-portlet` in our example). So in this case, you will see your generated sample porlet in the folder `generated/donut-portlet`. You can think of this folder as your local GitHub repository, feel free to move it to a more convenient location (e.g., `/home/simpson/projects`).

### Change output folder
If you want your created projects to be placed somewhere else, you can use the `-o`/`--output-dir` argument to instruct `generate.py` to place its output somewhere else:

```bash
./generate.py --type portal-lib --output-dir /home/simpson/projects
```

[maven]: https://maven.apache.org/
[cookiecutter]: https://cookiecutter.readthedocs.io
[junit]: https://junit.org
[travis]: https://travis-ci.com/
[travis-qbic]: https://travis-ci.com/profile/qbicsoftware
[travis-console]: https://github.com/travis-ci/travis.rb
[conda]: https://conda.io/docs/

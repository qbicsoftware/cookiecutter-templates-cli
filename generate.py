#!/usr/bin/env python

import argparse, shutil, os
from cookiecutter.main import cookiecutter

CLI_FOLDER = 'cli'
GENERIC_LIB_FOLDER = 'generic'
PORTAL_LIB_FOLDER = 'portal'
PORTLET_FOLDER = os.path.join('portal', 'portlet')

WORKING_FOLDER = 'cookiecutter_working_folder'
COMMON_FILES_FOLDER = 'common-files'

def main():
    parser = argparse.ArgumentParser(description='QBiC Project Generator.', prog='generate.py')
    parser.add_argument('-t', '--type', choices=['cli', 'generic-lib', 'portal-lib', 'portlet'], required=True, help='The type of artifact you want to generate (i.e., generic/portal library, CLI tool or portlet).')
    args = parser.parse_args()

    generate_cookiecutter_project(args)

def generate_cookiecutter_project(kwargs):
    cookiecutter_folder = os.path.join('cookiecutters', {
        'cli': CLI_FOLDER,
        'generic-lib': GENERIC_LIB_FOLDER,
        'portal-lib': PORTAL_LIB_FOLDER,
        'portlet': PORTLET_FOLDER
    }[kwargs.type])

    prepare_cookiecutter_template(cookiecutter_folder)
    cookiecutter(WORKING_FOLDER, no_input=True, overwrite_if_exists=True)
    
# 
def prepare_cookiecutter_template(cookiecutter_folder):
    refresh_working_folder()
    copy_cookiecutter_files_to_working_folder(cookiecutter_folder)
    copy_common_files_to_working_folder()

# copies files from one of the cookiecutter folders (e.g., portal/portlet) to our working directory
def copy_cookiecutter_files_to_working_folder(origin_folder):
    shutil.copytree(origin_folder, WORKING_FOLDER)

# refreshes our working folder (rm -Rf && mkdir)
def refresh_working_folder():
    shutil.rmtree(WORKING_FOLDER, ignore_errors=True)
    os.makedirs(WORKING_FOLDER)

# copies files from the common files folder to the destination folder
def copy_common_files_to_working_folder():
    shutil.copytree(COMMON_FILES_FOLDER, WORKING_FOLDER)
    #common_files = os.listdir(COMMON_FILES_FOLDER)
    # we ignore log4j configuration, because it needs to be packed in the src/main/resources folder of the working folder
    #for common_file in common_files:
    #    shutil.copy(os.path.join(COMMON_FILES_FOLDER, common_file), os.path.join(WORKING_FOLDER, common_file))

if __name__ == "__main__":
    main()

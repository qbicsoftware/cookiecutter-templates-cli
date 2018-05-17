#!/usr/bin/env python

import argparse, shutil, os, distutils
from distutils import dir_util
from cookiecutter.main import cookiecutter

# these variables are folder paths relative to the location of this script
COOKIECUTTERS_ROOT_FOLDER = 'cookiecutters'
CLI_FOLDER = 'cli'
GENERIC_LIB_FOLDER = 'generic'
PORTAL_LIB_FOLDER = 'portal'
PORTLET_FOLDER = os.path.join('portal', 'portlet')
WORKING_FOLDER = '.cookiecutter_working_folder'
COMMON_FILES_FOLDER = 'common-files'

def main():
    parser = argparse.ArgumentParser(description='QBiC Project Generator.', prog='generate.py')
    parser.add_argument('-t', '--type', choices=['cli', 'generic-lib', 'portal-lib', 'portlet'], required=True, help='The type of artifact you want to generate (i.e., generic/portal library, CLI tool or portlet).')
    args = parser.parse_args()

    generate_cookiecutter_project(args)

def generate_cookiecutter_project(kwargs):
    cookiecutter_folder = {
        'cli': CLI_FOLDER,
        'generic-lib': GENERIC_LIB_FOLDER,
        'portal-lib': PORTAL_LIB_FOLDER,
        'portlet': PORTLET_FOLDER
    }[kwargs.type]

    prepare_cookiecutter_template(os.path.join(COOKIECUTTERS_ROOT_FOLDER, cookiecutter_folder))
    cookiecutter(WORKING_FOLDER, no_input=True, overwrite_if_exists=True)
    
# removes any lingering working folder, copies content from the desired cookecutter folder (e.g., portal/portlet)
def prepare_cookiecutter_template(cookiecutter_folder):
    shutil.rmtree(WORKING_FOLDER, ignore_errors=True)
    copy_cookiecutter_files_to_working_folder(cookiecutter_folder)
    copy_common_files_to_working_folder()

# copies files from one of the cookiecutter folders (e.g., portal/portlet) to our working directory
def copy_cookiecutter_files_to_working_folder(origin_folder):
    #shutil.copytree(origin_folder, WORKING_FOLDER)
    dir_util.copy_tree(origin_folder, WORKING_FOLDER)

# copies files from the common files folder to the destination folder
def copy_common_files_to_working_folder():
    dir_util.copy_tree(COMMON_FILES_FOLDER, WORKING_FOLDER)
    """ for item in os.listdir(COMMON_FILES_FOLDER):
        source = os.path.join(COMMON_FILES_FOLDER, item)
        dest = os.path.join(WORKING_FOLDER, item)
        if os.path.isdir(source):
            shutil.copytree(source, dest)
        else:
            shutil.copy2(source, dest) """

if __name__ == "__main__":
    main()

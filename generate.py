#!/usr/bin/env python

import argparse, shutil, os, distutils
from distutils import dir_util
from cookiecutter.main import cookiecutter
from cookiecutter.cli import validate_extra_context

# these variables are folder paths relative to the location of this script
COOKIECUTTERS_ROOT_FOLDER = 'cookiecutters'
WORKING_FOLDER = '.cookiecutter_working_folder'
COMMON_FILES_FOLDER = 'common-files'
DEFAULT_OUTPUT_DIR = 'generated'

# parses arguments
def main():
    parser = argparse.ArgumentParser(description='QBiC Project Generator.', prog='generate.py', formatter_class=argparse.ArgumentDefaultsHelpFormatter)
    parser.add_argument('-t', '--type', choices=['cli', 'generic-lib', 'portal-lib', 'portlet', 'service', 'gui'], required=True, default='portlet',
        help='The type of artifact you want to generate (i.e., generic Java library, portal library, CLI tool or portlet).')
    parser.add_argument('--no-input', action='store_true', 
        help='If set, default values, as defined in the correspoing cookiecutter.json, will be used and no prompt will be displayed. There is one cookiecutter.json file associated with each type (e.g., cli/cookiecutter.json, portal/cookiecutter.json).')
    parser.add_argument('extra_context', metavar='extra_context', nargs='*', 
        help='List of variables/values that will override cookiecutter defaults (see cookiecutter documentation for a thorough explanation). Format: var1=val1 var2=val2 ... varN=valN.')
    parser.add_argument('-o', '--output-dir', default=DEFAULT_OUTPUT_DIR,
        help='Specifies the output folder of generated projects.')
    args = parser.parse_args()

    args.extra_context = validate_extra_context(None, None, args.extra_context)

    generate_cookiecutter_project(args)

def generate_cookiecutter_project(kwargs):
    prepare_cookiecutter_template(kwargs)
    cookiecutter(WORKING_FOLDER, no_input=kwargs.no_input, overwrite_if_exists=True, extra_context=kwargs.extra_context, output_dir=kwargs.output_dir)
    
# removes any lingering working folder, copies content from the desired cookecutter folder (e.g., portal/portlet)
def prepare_cookiecutter_template(kwargs):
    shutil.rmtree(WORKING_FOLDER, ignore_errors=True)
    copy_common_files_to_working_folder()
    copy_cookiecutter_files_to_working_folder(os.path.join(COOKIECUTTERS_ROOT_FOLDER, kwargs.type))

# copies files from one of the cookiecutter folders (e.g., portal/portlet) to our working directory
def copy_cookiecutter_files_to_working_folder(origin_folder):
    dir_util.copy_tree(origin_folder, WORKING_FOLDER)

# copies files from the common files folder to the destination folder
def copy_common_files_to_working_folder():
    dir_util.copy_tree(COMMON_FILES_FOLDER, WORKING_FOLDER)

if __name__ == "__main__":
    main()

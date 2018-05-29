from os.path import exists, join, isdir

FOLDER_NAME = 'template-test'

def test_folder_was_created():
    assert(exists(FOLDER_NAME))

def test_created_folder_is_folder():
    assert(isdir(FOLDER_NAME))

def test_common_files_were_copied():
    assert(exists(join(FOLDER_NAME, '.gitignore')))
    assert(exists(join(FOLDER_NAME, '.travis.settings.xml')))
    assert(exists(join(FOLDER_NAME, '.travis.sh')))
    assert(exists(join(FOLDER_NAME, '.travis.yml')))
    assert(exists(join(FOLDER_NAME, 'CODE_OF_CONDUCT.md')))
    assert(exists(join(FOLDER_NAME, 'LICENSE')))
    assert(exists(join(FOLDER_NAME, 'README.md')))
    assert(exists(join(FOLDER_NAME, 'src', 'main', 'resources', 'log4j2.xml')))
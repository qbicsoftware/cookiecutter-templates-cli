package life.qbic.cli;

import javafx.application.Application;

/**
 * Entry point for the {{ cookiecutter.display_name }} application.
 * 
 * The purpose of this class is to separate parsing of command-line arguments from JavaFX appications.
 */
public class {{ cookiecutter.main_class }}EntryPoint {

    private static final Logger LOG = LogManager.getLogger({{ cookiecutter.main_class }}EntryPoint.class);

    /**
     * Main method. Launches the JavaFX {@code Application}, passing all arguments.
     */
    public static void main(final String[] args) {
        LOG.debug("Starting {{ cookiecutter.main_class }} GUI");
        Application.launch({{ cookiecutter.main_class }}Application.class, args);
    }
}
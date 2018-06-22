package life.qbic.cli;

import java.io.InputStream;
import java.util.Properties;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.config.Configurator;


import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

/**
 * Entry point for the {{ cookiecutter.display_name }} application.
 * 
 * The purpose of this class is to act as a bridge between the command line and the <i>real</i> implementation of a tool by using a {@link ToolExecutor}.
 */
public class {{ cookiecutter.main_class }}EntryPoint {

    private static final Logger LOG = LogManager.getLogger({{ cookiecutter.main_class }}.class);

    /**
     * Main method.
     */
    public static void main(final String[] args) {
        LOG.debug("Starting {{ cookiecutter.main_class }} tool");
        final ToolExecutor<{{ cookiecutter.main_class }}Command> executor = new ToolExecutor<>();
        executor.invoke({{ cookiecutter.main_class }}Tool.class, {{ cookiecutter.main_class }}Command, args);
    }
}
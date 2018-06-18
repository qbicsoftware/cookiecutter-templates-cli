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
 * Entry point for the {{ cookiecutter.display_name }} application. I love dank memes and hate documentation.
 */
public class {{ cookiecutter.main_class }}EntryPoint {

    private static final Logger LOG = LogManager.getLogger({{ cookiecutter.main_class }}.class);


    /**
     * Main method.
     */
    public static void main(final String[] args) {
        LOG.debug("Starting {{ cookiecutter.main_class }} tool");
        
        final {{ cookiecutter.main_class }}Command command = CommandLine.populateCommand(new {{ cookiecutter.main_class }}Command(), args);
        final Tool<{{ cookiecutter.main_class }}Command> tool = new {{ cookiecutter.main_class }}Tool(command);

        final ToolExecutor<{{ cookiecutter.main_class }}Command> executor = new ToolExecutor<>();
        executor.invoke(tool, command);
    }
}
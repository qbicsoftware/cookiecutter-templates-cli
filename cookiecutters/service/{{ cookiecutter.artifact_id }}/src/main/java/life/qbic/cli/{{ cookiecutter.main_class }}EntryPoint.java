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
 * 
 * Command-line arguments are parsed using the {@link CommandLine#populateCommand(Object, String...)} method. This method returns an instance of 
 * a {@link {{ cookiecutter.main_class }}Command}, which will be provided to the tool implementation ({@link {{ cookiecutter.main_class }}Service}) via
 * its constructor.
 * 
 * A {@link ToolExecutor} then invokes the tool using the parsed command-line arguments using the {@link ToolExecutor#invoke(Tool, AbstractCommand)} method.
 */
public class {{ cookiecutter.main_class }}EntryPoint {

    private static final Logger LOG = LogManager.getLogger({{ cookiecutter.main_class }}.class);

    /**
     * Main method.
     */
    public static void main(final String[] args) {
        LOG.debug("Starting {{ cookiecutter.main_class }} tool");
        
        final {{ cookiecutter.main_class }}Command command = CommandLine.populateCommand(new {{ cookiecutter.main_class }}Command(), args);
        final Tool<{{ cookiecutter.main_class }}Command> tool = new {{ cookiecutter.main_class }}Service(command);

        final ToolExecutor<{{ cookiecutter.main_class }}Command> executor = new ToolExecutor<>();
        executor.invoke(tool, command);
    }
}
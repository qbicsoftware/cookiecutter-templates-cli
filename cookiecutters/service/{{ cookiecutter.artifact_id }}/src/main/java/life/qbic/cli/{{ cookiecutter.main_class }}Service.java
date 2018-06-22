package life.qbic.cli;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Implementation of {{ cookiecutter.display_name }}. Its command-line arguments are contained in instances of {@link {{ cookiecutter.main_class }}Command}.
 */
public class {{ cookiecutter.main_class }}Service extends QBiCTool<{{ cookiecutter.main_class }}Command> {

    private static final Logger LOG = LogManager.getLogger({{ cookiecutter.main_class }}.class);

    /**
     * Constructor.
     * 
     * @param command an object that represents the parsed command-line arguments.
     */
    public {{ cookiecutter.main_class }}Tool(final {{ cookiecutter.main_class }}Command command) {
        super(command);
    }

    @Override
    public void execute() {
        // get the parsed command-line arguments
        final {{ cookiecutter.main_class }}Command command = super.getCommand();

        // TODO: do something useful with the obtained command.
        //
        
    }

    @Override
    public void shutdown() {
        // TODO: perform clean-up routines
    }
}
package life.qbic.cli;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Implementation of {{ cookiecutter.display_name }}. Its command-line arguments are contained in instances of {@link {{ cookiecutter.main_class }}Command}.
 * 
 * I did not even bother to change this because I am too cool for school.
 */
public class {{ cookiecutter.main_class }}Tool extends AbstractTool<{{ cookiecutter.main_class }}Command> {

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
        // TODO: implement this method
        //
        // there is no need to handle help/version commands, that's done by ToolExecutor
        // you can access command-line arguments using the 'command' super class member
        // e.g., 'super.command.[member_name]'
        super.command;
    }

    // TODO: override the shutdown() method if you are implementing a daemon and want to take advantage of a shutdown hook for clean-up tasks
}
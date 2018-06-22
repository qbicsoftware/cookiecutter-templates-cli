package life.qbic.cli;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Implementation of {{ cookiecutter.display_name }}. Its command-line arguments are contained in instances of {@link {{ cookiecutter.main_class }}Command}.
 * 
 * I did not even bother to change this because I am too cool for school.
 */
public class {{ cookiecutter.main_class }}Tool extends QBiCTool<{{ cookiecutter.main_class }}Command> {

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
        final {{ cookiecutter.main_class }}Command command = super.getCommand();

        try {
            // FIXME: one would typically pack this kind of logic in a library
            final life.qbic.openbis.openbisclient.OpenBisClient openBisClient = new life.qbic.openbis.openbisclient.OpenBisClient(command.userName, command.password, command.url);
            openBisClient.login();
            returnCode = 0;
        } catch (Exception e) {
            LOG.error("Could not establish connection to openBIS server at {} (username={}, password=(****))", command.url, command.userName);
        }
        
    }
}
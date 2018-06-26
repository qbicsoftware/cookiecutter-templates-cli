package life.qbic.cli;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Implementation of openBIS Connectivity Tester Tool. Its command-line arguments are contained in instances of {@link OpenBISConnectionTesterCommand}.
 * 
 * I did not even bother to change this because I am too cool for school.
 */
public class OpenBISConnectionTesterTool extends QBiCTool<OpenBISConnectionTesterCommand> {

    private static final Logger LOG = LogManager.getLogger(OpenBISConnectionTesterTool.class);

    /**
     * Constructor.
     * 
     * @param command an object that represents the parsed command-line arguments.
     */
    public OpenBISConnectionTesterTool(final OpenBISConnectionTesterCommand command) {
        super(command);
    }

    @Override
    public void execute() {
        // get the parsed command-line arguments
        final OpenBISConnectionTesterCommand command = super.getCommand();

        try {
            final life.qbic.openbis.openbisclient.OpenBisClient openBisClient = new life.qbic.openbis.openbisclient.OpenBisClient(command.userName, command.password, command.url);
            openBisClient.login();
            LOG.info("openBIS instance at {} is reachable and listening for requests.", command.url);
        } catch (Exception e) {
            LOG.error("Could not establish connection to openBIS server at {} (username={}, password=(****)). See the application log in logs/app.log for more details.", command.url, command.userName);
            LOG.debug("Could not establish connection to openBIS server", e);
        }
        
    }
}
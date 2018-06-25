package life.qbic.cli;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Entry point for the openBIS Connectivity Tester Tool application.
 * 
 * The purpose of this class is to act as a bridge between the command line and the <i>real</i> implementation of a tool by using a {@link ToolExecutor}.
 */
public class OpenBISConnectionTesterEntryPoint {

    private static final Logger LOG = LogManager.getLogger(OpenBISConnectionTesterEntryPoint.class);

    /**
     * Main method.
     */
    public static void main(final String[] args) {
        LOG.debug("Starting OpenBISConnectionTester tool");
        final ToolExecutor executor = new ToolExecutor();
        executor.invoke(OpenBISConnectionTesterTool.class, OpenBISConnectionTesterCommand.class, args);
    }
}
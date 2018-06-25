package life.qbic.service;

import life.qbic.cli.QBiCTool;
import life.qbic.exceptions.ApplicationException;

import java.util.concurrent.atomic.AtomicBoolean;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Implementation of Ping-Pong Service. Its command-line arguments are contained in instances of {@link PingPongCommand}.
 */
public class PingPongService extends QBiCTool<PingPongCommand> {

    private static final Logger LOG = LogManager.getLogger(PingPongService.class);

    private volatile NanoHTTPD httpServer;
    private final AtomicBoolean serverCreated;

    /**
     * Constructor.
     * 
     * @param command an object that represents the parsed command-line arguments.
     */
    public PingPongService(final PingPongCommand command) {
        super(command);
        serverCreated = new AtomicBoolean(false);
    }

    @Override
    public void execute() {
        // get the parsed command-line arguments
        final PingPongCommand command = super.getCommand();

        // TODO: do something useful with the obtained command.
        //
    }

    @Override
    public void shutdown() {
        // TODO: perform clean-up tasks
        // Important: do not call System.exit. This method is executed by a "shutdown hook thread"
        //            See: https://docs.oracle.com/javase/8/docs/api/java/lang/Runtime.html#exit-int-
    }
}
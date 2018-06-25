package life.qbic.service;

import life.qbic.cli.QBiCTool;
import life.qbic.exceptions.ApplicationException;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicBoolean;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import fi.iki.elonen.NanoHTTPD;
import fi.iki.elonen.NanoHTTPD.Response;
import fi.iki.elonen.NanoHTTPD.IHTTPSession;


/**
 * Implementation of Ping-Pong Service. Its command-line arguments are contained in instances of {@link PingPongCommand}.
 */
public class PingPongService extends QBiCTool<PingPongCommand> {

    private static final Logger LOG = LogManager.getLogger(PingPongService.class);

    private volatile NanoHTTPD httpServer;
    private final AtomicBoolean serverStarted;

    /**
     * Constructor.
     * 
     * @param command an object that represents the parsed command-line arguments.
     */
    public PingPongService(final PingPongCommand command) {
        super(command);
        serverStarted = new AtomicBoolean(false);
    }

    @Override
    public void execute() {
        // get the parsed command-line arguments
        final PingPongCommand command = super.getCommand();

        httpServer = new NanoHTTPD(command.port) {
            @Override
            public Response serve(IHTTPSession session) {
                return doServe(session);                
            }};
        try {
			httpServer.start(NanoHTTPD.SOCKET_READ_TIMEOUT, false);
            serverStarted.set(true);
            LOG.info("Listening in port {}", command.port);
		} catch (IOException e) {
			throw new ApplicationException(String.format("Could not start http server using port %d", command.port), e);
		}
    }

    Response doServe(final IHTTPSession session) {
        LOG.info("pinged");
        return NanoHTTPD.newFixedLengthResponse("pong");
    }

    @Override
    public void shutdown() {
        LOG.info("Shutting down");
        if (serverStarted.get()) {
            httpServer.stop();
        }
    }
}
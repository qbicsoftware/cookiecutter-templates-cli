package life.qbic.service;

import life.qbic.cli.AbstractCommand;

import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

/**
 * Abstraction of command-line arguments that will be passed to {@link PingPongService} at construction time.
 */
@Command(
   name="PingPong",
   description="Service that replies 'pong' when pinged.")
public class PingPongCommand extends AbstractCommand {
    @Option(names={"-p", "--port"}, description="Port on which this service will listen to requests.", required=true)
    public int port;
}
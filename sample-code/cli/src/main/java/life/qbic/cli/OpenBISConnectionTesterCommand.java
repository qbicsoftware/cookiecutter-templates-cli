package life.qbic.cli;

import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

/**
 * Abstraction of command-line arguments that will be passed to {@link OpenBISConnectionTesterTool} at construction time.
 */
@Command(
   name="OpenBISConnectionTester",
   description="Command-line utility to test if openBIS is reachable.")
public class OpenBISConnectionTesterCommand extends AbstractCommand {
    /**
     * openBIS server URL.
     */
    @Option(names={"-u", "--url"}, description="openBIS server URL.", required=true)
    public String url;
    /**
     * Username.
     */
    @Option(names={"-n", "--username"}, description="openBIS username.", required=true)
    public String userName;
    /**
     * Password.
     */
    @Option(names={"-p", "--pass"}, description="openBIS password.", required=true)
    public String password;
}
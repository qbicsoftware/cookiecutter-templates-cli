package life.qbic.cli;

import java.io.InputStream;
import java.util.Properties;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

/**
 * Tests if an openBIS connection can be established (simple "is it online" tool).
 */
@Command(
   name="openBIS connectivity tester",
   description="This tool is a simple 'is it online' command-line utility.",
   headerHeading="Usage:%n%n")
public class {{ cookiecutter.main_class }}  {

    private static final String VERSION;
    private static final String PROJECT_URL;
    static {
        final Properties properties = new Properties();
        try (final InputStream inputStream = {{ cookiecutter.main_class }}.class.getClassLoader().getResourceAsStream("tool.properties")) {
            properties.load(inputStream);
        } catch (Exception e) {
            System.err.println("Could not load tools.properties file. Loading default version/project-url.");
            System.err.println(e);
        }
        VERSION = properties.getProperty("version", "0.0.1-SNAPSHOT");
        PROJECT_URL = properties.getProperty("project.url", "http://github.com/qbicsoftware");
    }

    @Option(names={"-u", "--url"}, description="openBIS server URL.")
    private String url;
    @Option(names={"-n", "--username"}, description="openBIS username.")
    private String userName;
    @Option(names={"-p", "--pass"}, description="openBIS password.")
    private String password;
    @Option(names={"--version"}, description="Prints version and exits.")
    private boolean printVersion;

    /**
     * Main method.
     */
    public static void main(final String[] args) {
        int returnCode = -1;
        final {{ cookiecutter.main_class }} app = CommandLine.populateCommand(new {{ cookiecutter.main_class }}(), args);

        if (app.printVersion) {
            System.out.println(String.format("{{ cookiecutter.main_class }}, version {}", VERSION));
            returnCode = 0;
        } else {
            try {
                // FIXME: one would typically pack this kind of logic in a library
                final life.qbic.openbis.openbisclient.OpenBisClient openBisClient = new life.qbic.openbis.openbisclient.OpenBisClient(app.userName, app.password, app.url);
                openBisClient.login();
                returnCode = 0;
            } catch (Exception e) {
                System.out.println(String.format("Could not establish connection to openBIS server at {} (username={}, password=(not shown!))", app.url, app.userName));
            }
        }
        System.exit(returnCode);
    }
}
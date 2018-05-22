package life.qbic.cli;

import java.io.InputStream;
import java.util.Properties;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.config.Configurator;


import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

/**
 * Tests if an openBIS connection can be established (simple "is it online" tool).
 */
@Command(
   name="{{ cookiecutter.main_class }}",
   description="This tool is a simple 'is it online?' command-line utility.")
public class {{ cookiecutter.main_class }}  {

    private static final Logger LOG = LogManager.getLogger({{ cookiecutter.main_class }}.class);

    private static final String VERSION;
    private static final String PROJECT_URL;
    static {
        final Properties properties = new Properties();
        try (final InputStream inputStream = {{ cookiecutter.main_class }}.class.getClassLoader().getResourceAsStream("tool.properties")) {
            properties.load(inputStream);
        } catch (Exception e) {
            LOG.warn("Could not load tools.properties file. Loading default version/project-url.", e);
        }
        VERSION = properties.getProperty("version", "0.0.1-SNAPSHOT");
        PROJECT_URL = properties.getProperty("project.url", "http://github.com/qbicsoftware");
    }

    @Option(names={"-u", "--url"}, description="openBIS server URL.", required=true)
    private String url;
    @Option(names={"-n", "--username"}, description="openBIS username.", required=true)
    private String userName;
    @Option(names={"-p", "--pass"}, description="openBIS password.", required=true)
    private String password;
    @Option(names={"-v", "--version"}, description="Prints version and exits.", versionHelp=true)
    private boolean printVersion;
    @Option(names={"-h", "--help"}, description="Prints usage and exists.", usageHelp=true)
    private boolean printHelp;

    /**
     * Main method.
     */
    public static void main(final String[] args) {
        LOG.debug("Starting {{ cookiecutter.main_class }} CLI tool");
        int returnCode = -1;
        try {
            final {{ cookiecutter.main_class }} app = CommandLine.populateCommand(new {{ cookiecutter.main_class }}(), args);

            if (app.printVersion || app.printHelp) {
                if (app.printVersion) {
                    LOG.debug("Version requested.");
                    LOG.info("{{ cookiecutter.main_class }}, version {}", VERSION);
                }
                if (app.printHelp) {
                    LOG.debug("Help requested.");
                    CommandLine.usage(app, System.out);
                }
                returnCode = 0;
            } else {
                try {
                    // FIXME: one would typically pack this kind of logic in a library
                    final life.qbic.openbis.openbisclient.OpenBisClient openBisClient = new life.qbic.openbis.openbisclient.OpenBisClient(app.userName, app.password, app.url);
                    openBisClient.login();
                    returnCode = 0;
                } catch (Exception e) {
                    LOG.error("Could not establish connection to openBIS server at {} (username={}, password=(****))", app.url, app.userName);
                }
            }
        } catch (Exception e) {
            LOG.error(e.getMessage());
            LOG.error("Check the application log in logs/app.log for more details.");
            LOG.debug("Full stack trace: ", e);
        }
        System.exit(returnCode);
    }
}
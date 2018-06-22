package life.qbic.cli;

import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

/**
 * Abstraction of command-line arguments that will be passed to {@link {{ cookiecutter.main_class }}Service} at construction time.
 */
@Command(
   name="{{ cookiecutter.main_class }}",
   description="{{ cookiecutter.short_description }}")
public class {{ cookiecutter.main_class }}Command extends AbstractCommand {
    // TODO: add your command-line options as members of this class using picocli's annotations, for instance:
    //
    // @Option(names={"-u", "--url"}, description="openBIS server URL.", required=true)
    // String url;
    //
    // using package access level for these members will allow you access them within your main and test classes
}
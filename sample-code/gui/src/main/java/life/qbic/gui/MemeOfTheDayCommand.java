package life.qbic.gui;

import life.qbic.cli.AbstractCommand;

import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

/**
 * Abstraction of command-line arguments that will be passed to {@link MemeOfTheDayApplication} at construction time.
 */
@Command(
   name="MemeOfTheDay",
   description="JavaFX stand-alone application that displays a different meme each day.")
public class MemeOfTheDayCommand extends AbstractCommand {
    @Option(names={"-n", "--nsfw"}, description="Use this flag to watch spicy memes (not safe for work).")
    boolean nsfw;
}
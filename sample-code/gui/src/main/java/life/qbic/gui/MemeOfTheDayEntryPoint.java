package life.qbic.gui;

import life.qbic.javafx.JavaFXExecutor;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Entry point for the Meme of the Day Application application.
 * 
 * The purpose of this class is to act as a bridge between the command line and the <i>real</i> implementation of a tool by using a {@link JavaFXExecutor}.
 */
public class MemeOfTheDayEntryPoint {

    private static final Logger LOG = LogManager.getLogger(MemeOfTheDayEntryPoint.class);

    /**
     * Main method.
     * 
     * @param args the command-line arguments.
     */
    public static void main(final String[] args) {
        LOG.debug("Starting MemeOfTheDay Application");
        final JavaFXExecutor executor = new JavaFXExecutor();
        executor.invokeAsJavaFX(MemeOfTheDayApplication.class, MemeOfTheDayCommand.class, args);
    }
}
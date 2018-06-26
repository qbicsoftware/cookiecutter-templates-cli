package life.qbic.gui;

import javafx.scene.Scene;
import javafx.scene.web.WebView;
import life.qbic.javafx.QBiCApplication;

import javafx.stage.Stage;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import picocli.CommandLine;

/**
 * Implementation of Meme of the Day Application. Its command-line arguments are contained in instances of {@link MemeOfTheDayCommand}.
 */
public class MemeOfTheDayApplication extends QBiCApplication<MemeOfTheDayCommand> {

    private static final Logger LOG = LogManager.getLogger(MemeOfTheDayApplication.class);

    @Override
    public void start(final Stage stage) throws Exception {
        final MemeOfTheDayCommand command = super.getCommand(MemeOfTheDayCommand.class);
        final String videoId;
        if (command.nsfw) {
            LOG.warn("Starting NSFW mode!");
            videoId = "oHg5SJYRHA0";
        } else {
            videoId = "yPYZpwSpKmA";
        }
        final WebView webview = new WebView();
        webview.getEngine().load(
                "https://www.youtube.com/embed/" + videoId
        );
        webview.setPrefSize(640, 390);

        stage.setScene(new Scene(webview));
        stage.show();
    }
}
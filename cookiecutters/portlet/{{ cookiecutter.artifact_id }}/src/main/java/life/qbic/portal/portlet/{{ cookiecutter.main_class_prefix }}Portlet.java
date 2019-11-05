package life.qbic.portal.portlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Widgetset;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Entry point for portlet {{ cookiecutter.artifact_id }}. This class derives from {@link QBiCPortletUI}, which is found in the {@code portal-utils-lib} library.
 * 
 * @see <a href=https://github.com/qbicsoftware/portal-utils-lib>portal-utils-lib</a>
 */
@Theme("mytheme")
@SuppressWarnings("serial")
@Widgetset("life.qbic.portal.portlet.AppWidgetSet")
public class {{ cookiecutter.main_class_prefix }}Portlet extends QBiCPortletUI {

    private static final Logger LOG = LogManager.getLogger({{ cookiecutter.main_class_prefix }}Portlet.class);

    @Override
    protected Layout getPortletContent(final VaadinRequest request) {
        LOG.info("Generating content for {}", {{ cookiecutter.main_class_prefix }}Portlet.class);

        // Verify that Groovy Code works
        final SampleClass sampleClass = new SampleClass();

        // TODO: generate content for your portlet
        //       this method returns any non-null layout to avoid a NullPointerException later on
        final VerticalLayout layout = new VerticalLayout();

        final TextField name = new TextField();
        name.setCaption("Type your name here:");

        Button button = new Button("Click Me");
        button.addClickListener(e -> {
            layout.addComponent(new Label("Thanks " + name.getValue()
            + ", it works!"));
        });

        layout.addComponents(name, button);

        return layout;
    }    
}

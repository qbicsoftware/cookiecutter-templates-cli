package life.qbic.portal.portlet;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;

import javax.portlet.PortletContext;
import javax.portlet.PortletSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Widgetset;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.WrappedPortletSession;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Label;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.Layout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;

import life.qbic.portal.utils.PortalUtils;
import life.qbic.portal.Styles;
import life.qbic.portal.Styles.NotificationType;


/**
 * Entry point for portlet {{ cookiecutter.artifact_id }}. This class derives from {@link QBiCPortletUI}, which is found in the {@code portal-utils-lib} library.
 */
@Theme("mytheme")
@SuppressWarnings("serial")
@Widgetset("life.qbic.portlet.AppWidgetSet")
public class {{ cookiecutter.main_ui }} extends QBiCPortletUI {

    private static final Logger LOG = LogManager.getLogger({{ cookiecutter.main_ui }}.class);

    @Override
    protected Layout getPortletContent(final VaadinRequest request) {
        // TODO: remove this method and to your own thing, please
        return REMOVE_THIS_METHOD_AND_DO_YOUR_OWN_THING_COMMA_PLEASE(request);
    }

    //================== this is just some test code, do with it whatever you must ==================
    private static final boolean USING_OPEN_BIS = {%- if cookiecutter.use_openbis == "yes" %} true {%- else -%} false {%- endif -%};
    private static final boolean USING_QBIC_DATABASES = {%- if cookiecutter.use_qbic_databases == "yes" %} true {%- else -%} false {%- endif -%};

    private Layout REMOVE_THIS_METHOD_AND_DO_YOUR_OWN_THING_COMMA_PLEASE(final VaadinRequest request) {
        LOG.info("Generating content for portlet {{ cookiecutter.artifact_id }}");
        final StringBuilder builder = new StringBuilder("<h1><b>This is just a sample \"sanity check\" test portlet.</b></h1>");
        if (PortalUtils.isLiferayPortlet()) {
            builder.append("Hello, ").append(PortalUtils.getUser().getScreenName()).append("!<br/>");
            builder.append("This is portlet ").append(getPortletContextName(request)).append(".<br/>");
            builder.append("This portal has ").append(getPortalCountOfRegisteredUsers()).append(" registered users (according to the data returned by Liferay's API call)");            
        } else {
            builder.append("You are currently in a local testing mode. No Liferay Portlet context found.");
        }        
        builder.append("<br/>You can now start developing. Start by modifying the <font face='monospace'>getPortletContent</font> method in the generated <font face='monospace'>src/main/java/life/qbic/portal/portlet/{{ cookiecutter.main_ui}}.java</font> file.<br/><br/>");

        final Label welcomeLabel = new Label(builder.toString(), ContentMode.HTML);

        final VerticalLayout panelContent = new VerticalLayout();
        panelContent.setSpacing(true);
        panelContent.setMargin(true);
        panelContent.addComponent(welcomeLabel);
        insertHorizontalSeparator(panelContent);
        insertLoggingComponents(panelContent);
        insertOptionalTestComponents(panelContent);

        final Panel mainPanel = new Panel("{{ cookiecutter.display_name }}");
        mainPanel.setContent(panelContent);
            
        final VerticalLayout mainLayout = new VerticalLayout();
        mainLayout.setSpacing(true);
        mainLayout.addComponent(mainPanel);

        return mainLayout;
    }

    private void insertLoggingComponents(final Layout layout) {
        final TextField logTextField = new TextField("Log entry");
        logTextField.setMaxLength(120);
        logTextField.setWidth("500px");

        final ComboBox logLevelComboBox = new ComboBox("Log level");
        logLevelComboBox.setImmediate(true);
        logLevelComboBox.setNullSelectionAllowed(false);
        logLevelComboBox.addItems("TRACE", "DEBUG", "INFO", "WARN", "ERROR", "FATAL");
        logLevelComboBox.setValue("INFO");
        
        final Button logButton = new Button("Log!");
        // Confused about "->"? Check out https://docs.oracle.com/javase/tutorial/java/javaOO/lambdaexpressions.html
        logButton.addClickListener((Button.ClickListener) event -> {
            final String logEntry = logTextField.getValue();
            switch((String)logLevelComboBox.getValue()) {
                // yeah, it could be done via reflection, but this is just a template
                case "TRACE":
                    LOG.trace(logEntry);
                    break;
                case "DEBUG":
                    LOG.debug(logEntry);
                    break;
                case "INFO":
                    LOG.info(logEntry);
                    break;
                case "WARN":
                    LOG.warn(logEntry);
                    break;
                case "ERROR":
                case "FATAL":
                    LOG.fatal(logEntry, new RuntimeException("DO NOT PANIC! This is just a demo of how stack traces are logged"));
                    break;
                default:
                    LOG.warn("Invalid log level selected!");
            }
        });

        final HorizontalLayout logControlsLayout = new HorizontalLayout();
        logControlsLayout.setSpacing(true);
        logControlsLayout.addComponent(logTextField);
        logControlsLayout.addComponent(logLevelComboBox);
        logControlsLayout.addComponent(logButton);
        
        final StringBuilder builder = new StringBuilder();
        builder.append("<h1><b>Logging best practices</b></h2>");
        builder.append("<h2><b>Developer note #1:</b></h2>Use<br/><pre>LOG.info(\"Hello {}! You have {} new notifications.\", user.getName(), newNotifications);</pre> instead of:");
        builder.append("<pre>LOG.info(\"Hello \" + user.getName() + \"! You have \" + newNotifications + \" new notifications.\");</pre>");
        builder.append("<h2><b>Developer note #2:</b></h2>If you are logging information that depends on an expensive process, it's best practice to do something similar to:<br/><br/>");
        builder.append("<pre>" + 
        "for (int i = 0; i < Integer.MAX_INTEGER; i++) {\n" + 
        "  if (LOG.isDebugEnabled()) {\n" + 
        "    // FIXME: checkIfPrimeNaive is a method that could take between 1 ns and 7 years to complete\n" +
        "    LOG.debug(\"About to process {}. Is {} a prime number? Answer: {}\", i, i, this.checkIfPrimeNaive(i));\n" + 
        "  }\n" + 
        "  // we need to process the number anyway\n" + 
        "  this.process(i);\n" + 
        "}</pre>");
        final Label developerInfoLabel = new Label(builder.toString(), ContentMode.HTML);

        builder.setLength(0);
        builder.append("Enter some text in the text field, select a log level and then click on the button to see <i>log4j2</i> in action (configuration taken from: <font face='monospace'>src/resources/log4j2.xml</font>).");
        builder.append("<br>Note: <font face='monospace'>ERROR</font> and <font face='monospace'>FATAL</font> log levels will also log an exception.");
        final Label logControlsLabel = new Label(builder.toString(), ContentMode.HTML);

        final VerticalLayout logLayout = new VerticalLayout();
        logLayout.setSpacing(true);
        logLayout.addComponent(developerInfoLabel);
        insertHorizontalSeparator(logLayout);
        logLayout.addComponent(logControlsLabel);
        logLayout.addComponent(logControlsLayout);

        layout.addComponent(logLayout);
    }
 
    private void insertOptionalTestComponents(final Layout layout) {
        if (USING_OPEN_BIS) {
            insertOpenBISConnectionTesterComponents(layout);
        }
        if (USING_QBIC_DATABASES) {
            insertInternalDatabasesConnectionTesterComponents(layout);
        }
    }

    private void insertOpenBISConnectionTesterComponents(final Layout layout) {
        insertHorizontalSeparator(layout);
        final HorizontalLayout openBisFieldsLayout = new HorizontalLayout();
        openBisFieldsLayout.setSpacing(true);

        final TextField dataSourceUser = new TextField("Username");
        final PasswordField dataSourcePassword = new PasswordField("Password");
        final TextField dataSourceUrl = new TextField("URL (e.g., https://openbis.de:443)");
        dataSourceUrl.setWidth("500px");
        dataSourceUrl.setValue("https://");
        final Button openBisTestButton = new Button("Test openBIS connection");

        openBisFieldsLayout.addComponent(dataSourceUser);
        openBisFieldsLayout.addComponent(dataSourcePassword);
        openBisFieldsLayout.addComponent(dataSourceUrl);
        openBisFieldsLayout.addComponent(openBisTestButton);

        openBisTestButton.addClickListener((Button.ClickListener) event -> {
            if (isOpenBISWorking(dataSourceUser.getValue(), dataSourcePassword.getValue(), dataSourceUrl.getValue())) {
                Styles.notification("{{ cookiecutter.display_name }}", "Connection to openBIS works!", NotificationType.SUCCESS); 
            } else {
                Styles.notification("{{ cookiecutter.display_name }}", "Could not establish openBIS connection. Check the logs for exceptions.", NotificationType.ERROR); 
            }
        });
        layout.addComponent(openBisFieldsLayout);
    }

    private boolean isOpenBISWorking(final String dataSourceUser, final String dataSourcePassword, final String dataSourceUrl) {
        {% if cookiecutter.use_openbis == "yes" -%}
        boolean success = false;
        try {
            final life.qbic.openbis.openbisclient.OpenBisClient openBisClient = new life.qbic.openbis.openbisclient.OpenBisClient(dataSourceUser, dataSourcePassword, dataSourceUrl);
            openBisClient.login();
            openBisClient.getProjectsOfSpace("CHICKEN_FARM");
            success = true;
        } catch (Exception e) {
            LOG.error("Could not connect to openBIS server", e);
        }
        return success;
        {%- else %}
        throw new RuntimeException("This is broken. This should never happen. Report this as a bug!");
        {% endif %}
    }

    private void insertInternalDatabasesConnectionTesterComponents(final Layout layout) {
        insertHorizontalSeparator(layout);
        final HorizontalLayout internalDatabaseLayout = new HorizontalLayout();
        internalDatabaseLayout.setSpacing(true);

        final TextField username = new TextField("Username");
        final PasswordField password = new PasswordField("Password");
        final TextField jdbcUrl = new TextField("Base JDBC URL (e.g., jdbc:mariadb://localhost:3306/db-name)");
        jdbcUrl.setWidth("500px");
        jdbcUrl.setValue("jdbc:mariadb://");
        final Button internalDatabaseTestButton = new Button("Test QBiC internal DB connection");

        internalDatabaseLayout.addComponent(username);
        internalDatabaseLayout.addComponent(password);
        internalDatabaseLayout.addComponent(jdbcUrl);
        internalDatabaseLayout.addComponent(internalDatabaseTestButton);

        internalDatabaseTestButton.addClickListener((Button.ClickListener) event -> {
            if (isQBiCDatabaseWorking(username.getValue(), password.getValue(), jdbcUrl.getValue())) {
                Styles.notification("{{ cookiecutter.display_name }}", "Connection to QBiC database works!", NotificationType.SUCCESS); 
            } else {
                Styles.notification("{{ cookiecutter.display_name }}", "Could not establish connection to QBiC database. Check the logs for exceptions.", NotificationType.ERROR); 
            }
        });
        layout.addComponent(internalDatabaseLayout);
    }

    private boolean isQBiCDatabaseWorking(final String username, final String password, final String jdbcUrl) {
        // this code will compile no matter which libraries are included (java.sql package is part of Java)
        if (!USING_QBIC_DATABASES) {
            throw new RuntimeException("This is broken. This should never happen. Report this as a bug!");
        }

        boolean success = false;
        try (final Connection connection = DriverManager.getConnection(String.format("%s?user=%s&password=%s", jdbcUrl, username, password))) {
            final DatabaseMetaData metaData = connection.getMetaData();
            final ResultSet resultSet = metaData.getTables(connection.getCatalog(), null, "%", null);

            while (resultSet.next()) {
                LOG.info("found table {}", resultSet.getString("TABLE_NAME"));
            }

            resultSet.close();

            success = true;
        } catch (Exception e) {
            LOG.error("Could not connect to QBiC database", e);
        }
        return success;

    }

    private void insertHorizontalSeparator(final Layout layout) {
        final Label horizontalSeparator = new Label("<hr width='100%'/>", ContentMode.HTML);
        layout.addComponent(horizontalSeparator);
    }

    private String getPortletContextName(VaadinRequest request) {
        WrappedPortletSession wrappedPortletSession = (WrappedPortletSession) request
                .getWrappedSession();
        PortletSession portletSession = wrappedPortletSession
                .getPortletSession();

        final PortletContext context = portletSession.getPortletContext();
        final String portletContextName = context.getPortletContextName();
        return portletContextName;
    }

    private Integer getPortalCountOfRegisteredUsers() {
        Integer result = null;

        try {
            result = UserLocalServiceUtil.getUsersCount();
        } catch (Exception e) {
            LOG.warn("Could not get user count", e);
        }

        return result;
    }
    //================== end of test code ==================
}
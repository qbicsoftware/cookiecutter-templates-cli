package life.qbic.portal.portlet;

import org.junit.Test;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class {{ cookiecutter.entry_point }}Test {

    @Test
    public void mainUIExtendsQBiCPortletUI() {
        assertTrue("The main UI class must extend life.qbic.portlet.QBiCPortletUI", 
            QBiCPortletUI.class.isAssignableFrom({{ cookiecutter.entry_point }}.class));
    }

    @Test
    public void mainUIIsNotQBiCPortletUI() {
        assertFalse("The main UI class must be different to life.qbic.portlet.QBiCPortletUI", 
            QBiCPortletUI.class.equals({{ cookiecutter.entry_point }}.class));
    }
}
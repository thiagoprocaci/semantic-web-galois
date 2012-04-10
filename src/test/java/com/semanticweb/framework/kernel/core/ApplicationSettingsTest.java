package com.semanticweb.framework.kernel.core;

import org.junit.Before;
import org.junit.Test;
import static junit.framework.Assert.*;

public class ApplicationSettingsTest {
    private static final String CADEIA_CARACTERES = "cadeia_caracteres";
    private static final String CAMINHO_LOG_2 = "caminho_log_2";
    private static final String STRING = "string";
    private ApplicationSettings applicationSettings;

    @Before
    public void doBefore() {
        applicationSettings = new ApplicationSettings();
        applicationSettings.setConfigSettings("config_test");
        applicationSettings.initialize();
    }

    @Test
    public void testGetAppCode() {
        applicationSettings.setAppCode("code");
        assertEquals("code", applicationSettings.getAppCode());
    }

    @Test
    public void testGetLogPath() {
        assertEquals("caminho_log", applicationSettings.getLogPath());
        applicationSettings.setLogPath(CAMINHO_LOG_2);
        assertEquals(CAMINHO_LOG_2, applicationSettings.getLogPath());
    }

    @Test
    public void testGetString() {
        assertEquals(CADEIA_CARACTERES, applicationSettings.getString(STRING));
    }

    @Test
    public void testGetObject() {
        assertEquals(CADEIA_CARACTERES, applicationSettings.get(STRING));
    }

    @Test
    public void testFakeConfig() {
        applicationSettings.setConfigSettings("config_test_");
        applicationSettings.initialize();
        try {
            applicationSettings.getString(STRING);
            fail();
        } catch (NullPointerException e) {
            assertNotNull(e);
        }
    }
}

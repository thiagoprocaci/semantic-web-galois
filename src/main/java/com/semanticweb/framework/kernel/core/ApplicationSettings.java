package com.semanticweb.framework.kernel.core;

import java.io.Serializable;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Required;

import com.semanticweb.framework.kernel.IApplicationSettings;

/**
 * Classe responsavel por disponibilizar as informacoes principais do projeto
 * contidas no config.properties
 */
public class ApplicationSettings implements Serializable, IApplicationSettings {
    private static final String LOG_PATH = "logpath";
    private static final long serialVersionUID = 5899548333825887211L;
    private static final Logger LOG = LoggerFactory.getLogger(ApplicationSettings.class);
    private String logPath;
    private String appCode;
    // Referencia para o resourceBundle para o arquivo de configuracoes
    private ResourceBundle config; //
    private String configSettings;

    /**
     * {@inheritDoc}
     */
    @Override
    public void initialize() {
        config = loadBundle(configSettings);
    }

    /**
     * Carrega um bundle tratando a excecao
     *
     * @param bundleName
     *            nome do bundle
     * @return o bundle carregado
     */
    private ResourceBundle loadBundle(String bundleName) {
        LOG.info("Starting config " + bundleName);
        try {
            return ResourceBundle.getBundle(bundleName);
        } catch (MissingResourceException e) {
            LOG.error("Erro ao localizar arquivo: " + bundleName);
        }
        return null;
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public String getLogPath() {
        if (logPath == null && config.getString(LOG_PATH) != null) {
            logPath = config.getString(LOG_PATH);
        }
        return logPath;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getString(String key) {
        return config.getString(key);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Object get(String key) {
        return config.getObject(key);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getAppCode() {
        return appCode;
    }

    @Required
    public void setAppCode(String appCode) {
        this.appCode = appCode;
    }

    @Required
    public void setConfigSettings(String configSettings) {
        this.configSettings = configSettings;
    }

    @Required
    public void setLogPath(String logPath) {
        this.logPath = logPath;
    }
}

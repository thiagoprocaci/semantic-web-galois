package com.semanticweb.framework.kernel.core;

import org.springframework.beans.factory.annotation.Required;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import com.semanticweb.framework.kernel.IApplicationSettings;
import com.semanticweb.framework.kernel.IKernelModule;
import com.semanticweb.framework.kernel.KernelModules;
import com.semanticweb.framework.module.file.IFileModule;
import com.semanticweb.framework.module.textmining.ITextMiningModule;
import com.semanticweb.framework.module.web.IWebModule;

/**
 * Classe utilitaria Central que prove uma interface dos framework que estiver
 * realizando a injecao de dependencia e iniciliza os componentes do framework.
 * Esta classe server para isolar o contexto de DI para a aplicacao bem como uma
 * interface para obtencao dos beans do proprio framework
 */
public class Kernel implements ApplicationContextAware {
    // spring applicationcontext
    private static ApplicationContext applicationContext;
    // ponteiro para os Settings da aplicacao (injecao + config.properties)
    private static IApplicationSettings applicationSettings = null;

    /**
     * Metodo que inicializa os componentes do framework.
     */
    public void initialize() {
        // inicializando os settgins (config.properties) da aplicacao.
        applicationSettings.initialize();
    }

    public static IApplicationSettings getApplicationSettings() {
        return Kernel.applicationSettings;
    }

    @Required
    public void setSettings(IApplicationSettings settings) {
        Kernel.applicationSettings = settings;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) {
        Kernel.applicationContext = applicationContext;
    }

    /**
     * Retorna um modulo existnte em KernelModules (enum)
     *
     * @param module
     *            modulo a ser retornado
     * @return modulo presente no contexto correspondente ao modulo informado
     */
    public static IKernelModule getModule(KernelModules module) {
        return (IKernelModule) Kernel.applicationContext.getBean(module.name());
    }

    /**
     *
     * @return Retorna web module
     */
    public static IWebModule getWebModule() {
        return (IWebModule) Kernel.getModule(KernelModules.webModule);
    }

    /**
     *
     * @return Retorna file module
     */
    public static IFileModule getFileModule() {
        return (IFileModule) Kernel.getModule(KernelModules.fileModule);
    }

    /**
     *
     * @return Retorna text minig module
     */
    public static ITextMiningModule getTextMiningModule() {
        return (ITextMiningModule) Kernel.getModule(KernelModules.textMiningModule);
    }
}

package com.semanticweb.framework.kernel.core;

import org.junit.Test;
import static junit.framework.Assert.*;
import org.unitils.UnitilsJUnit4;
import org.unitils.spring.annotation.SpringApplicationContext;

import com.semanticweb.framework.kernel.KernelModules;
import com.semanticweb.framework.module.file.IFileModule;
import com.semanticweb.framework.module.textmining.ITextMiningModule;
import com.semanticweb.framework.module.web.IWebModule;


@SpringApplicationContext("classpath:testContext.xml")
public class KernelTest extends UnitilsJUnit4 {

    @Test
    public void testGetWebModule() {
        assertNotNull(Kernel.getWebModule());
        assertTrue(Kernel.getWebModule() instanceof IWebModule);
    }

    @Test
    public void testGetFileModule() {
        assertNotNull(Kernel.getFileModule());
        assertTrue(Kernel.getFileModule() instanceof IFileModule);
    }

    @Test
    public void testGetTextMinigModule() {
        assertNotNull(Kernel.getTextMiningModule());
        assertTrue(Kernel.getTextMiningModule() instanceof ITextMiningModule);
    }

    @Test
    public void testGetModule() {
        assertNotNull(Kernel.getModule(KernelModules.fileModule));
        assertNotNull(Kernel.getModule(KernelModules.webModule));
        assertNotNull(Kernel.getModule(KernelModules.textMiningModule));

        assertTrue(Kernel.getModule(KernelModules.webModule) instanceof IWebModule);
        assertTrue(Kernel.getModule(KernelModules.fileModule) instanceof IFileModule);
        assertTrue(Kernel.getModule(KernelModules.textMiningModule) instanceof ITextMiningModule);
    }

}

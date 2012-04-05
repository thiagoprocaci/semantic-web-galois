package com.semanticweb.framework.module.textmining.core;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InOrder;

import com.semanticweb.framework.module.file.IFileModule;
import com.semanticweb.framework.module.textmining.model.KeyWordSet;
import com.semanticweb.framework.module.textmining.support.IMetadataAdvisor;

public class TextMiningModuleTest {
    private TextMiningModule textMiningModule;
    private IFileModule fileModule;
    private IMetadataAdvisor metadataAdvisor;

    @Before
    public void doBefore() {
        textMiningModule = new TextMiningModule();
        fileModule = mock(IFileModule.class);
        metadataAdvisor = mock(IMetadataAdvisor.class);
        textMiningModule.setFileModule(fileModule);
        textMiningModule.setMetadataAdvisor(metadataAdvisor);
    }

    @Test
    public void testSuggestKeyWords() {
        String filePath = "path";
        String text = "text";
        List<KeyWordSet> list =  new ArrayList<KeyWordSet>();
        when(fileModule.extractTextFromFile(filePath)).thenReturn(text);
        when(metadataAdvisor.suggestKeyWords(text)).thenReturn(list);
        assertEquals(list, textMiningModule.suggestKeyWords(filePath));
        InOrder inOrder = inOrder(fileModule, metadataAdvisor);
        inOrder.verify(fileModule).extractTextFromFile(filePath);
        inOrder.verify(metadataAdvisor).suggestKeyWords(text);
    }
}

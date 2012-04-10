package com.semanticweb.framework.kernel;

/**
 * Interface que disponibiliza as propriedades (constantes) globais ao sistema
 * tais como configuracoes e propriedades em um arquivo de propriedades que fica
 * no classpath
 */
public interface IApplicationSettings {

    /**
     *
     * @return Retorna codigo da aplicacao
     */
    public String getAppCode();

    /**
     * Metodo principal da interface que retorna uma propriedade oriunda do
     * arquivo (ResourceBundle) config.properties
     *
     * @param key
     *            chave da propriedade
     * @return valor associado a chave
     */
    public String getString(String key);

    /**
     * Metodo principal da interface que retorna um 'objeto' oriundo do arquivo
     * (ResourceBundle) config.properties
     *
     * @param key
     *            chave da propriedade
     * @return objeto associado a chave
     */
    public Object get(String key);

    /**
     * Metodo que retorna aonde esta o arquivo de configuracao do log.
     *
     * @return o path do arquivo de configuracao do log
     */
    public String getLogPath();

    /**
     * Metodo que inicializa os Settings da aplicacao carregando o arquivo
     * config.properties
     */
    public void initialize();
}
package com.semanticweb.framework.module.web;

import java.io.IOException;
import java.util.Locale;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * Interface do gerenciador de fluxo
 */
public interface IFlowController {

    /**
     * Enum to tipo de request
     */
    public enum Type {
        POST, GET;
    }

    /**
     * Retorna o request uri
     *
     * @return o request uri
     */
    String getRequestURI();

    /**
     * Retorna o uri de origem
     *
     * @return o uri de origem
     */
    String getOriginURI();

    /**
     * Initilization Method for the Flow Controller
     *
     * @param request o objeto request
     * @param response o objeto response
     */
    void processRequest(ServletRequest request, ServletResponse response);

    /**
     * Redireciona para a url
     *
     * @param url url do redirecionamento
     * @throws IOException quando ocorre erro no redirecionamento
     */
    void sendRedirect(String url) throws IOException;

    /**
     * Redireciona para a pagina de error
     *
     * @param arg codigo do erro
     * @throws IOException quando ocorre erro no redirecionamento
     */
    void sendError(int arg) throws IOException;

    /**
     * Informa se o request foi comitado
     *
     * @return true se o request foi comitado
     */
    boolean isRequestCommitted();

    /**
     * Informa se foi solicitado um redirect
     *
     * @return true se foi solicitado um redirect
     */
    public boolean isRedirectRequested();

    /**
     * Invalida a sessao
     */
    void invalidateSession();

    /**
     * Retorna o objeto request
     *
     * @return o objeto request
     */
    HttpServletRequest getRequest();

    /**
     * Informa se o request eh um refresh
     *
     * @return true se for um refresh
     */
    boolean isRefresh();

    /**
     * Adiciona ou substitui uma chave/valor string no header
     *
     * @param arg0 a chave
     * @param arg1 o valor inteiro
     */
    void setHeader(String arg0, String arg1);

    /**
     * Informa se o header contem a chave
     *
     * @param arg0 a chave a ser procurada no header
     * @return true se a chave estiver no header
     */
    boolean containsHeader(String arg0);

    /**
     * Adiciona uma chave/valor inteiro no header
     *
     * @param arg0 a chave
     * @param arg1 o valor inteiro
     */
    void addIntHeader(String arg0, int arg1);

    /**
     * Adiciona uma chave/valor string no header
     *
     * @param arg0 a chave
     * @param arg1 o valor string
     */
    void addHeader(String arg0, String arg1);

    /**
     * Adiciona um cookie
     *
     * @param arg0 valor do cookie a ser adicionado
     */
    void addCookie(Cookie arg0);

    /**
     * Seta o locale
     *
     * @param arg0 locale a ser setado
     */
    void setLocale(Locale arg0);

    /**
     * Seta o content type
     *
     * @param arg0 content type a ser setado
     */
    void setContentType(String arg0);

    /**
     * Seta o tamanho do content
     *
     * @param arg0 tamanho a ser setado
     */
    void setContentLength(int arg0);

    /**
     * Seta o charset
     *
     * @param arg0 charset a ser setado
     */
    void setCharacterEncoding(String arg0);

    /**
     * Seta o tamanho do buffer
     *
     * @param arg0 tamanho a ser setado
     */
    void setBufferSize(int arg0);

    /**
     * Reinicializa o buffer
     */
    void resetBuffer();

    /**
     * Retorna o locale
     *
     * @return o locale
     */
    Locale getLocale();

    /**
     * Retorna o content type do responde
     *
     * @return o content type do responde
     */
    String getResponseContentType();

    /**
     * Retorna o charset
     *
     * @return o charset
     */
    String getCharacterEncoding();

    /**
     * Retorna o tamanho do buffer
     *
     * @return o tamanho do buffer
     */
    int getBufferSize();

    /**
     * Adiciona uma chave/valor do tipo data ao header
     *
     * @param arg0 a chave
     * @param arg1 o valor
     */
    void addDateHeader(String arg0, long arg1);

    /**
     * Realiza o encode da url de direcionamento
     *
     * @param arg0 a url a ser encodada
     * @return a url encodada
     */
    String encodeRedirectURL(String arg0);

    /**
     * Realiza o encode da url
     *
     * @param arg0 a url a ser encodada
     * @return a url encodada
     */
    String encodeURL(String arg0);

    /**
     * Adiciona ou substitui uma chave/valor do tipo data ao header
     *
     * @param arg0 a chave
     * @param arg1 o valor
     */
    void setDateHeader(String arg0, long arg1);

    /**
     * Adiciona uma chave/valor inteiro ao header
     *
     * @param arg0 a chave
     * @param arg1 o valor inteiro
     */
    void setIntHeader(String arg0, int arg1);

    /**
     * Seta o status
     *
     * @param arg0 o valor do status a ser setado
     */
    void setStatus(int arg0);

    /**
     * Reinicializa o gerenciador de navegacao
     */
    void reset();
}

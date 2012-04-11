package com.semanticweb.framework.module.web.core;

import java.io.IOException;
import java.util.Locale;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.semanticweb.framework.module.web.IFlowController;

/**
 * Interface do gerenciador de fluxo
 */
public class FlowController implements IFlowController {
    private static final Logger LOG = LoggerFactory.getLogger(FlowController.class);
    private boolean redirectRequested = false;
    private HttpServletRequest request;
    private HttpServletResponse response;

    /**
     * {@inheritDoc}
     *
     * @param url
     *            {@inheritDoc}
     */
    @Override
    public void sendRedirect(String url) throws IOException {
        LOG.debug("Redirect requested");
        redirectRequested = true;
        response.sendRedirect(url);
    }

    /**
     * {@inheritDoc}
     *
     * @param arg
     *            {@inheritDoc}
     */
    @Override
    public void sendError(int arg) throws IOException {
        LOG.debug("Send Error Requested");
        redirectRequested = true;
        response.sendError(arg);
    }

    /**
     * {@inheritDoc}
     *
     * @param request
     *            {@inheritDoc}
     * @param response
     *            {@inheritDoc}
     */
    @Override
    public void processRequest(ServletRequest request, ServletResponse response) {
        this.request = (HttpServletRequest) request;
        this.response = (HttpServletResponse) response;
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public String getRequestURI() {
        return request.getRequestURI();
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public HttpServletRequest getRequest() {
        return request;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void invalidateSession() {
        if (request.getSession(false) != null) {
            request.getSession(false).invalidate();
        }
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public boolean isRedirectRequested() {
        return redirectRequested;
    }

    @Override
    public boolean isRequestCommitted() {
        return this.response.isCommitted();
    }

    /**
     * {@inheritDoc}
     *
     * @param arg0
     *            {@inheritDoc}
     * @param arg1
     *            {@inheritDoc}
     */
    @Override
    public void setHeader(String arg0, String arg1) {
        response.setHeader(arg0, arg1);
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public int getBufferSize() {
        return response.getBufferSize();
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public String getCharacterEncoding() {
        return response.getCharacterEncoding();
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public String getResponseContentType() {
        return response.getContentType();
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public Locale getLocale() {
        return response.getLocale();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void resetBuffer() {
        response.resetBuffer();
    }

    /**
     * {@inheritDoc}
     *
     * @param arg0
     *            {@inheritDoc}
     */
    @Override
    public void setBufferSize(int arg0) {
        response.setBufferSize(arg0);
    }

    /**
     * {@inheritDoc}
     *
     * @param {@inheritDoc}
     */
    @Override
    public void setCharacterEncoding(String arg0) {
        response.setCharacterEncoding(arg0);
    }

    /**
     * {@inheritDoc}
     *
     * @param arg0
     *            {@inheritDoc}
     */
    @Override
    public void setContentLength(int arg0) {
        response.setContentLength(arg0);
    }

    /**
     * {@inheritDoc}
     *
     * @param arg0
     *            {@inheritDoc}
     */
    @Override
    public void setContentType(String arg0) {
        response.setContentType(arg0);
    }

    /**
     * {@inheritDoc}
     *
     * @param arg0
     *            {@inheritDoc}
     */
    @Override
    public void setLocale(Locale arg0) {
        response.setLocale(arg0);
    }

    /**
     * {@inheritDoc}
     *
     * @param arg0
     *            {@inheritDoc}
     */
    @Override
    public void addCookie(Cookie arg0) {
        response.addCookie(arg0);
    }

    /**
     * {@inheritDoc}
     *
     * @param arg0
     *            {@inheritDoc}
     * @param arg1
     *            {@inheritDoc}
     */
    @Override
    public void addHeader(String arg0, String arg1) {
        response.addHeader(arg0, arg1);
    }

    /**
     * {@inheritDoc}
     *
     * @param arg0
     *            {@inheritDoc}
     * @param arg1
     *            {@inheritDoc}
     */
    @Override
    public void addIntHeader(String arg0, int arg1) {
        response.addIntHeader(arg0, arg1);
    }

    /**
     * {@inheritDoc}
     *
     * @param arg0
     *            {@inheritDoc}
     * @return {@inheritDoc}
     */
    @Override
    public boolean containsHeader(String arg0) {
        return response.containsHeader(arg0);
    }

    /**
     * {@inheritDoc}
     *
     * @param arg0
     *            {@inheritDoc}
     * @param arg1
     *            {@inheritDoc}
     */
    @Override
    public void addDateHeader(String arg0, long arg1) {
        response.addDateHeader(arg0, arg1);
    }

    /**
     * {@inheritDoc}
     *
     * @param arg0
     *            {@inheritDoc}
     * @return {@inheritDoc}
     */
    @Override
    public String encodeRedirectURL(String arg0) {
        return response.encodeRedirectURL(arg0);
    }

    /**
     * {@inheritDoc}
     *
     * @param arg0
     *            {@inheritDoc}
     * @return {@inheritDoc}
     */
    @Override
    public String encodeURL(String arg0) {
        return response.encodeURL(arg0);
    }

    /**
     * {@inheritDoc}
     *
     * @param arg0
     *            {@inheritDoc}
     * @param arg1
     *            {@inheritDoc}
     */
    @Override
    public void setDateHeader(String arg0, long arg1) {
        response.setDateHeader(arg0, arg1);
    }

    /**
     * {@inheritDoc}
     *
     * @param arg0
     *            {@inheritDoc}
     * @param arg1
     *            {@inheritDoc}
     */
    @Override
    public void setIntHeader(String arg0, int arg1) {
        response.setIntHeader(arg0, arg1);
    }

    /**
     * {@inheritDoc}
     *
     * @param arg0
     *            {@inheritDoc}
     */
    @Override
    public void setStatus(int arg0) {
        response.setStatus(arg0);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void reset() {
        response.reset();
    }
}

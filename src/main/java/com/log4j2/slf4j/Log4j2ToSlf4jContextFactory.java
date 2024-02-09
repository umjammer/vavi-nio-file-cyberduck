/*
 * https://github.com/rocky-peng/log4j2-over-slf4j
 */

package com.log4j2.slf4j;

import java.net.URI;

import org.apache.logging.log4j.spi.LoggerContext;
import org.apache.logging.log4j.spi.LoggerContextFactory;


/**
 * Log4j2ToSlf4jContextFactory.
 *
 * @author Rocky
 * @version 4/1/16 13:59
 */
public class Log4j2ToSlf4jContextFactory implements LoggerContextFactory {

    private static final LoggerContext context = new SimpleLoggerContext();

    @Override
    public LoggerContext getContext(String fqcn, ClassLoader loader, Object externalContext, boolean currentContext) {
System.err.println("here0: " + fqcn);
        return context;
    }

    @Override
    public LoggerContext getContext(String fqcn, ClassLoader loader, Object externalContext, boolean currentContext, URI configLocation, String name) {
System.err.println("here1: " + name);
        return context;
    }

    @Override
    public void removeContext(LoggerContext context) {
    }
}
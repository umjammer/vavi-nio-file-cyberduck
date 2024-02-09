/*
 * https://github.com/rocky-peng/log4j2-over-slf4j
 */

package com.log4j2.slf4j;

import org.apache.logging.log4j.message.MessageFactory;
import org.apache.logging.log4j.spi.ExtendedLogger;
import org.apache.logging.log4j.spi.LoggerContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ConcurrentHashMap;


/**
 * SimpleLoggerContext.
 *
 * @author Rocky
 * @version 4/1/16 14:26
 */
public class SimpleLoggerContext implements LoggerContext {

    private static final ConcurrentHashMap<String, ExtendedLogger> loggerMap = new ConcurrentHashMap<>();

    @Override
    public Object getExternalContext() {
        return null;
    }

    @Override
    public ExtendedLogger getLogger(String name) {
        if (!loggerMap.containsKey(name)) {
            Logger logger = LoggerFactory.getLogger(name);
            loggerMap.put(name, new ExtendedLoggerAdapter(logger));
        }
System.err.println("here: @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@: " + name + ", " + loggerMap.get(name));
        return loggerMap.get(name);
    }

    @Override
    public ExtendedLogger getLogger(String name, MessageFactory messageFactory) {
        return getLogger(name);
    }

    @Override
    public boolean hasLogger(String name) {
        return loggerMap.containsKey(name);
    }

    @Override
    public boolean hasLogger(String name, Class<? extends MessageFactory> messageFactoryClass) {
        return hasLogger(name);
    }

    @Override
    public boolean hasLogger(String name, MessageFactory messageFactory) {
        return hasLogger(name);
    }
}
/*
 * https://github.com/rocky-peng/log4j2-over-slf4j
 */

package com.log4j2.slf4j;

import java.util.Arrays;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.message.EntryMessage;
import org.apache.logging.log4j.message.FlowMessageFactory;
import org.apache.logging.log4j.message.Message;
import org.apache.logging.log4j.message.MessageFactory;
import org.apache.logging.log4j.spi.ExtendedLogger;
import org.apache.logging.log4j.util.MessageSupplier;
import org.apache.logging.log4j.util.Supplier;
import org.slf4j.Logger;


/**
 * ExtendedLoggerAdapter.
 *
 * TODO slf4j has Maker (need to convert)
 *
 * @author Rocky
 * @version 4/1/16 14:32
 */
public class ExtendedLoggerAdapter implements ExtendedLogger {

    private final Logger logger;

    public ExtendedLoggerAdapter(Logger logger) {
        this.logger = logger;
    }

    @Override
    public boolean isEnabled(Level level, Marker marker, Message message, Throwable t) {
        return isEnabled(level);
    }

    @Override
    public boolean isEnabled(Level level, Marker marker, CharSequence message, Throwable t) {
        return isEnabled(level);
    }

    @Override
    public boolean isEnabled(Level level, Marker marker, Object message, Throwable t) {
        return isEnabled(level);
    }

    @Override
    public boolean isEnabled(Level level, Marker marker, String message, Throwable t) {
        return isEnabled(level);
    }

    @Override
    public boolean isEnabled(Level level, Marker marker, String message) {
        return isEnabled(level);
    }

    @Override
    public boolean isEnabled(Level level, Marker marker, String message, Object... params) {
        return isEnabled(level);
    }

    @Override
    public boolean isEnabled(Level level, Marker marker, String message, Object p0) {
        return isEnabled(level);
    }

    @Override
    public boolean isEnabled(Level level, Marker marker, String message, Object p0, Object p1) {
        return isEnabled(level);
    }

    @Override
    public boolean isEnabled(Level level, Marker marker, String message, Object p0, Object p1, Object p2) {
        return isEnabled(level);
    }

    @Override
    public boolean isEnabled(Level level, Marker marker, String message, Object p0, Object p1, Object p2, Object p3) {
        return isEnabled(level);
    }

    @Override
    public boolean isEnabled(Level level, Marker marker, String message, Object p0, Object p1, Object p2, Object p3, Object p4) {
        return isEnabled(level);
    }

    @Override
    public boolean isEnabled(Level level, Marker marker, String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5) {
        return isEnabled(level);
    }

    @Override
    public boolean isEnabled(Level level, Marker marker, String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6) {
        return isEnabled(level);
    }

    @Override
    public boolean isEnabled(Level level, Marker marker, String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7) {
        return isEnabled(level);
    }

    @Override
    public boolean isEnabled(Level level, Marker marker, String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7, Object p8) {
        return isEnabled(level);
    }

    @Override
    public boolean isEnabled(Level level, Marker marker, String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7, Object p8, Object p9) {
        return isEnabled(level);
    }

    @Override
    public void logIfEnabled(String fqcn, Level level, Marker marker, Message message, Throwable t) {
        log(level, marker, message, t);
    }

    @Override
    public void logIfEnabled(String fqcn, Level level, Marker marker, CharSequence message, Throwable t) {
        log(level, marker, message, t);
    }

    @Override
    public void logIfEnabled(String fqcn, Level level, Marker marker, Object message, Throwable t) {
        log(level, marker, message, t);
    }

    @Override
    public void logIfEnabled(String fqcn, Level level, Marker marker, String message, Throwable t) {
        log(level, marker, message, t);
    }

    @Override
    public void logIfEnabled(String fqcn, Level level, Marker marker, String message) {
        log(level, marker, message);
    }

    @Override
    public void logIfEnabled(String fqcn, Level level, Marker marker, String message, Object... params) {
        log(level, marker, message, params);
    }

    @Override
    public void logIfEnabled(String fqcn, Level level, Marker marker, String message, Object p0) {
        log(level, marker, message, p0);
    }

    @Override
    public void logIfEnabled(String fqcn, Level level, Marker marker, String message, Object p0, Object p1) {
        log(level, marker, message, p0, p1);
    }

    @Override
    public void logIfEnabled(String fqcn, Level level, Marker marker, String message, Object p0, Object p1, Object p2) {
        log(level, marker, message, p0, p1, p2);
    }

    @Override
    public void logIfEnabled(String fqcn, Level level, Marker marker, String message, Object p0, Object p1, Object p2, Object p3) {
        log(level, marker, message, p0, p1, p2, p3);
    }

    @Override
    public void logIfEnabled(String fqcn, Level level, Marker marker, String message, Object p0, Object p1, Object p2, Object p3, Object p4) {
        log(level, marker, message, p0, p1, p2, p3, p4);
    }

    @Override
    public void logIfEnabled(String fqcn, Level level, Marker marker, String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5) {
        log(level, marker, message, p0, p1, p2, p3, p4, p5);
    }

    @Override
    public void logIfEnabled(String fqcn, Level level, Marker marker, String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6) {
        log(level, marker, message, p0, p1, p2, p3, p4, p5, p6);
    }

    @Override
    public void logIfEnabled(String fqcn, Level level, Marker marker, String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7) {
        log(level, marker, message, p0, p1, p2, p3, p4, p5, p6, p7);
    }

    @Override
    public void logIfEnabled(String fqcn, Level level, Marker marker, String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7, Object p8) {
        log(level, marker, message, p0, p1, p2, p3, p4, p5, p6, p7, p8);
    }

    @Override
    public void logIfEnabled(String fqcn, Level level, Marker marker, String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7, Object p8, Object p9) {
        log(level, marker, message, p0, p1, p2, p3, p4, p5, p6, p7, p8, p9);
    }

    @Override
    public void logMessage(String fqcn, Level level, Marker marker, Message message, Throwable t) {
        log(level, marker, message, t);
    }

    @Override
    public void logIfEnabled(String fqcn, Level level, Marker marker, MessageSupplier msgSupplier, Throwable t) {
        log(level, marker, msgSupplier.get(), t);
    }

    @Override
    @SuppressWarnings("deprecation")
    public void logIfEnabled(String fqcn, Level level, Marker marker, String message, Supplier<?>... paramSuppliers) {
        if (isEnabled(level)) {
            log(level, marker, message, paramSuppliers);
        }
    }

    @Override
    @SuppressWarnings("deprecation")
    public void logIfEnabled(String fqcn, Level level, Marker marker, Supplier<?> msgSupplier, Throwable t) {
        if (isEnabled(level)) {
            log(level, marker, msgSupplier, t);
        }
    }

    @Override
    public void catching(Level level, Throwable t) {
        logger.error("", t);
    }

    @Override
    public void catching(Throwable t) {
        logger.error("", t);
    }

    @Override
    public void debug(Marker marker, Message msg) {
        if (msg != null) {
            debug(marker, msg.toString());
        }
    }

    @Override
    public void debug(Marker marker, Message msg, Throwable t) {
        if (msg != null) {
            debug(marker, msg.toString(), t);
        }
    }

    @Override
    public void debug(Marker marker, MessageSupplier messageSupplier) {
        if (messageSupplier != null) {
            debug(marker, messageSupplier.get().toString());
        }
    }

    @Override
    public void debug(Marker marker, MessageSupplier messageSupplier, Throwable throwable) {
        if (messageSupplier != null) {
            debug(marker, messageSupplier.get().toString(), throwable);
        }
    }

    @Override
    public void debug(Marker marker, CharSequence message) {
        if (message != null) {
            debug(marker, message.toString());
        }
    }

    @Override
    public void debug(Marker marker, CharSequence message, Throwable throwable) {
        if (message != null) {
            debug(marker, message.toString(), throwable);
        }
    }

    @Override
    public void debug(Marker marker, Object message) {
        if (message != null) {
            debug(marker, message.toString());
        }
    }

    @Override
    public void debug(Marker marker, Object message, Throwable t) {
        if (message != null) {
            debug(marker, message.toString(), t);
        }
    }

    @Override
    public void debug(Marker marker, String message) {
        logger.debug(message);
    }

    @Override
    public void debug(Marker marker, String message, Object... params) {
        logger.debug(message, params);
    }

    @Override
    @SuppressWarnings("deprecation")
    public void debug(Marker marker, String message, Supplier<?>... paramSuppliers) {
        debug(marker, message, Arrays.stream(paramSuppliers).map(Supplier::get).toArray());
    }

    @Override
    public void debug(Marker marker, String message, Throwable t) {
        logger.debug(message, t);
    }

    @Override
    @SuppressWarnings("deprecation")
    public void debug(Marker marker, Supplier<?> messageSupplier) {
        debug(marker, messageSupplier.get().toString());
    }

    @Override
    @SuppressWarnings("deprecation")
    public void debug(Marker marker, Supplier<?> messageSupplier, Throwable throwable) {
        debug(marker, messageSupplier.get().toString(), throwable);
    }

    @Override
    public void debug(Message msg) {
        if (msg != null) {
            debug(msg.toString());
        }
    }

    @Override
    public void debug(Message msg, Throwable t) {
        if (msg != null) {
            debug(msg.toString(), t);
        }
    }

    @Override
    public void debug(MessageSupplier messageSupplier) {
        if (messageSupplier != null) {
            debug(messageSupplier.toString());
        }
    }

    @Override
    public void debug(MessageSupplier messageSupplier, Throwable throwable) {
        if (messageSupplier != null) {
            debug(messageSupplier.toString(), throwable);
        }
    }

    @Override
    public void debug(CharSequence message) {
        if (message != null) {
            debug(message.toString());
        }
    }

    @Override
    public void debug(CharSequence message, Throwable throwable) {
        if (message != null) {
            debug(message.toString(), throwable);
        }
    }

    @Override
    public void debug(Object message) {
        if (message != null) {
            debug(message.toString());
        }
    }

    @Override
    public void debug(Object message, Throwable t) {
        if (message != null) {
            debug(message.toString(), t);
        }
    }

    @Override
    public void debug(String message) {
        logger.debug(message);
    }

    @Override
    public void debug(String message, Object... params) {
        logger.debug(message, params);
    }

    @Override
    @SuppressWarnings("deprecation")
    public void debug(String message, Supplier<?>... paramSuppliers) {
        logger.debug(message, Arrays.stream(paramSuppliers).map(Supplier::get).toArray());
    }

    @Override
    public void debug(String message, Throwable t) {
        logger.debug(message, t);
    }

    @Override
    @SuppressWarnings("deprecation")
    public void debug(Supplier<?> messageSupplier) {
        if (messageSupplier != null) {
            debug(messageSupplier.toString());
        }
    }

    @Override
    @SuppressWarnings("deprecation")
    public void debug(Supplier<?> messageSupplier, Throwable throwable) {
        if (messageSupplier != null) {
            debug(messageSupplier.toString(), throwable);
        }
    }

    @Override
    public void debug(Marker marker, String message, Object p0) {
        debug(message, p0);
    }

    @Override
    public void debug(Marker marker, String message, Object p0, Object p1) {
        debug(message, p0, p1);
    }

    @Override
    public void debug(Marker marker, String message, Object p0, Object p1, Object p2) {
        debug(message, p0, p1, p2);
    }

    @Override
    public void debug(Marker marker, String message, Object p0, Object p1, Object p2, Object p3) {
        debug(message, p0, p1, p2, p3);
    }

    @Override
    public void debug(Marker marker, String message, Object p0, Object p1, Object p2, Object p3, Object p4) {
        debug(message, p0, p1, p2, p3, p4);
    }

    @Override
    public void debug(Marker marker, String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5) {
        debug(message, p0, p1, p2, p3, p4, p5);
    }

    @Override
    public void debug(Marker marker, String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6) {
        debug(message, p0, p1, p2, p3, p4, p5, p6);
    }

    @Override
    public void debug(Marker marker, String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7) {
        debug(message, p0, p1, p2, p3, p4, p5, p6, p7);
    }

    @Override
    public void debug(Marker marker, String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7, Object p8) {
        debug(message, p0, p1, p2, p3, p4, p5, p6, p7, p8);
    }

    @Override
    public void debug(Marker marker, String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7, Object p8, Object p9) {
        debug(message, p0, p1, p2, p3, p4, p5, p6, p7, p8, p9);
    }

    @Override
    public void debug(String message, Object p0) {
        logger.debug(message, p0);
    }

    @Override
    public void debug(String message, Object p0, Object p1) {
        logger.debug(message, p0, p1);
    }

    @Override
    public void debug(String message, Object p0, Object p1, Object p2) {
        logger.debug(message, p0, p1, p2);
    }

    @Override
    public void debug(String message, Object p0, Object p1, Object p2, Object p3) {
        logger.debug(message, p0, p1, p2, p3);
    }

    @Override
    public void debug(String message, Object p0, Object p1, Object p2, Object p3, Object p4) {
        logger.debug(message, p0, p1, p2, p3, p4);
    }

    @Override
    public void debug(String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5) {
        logger.debug(message, p0, p1, p2, p3, p4, p5);
    }

    @Override
    public void debug(String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6) {
        logger.debug(message, p0, p1, p2, p3, p4, p5, p6);
    }

    @Override
    public void debug(String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7) {
        logger.debug(message, p0, p1, p2, p3, p4, p5, p6, p7);
    }

    @Override
    public void debug(String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7, Object p8) {
        logger.debug(message, p0, p1, p2, p3, p4, p5, p6, p7, p8);
    }

    @Override
    public void debug(String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7, Object p8, Object p9) {
        logger.debug(message, p0, p1, p2, p3, p4, p5, p6, p7, p8, p9);
    }

    @Override
    public void entry() {
        //do thing
    }

    @Override
    public void entry(Object... params) {
        //do thing
    }

    @Override
    public void error(Marker marker, Message msg) {
        if (msg != null) {
            logger.error(msg.toString());
        }
    }

    @Override
    public void error(Marker marker, Message msg, Throwable t) {
        if (msg != null) {
            logger.error(msg.toString(), t);
        }
    }

    @Override
    public void error(Marker marker, MessageSupplier messageSupplier) {
        if (messageSupplier != null) {
            logger.error(messageSupplier.get().toString());
        }
    }

    @Override
    public void error(Marker marker, MessageSupplier messageSupplier, Throwable throwable) {
        if (messageSupplier != null) {
            logger.error(messageSupplier.get().toString(), throwable);
        }
    }

    @Override
    public void error(Marker marker, CharSequence message) {
        if (message != null) {
            logger.error(message.toString());
        }
    }

    @Override
    public void error(Marker marker, CharSequence message, Throwable throwable) {
        if (message != null) {
            logger.error(message.toString(), throwable);
        }
    }

    @Override
    public void error(Marker marker, Object message) {
        if (message != null) {
            logger.error(message.toString());
        }
    }

    @Override
    public void error(Marker marker, Object message, Throwable t) {
        if (message != null) {
            logger.error(message.toString(), t);
        }
    }

    @Override
    public void error(Marker marker, String message) {
        logger.error(message);
    }

    @Override
    public void error(Marker marker, String message, Object... params) {
        logger.error(message, params);
    }

    @Override
    @SuppressWarnings("deprecation")
    public void error(Marker marker, String message, Supplier<?>... paramSuppliers) {
        error(marker, message, Arrays.stream(paramSuppliers).map(Supplier::get).toArray());
    }

    @Override
    public void error(Marker marker, String message, Throwable t) {
        logger.error(message, t);
    }

    @Override
    @SuppressWarnings("deprecation")
    public void error(Marker marker, Supplier<?> messageSupplier) {
        error(marker, messageSupplier.get().toString());
    }

    @Override
    @SuppressWarnings("deprecation")
    public void error(Marker marker, Supplier<?> messageSupplier, Throwable throwable) {
        error(marker, messageSupplier.get().toString(), throwable);
    }

    @Override
    public void error(Message msg) {
        if (msg != null) {
            logger.error(msg.toString());
        }
    }

    @Override
    public void error(Message msg, Throwable t) {
        if (msg != null) {
            logger.error(msg.toString(), t);
        }
    }

    @Override
    public void error(MessageSupplier messageSupplier) {
        error(messageSupplier.get().toString());
    }

    @Override
    public void error(MessageSupplier messageSupplier, Throwable throwable) {
        error(messageSupplier.get().toString(), throwable);
    }

    @Override
    public void error(CharSequence message) {
        error(message.toString());
    }

    @Override
    public void error(CharSequence message, Throwable throwable) {
        error(message.toString(), throwable);
    }

    @Override
    public void error(Object message) {
        if (message != null) {
            logger.error(message.toString());
        }
    }

    @Override
    public void error(Object message, Throwable t) {
        if (message != null) {
            logger.error(message.toString(), t);
        }
    }

    @Override
    public void error(String message) {
        logger.error(message);
    }

    @Override
    public void error(String message, Object... params) {
        logger.error(message, params);
    }

    @Override
    @SuppressWarnings("deprecation")
    public void error(String message, Supplier<?>... paramSuppliers) {
        error(message, Arrays.stream(paramSuppliers).map(Supplier::get).toArray());
    }

    @Override
    public void error(String message, Throwable t) {
        logger.error(message, t);
    }

    @Override
    @SuppressWarnings("deprecation")
    public void error(Supplier<?> messageSupplier) {
        error(messageSupplier.get().toString());
    }

    @Override
    @SuppressWarnings("deprecation")
    public void error(Supplier<?> messageSupplier, Throwable throwable) {
        error(messageSupplier.get().toString(), throwable);
    }

    @Override
    public void error(Marker marker, String message, Object p0) {
        error(message, p0);
    }

    @Override
    public void error(Marker marker, String message, Object p0, Object p1) {
        error(message, p0, p1);
    }

    @Override
    public void error(Marker marker, String message, Object p0, Object p1, Object p2) {
        error(message, p0, p1, p2);
    }

    @Override
    public void error(Marker marker, String message, Object p0, Object p1, Object p2, Object p3) {
        error(message, p0, p1, p2, p3);
    }

    @Override
    public void error(Marker marker, String message, Object p0, Object p1, Object p2, Object p3, Object p4) {
        error(message, p0, p1, p2, p3, p4);
    }

    @Override
    public void error(Marker marker, String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5) {
        error(message, p0, p1, p2, p3, p4, p5);
    }

    @Override
    public void error(Marker marker, String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6) {
        error(message, p0, p1, p2, p3, p4, p5, p6);
    }

    @Override
    public void error(Marker marker, String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7) {
        error(message, p0, p1, p2, p3, p4, p5, p6, p7);
    }

    @Override
    public void error(Marker marker, String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7, Object p8) {
        error(message, p0, p1, p2, p3, p4, p5, p6, p7, p8);
    }

    @Override
    public void error(Marker marker, String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7, Object p8, Object p9) {
        error(message, p0, p1, p2, p3, p4, p5, p6, p7, p8, p9);
    }

    @Override
    public void error(String message, Object p0) {
        logger.error(message, p0);
    }

    @Override
    public void error(String message, Object p0, Object p1) {
        logger.error(message, p0, p1);
    }

    @Override
    public void error(String message, Object p0, Object p1, Object p2) {
        logger.error(message, p0, p1, p2);
    }

    @Override
    public void error(String message, Object p0, Object p1, Object p2, Object p3) {
        logger.error(message, p0, p1, p2, p3);
    }

    @Override
    public void error(String message, Object p0, Object p1, Object p2, Object p3, Object p4) {
        logger.error(message, p0, p1, p2, p3, p4);
    }

    @Override
    public void error(String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5) {
        logger.error(message, p0, p1, p2, p3, p4, p5);
    }

    @Override
    public void error(String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6) {
        logger.error(message, p0, p1, p2, p3, p4, p5, p6);
    }

    @Override
    public void error(String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7) {
        logger.error(message, p0, p1, p2, p3, p4, p5, p6, p7);
    }

    @Override
    public void error(String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7, Object p8) {
        logger.error(message, p0, p1, p2, p3, p4, p5, p6, p7, p8);
    }

    @Override
    public void error(String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7, Object p8, Object p9) {
        logger.error(message, p0, p1, p2, p3, p4, p5, p6, p7, p8, p9);
    }

    @Override
    public void exit() {
        //do nothing
    }

    @Override
    public <R> R exit(R result) {
        return null;
    }

    @Override
    public void fatal(Marker marker, Message msg) {
        error(marker, msg);
    }

    @Override
    public void fatal(Marker marker, Message msg, Throwable t) {
        error(marker, msg, t);
    }

    @Override
    public void fatal(Marker marker, MessageSupplier messageSupplier) {
        fatal(marker, messageSupplier.get().toString());
    }

    @Override
    public void fatal(Marker marker, MessageSupplier messageSupplier, Throwable throwable) {
        fatal(marker, messageSupplier.get().toString(), throwable);
    }

    @Override
    public void fatal(Marker marker, CharSequence message) {
        fatal(marker, marker.toString());
    }

    @Override
    public void fatal(Marker marker, CharSequence message, Throwable throwable) {
        fatal(marker, marker.toString(), throwable);
    }

    @Override
    public void fatal(Marker marker, Object message) {
        error(marker, message);
    }

    @Override
    public void fatal(Marker marker, Object message, Throwable t) {
        error(marker, message, t);
    }

    @Override
    public void fatal(Marker marker, String message) {
        error(marker, message);
    }

    @Override
    public void fatal(Marker marker, String message, Object... params) {
        error(marker, message, params);
    }

    @Override
    @SuppressWarnings("deprecation")
    public void fatal(Marker marker, String message, Supplier<?>... paramSuppliers) {
        fatal(marker, message, Arrays.stream(paramSuppliers).map(Supplier::get).toArray());
    }

    @Override
    public void fatal(Marker marker, String message, Throwable t) {
        error(marker, message, t);
    }

    @Override
    @SuppressWarnings("deprecation")
    public void fatal(Marker marker, Supplier<?> messageSupplier) {
        fatal(marker, messageSupplier.get().toString());
    }

    @Override
    @SuppressWarnings("deprecation")
    public void fatal(Marker marker, Supplier<?> messageSupplier, Throwable throwable) {
        fatal(marker, messageSupplier.get().toString(), throwable);
    }

    @Override
    public void fatal(Message msg) {
        error(msg);
    }

    @Override
    public void fatal(Message msg, Throwable t) {
        error(msg, t);
    }

    @Override
    public void fatal(MessageSupplier messageSupplier) {
        fatal(messageSupplier.get().toString());
    }

    @Override
    public void fatal(MessageSupplier messageSupplier, Throwable throwable) {
        fatal(messageSupplier.get().toString(), throwable);
    }

    @Override
    public void fatal(CharSequence message) {
        fatal(message.toString());
    }

    @Override
    public void fatal(CharSequence message, Throwable throwable) {
        fatal(message.toString(), throwable);
    }

    @Override
    public void fatal(Object message) {
        error(message);
    }

    @Override
    public void fatal(Object message, Throwable t) {
        error(message, t);
    }

    @Override
    public void fatal(String message) {
        error(message);
    }

    @Override
    public void fatal(String message, Object... params) {
        error(message, params);
    }

    @Override
    @SuppressWarnings("deprecation")
    public void fatal(String message, Supplier<?>... paramSuppliers) {
        fatal(message, Arrays.stream(paramSuppliers).map(Supplier::get).toArray());
    }

    @Override
    public void fatal(String message, Throwable t) {
        error(message, t);
    }

    @Override
    @SuppressWarnings("deprecation")
    public void fatal(Supplier<?> messageSupplier) {
        if (messageSupplier != null) {
            fatal(messageSupplier.get().toString());
        }
    }

    @Override
    @SuppressWarnings("deprecation")
    public void fatal(Supplier<?> messageSupplier, Throwable throwable) {
        if (messageSupplier != null) {
            fatal(messageSupplier.get().toString(), throwable);
        }
    }

    @Override
    public void fatal(Marker marker, String message, Object p0) {
        fatal(message, p0);
    }

    @Override
    public void fatal(Marker marker, String message, Object p0, Object p1) {
        fatal(message, p0, p1);
    }

    @Override
    public void fatal(Marker marker, String message, Object p0, Object p1, Object p2) {
        fatal(message, p0, p1, p2);
    }

    @Override
    public void fatal(Marker marker, String message, Object p0, Object p1, Object p2, Object p3) {
        fatal(message, p0, p1, p2, p3);
    }

    @Override
    public void fatal(Marker marker, String message, Object p0, Object p1, Object p2, Object p3, Object p4) {
        fatal(message, p0, p1, p2, p3, p4);
    }

    @Override
    public void fatal(Marker marker, String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5) {
        fatal(message, p0, p1, p2, p3, p4, p5);
    }

    @Override
    public void fatal(Marker marker, String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6) {
        fatal(message, p0, p1, p2, p3, p4, p5, p6);
    }

    @Override
    public void fatal(Marker marker, String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7) {
        fatal(message, p0, p1, p2, p3, p4, p5, p6, p7);
    }

    @Override
    public void fatal(Marker marker, String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7, Object p8) {
        fatal(message, p0, p1, p2, p3, p4, p5, p6, p7, p8);
    }

    @Override
    public void fatal(Marker marker, String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7, Object p8, Object p9) {
        fatal(message, p0, p1, p2, p3, p4, p5, p6, p7, p8, p9);
    }

    @Override
    public void fatal(String message, Object p0) {
        logger.error(message, p0);
    }

    @Override
    public void fatal(String message, Object p0, Object p1) {
        logger.error(message, p0, p1);
    }

    @Override
    public void fatal(String message, Object p0, Object p1, Object p2) {
        logger.error(message, p0, p1, p2);
    }

    @Override
    public void fatal(String message, Object p0, Object p1, Object p2, Object p3) {
        logger.error(message, p0, p1, p2, p3);
    }

    @Override
    public void fatal(String message, Object p0, Object p1, Object p2, Object p3, Object p4) {
        logger.error(message, p0, p1, p2, p3, p4);
    }

    @Override
    public void fatal(String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5) {
        logger.error(message, p0, p1, p2, p3, p4, p5);
    }

    @Override
    public void fatal(String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6) {
        logger.error(message, p0, p1, p2, p3, p4, p5, p6);
    }

    @Override
    public void fatal(String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7) {
        logger.error(message, p0, p1, p2, p3, p4, p5, p6, p7);
    }

    @Override
    public void fatal(String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7, Object p8) {
        logger.error(message, p0, p1, p2, p3, p4, p5, p6, p7, p8);
    }

    @Override
    public void fatal(String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7, Object p8, Object p9) {
        logger.error(message, p0, p1, p2, p3, p4, p5, p6, p7, p8, p9);
    }

    @Override
    public Level getLevel() {
        if (logger.isTraceEnabled()) {
            return Level.ALL;
        }
        if (logger.isDebugEnabled()) {
            return Level.DEBUG;
        }
        if (logger.isInfoEnabled()) {
            return Level.INFO;
        }
        if (logger.isWarnEnabled()) {
            return Level.WARN;
        }
        if (logger.isErrorEnabled()) {
            return Level.ERROR;
        }
        return Level.OFF;
    }

    @Override
    public FlowMessageFactory getFlowMessageFactory() {
        return null;
    }

    @Override
    public MessageFactory getMessageFactory() {
        return null;
    }

    @Override
    public String getName() {
        return logger.getName();
    }

    @Override
    public void info(Marker marker, Message msg) {
        if (msg != null) {
            info(msg.toString());
        }
    }

    @Override
    public void info(Marker marker, Message msg, Throwable t) {
        if (msg != null) {
            info(msg.toString(), t);
        }
    }

    @Override
    public void info(Marker marker, MessageSupplier messageSupplier) {
        if (messageSupplier != null) {
            info(messageSupplier.get().toString());
        }
    }

    @Override
    public void info(Marker marker, MessageSupplier messageSupplier, Throwable throwable) {
        if (messageSupplier != null) {
            info(messageSupplier.get().toString(), throwable);
        }
    }

    @Override
    public void info(Marker marker, CharSequence message) {
        if (message != null) {
            info(message.toString());
        }
    }

    @Override
    public void info(Marker marker, CharSequence message, Throwable throwable) {
        if (message != null) {
            info(message.toString(), throwable);
        }
    }

    @Override
    public void info(Marker marker, Object message) {
        if (message != null) {
            info(message.toString());
        }
    }

    @Override
    public void info(Marker marker, Object message, Throwable t) {
        if (message != null) {
            info(message.toString(), t);
        }
    }

    @Override
    public void info(Marker marker, String message) {
        logger.info(message);
    }

    @Override
    public void info(Marker marker, String message, Object... params) {
        logger.info(message, params);
    }

    @Override
    @SuppressWarnings("deprecation")
    public void info(Marker marker, String message, Supplier<?>... paramSuppliers) {
        info(marker, message, Arrays.stream(paramSuppliers).map(Supplier::get).toArray());
    }

    @Override
    public void info(Marker marker, String message, Throwable t) {
        logger.info(message, t);
    }

    @Override
    @SuppressWarnings("deprecation")
    public void info(Marker marker, Supplier<?> messageSupplier) {
        if (messageSupplier != null) {
            info(marker, messageSupplier.get().toString());
        }
    }

    @Override
    @SuppressWarnings("deprecation")
    public void info(Marker marker, Supplier<?> messageSupplier, Throwable throwable) {
        if (messageSupplier != null) {
            info(marker, messageSupplier.get().toString(), throwable);
        }
    }

    @Override
    public void info(Message msg) {
        if (msg != null) {
            logger.info(msg.toString());
        }
    }

    @Override
    public void info(Message msg, Throwable t) {
        if (msg != null) {
            logger.info(msg.toString(), t);
        }
    }

    @Override
    public void info(MessageSupplier messageSupplier) {
        if (messageSupplier != null) {
            info(messageSupplier.get().toString());
        }
    }

    @Override
    public void info(MessageSupplier messageSupplier, Throwable throwable) {
        if (messageSupplier != null) {
            info(messageSupplier.get().toString(), throwable);
        }
    }

    @Override
    public void info(CharSequence message) {
        if (message != null) {
            info(message.toString());
        }
    }

    @Override
    public void info(CharSequence message, Throwable throwable) {
        if (message != null) {
            info(message.toString(), throwable);
        }
    }

    @Override
    public void info(Object message) {
        if (message != null) {
            info(message.toString());
        }
    }

    @Override
    public void info(Object message, Throwable t) {
        if (message != null) {
            info(message.toString(), t);
        }
    }

    @Override
    public void info(String message) {
        logger.info(message);
    }

    @Override
    public void info(String message, Object... params) {
        logger.info(message, params);
    }

    @Override
    @SuppressWarnings("deprecation")
    public void info(String message, Supplier<?>... paramSuppliers) {
        info(message, Arrays.stream(paramSuppliers).map(Supplier::get).toArray());
    }

    @Override
    public void info(String message, Throwable t) {
        logger.info(message, t);
    }

    @Override
    @SuppressWarnings("deprecation")
    public void info(Supplier<?> messageSupplier) {
        if (messageSupplier != null) {
            info(messageSupplier.toString());
        }
    }

    @Override
    @SuppressWarnings("deprecation")
    public void info(Supplier<?> messageSupplier, Throwable throwable) {
        if (messageSupplier != null) {
            info(messageSupplier.toString(), throwable);
        }
    }

    @Override
    public void info(Marker marker, String message, Object p0) {
        info(message, p0);
    }

    @Override
    public void info(Marker marker, String message, Object p0, Object p1) {
        info(message, p0, p1);
    }

    @Override
    public void info(Marker marker, String message, Object p0, Object p1, Object p2) {
        info(message, p0, p1, p2);
    }

    @Override
    public void info(Marker marker, String message, Object p0, Object p1, Object p2, Object p3) {
        info(message, p0, p1, p2, p3);
    }

    @Override
    public void info(Marker marker, String message, Object p0, Object p1, Object p2, Object p3, Object p4) {
        info(message, p0, p1, p2, p3, p4);
    }

    @Override
    public void info(Marker marker, String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5) {
        info(message, p0, p1, p2, p3, p4, p5);
    }

    @Override
    public void info(Marker marker, String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6) {
        info(message, p0, p1, p2, p3, p4, p5, p6);
    }

    @Override
    public void info(Marker marker, String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7) {
        info(message, p0, p1, p2, p3, p4, p5, p6, p7);
    }

    @Override
    public void info(Marker marker, String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7, Object p8) {
        info(message, p0, p1, p2, p3, p4, p5, p6, p7, p8);
    }

    @Override
    public void info(Marker marker, String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7, Object p8, Object p9) {
        info(message, p0, p1, p2, p3, p4, p5, p6, p7, p8, p9);
    }

    @Override
    public void info(String message, Object p0) {
        logger.info(message, p0);
    }

    @Override
    public void info(String message, Object p0, Object p1) {
        logger.info(message, p0, p1);
    }

    @Override
    public void info(String message, Object p0, Object p1, Object p2) {
        logger.info(message, p0, p1, p2);
    }

    @Override
    public void info(String message, Object p0, Object p1, Object p2, Object p3) {
        logger.info(message, p0, p1, p2, p3);
    }

    @Override
    public void info(String message, Object p0, Object p1, Object p2, Object p3, Object p4) {
        logger.info(message, p0, p1, p2, p3, p4);
    }

    @Override
    public void info(String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5) {
        logger.info(message, p0, p1, p2, p3, p4, p5);
    }

    @Override
    public void info(String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6) {
        logger.info(message, p0, p1, p2, p3, p4, p5, p6);
    }

    @Override
    public void info(String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7) {
        logger.info(message, p0, p1, p2, p3, p4, p5, p6, p7);
    }

    @Override
    public void info(String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7, Object p8) {
        logger.info(message, p0, p1, p2, p3, p4, p5, p6, p7, p8);
    }

    @Override
    public void info(String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7, Object p8, Object p9) {
        logger.info(message, p0, p1, p2, p3, p4, p5, p6, p7, p8, p9);
    }

    @Override
    public boolean isDebugEnabled() {
        return isEnabled(Level.DEBUG);
    }

    @Override
    public boolean isDebugEnabled(Marker marker) {
        return isEnabled(Level.DEBUG);
    }

    @Override
    public boolean isEnabled(Level level) {
        if (level == Level.ALL || level == Level.TRACE) {
            return logger.isTraceEnabled();
        }
        if (level == Level.DEBUG) {
            return logger.isDebugEnabled();
        }
        if (level == Level.INFO) {
            return logger.isInfoEnabled();
        }
        if (level == Level.WARN) {
            return logger.isWarnEnabled();
        }
        if (level == Level.ERROR || level == Level.FATAL) {
            return logger.isErrorEnabled();
        }
        if (level == Level.OFF) {
            return !logger.isTraceEnabled();
        }
        return true;
    }

    @Override
    public boolean isEnabled(Level level, Marker marker) {
        return isEnabled(level);
    }

    @Override
    public boolean isErrorEnabled() {
        return isEnabled(Level.ERROR);
    }

    @Override
    public boolean isErrorEnabled(Marker marker) {
        return isEnabled(Level.ERROR);
    }

    @Override
    public boolean isFatalEnabled() {
        return isEnabled(Level.FATAL);
    }

    @Override
    public boolean isFatalEnabled(Marker marker) {
        return isEnabled(Level.FATAL);
    }

    @Override
    public boolean isInfoEnabled() {
        return isEnabled(Level.INFO);
    }

    @Override
    public boolean isInfoEnabled(Marker marker) {
        return isEnabled(Level.INFO);
    }

    @Override
    public boolean isTraceEnabled() {
        return isEnabled(Level.TRACE);
    }

    @Override
    public boolean isTraceEnabled(Marker marker) {
        return isEnabled(Level.TRACE);
    }

    @Override
    public boolean isWarnEnabled() {
        return isEnabled(Level.WARN);
    }

    @Override
    public boolean isWarnEnabled(Marker marker) {
        return isEnabled(Level.WARN);
    }


    @Override
    public void log(Level level, Marker marker, String message) {
        if (level == Level.ALL || level == Level.TRACE) {
            logger.trace(message);
        }
        if (level == Level.DEBUG) {
            logger.debug(message);
        }
        if (level == Level.INFO) {
            logger.info(message);
        }
        if (level == Level.WARN) {
            logger.warn(message);
        }
        if (level == Level.ERROR || level == Level.FATAL) {
            logger.error(message);
        }
    }

    @Override
    public void log(Level level, Marker marker, String message, Object... params) {
        if (level == Level.ALL || level == Level.TRACE) {
            logger.trace(message, params);
        }
        if (level == Level.DEBUG) {
            logger.debug(message, params);
        }
        if (level == Level.INFO) {
            logger.info(message, params);
        }
        if (level == Level.WARN) {
            logger.warn(message, params);
        }
        if (level == Level.ERROR || level == Level.FATAL) {
            logger.error(message, params);
        }
    }

    @Override
    @SuppressWarnings("deprecation")
    public void log(Level level, Marker marker, String message, Supplier<?>... paramSuppliers) {
        log(level, marker, message, Arrays.stream(paramSuppliers).map(Supplier::get).toArray());
    }

    @Override
    public void log(Level level, Marker marker, String message, Throwable t) {
        if (level == Level.ALL || level == Level.TRACE) {
            logger.trace(message, t);
        }
        if (level == Level.DEBUG) {
            logger.debug(message, t);
        }
        if (level == Level.INFO) {
            logger.info(message, t);
        }
        if (level == Level.WARN) {
            logger.warn(message, t);
        }
        if (level == Level.ERROR || level == Level.FATAL) {
            logger.error(message, t);
        }
    }

    @Override
    @SuppressWarnings("deprecation")
    public void log(Level level, Marker marker, Supplier<?> messageSupplier) {
        if (messageSupplier != null) {
            log(level, marker, messageSupplier.get());
        }
    }

    @Override
    @SuppressWarnings("deprecation")
    public void log(Level level, Marker marker, Supplier<?> messageSupplier, Throwable throwable) {
        if (messageSupplier != null) {
            log(level, marker, messageSupplier.get(), throwable);
        }
    }


    @Override
    public void log(Level level, Marker marker, Message msg) {
        if (msg != null) {
            log(level, marker, msg.toString());
        }
    }

    @Override
    public void log(Level level, Marker marker, Message msg, Throwable t) {
        if (msg != null) {
            log(level, marker, msg.toString(), t);
        }
    }

    @Override
    public void log(Level level, Marker marker, MessageSupplier messageSupplier) {
        if (messageSupplier != null) {
            log(level, marker, messageSupplier.get().toString());
        }
    }

    @Override
    public void log(Level level, Marker marker, MessageSupplier messageSupplier, Throwable throwable) {
        if (messageSupplier != null) {
            log(level, marker, messageSupplier.get().toString(), throwable);
        }
    }

    @Override
    public void log(Level level, Marker marker, CharSequence message) {
        if (message != null) {
            log(level, marker, message.toString());
        }
    }

    @Override
    public void log(Level level, Marker marker, CharSequence message, Throwable throwable) {
        if (message != null) {
            log(level, marker, message.toString(), throwable);
        }
    }

    @Override
    public void log(Level level, Marker marker, Object msg) {
        if (msg != null) {
            log(level, marker, msg.toString());
        }
    }

    @Override
    public void log(Level level, Marker marker, Object msg, Throwable t) {
        if (msg != null) {
            log(level, marker, msg.toString(), t);
        }
    }

    @Override
    public void log(Level level, Message msg) {
        log(level, (Marker) null, msg);
    }

    @Override
    public void log(Level level, Message msg, Throwable t) {
        log(level, (Marker) null, msg, t);
    }

    @Override
    public void log(Level level, MessageSupplier messageSupplier) {
        if (messageSupplier != null) {
            log(level, messageSupplier.get().toString());
        }
    }

    @Override
    public void log(Level level, MessageSupplier messageSupplier, Throwable throwable) {
        if (messageSupplier != null) {
            log(level, messageSupplier.get().toString(), throwable);
        }
    }

    @Override
    public void log(Level level, CharSequence message) {
        if (message != null) {
            log(level, message.toString());
        }
    }

    @Override
    public void log(Level level, CharSequence message, Throwable throwable) {
        if (message != null) {
            log(level, message.toString(), throwable);
        }
    }

    @Override
    public void log(Level level, Object message) {
        log(level, (Marker) null, message);
    }

    @Override
    public void log(Level level, Object message, Throwable t) {
        log(level, (Marker) null, message, t);
    }

    @Override
    public void log(Level level, String message) {
        log(level, (Marker) null, message);
    }

    @Override
    public void log(Level level, String message, Object... params) {
        log(level, (Marker) null, message, params);
    }

    @Override
    @SuppressWarnings("deprecation")
    public void log(Level level, String message, Supplier<?>... paramSuppliers) {
        log(level, message, Arrays.stream(paramSuppliers).map(Supplier::get).toArray());
    }

    @Override
    public void log(Level level, String message, Throwable t) {
        log(level, (Marker) null, message, t);
    }

    @Override
    @SuppressWarnings("deprecation")
    public void log(Level level, Supplier<?> messageSupplier) {
        if (messageSupplier != null) {
            log(level, messageSupplier.get().toString());
        }
    }

    @Override
    @SuppressWarnings("deprecation")
    public void log(Level level, Supplier<?> messageSupplier, Throwable throwable) {
        if (messageSupplier != null) {
            log(level, messageSupplier.get().toString(), throwable);
        }
    }

    @Override
    public void log(Level level, Marker marker, String message, Object p0) {
        log(level, message, p0);
    }

    @Override
    public void log(Level level, Marker marker, String message, Object p0, Object p1) {
        log(level, message, p0, p1);
    }

    @Override
    public void log(Level level, Marker marker, String message, Object p0, Object p1, Object p2) {
        log(level, message, p0, p1, p2);
    }

    @Override
    public void log(Level level, Marker marker, String message, Object p0, Object p1, Object p2, Object p3) {
        log(level, message, p0, p1, p2, p3);
    }

    @Override
    public void log(Level level, Marker marker, String message, Object p0, Object p1, Object p2, Object p3, Object p4) {
        log(level, message, p0, p1, p2, p3, p4);
    }

    @Override
    public void log(Level level, Marker marker, String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5) {
        log(level, message, p0, p1, p2, p3, p4, p5);
    }

    @Override
    public void log(Level level, Marker marker, String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6) {
        log(level, message, p0, p1, p2, p3, p4, p5, p6);
    }

    @Override
    public void log(Level level, Marker marker, String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7) {
        log(level, message, p0, p1, p2, p3, p4, p5, p6, p7);
    }

    @Override
    public void log(Level level, Marker marker, String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7, Object p8) {
        log(level, message, p0, p1, p2, p3, p4, p5, p6, p7, p8);
    }

    @Override
    public void log(Level level, Marker marker, String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7, Object p8, Object p9) {
        log(level, message, p0, p1, p2, p3, p4, p5, p6, p7, p8, p9);
    }

    @Override
    public void log(Level level, String message, Object p0) {
        log(level, (Marker) null, message, p0);
    }

    @Override
    public void log(Level level, String message, Object p0, Object p1) {
        log(level, (Marker) null, message, new Object[] { p0, p1 });
    }

    @Override
    public void log(Level level, String message, Object p0, Object p1, Object p2) {
        log(level, (Marker) null, message, new Object[] { p0, p1, p2 });
    }

    @Override
    public void log(Level level, String message, Object p0, Object p1, Object p2, Object p3) {
        log(level, (Marker) null, message, new Object[] { p0, p1, p2, p3 });
    }

    @Override
    public void log(Level level, String message, Object p0, Object p1, Object p2, Object p3, Object p4) {
        log(level, (Marker) null, message, new Object[] { p0, p1, p2, p3, p4 });
    }

    @Override
    public void log(Level level, String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5) {
        log(level, (Marker) null, message, new Object[] { p0, p1, p2, p3, p4, p5 });
    }

    @Override
    public void log(Level level, String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6) {
        log(level, (Marker) null, message, new Object[] { p0, p1, p2, p3, p4, p5, p6 });
    }

    @Override
    public void log(Level level, String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7) {
        log(level, (Marker) null, message, new Object[] { p0, p1, p2, p3, p4, p5, p6, p7 });
    }

    @Override
    public void log(Level level, String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7, Object p8) {
        log(level, (Marker) null, message, new Object[] { p0, p1, p2, p3, p4, p5, p6, p7, p8 });
    }

    @Override
    public void log(Level level, String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7, Object p8, Object p9) {
        log(level, (Marker) null, message, new Object[] { p0, p1, p2, p3, p4, p5, p6, p7, p8, p9 });
    }

    @Override
    public void printf(Level level, Marker marker, String format, Object... params) {
        log(level, (Marker) null, format, params);
    }

    @Override
    public void printf(Level level, String format, Object... params) {
        log(level, (Marker) null, format, params);
    }

    @Override
    public <T extends Throwable> T throwing(Level level, T t) {
        log(level, (Marker) null, "", t);
        return t;
    }

    @Override
    public <T extends Throwable> T throwing(T t) {
        Level level = getLevel();
        log(level, (Marker) null, "", t);
        return t;
    }

    @Override
    public void trace(Marker marker, Message msg) {
        if (msg != null) {
            trace(marker, msg.toString());
        }
    }

    @Override
    public void trace(Marker marker, Message msg, Throwable t) {
        if (msg != null) {
            trace(marker, msg.toString(), t);
        }
    }

    @Override
    public void trace(Marker marker, MessageSupplier messageSupplier) {
        if (messageSupplier != null) {
            trace(marker, messageSupplier.get().toString());
        }
    }

    @Override
    public void trace(Marker marker, MessageSupplier messageSupplier, Throwable throwable) {
        if (messageSupplier != null) {
            trace(marker, messageSupplier.get().toString(), throwable);
        }
    }

    @Override
    public void trace(Marker marker, CharSequence message) {
        if (message != null) {
            trace(marker, message.toString());
        }
    }

    @Override
    public void trace(Marker marker, CharSequence message, Throwable throwable) {
        if (message != null) {
            trace(marker, message.toString(), throwable);
        }
    }

    @Override
    public void trace(Marker marker, Object message) {
        if (message != null) {
            trace(marker, message.toString());
        }
    }

    @Override
    public void trace(Marker marker, Object message, Throwable t) {
        if (message != null) {
            trace(marker, message.toString(), t);
        }
    }

    @Override
    public void trace(Marker marker, String message) {
        trace(message);
    }

    @Override
    public void trace(Marker marker, String message, Object... params) {
        trace(message, params);
    }

    @Override
    @SuppressWarnings("deprecation")
    public void trace(Marker marker, String message, Supplier<?>... paramSuppliers) {
        trace(marker, message, Arrays.stream(paramSuppliers).map(Supplier::get).toArray());
    }

    @Override
    public void trace(Marker marker, String message, Throwable t) {
        trace(message, t);
    }

    @Override
    @SuppressWarnings("deprecation")
    public void trace(Marker marker, Supplier<?> messageSupplier) {
        if (messageSupplier != null) {
            trace(marker, messageSupplier.get().toString());
        }
    }

    @Override
    @SuppressWarnings("deprecation")
    public void trace(Marker marker, Supplier<?> messageSupplier, Throwable throwable) {
        if (messageSupplier != null) {
            trace(marker, messageSupplier.get().toString(), throwable);
        }
    }

    @Override
    public void trace(Message msg) {
        if (msg != null) {
            trace(msg.toString());
        }
    }

    @Override
    public void trace(Message msg, Throwable t) {
        if (msg != null) {
            trace(msg.toString(), t);
        }
    }

    @Override
    public void trace(MessageSupplier messageSupplier) {
        if (messageSupplier != null) {
            trace(messageSupplier.get().toString());
        }
    }

    @Override
    public void trace(MessageSupplier messageSupplier, Throwable throwable) {
        if (messageSupplier != null) {
            trace(messageSupplier.get().toString());
        }
    }

    @Override
    public void trace(CharSequence message) {
        if (message != null) {
            trace(message.toString());
        }
    }

    @Override
    public void trace(CharSequence message, Throwable throwable) {
        if (message != null) {
            trace(message.toString(), throwable);
        }
    }

    @Override
    public void trace(Object message) {
        if (message != null) {
            logger.trace(message.toString());
        }
    }

    @Override
    public void trace(Object message, Throwable t) {
        if (message != null) {
            logger.trace(message.toString(), t);
        }
    }

    @Override
    public void trace(String message) {
        logger.trace(message);
    }

    @Override
    public void trace(String message, Object... params) {
        logger.trace(message, params);
    }

    @Override
    @SuppressWarnings("deprecation")
    public void trace(String message, Supplier<?>... paramSuppliers) {
        trace(message, Arrays.stream(paramSuppliers).map(Supplier::get).toArray());
    }

    @Override
    public void trace(String message, Throwable t) {
        logger.trace(message, t);
    }

    @Override
    @SuppressWarnings("deprecation")
    public void trace(Supplier<?> messageSupplier) {
        if (messageSupplier != null) {
            trace(messageSupplier.get().toString());
        }
    }

    @Override
    @SuppressWarnings("deprecation")
    public void trace(Supplier<?> messageSupplier, Throwable throwable) {
        if (messageSupplier != null) {
            trace(messageSupplier.get().toString());
        }
    }

    @Override
    public void trace(Marker marker, String message, Object p0) {
        trace(message, p0);
    }

    @Override
    public void trace(Marker marker, String message, Object p0, Object p1) {
        trace(message, p0, p1);
    }

    @Override
    public void trace(Marker marker, String message, Object p0, Object p1, Object p2) {
        trace(message, p0, p1, p2);
    }

    @Override
    public void trace(Marker marker, String message, Object p0, Object p1, Object p2, Object p3) {
        trace(message, p0, p1, p2, p3);
    }

    @Override
    public void trace(Marker marker, String message, Object p0, Object p1, Object p2, Object p3, Object p4) {
        trace(message, p0, p1, p2, p3, p4);
    }

    @Override
    public void trace(Marker marker, String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5) {
        trace(message, p0, p1, p2, p3, p4, p5);
    }

    @Override
    public void trace(Marker marker, String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6) {
        trace(message, p0, p1, p2, p3, p4, p5, p6);
    }

    @Override
    public void trace(Marker marker, String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7) {
        trace(message, p0, p1, p2, p3, p4, p5, p6, p7);
    }

    @Override
    public void trace(Marker marker, String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7, Object p8) {
        trace(message, p0, p1, p2, p3, p4, p5, p6, p7, p8);
    }

    @Override
    public void trace(Marker marker, String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7, Object p8, Object p9) {
        trace(message, p0, p1, p2, p3, p4, p5, p6, p7, p8, p9);
    }

    @Override
    public void trace(String message, Object p0) {
        logger.trace(message, p0);
    }

    @Override
    public void trace(String message, Object p0, Object p1) {
        logger.trace(message, p0, p1);
    }

    @Override
    public void trace(String message, Object p0, Object p1, Object p2) {
        logger.trace(message, p0, p1, p2);
    }

    @Override
    public void trace(String message, Object p0, Object p1, Object p2, Object p3) {
        logger.trace(message, p0, p1, p2, p3);
    }

    @Override
    public void trace(String message, Object p0, Object p1, Object p2, Object p3, Object p4) {
        logger.trace(message, p0, p1, p2, p3, p4);
    }

    @Override
    public void trace(String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5) {
        logger.trace(message, p0, p1, p2, p3, p4, p5);
    }

    @Override
    public void trace(String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6) {
        logger.trace(message, p0, p1, p2, p3, p4, p5, p6);
    }

    @Override
    public void trace(String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7) {
        logger.trace(message, p0, p1, p2, p3, p4, p5, p6, p7);
    }

    @Override
    public void trace(String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7, Object p8) {
        logger.trace(message, p0, p1, p2, p3, p4, p5, p6, p7, p8);
    }

    @Override
    public void trace(String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7, Object p8, Object p9) {
        logger.trace(message, p0, p1, p2, p3, p4, p5, p6, p7, p8, p9);
    }

    @Override
    public EntryMessage traceEntry() {
        return null;
    }

    @Override
    public EntryMessage traceEntry(String format, Object... params) {
        return null;
    }

    @Override
    public EntryMessage traceEntry(Supplier<?>... paramSuppliers) {
        return null;
    }

    @Override
    @SuppressWarnings("deprecation")
    public EntryMessage traceEntry(String format, Supplier<?>... paramSuppliers) {
        return traceEntry(format, Arrays.stream(paramSuppliers).map(Supplier::get).toArray());
    }

    @Override
    public EntryMessage traceEntry(Message message) {
        return traceEntry(message.toString());
    }

    @Override
    public void traceExit() {

    }

    @Override
    public <R> R traceExit(R result) {
        return null;
    }

    @Override
    public <R> R traceExit(String format, R result) {
        return null;
    }

    @Override
    public void traceExit(EntryMessage message) {

    }

    @Override
    public <R> R traceExit(EntryMessage message, R result) {
        return null;
    }

    @Override
    public <R> R traceExit(Message message, R result) {
        return null;
    }

    @Override
    public void warn(Marker marker, Message msg) {
        if (msg != null) {
            logger.warn(msg.toString());
        }
    }

    @Override
    public void warn(Marker marker, Message msg, Throwable t) {
        if (msg != null) {
            warn(marker, msg.toString(), t);
        }
    }

    @Override
    public void warn(Marker marker, MessageSupplier messageSupplier) {
        if (messageSupplier != null) {
            warn(marker, messageSupplier.get().toString());
        }
    }

    @Override
    public void warn(Marker marker, MessageSupplier messageSupplier, Throwable throwable) {
        if (messageSupplier != null) {
            warn(marker, messageSupplier.get().toString(), throwable);
        }
    }

    @Override
    public void warn(Marker marker, CharSequence message) {
        if (message != null) {
            warn(marker, message.toString());
        }
    }

    @Override
    public void warn(Marker marker, CharSequence message, Throwable throwable) {
        if (message != null) {
            warn(marker, message.toString(), throwable);
        }
    }

    @Override
    public void warn(Marker marker, Object message) {
        if (message != null) {
            warn(marker, message.toString());
        }
    }

    @Override
    public void warn(Marker marker, Object message, Throwable t) {
        if (message != null) {
            warn(marker, message.toString(), t);
        }
    }

    @Override
    public void warn(Marker marker, String message) {
        logger.warn(message);
    }

    @Override
    public void warn(Marker marker, String message, Object... params) {
        logger.warn(message, params);
    }

    @Override
    @SuppressWarnings("deprecation")
    public void warn(Marker marker, String message, Supplier<?>... paramSuppliers) {
        warn(marker, message, Arrays.stream(paramSuppliers).map(Supplier::get).toArray());
    }

    @Override
    public void warn(Marker marker, String message, Throwable t) {
        logger.warn(message, t);
    }

    @Override
    @SuppressWarnings("deprecation")
    public void warn(Marker marker, Supplier<?> messageSupplier) {
        if (messageSupplier != null) {
            warn(marker, messageSupplier.toString());
        }
    }

    @Override
    @SuppressWarnings("deprecation")
    public void warn(Marker marker, Supplier<?> messageSupplier, Throwable throwable) {
        if (messageSupplier != null) {
            warn(marker, messageSupplier.toString(), throwable);
        }
    }

    @Override
    public void warn(Message msg) {
        if (msg != null) {
            warn(msg.toString());
        }
    }

    @Override
    public void warn(Message msg, Throwable t) {
        if (msg != null) {
            warn(msg.toString(), t);
        }
    }

    @Override
    public void warn(MessageSupplier messageSupplier) {
        if (messageSupplier != null) {
            warn(messageSupplier.toString());
        }
    }

    @Override
    public void warn(MessageSupplier messageSupplier, Throwable throwable) {
        if (messageSupplier != null) {
            warn(messageSupplier.toString(), throwable);
        }
    }

    @Override
    public void warn(CharSequence message) {
        if (message != null) {
            warn(message.toString());
        }
    }

    @Override
    public void warn(CharSequence message, Throwable throwable) {
        if (message != null) {
            warn(message.toString(), throwable);
        }
    }

    @Override
    public void warn(Object message) {
        if (message != null) {
            warn(message.toString());
        }
    }

    @Override
    public void warn(Object message, Throwable t) {
        if (message != null) {
            warn(message.toString(), t);
        }
    }

    @Override
    public void warn(String message) {
        logger.warn(message);
    }

    @Override
    public void warn(String message, Object... params) {
        logger.warn(message, params);
    }

    @Override
    @SuppressWarnings("deprecation")
    public void warn(String message, Supplier<?>... paramSuppliers) {
        if (message != null) {
            warn(message, Arrays.stream(paramSuppliers).map(Supplier::get).toArray());
        }
    }

    @Override
    public void warn(String message, Throwable t) {
        logger.warn(message, t);
    }

    @Override
    @SuppressWarnings("deprecation")
    public void warn(Supplier<?> messageSupplier) {
        if (messageSupplier != null) {
            warn(messageSupplier.get().toString());
        }
    }

    @Override
    @SuppressWarnings("deprecation")
    public void warn(Supplier<?> messageSupplier, Throwable throwable) {
        if (messageSupplier != null) {
            warn(messageSupplier.toString(), throwable);
        }
    }

    @Override
    public void warn(Marker marker, String message, Object p0) {
        warn(message, p0);
    }

    @Override
    public void warn(Marker marker, String message, Object p0, Object p1) {
        warn(message, p0, p1);
    }

    @Override
    public void warn(Marker marker, String message, Object p0, Object p1, Object p2) {
        warn(message, p0, p1, p2);
    }

    @Override
    public void warn(Marker marker, String message, Object p0, Object p1, Object p2, Object p3) {
        warn(message, p0, p1, p2, p3);
    }

    @Override
    public void warn(Marker marker, String message, Object p0, Object p1, Object p2, Object p3, Object p4) {
        warn(message, p0, p1, p2, p3, p4);
    }

    @Override
    public void warn(Marker marker, String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5) {
        warn(message, p0, p1, p2, p3, p4, p5);
    }

    @Override
    public void warn(Marker marker, String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6) {
        warn(message, p0, p1, p2, p3, p4, p5, p6);
    }

    @Override
    public void warn(Marker marker, String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7) {
        warn(message, p0, p1, p2, p3, p4, p5, p6, p7);
    }

    @Override
    public void warn(Marker marker, String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7, Object p8) {
        warn(message, p0, p1, p2, p3, p4, p5, p6, p7, p8);
    }

    @Override
    public void warn(Marker marker, String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7, Object p8, Object p9) {
        warn(message, p0, p1, p2, p3, p4, p5, p6, p7, p8, p9);
    }

    @Override
    public void warn(String message, Object p0) {
        logger.warn(message, p0);
    }

    @Override
    public void warn(String message, Object p0, Object p1) {
        logger.warn(message, p0, p1);
    }

    @Override
    public void warn(String message, Object p0, Object p1, Object p2) {
        logger.warn(message, p0, p1, p2);
    }

    @Override
    public void warn(String message, Object p0, Object p1, Object p2, Object p3) {
        logger.warn(message, p0, p1, p2, p3);
    }

    @Override
    public void warn(String message, Object p0, Object p1, Object p2, Object p3, Object p4) {
        logger.warn(message, p0, p1, p2, p3, p4);
    }

    @Override
    public void warn(String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5) {
        logger.warn(message, p0, p1, p2, p3, p4, p5);
    }

    @Override
    public void warn(String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6) {
        logger.warn(message, p0, p1, p2, p3, p4, p5, p6);
    }

    @Override
    public void warn(String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7) {
        logger.warn(message, p0, p1, p2, p3, p4, p5, p6, p7);
    }

    @Override
    public void warn(String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7, Object p8) {
        logger.warn(message, p0, p1, p2, p3, p4, p5, p6, p7, p8);
    }

    @Override
    public void warn(String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7, Object p8, Object p9) {
        logger.warn(message, p0, p1, p2, p3, p4, p5, p6, p7, p8, p9);
    }
}
/*
 * Copyright (c) 2004-2011 QOS.ch
 * All rights reserved.
 *
 * Permission is hereby granted, free  of charge, to any person obtaining
 * a  copy  of this  software  and  associated  documentation files  (the
 * "Software"), to  deal in  the Software without  restriction, including
 * without limitation  the rights to  use, copy, modify,  merge, publish,
 * distribute,  sublicense, and/or sell  copies of  the Software,  and to
 * permit persons to whom the Software  is furnished to do so, subject to
 * the following conditions:
 *
 * The  above  copyright  notice  and  this permission  notice  shall  be
 * included in all copies or substantial portions of the Software.
 *
 * THE  SOFTWARE IS  PROVIDED  "AS  IS", WITHOUT  WARRANTY  OF ANY  KIND,
 * EXPRESS OR  IMPLIED, INCLUDING  BUT NOT LIMITED  TO THE  WARRANTIES OF
 * MERCHANTABILITY,    FITNESS    FOR    A   PARTICULAR    PURPOSE    AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE
 * LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION
 * OF CONTRACT, TORT OR OTHERWISE,  ARISING FROM, OUT OF OR IN CONNECTION
 * WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package org.slf4j.bridge;

import java.util.logging.Handler;
import java.util.logging.LogManager;
import java.util.logging.LogRecord;

import org.slf4j.Logger;
import org.slf4j.spi.LocationAwareLogger;

/**
 * ⚠⚠⚠ WARNING ⚠⚠⚠
 * {@link ch.cyberduck.core.preferences.Preferences#setLogging(String)} uses this class (original version)'s method.
 * it's not loosely coupled. so i made it to ignore methods of this class.
 */
public class SLF4JBridgeHandler extends Handler {

  /**
   */
  public static void install() {
    // ignore
  }

  private static java.util.logging.Logger getRootLogger() {
    return LogManager.getLogManager().getLogger("");
  }

  /**
   * Removes previously installed SLF4JBridgeHandler instances. See also
   * {@link #install()}.
   *
   * @throws SecurityException A <code>SecurityException</code> is thrown, if a security manager
   *                           exists and if the caller does not have
   *                           LoggingPermission("control").
   */
  public static void uninstall() throws SecurityException {
    // ignore
  }

  /**
   * Returns true if SLF4JBridgeHandler has been previously installed, returns false otherwise.
   *
   * @return true if SLF4JBridgeHandler is already installed, false other wise
   * @throws SecurityException
   */
  public static boolean isInstalled() throws SecurityException {
    return false;
  }

  /**
   * Invoking this method removes/unregisters/detaches all handlers currently attached to the root logger
   * @since 1.6.5
   */
  public static void removeHandlersForRootLogger() {
    // ignore
  }

  /**
   * Initialize this handler.
   */
  public SLF4JBridgeHandler() {
  }

  /**
   * No-op implementation.
   */
  public void close() {
    // empty
  }

  /**
   * No-op implementation.
   */
  public void flush() {
    // empty
  }

  /**
   * Return the Logger instance that will be used for logging.
   */
  protected Logger getSLF4JLogger(LogRecord record) {
    throw new UnsupportedOperationException();
  }

  protected void callLocationAwareLogger(LocationAwareLogger lal, LogRecord record) {
    throw new UnsupportedOperationException();
  }

  /**
   * Get the record's message, possibly via a resource bundle.
   *
   * @param record
   * @return
   */
  private String getMessageI18N(LogRecord record) {
    throw new UnsupportedOperationException();
  }

  /**
   * Publish a LogRecord.
   * <p/>
   * The logging request was made initially to a Logger object, which
   * initialized the LogRecord and forwarded it here.
   * <p/>
   * This handler ignores the Level attached to the LogRecord, as SLF4J cares
   * about discarding log statements.
   *
   * @param record Description of the log event. A null record is silently ignored
   *               and is not published.
   */
  public void publish(LogRecord record) {
    throw new UnsupportedOperationException();
  }
}
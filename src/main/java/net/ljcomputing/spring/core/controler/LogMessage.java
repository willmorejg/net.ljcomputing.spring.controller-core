/**
           Copyright 2015, James G. Willmore

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
 */

package net.ljcomputing.spring.core.controler;

/**
 * Client-side log message.
 * 
 * @author James G. Willmore
 *
 */
public class LogMessage {

  /** The browser used by the client. */
  private String browser;

  /** The location requested when the request was logged. */
  private String location;

  /** The message to log. */
  private String message;

  /**
   * Gets the browser.
   *
   * @return the browser
   */
  public String getBrowser() {
    return browser;
  }

  /**
   * Sets the browser.
   *
   * @param browser
   *            the new browser
   */
  public void setBrowser(final String browser) {
    this.browser = browser;
  }

  /**
   * Gets the location.
   *
   * @return the location
   */
  public String getLocation() {
    return location;
  }

  /**
   * Sets the location.
   *
   * @param location
   *            the new location
   */
  public void setLocation(final String location) {
    this.location = location;
  }

  /**
   * Gets the message.
   *
   * @return the message
   */
  public String getMessage() {
    return message;
  }

  /**
   * Gets the printable message (convert '\n' to ' ').
   *
   * @return the printable message
   */
  public String getPrintableMessage() {
    return message.replace("\\n", " ");
  }

  /**
   * Sets the message.
   *
   * @param message
   *            the new message
   */
  public void setMessage(final String message) {
    this.message = message;
  }
}

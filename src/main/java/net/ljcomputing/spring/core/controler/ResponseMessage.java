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
 * JSON-associated response message from controller.
 * 
 * @author James G. Willmore
 *
 */
public class ResponseMessage {

  /** The message. */
  private String message;

  /** The success. */
  private Boolean success = Boolean.TRUE;

  /**
   * Instantiates a new response message.
   */
  public ResponseMessage() {
    this(Boolean.TRUE, null);
  }

  /**
   * Instantiates a new response message.
   *
   * @param result the result
   * @param message the message
   */
  public ResponseMessage(final Boolean result, final String message) {
    this.success = result;
    this.message = message;
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
   * Sets the message.
   *
   * @param message the new message
   */
  public void setMessage(final String message) {
    this.message = message;
  }

  /**
   * Gets the success.
   *
   * @return the success
   */
  public Boolean getSuccess() {
    return success;
  }

  /**
   * Sets the success.
   *
   * @param result the new success
   */
  public void setSuccess(final Boolean result) {
    this.success = result;
  }

}

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

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;

/**
 * Error information.
 * 
 * @author James G. Willmore
 */
public class ErrorInfo {

  /** The timestamp. */
  public final transient String timestamp;

  /** The status. */
  public final transient String status;

  /** The error. */
  public final transient String error;

  /** The message. */
  public final transient String message;

  /** The path. */
  public final transient String path;

  /** The Constant COMMA - suggested by PMD. */
  private static final String COMMA = ",";

  /**
   * Instantiates a new error info.
   *
   * @param timestamp the timestamp
   * @param status the status
   * @param path the path
   * @param exception the exception
   */
  public ErrorInfo(final String timestamp, final HttpStatus status, final String path,
      final Exception exception) {
    this.timestamp = timestamp;
    this.status = String.valueOf(status.value());
    this.error = status.getReasonPhrase();
    this.path = path;

    if (exception instanceof ConstraintViolationException) {
      final StringBuffer errorBuffer = new StringBuffer("The save failed validation as follows: ");

      for (final ConstraintViolation<?> violation : ((ConstraintViolationException) exception)
          .getConstraintViolations()) {
        errorBuffer.append(violation.getMessage()).append(COMMA);
      }

      errorBuffer.reverse().replace(0, 1, "").reverse();

      this.message = errorBuffer.toString();
    } else if (exception.getLocalizedMessage() != null) {
      this.message = exception.getLocalizedMessage();
    } else if (exception.getMessage() != null) {
      this.message = exception.getMessage();
    } else {
      this.message = "An error occured during processing: " + exception.toString();
    }
  }
}

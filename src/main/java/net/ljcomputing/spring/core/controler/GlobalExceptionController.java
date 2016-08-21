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

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;

import org.slf4j.Logger;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import net.ljcomputing.logging.annotation.InjectLogging;

/**
 * Global exception handler for controllers.
 * 
 * @author James G. Willmore
 *
 */
@ControllerAdvice
public class GlobalExceptionController {

  /** The Constant logger. */
  @InjectLogging
  private static Logger logger;

  /**
   * Gets the current timestamp.
   *
   * @return the current timestamp
   */
  private String getCurrentTimestamp() {
    final LocalDateTime dateTime = LocalDateTime.now();
    final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d, yyyy h:m:s a");
    return dateTime.format(formatter);
  }
  
  /**
   * Gets the request URL as a String.
   *
   * @param request the request
   * @return the request url
   */
  private static final String getRequestUrl(final HttpServletRequest request) {
    final StringBuffer buffer = request.getRequestURL();
    return buffer.toString();
  }

  /**
   * Handle all exceptions.
   *
   * @param request the request
   * @param exception the exception
   * @return the error info
   */
  @Order(Ordered.LOWEST_PRECEDENCE)
  @ExceptionHandler(Exception.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public @ResponseBody ErrorInfo handleAllExceptions(final HttpServletRequest request,
      final Exception exception) {
    logger.error("An error occured during the processing of {}:",
        getRequestUrl(request), exception);

    return new ErrorInfo(getCurrentTimestamp(), HttpStatus.BAD_REQUEST,
        getRequestUrl(request), exception);
  }

  /**
   * Handle all null pointer exceptions.
   *
   * @param request the request
   * @param exception the exception
   * @return the error info
   */
  @Order(Ordered.HIGHEST_PRECEDENCE)
  @ExceptionHandler(NullPointerException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public @ResponseBody ErrorInfo handleAllNullPointerExceptions(final HttpServletRequest request,
      final Exception exception) {
    logger.error("The data sent for processing had errors {}:", getRequestUrl(request),
        exception);

    return new ErrorInfo(getCurrentTimestamp(), HttpStatus.BAD_REQUEST,
        getRequestUrl(request),
        new Exception("An invalid value was sent or requested."));
  }

  /**
   * Handle all constraint violation exceptions.
   *
   * @param request the request
   * @param exception the exception
   * @return the error info
   */
  @Order(Ordered.HIGHEST_PRECEDENCE)
  @ExceptionHandler(ConstraintViolationException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public @ResponseBody ErrorInfo handleAllConstraintViolationExceptions(
      final HttpServletRequest request, final Exception exception) {
    final ConstraintViolationException cve = (ConstraintViolationException) exception;

    logger.warn("A required value is missing : {}:", getRequestUrl(request));

    return new ErrorInfo(getCurrentTimestamp(), HttpStatus.BAD_REQUEST,
        getRequestUrl(request), cve);
  }

  /**
   * Handle all data integrity violation exceptions.
   *
   * @param request the request
   * @param exception the exception
   * @return the error info
   */
  @Order(Ordered.HIGHEST_PRECEDENCE)
  @ExceptionHandler(DataIntegrityViolationException.class)
  @ResponseStatus(HttpStatus.CONFLICT)
  public @ResponseBody ErrorInfo handleAllDataIntegrityViolationExceptions(
      final HttpServletRequest request, final Exception exception) {
    ErrorInfo errorInfo = null;

    logger.error("The data sent for processing had errors {}:", getRequestUrl(request),
        exception);

    if (exception.getMessage().contains("Unique property")) {
      errorInfo = new ErrorInfo(getCurrentTimestamp(), HttpStatus.CONFLICT,
          getRequestUrl(request), new Exception("The saved value already exists."));
    } else {
      errorInfo = new ErrorInfo(getCurrentTimestamp(), HttpStatus.CONFLICT,
          getRequestUrl(request), exception);
    }

    return errorInfo;
  }
}

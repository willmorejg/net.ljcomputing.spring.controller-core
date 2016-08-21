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

import java.util.ArrayList;
import java.util.List;

/**
 * JSON-associated data response from controller consumed by JavaScript APIs.
 * 
 * @author James G. Willmore
 *
 */
public class ResponseData extends ResponseMessage {

  /** The data. */
  @SuppressWarnings("rawtypes")
  private final List data = new ArrayList();

  /** The total. */
  @SuppressWarnings("unused")
  private transient Integer total = 0;

  /**
   * Instantiates a new response data.
   */
  public ResponseData() {
    super();
  }

  /**
   * Instantiates a new ext response data.
   * 
   * @param message
   *            the message
   */
  public ResponseData(final String message) {
    super(Boolean.FALSE, message);
  }

  /**
   * Instantiates a new ext response data.
   * 
   * @param data
   *            the data
   */
  @SuppressWarnings({ "unchecked", "rawtypes" })
  public ResponseData(final List data) {
    super();

    if (data != null) {
      this.data.addAll(data);
      this.total = data.size();
    }
  }

  /**
   * Gets the data.
   * 
   * @return the data
   */
  public List<?> getData() {
    return data;
  }
}

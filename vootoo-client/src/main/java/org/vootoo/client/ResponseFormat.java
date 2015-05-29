/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.vootoo.client;

/**
 * 1=json, 2=javabin, 3=xml
 * 4=csv, 5=python, 6=php, 7=phps, 8=avro
 */
public enum ResponseFormat {
  /** json */
  JSON(1),

  /** javabin*/
  JAVA_BIN(2),

  /** xml */
  XML(3),

  /** csv */
  CSV(4),

  /** python */
  PYTHON(5),

  /** php */
  PHP(6),

  /** phps */
  PHPS(7),

  /** avro */
  AVRO(8)
  ;
  private final int format;

  ResponseFormat (int format) {
    this.format = format;
  }

  public int getFormat() {
    return format;
  }

  public static ResponseFormat valueOf(int format) {
    for(ResponseFormat responseFormat : ResponseFormat.values()) {
      if(responseFormat.getFormat() == format) {
        return responseFormat;
      }
    }
    throw new IllegalArgumentException("not found format value='"+format+"' ResponseFormat");
  }
}

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

package org.vootoo.client.netty;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import org.vootoo.client.netty.protocol.SolrProtocol;

/**
 */
public class AwaitResponseCallback implements ResponseCallback {

  private final CountDownLatch finishLatch = new CountDownLatch(1);
  private SolrProtocol.SolrResponse solrResponse;

  @Override
  public void response(SolrProtocol.SolrResponse solrResponse) {
    this.solrResponse = solrResponse;
    finishLatch.countDown();
  }

  public SolrProtocol.SolrResponse awaitResponse(long timeout) {
    try {
      if(timeout > 0) {
        finishLatch.await(timeout, TimeUnit.MILLISECONDS);
      } else {
        finishLatch.await();
      }
    } catch (InterruptedException e) {

    }

    return solrResponse;
  }
}
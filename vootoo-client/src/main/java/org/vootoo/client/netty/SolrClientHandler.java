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

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.vootoo.client.netty.protocol.SolrProtocol;

/**
 */
public class SolrClientHandler extends SimpleChannelInboundHandler<SolrProtocol.SolrResponse> {

  private static final Logger logger = LoggerFactory.getLogger(SolrClientHandler.class);

  @Override
  protected void channelRead0(ChannelHandlerContext ctx, SolrProtocol.SolrResponse solrResponse) throws Exception {
    ResponseCallback callback = NettyClient.remove(solrResponse.getRid());
    if (callback != null) {
      logger.debug("receive response={}", solrResponse);
      callback.applyResult(solrResponse);
    } else {
      logger.warn("miss rid='{}' at {} applyResult callback", solrResponse.getRid(), ctx.channel());
    }
  }

  @Override
  public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
    logger.warn("Unexpected exception from downstream.", cause);
    ctx.close();
  }
}

/**
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 * 
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.jafka.network.handlers;

import io.jafka.api.CreaterRequest;
import io.jafka.api.RequestKeys;
import io.jafka.log.LogManager;
import io.jafka.network.Receive;
import io.jafka.network.Send;
import io.jafka.network.NumbersSend;

/**
 * @author adyliu (imxylz@gmail.com)
 * @since 1.2
 */
public class CreaterHandler extends AbstractHandler {

    public CreaterHandler(LogManager logManager) {
        super(logManager);
    }

    @Override
    public Send handler(RequestKeys requestType, Receive request) {
        CreaterRequest createrRequest = CreaterRequest.readFrom(request.buffer());
        if (logger.isDebugEnabled()) {
            logger.debug("Create request " + createrRequest);
        }
        int value = logManager.createLogs(createrRequest.topic, createrRequest.partitions, createrRequest.enlarge);
        return new NumbersSend.IntegersSend(value);
    }

}
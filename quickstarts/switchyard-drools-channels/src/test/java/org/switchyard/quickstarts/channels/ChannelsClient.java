/*
 * JBoss, Home of Professional Open Source
 * Copyright 2013, Red Hat, Inc. and/or its affiliates, and individual
 * contributors by the @authors tag. See the copyright.txt in the
 * distribution for a full listing of individual contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.switchyard.quickstarts.channels;

import java.io.IOException;

import javax.xml.namespace.QName;

import org.switchyard.remote.RemoteInvoker;
import org.switchyard.remote.RemoteMessage;
import org.switchyard.remote.http.HttpInvoker;


/**
 * Test client which uses RemoteInvoker to invoke a service with an SCA binding.
 */
public final class ChannelsClient {

    private static final QName SERVICE = new QName("urn:switchyard-quickstart.switchyard:switchyard-drools-channels:1.0", "Channel");

    /**
     * Private no-args constructor.
     */
    private ChannelsClient() {
    }

    /**
     * Only execution point for this application.
     * @param ignored not used.
     * @throws Exception if something goes wrong.
     */
    public static void main(final String[] ignored) throws Exception {
        String port = System.getProperty("org.switchyard.component.sca.client.port", "8181");
        RemoteInvoker invoker = new HttpInvoker("http://localhost:" + port + "/switchyard-remote");

        createRequestServer("DAVID", invoker);
    }

    public static void createRequestServer(String content, RemoteInvoker invoker) throws IOException {
        // Create the request message
        RemoteMessage message = new RemoteMessage();
        message.setService(SERVICE).setOperation("process").setContent(new String(content));
        invoker.invoke(message);
    }
}

/*
 * Copyright 2014 Red Hat Inc. and/or its affiliates and other contributors.
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
package org.switchyard.component.common.knowledge.runtime.remote;

import java.util.concurrent.atomic.AtomicLong;

import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.manager.Context;
import org.kie.services.client.api.command.RemoteConfiguration;
import org.kie.services.client.api.command.RemoteRuntimeEngine;

/** ExtendedRemoteRuntimeEngine.
 *
 * @author David Ward &lt;<a href="mailto:dward@jboss.org">dward@jboss.org</a>&gt; &copy; 2014 Red Hat Inc. */
public class ExtendedRemoteRuntimeEngine extends RemoteRuntimeEngine {

    private static final AtomicLong SESSION_IDENTIFIER = new AtomicLong();

    private final KieSession _kieSession;

    ExtendedRemoteRuntimeEngine(RemoteConfiguration configuration, Context<?> context) {
        super(configuration);
        // we use negative numbers so as not to clash with ones generated by kie/drools/jbpm
        _kieSession = new ExtendedKieSessionClientCommandObject(configuration, context, Long.valueOf(SESSION_IDENTIFIER.decrementAndGet()));
    }

    /** {@inheritDoc} */
    @Override
    public KieSession getKieSession() {
        return _kieSession;
    }

}

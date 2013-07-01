/* 
 * JBoss, Home of Professional Open Source 
 * Copyright 2012 Red Hat Inc. and/or its affiliates and other contributors
 * as indicated by the @author tags. All rights reserved. 
 * See the copyright.txt in the distribution for a 
 * full listing of individual contributors.
 *
 * This copyrighted material is made available to anyone wishing to use, 
 * modify, copy, or redistribute it subject to the terms and conditions 
 * of the GNU Lesser General Public License, v. 2.1. 
 * This program is distributed in the hope that it will be useful, but WITHOUT A 
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A 
 * PARTICULAR PURPOSE.  See the GNU Lesser General Public License for more details. 
 * You should have received a copy of the GNU Lesser General Public License, 
 * v.2.1 along with this distribution; if not, write to the Free Software 
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, 
 * MA  02110-1301, USA.
 */
package org.switchyard.component.common.knowledge.util;

import java.util.Properties;

import org.drools.compiler.compiler.PackageBuilderConfiguration;
import org.drools.core.RuleBaseConfiguration;
import org.kie.api.KieBaseConfiguration;
import org.kie.api.runtime.KieSessionConfiguration;
import org.kie.internal.KnowledgeBaseFactory;
import org.kie.internal.builder.KnowledgeBuilderConfiguration;
import org.kie.internal.utils.ClassLoaderUtil;
import org.kie.internal.utils.CompositeClassLoader;
import org.switchyard.component.common.knowledge.config.model.KnowledgeComponentImplementationModel;

/**
 * Configuration functions.
 *
 * @author David Ward &lt;<a href="mailto:dward@jboss.org">dward@jboss.org</a>&gt; &copy; 2012 Red Hat Inc.
 */
public final class Configurations {

    /**
     * Gets a base configuration.
     * @param model the model
     * @param loader the class loader
     * @param overrides any overrides
     * @return the base configuration
     */
    public static KieBaseConfiguration getBaseConfiguration(KnowledgeComponentImplementationModel model, ClassLoader loader, Properties overrides) {
        //return KnowledgeBaseFactory.newKnowledgeBaseConfiguration(Propertys.getProperties(model, overrides), loader);
        CompositeClassLoader ccl = ClassLoaderUtil.getClassLoader(new ClassLoader[0], RuleBaseConfiguration.class, false);
        ccl.addClassLoaderToEnd(loader);
        return new RuleBaseConfiguration(Propertys.getProperties(model, overrides), ccl);
    }

    /**
     * Gets a builder configuration.
     * @param model the model
     * @param loader the class loader
     * @param overrides any overrides
     * @return the builder configuration
     */
    public static KnowledgeBuilderConfiguration getBuilderConfiguration(KnowledgeComponentImplementationModel model, ClassLoader loader, Properties overrides) {
        //return KnowledgeBuilderFactory.newKnowledgeBuilderConfiguration(Propertys.getProperties(model, overrides), loader);
        CompositeClassLoader ccl = ClassLoaderUtil.getClassLoader(new ClassLoader[0], PackageBuilderConfiguration.class, false);
        ccl.addClassLoaderToEnd(loader);
        return new PackageBuilderConfiguration(Propertys.getProperties(model, overrides), ccl);
    }

    /**
     * Gets a session configuration.
     * @param model the model
     * @param loader the class loader
     * @param overrides any overrides
     * @return the session configuration
     */
    public static KieSessionConfiguration getSessionConfiguration(KnowledgeComponentImplementationModel model, ClassLoader loader, Properties overrides) {
        return KnowledgeBaseFactory.newKnowledgeSessionConfiguration(Propertys.getProperties(model, overrides));
    }

    private Configurations() {}

}

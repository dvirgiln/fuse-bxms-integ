/*
 * Copyright 2016 Red Hat Inc. and/or its affiliates and other contributors.
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
package org.kie.camel.component;

import org.custommonkey.xmlunit.Diff;
import org.custommonkey.xmlunit.XMLAssert;
import org.custommonkey.xmlunit.XMLUnit;
import org.custommonkey.xmlunit.examples.RecursiveElementNameAndTextQualifier;
import org.junit.Before;
import org.junit.Ignore;

@Ignore
public class XStreamBatchTest extends BatchTest {

    public XStreamBatchTest() {
        this.dataformat = "xstream";
    }

    @Before
    public void setUp() throws Exception {
        XMLUnit.setIgnoreComments(true);
        XMLUnit.setIgnoreWhitespace(true);
        XMLUnit.setIgnoreAttributeOrder(true);
        XMLUnit.setNormalizeWhitespace(true);
        XMLUnit.setNormalize(true);
    }

    public void assertXMLEqual(String expectedXml, String resultXml) {
        try {
            Diff diff = new Diff(expectedXml, resultXml);
            diff.overrideElementQualifier(new RecursiveElementNameAndTextQualifier());
            XMLAssert.assertXMLEqual(diff, true);
        } catch (Exception e) {
            throw new RuntimeException("XML Assertion failure", e);
        }
    }

}

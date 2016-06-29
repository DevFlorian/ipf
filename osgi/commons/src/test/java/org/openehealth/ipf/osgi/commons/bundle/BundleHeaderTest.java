/*
 * Copyright 2009 the original author or authors.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *     
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.openehealth.ipf.osgi.commons.bundle;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;

import java.util.Collections;

import org.junit.Before;
import org.junit.Test;
import org.osgi.framework.Bundle;
import org.springframework.osgi.mock.MockBundle;

/**
 * @author Martin Krasser
 */
public class BundleHeaderTest {

    private Bundle bundle;
    
    @Before
    @SuppressWarnings("unchecked")
    public void setUp() throws Exception {
        bundle = new MockBundle();
        bundle.getHeaders().put("a", "x");
        bundle.getHeaders().put("b", " x , y ");
    }

    @Test
    public void testGetEntriesForA() {
        assertEquals(asList("x"), bundleHeader("a").getValues());
    }

    @Test
    public void testGetEntriesForB() {
        assertEquals(asList("x", "y"), bundleHeader("b").getValues());
    }

    @Test
    public void testGetEntriesForC() {
        assertEquals(Collections.emptyList(), bundleHeader("c").getValues());
    }

    private BundleHeader bundleHeader(String name) {
        BundleHeader bundleHeader = new BundleHeader();
        bundleHeader.setBundle(bundle);
        bundleHeader.setName(name);
        return bundleHeader;
    }

}

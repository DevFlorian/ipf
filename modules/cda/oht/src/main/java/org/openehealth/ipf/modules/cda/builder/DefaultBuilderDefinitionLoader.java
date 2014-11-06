/**
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
package org.openehealth.ipf.modules.cda.builder;

import groovytools.builder.MetaBuilder;

import java.io.IOException;
import java.util.Collection;
import java.util.HashSet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class DefaultBuilderDefinitionLoader implements
        BuilderDefinitionLoader {

    private static final Logger LOG = LoggerFactory.getLogger(DefaultBuilderDefinitionLoader.class);

    private final MetaBuilder builder;

    public DefaultBuilderDefinitionLoader(MetaBuilder builder) {
        super();
        this.builder = builder;
    }

    /*
     * (non-Javadoc)
     * @see org.openehealth.ipf.modules.cda.builder.BuilderDefinitionLoader#load()
     */
    public Collection<String> load() {
        Collection<String> loaded = new HashSet<>();
        load(loaded);
        return loaded;
    }

    synchronized protected boolean doLoad(String resource,
            Collection<String> loaded) throws IOException {
        if (!loaded.contains(resource)) {
            LOG.debug("Loading builder from {} ", resource);
            builder.define(getClass().getResource(resource));
            loaded.add(resource);
            return true;
        } else {
            LOG.debug("Skip loading from {}", resource);
            return false;
        }
    }

}

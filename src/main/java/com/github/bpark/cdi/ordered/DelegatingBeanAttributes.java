/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements. See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership. The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package com.github.bpark.cdi.ordered;

import javax.enterprise.inject.spi.BeanAttributes;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.Set;

class DelegatingBeanAttributes<T> implements BeanAttributes<T> {

    private final BeanAttributes<T> delegate;

    DelegatingBeanAttributes(BeanAttributes<T> delegate) {
        this.delegate = delegate;
    }

    @Override
    public Set<Type> getTypes() {
        return delegate.getTypes();
    }

    @Override
    public Set<Annotation> getQualifiers() {
        return delegate.getQualifiers();
    }

    @Override
    public Class<? extends Annotation> getScope() {
        return delegate.getScope();
    }

    @Override
    public String getName() {
        return delegate.getName();
    }

    @Override
    public Set<Class<? extends Annotation>> getStereotypes() {
        return delegate.getStereotypes();
    }

    @Override
    public boolean isAlternative() {
        return delegate.isAlternative();
    }
}

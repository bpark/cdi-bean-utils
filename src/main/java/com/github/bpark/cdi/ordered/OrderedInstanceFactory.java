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

import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Instance;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.CDI;
import javax.enterprise.inject.spi.InjectionPoint;
import java.lang.annotation.Annotation;
import java.lang.reflect.ParameterizedType;
import java.util.Set;

@Dependent
public class OrderedInstanceFactory {

    @Produces
    @Ordered
    @SuppressWarnings("unchecked")
    public <T> OrderedInstance<T> createInstance(OrderedInstance<T> orderedInstance, InjectionPoint injectionPoint) {

        Set<Annotation> qualifiers = injectionPoint.getQualifiers();

        Class<T> typeClazz = (Class<T>) getGenericType(injectionPoint);

        Annotation[] annotations = qualifiers.toArray(new Annotation[qualifiers.size()]);
        Instance<T> instance = CDI.current().select(typeClazz, annotations);

        orderedInstance.setInstance(instance);

        return orderedInstance;
    }

    private Class<?> getGenericType(InjectionPoint injectionPoint) {
        return (Class<?>) ((ParameterizedType) injectionPoint.getAnnotated().getBaseType()).getActualTypeArguments()[0];
    }

}

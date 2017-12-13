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

import javax.enterprise.event.Observes;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.Extension;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.enterprise.inject.spi.ProcessBeanAttributes;
import javax.enterprise.inject.spi.ProcessInjectionPoint;
import java.lang.annotation.Annotation;
import java.util.HashSet;
import java.util.Set;

public class OrderedExtension implements Extension {

    private Set<Annotation> collectedQualifiers = new HashSet<>();


    public void captureConfigTypes(@Observes ProcessInjectionPoint<?, ?> pip) {
        InjectionPoint ip = pip.getInjectionPoint();
        if (ip.getType().getTypeName().startsWith("com.github.bpark.cdi.ordered.OrderedInstance")) {
            System.out.println(ip.getType().getTypeName());
            Set<Annotation> qualifiers = ip.getQualifiers();
            collectedQualifiers.addAll(qualifiers);
        }
    }

    public void filterSpecialClassBean(@Observes ProcessBeanAttributes<OrderedInstance> event) {
        System.out.println();
        System.out.println(event.getAnnotated());
        System.out.println(event.getAnnotated().getBaseType().getTypeName());

        if (event.getAnnotated().isAnnotationPresent(Produces.class)
                && event.getAnnotated().getBaseType().getTypeName().equals("com.github.bpark.cdi.ordered.OrderedInstance<T>")) {

            event.setBeanAttributes(new DelegatingBeanAttributes<OrderedInstance>(event.getBeanAttributes()) {
                @Override
                public Set<Annotation> getQualifiers() {
                    Set<Annotation> qualifiers = new HashSet<>(super.getQualifiers());
                    qualifiers.addAll(collectedQualifiers);
                    return qualifiers;
                }
            });
        }

    }

}

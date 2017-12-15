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

import javax.enterprise.inject.Instance;
import javax.enterprise.util.TypeLiteral;
import javax.inject.Provider;
import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

public class OrderedInstance<T> implements Iterable<T>, Provider<T> {

    private Instance<T> instance;
    private Iterator<T> iterator;

    public Iterator<T> iterator() {
        return iterator;
    }


    public Instance<T> select(Annotation... annotations) {
        return instance.select(annotations);
    }

    public <U extends T> Instance<U> select(Class<U> aClass, Annotation... annotations) {
        return instance.select(aClass, annotations);
    }

    public <U extends T> Instance<U> select(TypeLiteral<U> typeLiteral, Annotation... annotations) {
        return instance.select(typeLiteral, annotations);
    }

    public boolean isUnsatisfied() {
        return instance.isUnsatisfied();
    }

    public boolean isAmbiguous() {
        return instance.isAmbiguous();
    }

    public void destroy(T t) {
        instance.destroy(t);
    }

    public T get() {
        return instance.get();
    }

    void setInstance(Instance<T> instance) {
        this.instance = instance;
        Comparator<T> comparator = Comparator.comparingInt(a -> a.getClass().getAnnotation(Ordered.class).value());
        iterator = sortIterator(instance.iterator(), comparator);
    }

    private Iterator<T> sortIterator(Iterator<T> unsortedIterator, Comparator<T> comparator) {
        List<T> list = new ArrayList<>();
        while (unsortedIterator.hasNext()) {
            list.add(unsortedIterator.next());
        }

        list.sort(comparator);
        return list.iterator();
    }
}

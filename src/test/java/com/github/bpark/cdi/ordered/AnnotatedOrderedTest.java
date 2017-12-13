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

import org.jglue.cdiunit.AdditionalClasses;
import org.jglue.cdiunit.CdiRunner;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;
import java.util.Iterator;

@RunWith(CdiRunner.class)
@AdditionalClasses({OrderedInstanceFactory.class, SimpleServiceOne.class, SimpleServiceTwo.class, AnnotatedServiceTwo.class, AnnotatedServiceOne.class, OrderedExtension.class})
public class AnnotatedOrderedTest {

    @Inject
    @Ordered
    @MyQualifier
    private OrderedInstance<OrderedServices> instances;

    @Test
    public void testStart() {
        Iterator<OrderedServices> iterator = instances.iterator();

        Assert.assertTrue(iterator.hasNext());
        OrderedServices service1 = iterator.next();

        Assert.assertTrue(iterator.hasNext());
        OrderedServices service2 = iterator.next();

        Assert.assertFalse(iterator.hasNext());

        Assert.assertEquals(1, service1.getOrder());
        Assert.assertEquals(2, service2.getOrder());

    }

}

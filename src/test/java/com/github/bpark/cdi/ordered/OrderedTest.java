package com.github.bpark.cdi.ordered;

import org.jglue.cdiunit.AdditionalClasses;
import org.jglue.cdiunit.CdiRunner;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;
import java.util.Iterator;

@RunWith(CdiRunner.class)
@AdditionalClasses({OrderedInstanceFactory.class, AnotherService.class, Service.class})
public class OrderedTest {

    @Inject
    @Ordered
    private OrderedInstance<OrderedServices> instances;

    @Test
    public void testStart() {
        Iterator<OrderedServices> iterator = instances.iterator();

        Assert.assertTrue(iterator.hasNext());
        OrderedServices service1 = iterator.next();

        Assert.assertTrue(iterator.hasNext());
        OrderedServices service2 = iterator.next();

        Assert.assertEquals(1, service1.getOrder());
        Assert.assertEquals(2, service2.getOrder());

    }

}

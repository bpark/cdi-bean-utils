package com.github.bpark.cdi.ordered;

import org.jglue.cdiunit.CdiRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;

@RunWith(CdiRunner.class)
public class OrderedTest {

    @Inject
    @Ordered
    private OrderedInstance<Service> instances;

    @Test
    public void testStart() {
        instances.iterator();
    }

}

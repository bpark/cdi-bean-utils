package com.github.bpark.cdi.ordered;

@Ordered(2)
public class AnotherService implements OrderedServices {

    @Override
    public int getOrder() {
        return 2;
    }
}

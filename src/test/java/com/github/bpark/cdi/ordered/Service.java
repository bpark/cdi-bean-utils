package com.github.bpark.cdi.ordered;

@Ordered(1)
public class Service implements OrderedServices {

    @Override
    public int getOrder() {
        return 1;
    }
}
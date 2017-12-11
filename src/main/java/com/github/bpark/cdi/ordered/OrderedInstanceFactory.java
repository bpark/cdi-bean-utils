package com.github.bpark.cdi.ordered;

import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import java.lang.annotation.Annotation;
import java.util.Set;

@Dependent
public class OrderedInstanceFactory {

    @Produces
    @Ordered
    public OrderedInstance createInstance(InjectionPoint injectionPoint) {

        Set<Annotation> annotations = injectionPoint.getAnnotated().getAnnotations();
        String typeName = injectionPoint.getType().getTypeName();

        System.out.println(typeName);

        return new OrderedInstanceImpl<String>();
    }
}

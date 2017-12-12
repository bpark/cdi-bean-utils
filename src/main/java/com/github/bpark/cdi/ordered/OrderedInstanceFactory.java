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

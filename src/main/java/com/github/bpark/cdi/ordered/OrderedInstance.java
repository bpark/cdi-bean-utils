package com.github.bpark.cdi.ordered;

import javax.enterprise.inject.Instance;
import javax.enterprise.util.TypeLiteral;
import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

public class OrderedInstance<T> implements Instance<T> {

    private Instance<T> instance;
    private Iterator<T> iterator;

    @Override
    public Iterator<T> iterator() {
        return iterator;
    }


    @Override
    public Instance<T> select(Annotation... annotations) {
        return instance.select(annotations);
    }

    @Override
    public <U extends T> Instance<U> select(Class<U> aClass, Annotation... annotations) {
        return instance.select(aClass, annotations);
    }

    @Override
    public <U extends T> Instance<U> select(TypeLiteral<U> typeLiteral, Annotation... annotations) {
        return instance.select(typeLiteral, annotations);
    }

    @Override
    public boolean isUnsatisfied() {
        return instance.isUnsatisfied();
    }

    @Override
    public boolean isAmbiguous() {
        return instance.isAmbiguous();
    }

    @Override
    public void destroy(T t) {
        instance.destroy(t);
    }

    @Override
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

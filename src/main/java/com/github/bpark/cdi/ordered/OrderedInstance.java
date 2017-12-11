package com.github.bpark.cdi.ordered;

import java.util.Iterator;

public interface OrderedInstance<T> extends Iterable<T> {

    @Override
    Iterator<T> iterator();
}

# cdi-bean-utils
Implementation of cdi util functionality.

##1. Ordered instance injection

Use the ```Ordered()``` qualifier and ```OrderedInstance<>``` to get ordered instances.

Assume we have two services:

```
@Ordered(1)
class ServiceOne implements Service {}
```

```
@Ordered(2)
class ServiceTwo implements Service {}
```

Use the ```OrderedInstance``` syntax to retrieve the the services in the defined order:

```
@Inject
@Ordered
OrderedInstance<Service> instances;

...
Iterator<Service> it = instances.iterator();
Servcie serviceOne = it.next();  // ServiceOne
Service serviceTwo = it.next();  // ServiceTwo
...
```

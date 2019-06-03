package com.dynamic.foundations.core.injection;

//TODO javadoc
abstract class ObjectSource<T> {
    DependencyInjectingObjectFactory dependencyInjectingObjectFactory;

    ObjectSource(DependencyInjectingObjectFactory aDependencyInjectingObjectFactory) {
        dependencyInjectingObjectFactory = aDependencyInjectingObjectFactory;
    }

    abstract T getObject();
}
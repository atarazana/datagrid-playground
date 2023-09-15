package com.olleb.model;

import org.infinispan.protostream.annotations.ProtoField;

public class Example {

    @ProtoField(number = 1)
    public String name;

    @ProtoField(number = 2)
    public String description;

    public Example() {
    }

    public Example(String name) {
        this.name = name;
    }

}
package com.olleb.model;

import org.infinispan.protostream.annotations.ProtoField;

public class Example {

    @ProtoField(number = 1)
    public String name;

    @ProtoField(number = 2)
    public String description;

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Example {" +
                "name='" + name + "'," +
                "description='" + description + "'}";
    }

}
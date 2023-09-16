package com.olleb.test.model;

import java.util.ArrayList;
import java.util.List;

import org.infinispan.protostream.annotations.ProtoFactory;
import org.infinispan.protostream.annotations.ProtoField;

import lombok.Data;

@Data
public class CB {

    @ProtoField(number = 1, collectionImplementation = ArrayList.class)
    final List<B> bs;

    @ProtoFactory
    public CB(List<B> bs) {
        this.bs = bs;
    }

}

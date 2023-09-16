package com.olleb.test.model;

import java.io.Serializable;

import org.infinispan.protostream.annotations.ProtoField;

import lombok.Data;

@Data
public class B implements Serializable {

	@ProtoField(number = 1)
	public String a = "";

}

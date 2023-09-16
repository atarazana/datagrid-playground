package com.olleb.test.model;

import org.infinispan.protostream.GeneratedSchema;
import org.infinispan.protostream.annotations.AutoProtoSchemaBuilder;

@AutoProtoSchemaBuilder(includeClasses = {
                B.class,
                CB.class
}, schemaFilePath = "proto/", schemaPackageName = "global_entities")
public interface SchInitializer extends GeneratedSchema {
}

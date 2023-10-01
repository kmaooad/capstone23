package edu.kmaooad.capstone23.common;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.bson.types.ObjectId;

import java.io.IOException;

/**
 * @implNote Can be used in prior of <ObjectMapper> to
 */
public class ObjectMapperSerializingToHexObjectId extends ObjectMapper {
  ObjectMapperSerializingToHexObjectId() {
    super();

    SimpleModule module = new SimpleModule();

    module.addSerializer(ObjectId.class, new ObjectIdToHexStringSerializer());

    this.registerModule(module);
  }

  private static class ObjectIdToHexStringSerializer extends JsonSerializer<ObjectId> {
    @Override
    public void serialize(
        ObjectId value,
        JsonGenerator generator,
        SerializerProvider serializers
    ) throws IOException {
      if (value == null) {
        generator.writeNull();
      } else {
        generator.writeString(value.toHexString());
      }
    }
  }
}

package br.com.valim.contratoapi.responses;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.springframework.boot.jackson.JsonComponent;
import org.springframework.data.domain.PageImpl;

import java.io.IOException;

@JsonComponent
public class PageSerializerResponse extends JsonSerializer<PageImpl<?>> {
    @Override
    public void serialize(PageImpl<?> page, JsonGenerator gen, SerializerProvider serializers) throws IOException {

        gen.writeStartObject();
        gen.writeObjectField("items", page.getContent());
        gen.writeObjectField("hasNext", page.hasNext());
        gen.writeEndObject();
    }
}

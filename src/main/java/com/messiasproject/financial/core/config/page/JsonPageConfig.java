package com.messiasproject.financial.core.config.page;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.springframework.boot.jackson.JsonComponent;
import org.springframework.data.domain.Page;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@JsonComponent
public class JsonPageConfig extends JsonSerializer<Page<?>> {
    @Override
    public void serialize(Page<?> page, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {

        Map<String, String> sortType = new HashMap<>();
        Map<String, String> exampleValueSort = new HashMap<>();
        Map<String, String> modeUseSort = new HashMap<>();

        Map<String, Map<String, String>> sort = new HashMap<>();
        sortType.put("size", "10");
        sortType.put("sort", "nameAtribute,desc (Search first recent add) or nameAtribute,asc (Search first old add)");
        sortType.put("page", "2");

        exampleValueSort.put("name,desc", "Messias, Mariana");
        exampleValueSort.put("name,asc", "Mariana, Messias");
        exampleValueSort.put("dateTransaction,desc", "11/09, 11/08 (find fird recent add)");
        exampleValueSort.put("dateTransaction,asc", "11/08, 11/09");

        modeUseSort.put("putUrl", "&sort=nameAttribute,desc");
        sort.put("sortType", sortType);
        sort.put("exampleValueSort", exampleValueSort);
        sort.put("modeUseSort", modeUseSort);
        jsonGenerator.writeStartObject();

        jsonGenerator.writeObjectField("content", page.getContent());
        jsonGenerator.writeObjectField("sort", sort);
        jsonGenerator.writeNumberField("size", page.getSize());
        jsonGenerator.writeNumberField("totalElements", page.getTotalElements());
        jsonGenerator.writeNumberField("totalPages", page.getTotalPages());
        jsonGenerator.writeNumberField("elementsOnThePage", page.getNumberOfElements());
        jsonGenerator.writeBooleanField("isLastPage", page.isLast());

        jsonGenerator.writeEndObject();
    }
}

package com.messiasproject.financial.api.model.specification;

import com.messiasproject.financial.api.model.tag.Status;
import lombok.Data;

@Data
public class TagFilterSpec {
    String name;
    Status status;
}

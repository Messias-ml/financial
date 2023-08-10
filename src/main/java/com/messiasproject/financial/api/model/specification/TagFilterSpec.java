package com.messiasproject.financial.api.model.specification;

import com.messiasproject.financial.api.model.tag.StatusTag;
import lombok.Data;

@Data
public class TagFilterSpec {
    String name;
    StatusTag status = StatusTag.ATIVO;
}

package com.messiasproject.financial.api.controller;

import com.messiasproject.financial.api.model.tag.CreationTagDTO;
import com.messiasproject.financial.api.model.tag.TagDTO;
import com.messiasproject.financial.domain.service.TagService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tags")
public class TagController {

    @Autowired
    private TagService tagService;

    @GetMapping("/all")
    public List<TagDTO> findTags(){
        return tagService.findAllTags();
    }

    @GetMapping
    public List<TagDTO> findTagsByName(@RequestParam @NotBlank String name){
        return tagService.findTagsByName(name);
    }

    @GetMapping("/{uuid}")
    public TagDTO findTagByUuid(@PathVariable @NotBlank String uuid){
        return tagService.findTagByUuid(uuid);
    }

    @PostMapping
    public void createTag(@RequestBody @Valid CreationTagDTO tagDTO){
        tagService.createTag(tagDTO);
    }
}

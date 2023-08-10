package com.messiasproject.financial.api.controller;

import com.messiasproject.financial.api.model.tag.CreationTagDTO;
import com.messiasproject.financial.api.model.tag.TagDTO;
import com.messiasproject.financial.domain.service.TagService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tags")
public class TagController {

    @Autowired
    private TagService tagService;

    @GetMapping("/all")
    public Page<TagDTO> findTags(@PageableDefault Pageable pageable){
        return tagService.findAllTags(pageable);
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

    @DeleteMapping("/{uuid}")
    public void deleteTag(@PathVariable String uuid){
        tagService.deleteTag(uuid);
    }
}

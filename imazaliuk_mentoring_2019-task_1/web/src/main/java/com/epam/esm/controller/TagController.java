package com.epam.esm.controller;

import com.epam.esm.dto.CertificateTO;
import com.epam.esm.dto.TagTO;
import com.epam.esm.entity.TagSearch;
import com.epam.esm.service.TagService;
import com.epam.esm.service.impl.TagServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/tags")
public class TagController {

    private TagService service;

    @Autowired
    public TagController(TagServiceImpl service) {
        this.service = service;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<TagTO> findTags(TagSearch search) {
        return service.findTags(search);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void addTag(@Valid @RequestBody TagTO tagDTO) {
        service.save(tagDTO);
    }

    @GetMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public TagTO findById(@PathVariable Long id) {

        return service.findById(id);
    }


    @GetMapping(value = "/{id}/certificates")
    public List<CertificateTO> findTagCertificates(@PathVariable Long id) {

        return service.findByTag(id);
    }

    @PutMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void updateTag(@PathVariable Long id, @Valid @RequestBody TagTO tagTO) {

        service.updateTag(id, tagTO);
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteById(@PathVariable Long id) {

        service.deleteById(id);
    }
}

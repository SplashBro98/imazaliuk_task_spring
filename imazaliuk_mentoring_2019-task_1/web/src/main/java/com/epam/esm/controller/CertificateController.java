package com.epam.esm.controller;

import com.epam.esm.dto.CertificateTO;
import com.epam.esm.dto.TagTO;
import com.epam.esm.entity.CertificateSearch;
import com.epam.esm.service.CertificateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/certificates")
public class CertificateController {

    private CertificateService service;

    @Autowired
    public CertificateController(CertificateService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void addCertificate(@Valid @RequestBody CertificateTO cert) {

        service.save(cert);
    }

    @GetMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CertificateTO findById(@PathVariable Long id) {

        return service.findById(id);
    }

    @GetMapping(value = "/{id}/tags")
    @ResponseStatus(HttpStatus.OK)
    public List<TagTO> findCertificateTags(@PathVariable Long id) {

        return service.findCertificateTags(id);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<CertificateTO> findCertificates(CertificateSearch certificateSearch) {
        return service.findCertificates(certificateSearch);
    }

    @PutMapping(value = "/update/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CertificateTO updateCertificate(@PathVariable Long id, @Valid @RequestBody CertificateTO certificateTO) {

        return service.updateCertificate(id, certificateTO);
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteById(@PathVariable Long id) {

        service.deleteById(id);
    }
}

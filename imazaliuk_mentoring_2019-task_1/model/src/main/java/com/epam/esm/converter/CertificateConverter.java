package com.epam.esm.converter;

import com.epam.esm.dto.CertificateTO;
import com.epam.esm.entity.Certificate;

import java.util.LinkedList;
import java.util.List;

public final class CertificateConverter {


    private CertificateConverter() {

    }

    public static List<CertificateTO> fromEntityToDTO(List<Certificate> list) {
        List<CertificateTO> result = new LinkedList<>();
        list.forEach(c -> result.add(fromEntityToDTO(c)));
        return result;
    }

    public static CertificateTO fromEntityToDTO(Certificate cert) {
        CertificateTO dto = new CertificateTO();
        dto.setName(cert.getName());
        dto.setCertificateId(cert.getCertificateId());
        dto.setDescription(cert.getDescription());
        dto.setPrice(cert.getPrice());
        dto.setDateOfCreation(cert.getDateOfCreation());
        dto.setDateOfModification(cert.getDateOfModification());
        dto.setDuration(cert.getDuration());
        dto.setTags(TagConverter.fromEntityToDTO(cert.getTags()));
        return dto;
    }

    public static Certificate fromDTOToEntity(CertificateTO dto) {
        Certificate certificate = new Certificate();
        certificate.setDateOfCreation(dto.getDateOfCreation());
        certificate.setDateOfModification(dto.getDateOfModification());
        certificate.setCertificateId(dto.getCertificateId());
        certificate.setName(dto.getName());
        certificate.setPrice(dto.getPrice());
        certificate.setDescription(dto.getDescription());
        certificate.setDuration(dto.getDuration());
        certificate.setTags(TagConverter.fromDTOToEntity(dto.getTags()));
        return certificate;
    }

    public static List<Certificate> fromDTOToEntity(List<CertificateTO> list) {
        List<Certificate> result = new LinkedList<>();
        list.forEach(c -> result.add(fromDTOToEntity(c)));
        return result;
    }


}

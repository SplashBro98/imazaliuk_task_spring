package test.epam.esm;

import com.epam.esm.converter.CertificateConverter;
import com.epam.esm.dto.CertificateTO;
import com.epam.esm.entity.Certificate;
import com.epam.esm.entity.CertificateSearch;
import com.epam.esm.entity.Tag;
import com.epam.esm.handling.CertificateNotFoundException;
import com.epam.esm.repository.impl.CertificateRepositoryImpl;
import com.epam.esm.service.impl.CertificateServiceImpl;
import com.epam.esm.specification.Specification;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
public class CertificateServiceTest {

    @Mock
    private CertificateRepositoryImpl certificateRepositoryMock;

    @InjectMocks
    private CertificateServiceImpl certificateService;

    @Test
    public void findAllCertificatesPositive() {

        List<Certificate> certificates = new LinkedList<>();

        Mockito.when(certificateRepositoryMock.query(Mockito
                .isA(Specification.class))).thenReturn(certificates);

        List<CertificateTO> actual = certificateService.findCertificates(new CertificateSearch());
        Assert.assertEquals(actual, certificates);
    }

    @Test
    public void findCertificateByIdTest() {
        Long id = Long.valueOf(2);
        Certificate cert = new Certificate();
        cert.setName("Robbo");
        cert.setDescription("asd");
        cert.setPrice(new BigDecimal(4));
        cert.setDateOfCreation(LocalDate.now());
        cert.setDateOfModification(LocalDate.now());
        cert.setDuration(5);
        cert.setTags(Arrays.asList(new Tag("splash")));
        Mockito.when(certificateRepositoryMock.findById(Mockito.isA(Long.class)))
                .thenReturn(Optional.of(cert));

        CertificateTO actual = certificateService.findById(id);

        Assert.assertEquals(actual, CertificateConverter.fromEntityToDTO(cert));

        Mockito.verify(certificateRepositoryMock).findById(id);

    }


    @Test(expected = CertificateNotFoundException.class)
    public void findCertificateByIdThrowException() {

        Long id = Long.valueOf(100);
        Mockito.when(certificateRepositoryMock.findById(Mockito.eq(id)))
                .thenThrow(new CertificateNotFoundException());

        CertificateTO certificateTO = certificateService.findById(id);

    }


}

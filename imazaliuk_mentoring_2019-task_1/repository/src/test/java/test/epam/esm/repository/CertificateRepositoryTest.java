package test.epam.esm.repository;

import com.epam.esm.config.SpringConfig;
import com.epam.esm.entity.Certificate;
import com.epam.esm.query.CertificateQuery;
import com.epam.esm.repository.CertificateRepository;
import com.epam.esm.specification.Specification;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import test.epam.esm.config.SpringTestConfig;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {SpringConfig.class, SpringTestConfig.class})
@WebAppConfiguration
public class CertificateRepositoryTest {

    @Autowired
    private CertificateRepository certificateRepository;

    @Test
    @Sql(scripts = {"classpath:drop_tables.sql", "classpath:create_tables.sql"},
            executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    public void findAllCertificatesPositive() {

        int certificateCount = 2;
        Specification spec = new Specification();
        spec.setQuery(CertificateQuery.SELECT_ALL);
        Assert.assertEquals(certificateCount, certificateRepository.query(spec).size());
    }

    @Test
    @Sql(scripts = {"classpath:drop_tables.sql", "classpath:create_tables.sql"},
            executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    public void findCertificatesByTagPositive() {
        String king = "king";
        Specification spec = new Specification();
        spec.setQuery(CertificateQuery.SELECT_BY_TAG_NAME);
        spec.setParams(Optional.of(Arrays.asList(king)));
        List<Certificate> certificateList = certificateRepository.query(spec);

        certificateList.forEach(c -> c.getTags().stream()
                .filter(t -> t.getName().equals(king)));
        certificateList.forEach(c -> Assert.
                assertTrue(c.getTags().get(0).getName().equals(king)));
    }

    @Test
    @Sql(scripts = {"classpath:drop_tables.sql", "classpath:create_tables.sql"},
            executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    public void findCertificatesByNamePositive() {
        String name = "Robbo";
        Specification spec = new Specification();
        spec.setQuery(CertificateQuery.SELECT_ALL + " where c.name = ?");
        spec.setParams(Optional.of(Arrays.asList(name)));
        Certificate certificate = certificateRepository.query(spec).get(0);

        Long expected = Long.valueOf(1);
        Assert.assertEquals(expected, certificate.getCertificateId());
    }


    @Test
    @Sql(scripts = {"classpath:drop_tables.sql", "classpath:create_tables.sql"},
            executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    public void deleteCertificateByIdPositive() {
        Long id = Long.valueOf(1);
        Specification spec = new Specification();
        spec.setQuery(CertificateQuery.DELETE_BY_ID);
        spec.setParams(Optional.of(Arrays.asList(id)));

        certificateRepository.delete(spec);
        Optional<Certificate> certificate = certificateRepository.findById(id);

        Assert.assertFalse(certificate.isPresent());
    }


    @Test
    @Sql(scripts = {"classpath:drop_tables.sql", "classpath:create_tables.sql"},
            executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    public void insertCertificateTestPositive() {
        Certificate certificate = new Certificate();
        certificate.setName("Dejan");
        certificate.setDuration(20);
        certificate.setDescription("Best defender");
        certificate.setDateOfCreation(LocalDate.now());
        certificate.setDateOfModification(LocalDate.now());
        certificate.setPrice(new BigDecimal(6));

        Specification spec = new Specification();
        spec.setQuery(CertificateQuery.SELECT_ALL);
        int first = certificateRepository.query(spec).size();
        certificateRepository.save(certificate);
        int second = certificateRepository.query(spec).size() - 1;
        Assert.assertEquals(first, second);
    }


    @Test
    @Sql(scripts = {"classpath:drop_tables.sql", "classpath:create_tables.sql"},
            executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    public void updateCertificateTest() {

        Long id = Long.valueOf(1);
        Certificate actual = certificateRepository.findById(id).get();
        actual.setName("James");
        Specification spec = new Specification();
        spec.setQuery(CertificateQuery.UPDATE_CERTIFICATE);
        spec.setParams(Optional.of(Arrays.asList(actual.getName(), actual.getDescription(), actual.getPrice(),
                actual.getDateOfCreation().toString(), actual.getDateOfModification().toString(),
                actual.getDuration(), actual.getCertificateId())));
        certificateRepository.update(spec);
        Certificate expected = certificateRepository.findById(id).get();

        Assert.assertEquals(actual, expected);

    }

    @Test(expected = NoSuchElementException.class)
    @Sql(scripts = {"classpath:drop_tables.sql", "classpath:create_tables.sql"},
            executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    public void emptyFindByIdTest() {

        Long id = Long.valueOf(250);
        Optional<Certificate> certificate = certificateRepository.findById(id);
        certificate.get().setPrice(new BigDecimal(10));
    }

    @Test
    @Sql(scripts = {"classpath:drop_tables.sql", "classpath:create_tables.sql"},
            executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    public void insertCertificateTagPositive() {

        Long certId = Long.valueOf(2);
        Long tagId = Long.valueOf(1);
        int actualTagCount = certificateRepository.findById(certId).get().getTags().size();
        certificateRepository.saveTagCertificate(certId, tagId);
        int expectedTagCount = certificateRepository.findById(certId).get().getTags().size();

        Assert.assertEquals(actualTagCount + 1, expectedTagCount);
    }


}

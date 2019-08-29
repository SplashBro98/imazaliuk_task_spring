package test.epam.esm.repository;

import com.epam.esm.config.SpringConfig;
import com.epam.esm.entity.Tag;
import com.epam.esm.query.TagQuery;
import com.epam.esm.repository.impl.TagRepositoryImpl;
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

import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Optional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {SpringConfig.class, SpringTestConfig.class})
@WebAppConfiguration
public class TagRepositoryTest {

    @Autowired
    private TagRepositoryImpl tagRepository;


    @Test
    @Sql(scripts = {"classpath:drop_tables.sql", "classpath:create_tables.sql"},
            executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    public void insertTagTestPositive() {
        Tag tag = new Tag("rangers");

        Specification spec = new Specification();
        spec.setQuery(TagQuery.SELECT_ALL);

        int first = tagRepository.query(spec).size();
        tagRepository.save(tag);
        int second = tagRepository.query(spec).size() - 1;
        Assert.assertEquals(first, second);
    }

    @Test
    @Sql(scripts = {"classpath:drop_tables.sql", "classpath:create_tables.sql"},
            executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    public void findAllTagsPositive() {

        int tagCount = 2;
        Specification spec = new Specification();
        spec.setQuery(TagQuery.SELECT_ALL);
        Assert.assertEquals(tagCount, tagRepository.query(spec).size());
    }

    @Test
    @Sql(scripts = {"classpath:drop_tables.sql", "classpath:create_tables.sql"},
            executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    public void deleteTagTestPositive() {

        Specification spec = new Specification();
        spec.setQuery(TagQuery.SELECT_ALL);
        int first = tagRepository.query(spec).size();
        spec.setQuery(TagQuery.DELETE_BY_ID);
        spec.setParams(Optional.of(Arrays.asList(first - 1)));
        tagRepository.delete(spec);
        spec = new Specification();
        spec.setQuery(TagQuery.SELECT_ALL);
        int second = tagRepository.query(spec).size() + 1;
        Assert.assertEquals(first, second);
    }

    @Test
    @Sql(scripts = {"classpath:drop_tables.sql", "classpath:create_tables.sql"},
            executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    public void updateTagNameTest() {

        String name = "james";
        Long id = Long.valueOf(1);
        Specification spec = new Specification();
        spec.setQuery(TagQuery.UPDATE_TAG);
        spec.setParams(Optional.of(Arrays.asList(name, id)));
        tagRepository.update(spec);
        Tag tag = tagRepository.findById(id).get();
        Assert.assertEquals(tag.getName(), name);
    }

    @Test(expected = NoSuchElementException.class)
    @Sql(scripts = {"classpath:drop_tables.sql", "classpath:create_tables.sql"},
            executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    public void NoSuchTagPositive() {
        Long id = Long.valueOf(100);
        Optional<Tag> tag = tagRepository.findById(id);
        tag.get().setName("avada-kidavra");

    }


}

package test.epam.esm;

import com.epam.esm.converter.TagConverter;
import com.epam.esm.dto.TagTO;
import com.epam.esm.entity.Tag;
import com.epam.esm.entity.TagSearch;
import com.epam.esm.handling.TagNotFoundException;
import com.epam.esm.repository.impl.TagRepositoryImpl;
import com.epam.esm.service.impl.TagServiceImpl;
import com.epam.esm.specification.Specification;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
public class TagServiceTest {

    @Mock
    private TagRepositoryImpl tagRepositoryMock;

    @InjectMocks
    private TagServiceImpl tagService;

    @Test
    public void findAllTagsPositive() {

        List<Tag> tags = new LinkedList<>();
        Mockito.when(tagRepositoryMock.query(Mockito.isA(Specification.class)))
                .thenReturn(tags);
        List<TagTO> actual = tagService.findTags(new TagSearch());

        Assert.assertEquals(actual, tags);

    }

    @Test
    public void findTagByIdTest() {
        Long id = Long.valueOf(2);
        Tag tag = new Tag();
        tag.setName("splash");
        Mockito.when(tagRepositoryMock.findById(Mockito.isA(Long.class)))
                .thenReturn(Optional.of(tag));

        TagTO actual = tagService.findById(id);

        Assert.assertEquals(actual, TagConverter.fromEntityToDTO(tag));

        Mockito.verify(tagRepositoryMock).findById(id);
    }

    @Test(expected = TagNotFoundException.class)
    public void findTagByIdThrowException() {

        Long id = Long.valueOf(100);
        Mockito.when(tagRepositoryMock.findById(Mockito.eq(id)))
                .thenThrow(new TagNotFoundException());

        TagTO tag = tagService.findById(id);

    }
}

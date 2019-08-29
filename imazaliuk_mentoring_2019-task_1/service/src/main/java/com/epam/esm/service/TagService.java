package com.epam.esm.service;

import com.epam.esm.dto.CertificateTO;
import com.epam.esm.dto.TagTO;
import com.epam.esm.entity.TagSearch;

import java.util.List;

/**
 * special interface for tag entities
 */
public interface TagService extends GiftService<TagTO> {

    /**
     * find certificates with current tag
     *
     * @param tagName is name of tag
     * @return list of certificates with this tag
     */
    List<CertificateTO> findByTag(String tagName);

    /**
     * find certificates with current tag
     *
     * @param id is id of tag
     * @return list of certificates with this tag
     */
    List<CertificateTO> findByTag(Long id);

    /**
     * find tag and update it
     *
     * @param id    is id of current tag
     * @param tagTO is object with updated values
     * @return tag with updated values
     */
    TagTO updateTag(Long id, TagTO tagTO);

    /**
     * find tags by criteria
     *
     * @param search is object with criteria values
     * @return list of tags
     */
    List<TagTO> findTags(TagSearch search);
}

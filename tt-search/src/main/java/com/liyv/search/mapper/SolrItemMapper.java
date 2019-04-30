package com.liyv.search.mapper;

import com.liyv.search.dto.SolrItemDTO;

import java.util.List;

public interface SolrItemMapper {

    public List<SolrItemDTO> getItemList();
}

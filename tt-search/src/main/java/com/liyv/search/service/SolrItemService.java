package com.liyv.search.service;

import com.liyv.search.dto.SearchResult;
import com.liyv.taotao.dto.Result;

public interface SolrItemService {

    public Result importAllItems() throws Exception;

    public SearchResult search(String query, int page, int rows) throws Exception;
}

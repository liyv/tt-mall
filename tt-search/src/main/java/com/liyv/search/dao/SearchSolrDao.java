package com.liyv.search.dao;

import com.liyv.search.dto.SearchResult;
import org.apache.solr.client.solrj.SolrQuery;

public interface SearchSolrDao {

    public SearchResult search(SolrQuery query) throws Exception;
}

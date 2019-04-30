package com.liyv.search.dao.impl;

import com.liyv.search.dao.SearchSolrDao;
import com.liyv.search.dto.SearchResult;
import com.liyv.search.dto.SolrItemDTO;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.ListUtils;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class SearchSolrDaoImpl implements SearchSolrDao {

    @Value("${SOLR_SEARCH_URL}")
    private String url;

    @Override
    public SearchResult search(SolrQuery query) throws Exception {
        SolrClient client = new HttpSolrClient.Builder(url).build();
        //执行查询
        QueryResponse response = client.query("new_core", query);
        SolrDocumentList solrDocuments = response.getResults();
        //得到查询列表
        List<SolrItemDTO> itemList = new ArrayList<>(solrDocuments.size());
        //取高亮显示
        Map<String, Map<String, List<String>>> highLighting = response.getHighlighting();
        for (SolrDocument doc : solrDocuments) {
            SolrItemDTO item = new SolrItemDTO();
            item.setId((String) doc.get("id"));
            item.setTitle((String) doc.get("item_title"));
            item.setSellPoint((String) doc.get("item_sell_point"));
            item.setPrice((long) doc.get("item_price"));
            item.setImage((String) doc.get("item_image"));
            item.setCategoryName((String) doc.get("item_category_name"));
            List<String> list = highLighting.get(item.getId()).get("item_title");
            if (!CollectionUtils.isEmpty(list)) {
                //非空
                item.setTitle(list.get(0));
            }
            itemList.add(item);
        }
        //构造 SearchResult
        SearchResult result = new SearchResult();
        result.setItemList(itemList);
        result.setRecordCount(solrDocuments.getNumFound());
        result.setCurPage(solrDocuments.getStart());
        client.close();
        return result;
    }
}

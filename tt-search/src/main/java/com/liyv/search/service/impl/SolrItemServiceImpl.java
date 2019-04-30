package com.liyv.search.service.impl;

import com.liyv.search.dao.SearchSolrDao;
import com.liyv.search.dto.SearchResult;
import com.liyv.search.dto.SolrItemDTO;
import com.liyv.search.mapper.SolrItemMapper;
import com.liyv.search.service.SolrItemService;
import com.liyv.taotao.dto.Result;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SolrItemServiceImpl implements SolrItemService {

    @Autowired
    private SearchSolrDao searchSolrDao;

    @Autowired
    private SolrItemMapper solrItemMapper;
    @Value("${SOLR_SEARCH_URL}")
    private String url;

    @Override
    public Result importAllItems() throws Exception {
        //查询商品列表
        List<SolrItemDTO> list = solrItemMapper.getItemList();
        SolrClient client = new HttpSolrClient.Builder(url).build();
        //把商品信息写入索引库
        ArrayList<SolrInputDocument> documentArrayList = new ArrayList<>(list.size());
        for (SolrItemDTO dto : list) {
            SolrInputDocument document = new SolrInputDocument();
            document.addField("id", dto.getId());
            document.addField("item_title", dto.getTitle());
            document.addField("item_sell_point", dto.getSellPoint());
            document.addField("item_price", dto.getPrice());
            document.addField("item_image", dto.getImage());
            document.addField("item_category_name", dto.getCategoryName());
            //写入索引库
            documentArrayList.add(document);
        }
        try {
            client.add("new_core", documentArrayList);
            client.commit("new_core");
            client.close();
        } catch (Exception e) {
            e.printStackTrace();
            client.close();
            throw e;
        }
        return new Result(true, "");
    }

    @Override
    public SearchResult search(String query, int page, int rows) throws Exception {
        //创建查询条件
        SolrQuery solrQuery = new SolrQuery();
        solrQuery.setQuery(query);
        //设置分页
        solrQuery.setStart((page - 1) * rows);
        solrQuery.setRows(rows);
        //设置默认搜索域
        solrQuery.set("df", "item_title");
        //设置高亮
        solrQuery.setHighlight(true);
        solrQuery.addHighlightField("item_title");
        solrQuery.setHighlightSimplePre("<font class=\"skcolor_ljg\">");
        solrQuery.setHighlightSimplePost("</font>");
        //执行查询
        SearchResult searchResult = searchSolrDao.search(solrQuery);
        //计算总页数
        Long recordCount = searchResult.getRecordCount();
        int pageCount = (int) (recordCount / rows);
        if (recordCount % rows > 0) {
            pageCount++;
        }
        searchResult.setPageCount(pageCount);
        searchResult.setCurPage(page);
        return searchResult;
    }
}

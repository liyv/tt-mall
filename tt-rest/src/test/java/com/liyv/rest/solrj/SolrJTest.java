package com.liyv.rest.solrj;

import com.liyv.rest.BaseTest;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.UpdateResponse;
import org.apache.solr.common.SolrInputDocument;
import org.junit.Test;

public class SolrJTest extends BaseTest {

    private final String url = "http://192.168.17.130:8080/solr";

    @Test
    public void addDocument() throws Exception {
        //创建一个连接
        SolrClient solrClient = new HttpSolrClient.Builder(url).build();

        //创建一个文档对象
//        SolrInputDocument doc = new SolrInputDocument();
//        doc.addField("id", "1234");
//        doc.addField("name", "liyv2");
//        UpdateResponse response = solrClient.add("new_core", doc);
//        solrClient.commit("new_core");
        solrClient.deleteById("new_core", "1234");
        solrClient.commit("new_core");
        //把文档对象写入索引库
    }
}

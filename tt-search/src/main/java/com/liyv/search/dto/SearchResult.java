package com.liyv.search.dto;

import java.util.List;

public class SearchResult {

    private List<SolrItemDTO> itemList;
    private long recordCount;
    private long pageCount;
    private long curPage;//当前页

    public List<SolrItemDTO> getItemList() {
        return itemList;
    }

    public void setItemList(List<SolrItemDTO> itemList) {
        this.itemList = itemList;
    }

    public long getRecordCount() {
        return recordCount;
    }

    public void setRecordCount(long recordCount) {
        this.recordCount = recordCount;
    }

    public long getPageCount() {
        return pageCount;
    }

    public void setPageCount(long pageCount) {
        this.pageCount = pageCount;
    }

    public long getCurPage() {
        return curPage;
    }

    public void setCurPage(long curPage) {
        this.curPage = curPage;
    }
}

package com.liyv.taotao.dto;

import java.util.List;

/**
 * easuui dataGrid数据格式
 */
public class EUDataGridDTO {
    private List<?> rows;
    private int total;

    public List<?> getRows() {
        return rows;
    }

    public void setRows(List<?> rows) {
        this.rows = rows;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}

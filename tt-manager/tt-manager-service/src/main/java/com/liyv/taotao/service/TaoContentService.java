package com.liyv.taotao.service;

import com.liyv.taotao.dto.EUDataGridDTO;

public interface TaoContentService {

    public EUDataGridDTO listContent(long categoryId, int page, int rows);
}

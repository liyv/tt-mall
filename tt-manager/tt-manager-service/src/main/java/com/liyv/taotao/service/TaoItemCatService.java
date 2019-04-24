package com.liyv.taotao.service;

import com.liyv.taotao.dto.TaoItemCatDTO;

import java.util.List;

public interface TaoItemCatService {

    public List<TaoItemCatDTO> listCategoryByPid(long pid);
}

package com.liyv.portal.service;

import com.liyv.portal.dto.SearchResult;

import java.io.IOException;

public interface SearchService {

    SearchResult search(String keyword, int page, int rows)throws IOException;
}

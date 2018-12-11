package cn.e3mall.search.service;

import cn.e3mall.common.pojo.SearchItem;
import cn.e3mall.common.pojo.SearchResult;

public interface SearchService {
	SearchResult search(String keyword,int page,int rows) throws Exception;
}

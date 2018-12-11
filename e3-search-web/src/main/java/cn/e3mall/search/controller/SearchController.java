package cn.e3mall.search.controller;

import java.io.UnsupportedEncodingException;

import javax.jws.WebParam.Mode;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.alibaba.dubbo.rpc.Result;

import cn.e3mall.common.pojo.SearchResult;
import cn.e3mall.search.service.SearchService;

@Controller
public class SearchController {

	@Autowired
	private SearchService searchService;
	@Value("${SEARCH_RESULT_ROWS}")
	private Integer SEARCH_RESULT_ROWS;
	
	
	@RequestMapping("/search")
	public String search(String keyword,@RequestParam(defaultValue="1")Integer page,
			Model model) throws Exception{
		keyword=new String(keyword.getBytes("iso-8859-1"),"utf-8");
		SearchResult result = searchService.search(keyword, page, SEARCH_RESULT_ROWS);
		model.addAttribute("query", keyword);
		int totalPages = result.getTotalPages();
		model.addAttribute("totalPages", totalPages);
		model.addAttribute("recourdCount", result.getRecordCount());
		model.addAttribute("page", page);
		model.addAttribute("itemList", result.getItemList());
		int nextPage=0;
		int prevPage=0;
		if(page==1){
			nextPage=page+1;
			model.addAttribute("nextPage", nextPage);
			model.addAttribute("prevPage", 1);
		}else if (page>1&&page<totalPages) {
			nextPage=page+1;
			model.addAttribute("nextPage", nextPage);
			prevPage=page-1;
			model.addAttribute("prevPage", prevPage);
		}else if (page==totalPages) {
			model.addAttribute("nextPage", totalPages);
			prevPage=page-1;
			model.addAttribute("prevPage", prevPage);
		}
	
		
		return "search";
	}
}

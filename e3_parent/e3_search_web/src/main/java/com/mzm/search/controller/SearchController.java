package com.mzm.search.controller;

import java.io.UnsupportedEncodingException;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mzm.search.pojo.SearchResult;
import com.mzm.search.service.SearchService;

@Controller
public class SearchController {
	
	@Autowired
	private SearchService searchService;
	@Value("${search.result.rows}")
	private Integer resultRows;
	@RequestMapping("/search")
	public String search(String keyword,@RequestParam(defaultValue="1") Integer page,Model model) throws Exception{
		
		//异常测试
		//int i = 1/0;
		
		if(StringUtils.isNotBlank(keyword)){
			keyword = new String(keyword.getBytes("iso8859-1"),("utf-8"));
		}
		//调用Service查询商品列表
		SearchResult searchResult = searchService.search(keyword, page, resultRows);
		//把结果传递给jsp
		model.addAttribute("totalPages", searchResult.getTotalPages());
		model.addAttribute("recourdCount", searchResult.getRecourdCount());
		model.addAttribute("itemList", searchResult.getItemList());
		//查询参数回显
		model.addAttribute("query", keyword);
		model.addAttribute("page", page);
		//返回逻辑视图
		return "search";
	}
	
}

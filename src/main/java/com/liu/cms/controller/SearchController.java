package com.liu.cms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.pagehelper.PageInfo;
import com.liu.cms.pojo.Article;
import com.liu.cms.pojo.Link;
import com.liu.cms.service.LinkService;
import com.liu.cms.utils.EsUtils;
/**
 * 搜索控制器
 */
@Controller
public class SearchController {
	@Autowired
	private ElasticsearchTemplate elasticsearchTemplate;
	@Autowired
	private LinkService linkService;
	/**
	 * 搜索
	 * @param keyword 关键词
	 * @param model
	 * @param pageNum 分页
	 * @return
	 */
	@RequestMapping("search")
	public String search(String keyword,Model model,@RequestParam(value="pageNum",defaultValue="1") Integer pageNum) {
		PageInfo<?> pageInfo = EsUtils.findByKeyword(elasticsearchTemplate, keyword, Article.class, 
				"id", pageNum, 10, "title");
		model.addAttribute("pageInfo", pageInfo);
		model.addAttribute("keyword", keyword);
		/** 友情链接 **/
		List<Link> linkList = linkService.getLinkListAll();
		model.addAttribute("linkList", linkList);
		return "search";
	}
}

package com.hanbit.spring.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hanbit.spring.core.service.BoardService;
import com.hanbit.spring.core.vo.ArticleVO;

@Controller
public class BoardController {
	
	@Autowired
	private BoardService boardService;

	@RequestMapping("/")
	public String list(Model model) {
		model.addAttribute("list", boardService.getList());
		
		return "board/list";
	}
	
	@RequestMapping("/api/list")
	@ResponseBody
	public List<ArticleVO> apiList() {
		return boardService.getList();
	}
	
	@RequestMapping("/api/search")
	@ResponseBody
	public List<ArticleVO> apiSearch(@RequestParam("keyword") String keyword) {
		return boardService.search(keyword);
	}
	
	@RequestMapping("/board/write")
	public String write(){
		return "board/write";
	}
	
	@RequestMapping(value="/api/save",method={RequestMethod.POST})
	@ResponseBody
	public Map apiSave(@RequestBody ArticleVO articleVo){
		
		boardService.addArticle(articleVo);
		
		Map result = new HashMap();
		result.put("status", "OK");
				
		return result;
	}
	
}
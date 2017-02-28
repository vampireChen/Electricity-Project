package com.taotao.portal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
/**
 * 显示首页
 * <p>Title: IndexController</p>
 * <p>@Description:TODO</p>
 * <p>Company: www.chenhaitao.com</p>	
 * @author chenhaitao
 * @date:2017年2月26日下午1:16:22
 * @version:1.0
 */
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.portal.service.ContentService;

@Controller
public class IndexController {
	@Autowired
	private ContentService contentService;
	@RequestMapping("/index")//因为web.xml中初始化页面是index.xml,且使用了伪静态化*.html,所以.html的会被拦截
	public String showIndex(Model model){
		String adJson = contentService.getContentList();
		model.addAttribute("ad1", adJson);
		return "index";
	}
	
	@RequestMapping(value="/httpclient/post",method=RequestMethod.POST)
	@ResponseBody
	public String testPost(String username,String password){
		return "username" + username +"/t" + "password" + password;
	}
}

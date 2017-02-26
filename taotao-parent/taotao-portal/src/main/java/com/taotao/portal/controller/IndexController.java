package com.taotao.portal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
/**
 * 显示首页
 * <p>Title: IndexController</p>
 * <p>@Description:TODO</p>
 * <p>Company: www.chenhaitao.com</p>	
 * @author chenhaitao
 * @date:2017年2月26日下午1:16:22
 * @version:1.0
 */
@Controller
public class IndexController {
	@RequestMapping("/index")//因为web.xml中使用了伪静态*.html,所以这里requsetMapping参数必须用"index"
	public String showIndex(){
		return "index";
	}
}

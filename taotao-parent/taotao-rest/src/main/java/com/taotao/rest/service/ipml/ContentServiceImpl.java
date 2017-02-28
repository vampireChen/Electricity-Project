package com.taotao.rest.service.ipml;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taotao.mapper.TbContentMapper;
import com.taotao.pojo.TbContent;
import com.taotao.pojo.TbContentExample;
import com.taotao.pojo.TbContentExample.Criteria;
import com.taotao.rest.service.ContentService;
/**
 * 广告服务service
 * <p>Title: ContentServiceImpl</p>
 * <p>@Description:TODO</p>
 * <p>Company: www.chenhaitao.com</p>	
 * @author chenhaitao
 * @date:2017年2月28日下午4:07:43
 * @version:1.0
 */
@Service
public class ContentServiceImpl implements ContentService {
	@Autowired
	private TbContentMapper contentMapper;
	/**
	 * 根据分类ID取内容
	 * <p>Title: getContenList</p>
	 * <p>Description: </p>
	 * @param categoryId
	 * @return: List<TbContent>
	 */
	@Override
	public List<TbContent> getContenList(Long categoryId) {
		TbContentExample example = new TbContentExample();
		Criteria criteria = example.createCriteria();
		criteria.andCategoryIdEqualTo(categoryId);
		List<TbContent> list = contentMapper.selectByExample(example);
		if(list != null && list.size() > 0){
			return list;
		}
		return null;
	}

}

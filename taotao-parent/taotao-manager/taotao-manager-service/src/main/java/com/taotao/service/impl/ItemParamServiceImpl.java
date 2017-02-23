package com.taotao.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taotao.mapper.TbItemParamMapper;
import com.taotao.pojo.TbItemParam;
import com.taotao.pojo.TbItemParamExample;
import com.taotao.pojo.TbItemParamExample.Criteria;
import com.taotao.result.TaotaoResult;
import com.taotao.service.ItemParamService;
/**
 * 商品的规格参数模板
 * <p>Title: ItemParamServiceImpl</p>
 * <p>@Description:TODO</p>
 * <p>Company: www.chenhaitao.com</p>	
 * @author chenhaitao
 * @date:2017年2月22日下午6:42:32
 * @version:1.0
 */
@Service
public class ItemParamServiceImpl implements ItemParamService {
	@Autowired
	private TbItemParamMapper itemParamMapper;
	/**
	 * 根据cid查询商品规格参数模板是否存在
	 * <p>Title: getItemParamByCid</p>
	 * <p>Description: </p>
	 * @param cid
	 * @return: TaotaoResult
	 */
	@Override
	public TaotaoResult getItemParamByCid(long cid) {
		TbItemParamExample example = new TbItemParamExample();
		Criteria criteria = example.createCriteria();
		criteria.andItemCatIdEqualTo(cid);
		List<TbItemParam> list = itemParamMapper.selectByExample(example);
		//判断是否查询到结果
		if(list != null && list.size() > 0){
			return TaotaoResult.ok(list.get(0));
		}
		return TaotaoResult.ok();
	}
	
	/**
	 * 将生成的模板存入到tb_item_param表中
	 * <p>Title: insertItemParam</p>
	 * <p>Description: </p>
	 * @param cid
	 * @return: TaotaoResult
	 */
	@Override
	public TaotaoResult insertItemParam(TbItemParam itemParam) {
		// 补全pojo
		itemParam.setCreated(new Date());
		itemParam.setUpdated(new Date());
		// 插入到规格参数模板表
		itemParamMapper.insert(itemParam);
		return TaotaoResult.ok();
	}

}

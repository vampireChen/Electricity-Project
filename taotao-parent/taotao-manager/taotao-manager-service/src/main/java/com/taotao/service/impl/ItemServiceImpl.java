package com.taotao.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.reflection.ExceptionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.mapper.TbItemDescMapper;
import com.taotao.mapper.TbItemMapper;
import com.taotao.pojo.EUDataGridResult;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemDesc;
import com.taotao.pojo.TbItemExample;
import com.taotao.pojo.TbItemExample.Criteria;
import com.taotao.result.TaotaoResult;
import com.taotao.service.ItemService;
import com.taotao.utils.IDUtils;
/**
 * 商品管理service
 * <p>Title:ItemServiceImpl</p>
 * <P>Description:</P>
 * <p>Company:www.chenhaitao.com</p>
 * @author chenhaitao
 * @date: 2017.02.17 14:51:02
 * @version 1.0
 */
@Service
public class ItemServiceImpl implements ItemService{
	@Autowired
	private TbItemMapper itemMapper;
	@Autowired
	private TbItemDescMapper itemDescMapper;
	/**
	 * 通过商品id查询商品
	 * <p>Title: getItemById</p>
	 * <p>Description: </p>
	 * @param itemId
	 * @return: TbItem
	 */
	@Override
	public TbItem getItemById(long itemId) {
		/*通过Id查询*/
		//TbItem item = itemMapper.selectByPrimaryKey(itemId);
		/*通过查询条件查询*/
		TbItemExample itemExample = new TbItemExample();
		//添加查询条件
		Criteria criteria = itemExample.createCriteria();
		criteria.andIdEqualTo(itemId);
		List<TbItem> list = itemMapper.selectByExample(itemExample);
		if(list != null && list.size() > 0){
			TbItem item = list.get(0);
			return item;
		}
		return null;
	}
	
	/**
	 * 商品列表分页查询
	 * <p>Title: getItemList</p>
	 * <p>Description: </p>
	 * @param page
	 * @param rows
	 * @return: EUDataGridResult
	 */
	@Override
	public EUDataGridResult getItemList(int page, int rows) {
		// 查询商品列表
		TbItemExample example = new TbItemExample();
		// 分页处理
		PageHelper.startPage(page, rows);
		List<TbItem> list = itemMapper.selectByExample(example);
		// 创建一个返回值对象
		EUDataGridResult result = new EUDataGridResult();
		result.setRows(list);
		// 取记录总条数
		PageInfo<TbItem> pageInfo = new PageInfo<>(list);
		result.setTotal(pageInfo.getTotal());
		return result;
	}
	/**
	 * 商品添加
	 * <p>Title: addItem</p>
	 * <p>Description: </p>
	 * @param tbItem
	 * @return: TaotaoResult
	 */
	@Override
	public TaotaoResult addItem(TbItem tbItem,TbItemDesc tbItemDesc) {
		try {
			/**
			 * 补全商品信息
			 */
			//生成商品ID
			Long itemId = IDUtils.genItemId();
			tbItem.setId(itemId);
			tbItem.setStatus((byte) 1);
			Date date = new Date();
			tbItem.setCreated(date);
			tbItem.setUpdated(date);
			//把数据插入到商品表
			itemMapper.insert(tbItem);
			//添加商品描述
			tbItemDesc.setItemId(itemId);
			tbItemDesc.setCreated(date);
			tbItemDesc.setUpdated(date);
			//把商品描述添加到商品描述表
			itemDescMapper.insert(tbItemDesc);
		} catch (Exception e) {
			e.printStackTrace();
			return TaotaoResult.build(500, "系统发生异常");
		}
		return TaotaoResult.ok();
	}
	
}

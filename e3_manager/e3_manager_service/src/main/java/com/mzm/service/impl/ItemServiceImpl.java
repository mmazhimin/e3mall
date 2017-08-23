package com.mzm.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mzm.mapper.TbItemMapper;
import com.mzm.pojo.TbItem;
import com.mzm.service.ItemService;
@Service
public class ItemServiceImpl implements ItemService {

	@Autowired
	private TbItemMapper itemMapper;
	//测试
	@Override
	public TbItem getItemById(long id) {
	TbItem item = itemMapper.selectByPrimaryKey(id);
	return item;
	}
}

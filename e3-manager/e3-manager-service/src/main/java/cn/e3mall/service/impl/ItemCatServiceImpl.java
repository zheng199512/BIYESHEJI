package cn.e3mall.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.e3mall.common.pojo.EasyUITreeNode;
import cn.e3mall.mapper.TbItemCatMapper;
import cn.e3mall.pojo.TbItemCat;
import cn.e3mall.pojo.TbItemCatExample;
import cn.e3mall.pojo.TbItemCatExample.Criteria;
/**
 * 商品分类管理
 * @author 郑小飞
 *
 */

@Service
public class ItemCatServiceImpl implements cn.e3mall.service.ItemCatService {

	@Autowired
	private TbItemCatMapper itemCatMapper;
	
	@Override
	public List<EasyUITreeNode> getItemCatlist(long parentId) {
		//根据parentId查询子节点列表
		TbItemCatExample example=new TbItemCatExample();
		Criteria criteria=example.createCriteria();
		//设置查询条件
		criteria.andParentIdEqualTo(parentId);
		//执行查询
		List<TbItemCat> list = itemCatMapper.selectByExample(example);
		//创建返回结果List
		List<EasyUITreeNode> resultList=new ArrayList<>();
		//把列表转换成EasyUITreeNode列表
		for(TbItemCat tbItemCat:list){
			EasyUITreeNode node=new EasyUITreeNode();
			//设置属性
			node.setId(tbItemCat.getId());
			node.setText(tbItemCat.getName());
			node.setState(tbItemCat.getIsParent()?"closed":"open");
			//添加到结果集
			resultList.add(node);
		}
		//itemCatMapper.selectByExample(example);
		return resultList;
	}

}

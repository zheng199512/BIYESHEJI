package cn.e3mall.service;

import cn.e3mall.common.pojo.EasyUIDataGridResult;
import cn.e3mall.common.utils.E3Result;
import cn.e3mall.pojo.TbItem;
import cn.e3mall.pojo.TbItemDesc;

public interface ItemService {
	TbItem getItemById(long itemId); 
	TbItemDesc getItemDescById(long itemId);
	EasyUIDataGridResult getItemList(int page,int rows);
	//添加商品数据
	E3Result addItem(TbItem item,String desc);
	//上架商品
	E3Result shelfItem(String params);
	//下架商品
	E3Result instock(String ids);
	//删除商品（可以选择多个）
	E3Result deleteTbItemAndDesc(String ids);
	//查询得到DESC
	TbItemDesc getItemDescById(Long id);
	//更新产品信息
	E3Result updateTbItemAndDesc(TbItem item,String desc);
	
}

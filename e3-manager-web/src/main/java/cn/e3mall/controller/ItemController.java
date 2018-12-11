package cn.e3mall.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.e3mall.common.pojo.EasyUIDataGridResult;
import cn.e3mall.common.utils.E3Result;
import cn.e3mall.pojo.TbItem;
import cn.e3mall.pojo.TbItemDesc;
import cn.e3mall.service.ItemService;

/**
 * 商品管理Controller
 * @author 郑小飞
 *
 */
@Controller
public class ItemController {
	@Autowired
	private ItemService itemService;
	
	@RequestMapping("/item/{itemId}")
	@ResponseBody
	public TbItem getItemById(@PathVariable Long itemId){
		TbItem tbItem=itemService.getItemById(itemId);
		return tbItem;	
	}
	
	@RequestMapping("/item/list")
	@ResponseBody
	public EasyUIDataGridResult getItemList(Integer page,Integer rows){
		EasyUIDataGridResult result=itemService.getItemList(page, rows);
		return result;	
	}
	//商品添加功能
	@RequestMapping(value="/item/save",method=RequestMethod.POST)
	@ResponseBody
	public E3Result addItem(TbItem item,String desc){
		E3Result result=itemService.addItem(item, desc);
		return result;
	}
	
	//上架   1-正常，2-下架，3-删除
	@RequestMapping(value="/rest/item/reshelf",method=RequestMethod.POST)
	@ResponseBody
	public E3Result shelfItem(String ids){
		E3Result result=itemService.shelfItem(ids);
		return result;
	}
	//下架商品
	@RequestMapping(value="/rest/item/instock",method=RequestMethod.POST)
	@ResponseBody
	public E3Result instock(String ids){
		E3Result result=itemService.instock(ids);
		return result;
	}
	//删除商品 /rest/item/delete
	@RequestMapping(value=" /rest/item/delete",method=RequestMethod.POST)
	@ResponseBody
	public E3Result deleteTbItemAndDesc(String ids){
		E3Result result=itemService.deleteTbItemAndDesc(ids);
		return result;
	}
	//修改商品 /rest/item/update
	@RequestMapping(value="/rest/item/update",method=RequestMethod.POST)
	@ResponseBody
	public E3Result updateTbItemAndDesc(TbItem item,String desc){
		E3Result result=itemService.updateTbItemAndDesc(item,desc);
		return result;
	}
	//查询得到DESC
	@RequestMapping("/rest/item/query/item/desc/{itemId}")
	@ResponseBody
	public TbItemDesc getItemDescById(@PathVariable Long itemId){
		TbItemDesc result=itemService.getItemDescById(itemId);
		return result;
	}
	
}

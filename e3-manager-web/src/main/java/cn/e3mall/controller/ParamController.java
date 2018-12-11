package cn.e3mall.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.dubbo.rpc.Result;

import cn.e3mall.common.pojo.EasyUIDataGridResult;
import cn.e3mall.common.utils.E3Result;
import cn.e3mall.pojo.TbItem;
import cn.e3mall.service.ParamService;

@Controller
public class ParamController {
	@Autowired
	private ParamService paramService;
	
	@RequestMapping("/item/param/list")
	@ResponseBody
	public EasyUIDataGridResult getParamlist(int page, int rows) {
		EasyUIDataGridResult result = paramService.getParamlist(page, rows);
		return result;
	}
	
	/**
	 * 删除商品参数(value=" /rest/item/delete",method=RequestMethod.POST)
	 */
	@RequestMapping(value="/item/param/delete",method=RequestMethod.POST)
	@ResponseBody
	public E3Result deleteParam(String ids){
		E3Result result=paramService.deleteParam(ids);
		return result;
	}
	/**
	 * 添加商品参数（存在检测）/item/param/query/itemcatid/
	 */
	@RequestMapping("/item/param/query/itemcatid/{cid}")
	@ResponseBody
	public E3Result addQueryParam(@PathVariable Long cid){
		E3Result result=paramService.addQueryParam(cid);
		return result;	
	}
	
	/**
	 * 添加商品成功
	 * public E3Result addItem(TbItem item,String desc){
	 */
	@RequestMapping(value="/item/param/save/{cid}",method=RequestMethod.POST)
	@ResponseBody
	public E3Result addParam(@PathVariable String cid,String paramData){
		E3Result result=paramService.addParam(cid,paramData);
		return result;
	}
}

package cn.e3mall.controller;

import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.e3mall.common.pojo.EasyUIDataGridResult;
import cn.e3mall.common.utils.E3Result;
import cn.e3mall.content.service.ContentService;
import cn.e3mall.pojo.TbContent;

@Controller
public class ContentController {
	@Autowired
	private ContentService contentService;
	
	@RequestMapping("/content/query/list")
	@ResponseBody
	public EasyUIDataGridResult getContentList(long categoryId,Integer page,Integer rows){
		EasyUIDataGridResult result = contentService.getContentList(categoryId, page, rows);
		return result;	
	}
	/**
	 * 添加内容Controller
	 * @param content
	 * @return
	 */
	@RequestMapping(value="/content/save",method=RequestMethod.POST)
	@ResponseBody
	public E3Result addContent(TbContent content){
		E3Result e3Result = contentService.addContent(content);
		return e3Result;
	}
	/**
	 * 类目编辑controller   /rest/content/edit
	 */
	@RequestMapping(value="/rest/content/edit",method=RequestMethod.POST)
	@ResponseBody
	public E3Result updateContent(TbContent content){
		E3Result e3Result = contentService.updateContent(content);
		return e3Result;
	}
	/**
	 * 删除类目controller /content/delete
	 */
	@RequestMapping(value="/content/delete",method=RequestMethod.POST)
	@ResponseBody
	public E3Result deleteContent(String ids){
		E3Result e3Result = contentService.deleteContent(ids);
		return e3Result;
	}
}

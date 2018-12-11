package cn.e3mall.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.e3mall.common.pojo.EasyUITreeNode;
import cn.e3mall.common.utils.E3Result;
import cn.e3mall.content.service.ContentCategoryService;
/**
 * 内容分类管理Controller
 * @author 郑小飞
 *
 */
@Controller
public class ContentCatController {

	@Autowired
	private ContentCategoryService contentCategoryService;
	
	@RequestMapping("/content/category/list")
	@ResponseBody
	public List<EasyUITreeNode> getContentCatList(@RequestParam(name="id",defaultValue="0")Long parentId){
		List<EasyUITreeNode> list = contentCategoryService.getContentCatList(parentId);
		return list;
	}
	
	/**
	 * 添加节点分类
	 */
	@RequestMapping(value="/content/category/create",method=RequestMethod.POST)
	@ResponseBody
	public E3Result createContentCategory(long parentId,String name){
		E3Result e3Result = contentCategoryService.addContentCategory(parentId, name);
		return e3Result;	
	}
	//修改节点名称    /content/category/update
	@RequestMapping(value="/content/category/update",method=RequestMethod.POST)
	@ResponseBody
	public void updateContentCategory(long id,String name){
		contentCategoryService.updateContentCategory(id, name);	
	}
	//删除节点 /content/category/delete/
	@RequestMapping(value="/content/category/delete/",method=RequestMethod.POST)
	@ResponseBody
	public void deleteContentCategory(long id){
		contentCategoryService.deleteContentCategory(id);	
	}
	
}
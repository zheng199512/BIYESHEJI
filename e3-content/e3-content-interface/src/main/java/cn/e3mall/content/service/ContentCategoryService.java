package cn.e3mall.content.service;

import java.util.List;

import cn.e3mall.common.pojo.EasyUITreeNode;
import cn.e3mall.common.utils.E3Result;

public interface ContentCategoryService {
	
	List<EasyUITreeNode> getContentCatList(Long parentId);
	//添加节点
	E3Result addContentCategory(long parentId,String name);
	//修改节点名称
	void updateContentCategory(long id, String name);
	//删除节点
	void deleteContentCategory(long id);
}

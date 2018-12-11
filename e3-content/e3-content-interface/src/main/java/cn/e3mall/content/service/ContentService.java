package cn.e3mall.content.service;

import java.util.List;

import cn.e3mall.common.pojo.EasyUIDataGridResult;
import cn.e3mall.common.utils.E3Result;
import cn.e3mall.pojo.TbContent;

public interface ContentService {
	//表格的显示
	EasyUIDataGridResult getContentList(long categoryId,int page,int rows);
	//添加内容
	E3Result addContent(TbContent content);
	//编辑更新
	E3Result updateContent(TbContent content);
	//删除类目
	E3Result deleteContent(String ids);
	//首页轮播图的交互
	List<TbContent> getContentByCid(long cid);
}

package cn.e3mall.content.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.e3mall.common.pojo.EasyUITreeNode;
import cn.e3mall.common.utils.E3Result;
import cn.e3mall.content.service.ContentCategoryService;
import cn.e3mall.mapper.TbContentCategoryMapper;
import cn.e3mall.pojo.TbContentCategory;
import cn.e3mall.pojo.TbContentCategoryExample;
import cn.e3mall.pojo.TbContentCategoryExample.Criteria;
/**
 * 内容分类管理Service
 * @author 郑小飞
 *
 */
@Service
public class ContentCategoryServiceImpl implements ContentCategoryService {
	
	@Autowired
	private TbContentCategoryMapper contentCategoryMapper;
	@Override
	public List<EasyUITreeNode> getContentCatList(Long parentId) {

		TbContentCategoryExample example=new TbContentCategoryExample();
		Criteria criteria=example.createCriteria();
		criteria.andParentIdEqualTo(parentId);
		List<TbContentCategory> catList=contentCategoryMapper.selectByExample(example);
		List<EasyUITreeNode> nodeList=new ArrayList<>();
		for (TbContentCategory tbContentCategory : catList) {
			EasyUITreeNode node=new EasyUITreeNode();
			node.setId(tbContentCategory.getId());
			node.setText(tbContentCategory.getName());;
			node.setState(tbContentCategory.getIsParent()?"closed":"open");
			nodeList.add(node);
		}
		return nodeList;
	}
	@Override
	public E3Result addContentCategory(long parentId, String name) {
		TbContentCategory contentCategory=new TbContentCategory();
		contentCategory.setParentId(parentId);
		contentCategory.setName(name);
		//状态1正常2删除
		contentCategory.setStatus(1);
		//默认排序是1
		contentCategory.setSortOrder(1);
		//新创建的节点一定不是父节点
		contentCategory.setIsParent(false);
		contentCategory.setCreated(new Date());
		contentCategory.setUpdated(new Date());
		contentCategoryMapper.insert(contentCategory);
		//判断父节点isparent属性，如果不是true改为true
		TbContentCategory parent=contentCategoryMapper.selectByPrimaryKey(parentId);
		if(!parent.getIsParent()){
			parent.setIsParent(true);
			contentCategoryMapper.updateByPrimaryKey(parent);
		}
		return E3Result.ok(contentCategory);
	}
	/**
	 * 修改节点名称
	 */
	@Override
	public void updateContentCategory(long id, String name) {
		TbContentCategory selectByPrimaryKey = contentCategoryMapper.selectByPrimaryKey(id);
		selectByPrimaryKey.setName(name);
		selectByPrimaryKey.setUpdated(new Date());
		contentCategoryMapper.updateByPrimaryKeySelective(selectByPrimaryKey);
	}
	/**
	 * 删除节点
	 */
	@Override
	public void deleteContentCategory(long id) {
		TbContentCategory selectByPrimaryKey = contentCategoryMapper.selectByPrimaryKey(id);
		long parentId=selectByPrimaryKey.getParentId();
		Boolean isParent=selectByPrimaryKey.getIsParent();
		if(!isParent){
			contentCategoryMapper.deleteByPrimaryKey(id);
			TbContentCategoryExample example=new TbContentCategoryExample();
			Criteria criteria=example.createCriteria();
			criteria.andParentIdEqualTo(parentId);
			int countByExample = contentCategoryMapper.countByExample(example);
			if(countByExample==0){
				TbContentCategory selectParent = contentCategoryMapper.selectByPrimaryKey(parentId);
				selectParent.setIsParent(false);
				selectParent.setUpdated(new Date());
				contentCategoryMapper.updateByPrimaryKeySelective(selectParent);
			}
		}	
	}
}

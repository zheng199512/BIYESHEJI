package cn.e3mall.content.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.alibaba.druid.util.StringUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.e3mall.common.jedis.JedisClient;
import cn.e3mall.common.pojo.EasyUIDataGridResult;
import cn.e3mall.common.utils.E3Result;
import cn.e3mall.common.utils.JsonUtils;
import cn.e3mall.content.service.ContentService;
import cn.e3mall.mapper.TbContentMapper;
import cn.e3mall.mapper.TbItemDescMapper;
import cn.e3mall.pojo.TbContent;
import cn.e3mall.pojo.TbContentExample;
import cn.e3mall.pojo.TbItem;
import cn.e3mall.pojo.TbItemExample;
import cn.e3mall.pojo.TbContentExample.Criteria;

@Service
public class ContentServiceImpl implements ContentService {

	@Autowired
	private TbContentMapper contentMapper;
	@Autowired
	private JedisClient jedisClient;
	
	@Value("${CONTENT_LIST}")
	private String CONTENT_LIST;
	
	@Override
	public EasyUIDataGridResult getContentList(long categoryId,int page,int rows) {
		PageHelper.startPage(page, rows);
		//执行查询
		TbContentExample example = new TbContentExample();
		Criteria criteria=example.createCriteria();
		criteria.andCategoryIdEqualTo(categoryId);
		List<TbContent> list=contentMapper.selectByExampleWithBLOBs(example);
		//创建返回值
		EasyUIDataGridResult result=new EasyUIDataGridResult();
		result.setRows(list);
		//取结果
		PageInfo<TbContent> pageInfo=new PageInfo<>(list);
		long total=pageInfo.getTotal();
		result.setTotal(total);
		return result;
	}

	@Override
	public E3Result addContent(TbContent content) {
		content.setCreated(new Date());
		content.setUpdated(new Date());
		contentMapper.insert(content);
		jedisClient.hdel(CONTENT_LIST, content.getCategoryId().toString());
		return E3Result.ok();
	}

	@Override
	public E3Result updateContent(TbContent content) {
		content.setUpdated(new Date());
		contentMapper.updateByPrimaryKeySelective(content);
		jedisClient.hdel(CONTENT_LIST, content.getCategoryId().toString());
		return E3Result.ok();
	}

	@Override
	public E3Result deleteContent(String ids) {
		String[] idsArr=ids.split(",");
		for(int i=0;i<idsArr.length;i++){
			long idLong=Long.parseLong(idsArr[i]);
			contentMapper.deleteByPrimaryKey(idLong);	
		}
		return E3Result.ok();
	}

	@Override
	public List<TbContent> getContentByCid(long cid) {
		try {
			String json = jedisClient.hget(CONTENT_LIST, cid+"");
			if(!StringUtils.isEmpty(json)){
				List<TbContent> list = JsonUtils.jsonToList(json, TbContent.class);
				return list;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		TbContentExample example=new TbContentExample();
		Criteria criteria = example.createCriteria();
		criteria.andCategoryIdEqualTo(cid);
		List<TbContent> list = contentMapper.selectByExampleWithBLOBs(example);
		try {
			jedisClient.hset(CONTENT_LIST, cid+"",JsonUtils.objectToJson(list));	
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

}

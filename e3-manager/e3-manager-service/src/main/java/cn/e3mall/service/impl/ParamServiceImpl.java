package cn.e3mall.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mysql.fabric.xmlrpc.base.Array;

import cn.e3mall.common.pojo.EasyUIDataGridResult;
import cn.e3mall.common.pojo.EasyUITreeNode;
import cn.e3mall.common.utils.E3Result;
import cn.e3mall.common.utils.IDUtils;
import cn.e3mall.mapper.TbItemCatMapper;
import cn.e3mall.mapper.TbItemParamMapper;
import cn.e3mall.pojo.TbItem;
import cn.e3mall.pojo.TbItemCat;
import cn.e3mall.pojo.TbItemCatExample;
import cn.e3mall.pojo.TbItemExample;
import cn.e3mall.pojo.TbItemParam;
import cn.e3mall.pojo.TbItemParamExample;
import cn.e3mall.pojo.TbItemParamExample.Criteria;
import cn.e3mall.service.ParamService;
@Service
public class ParamServiceImpl implements ParamService {

	@Autowired
	private TbItemParamMapper itemParamMapper;
	@Autowired
	private TbItemCatMapper itemCatMapper;
	
	@Override
	public EasyUIDataGridResult getParamlist(int page, int rows) {
		//设置分页信息
		PageHelper.startPage(page, rows);
		//执行查询
		TbItemParamExample example=new TbItemParamExample();
		List<TbItemParam> list=itemParamMapper.selectByExampleWithBLOBs(example);
		for (TbItemParam tbItemParam : list) {
			Long itemCatId = tbItemParam.getItemCatId();
			TbItemCat itemCat = itemCatMapper.selectByPrimaryKey(itemCatId);
			System.out.println(itemCat.getName());
		}
		
		//创建返回值
		EasyUIDataGridResult result=new EasyUIDataGridResult();
		
		result.setRows(list);
		//取结果
		PageInfo<TbItemParam> pageInfo=new PageInfo<>(list);
		long total=pageInfo.getTotal();
		result.setTotal(total);
		return result;
	}

	@Override
	public E3Result deleteParam(String ids) {
		String[] idsArr=ids.split(",");
		for(int i=0;i<idsArr.length;i++){
			long idLong=Long.parseLong(idsArr[i]);
			itemParamMapper.deleteByPrimaryKey(idLong);
		}
		return E3Result.ok();
	}
	/**
	 * 查询添加失败
	 */
	@Override
	public E3Result addQueryParam(Long cid) {
		TbItemParamExample example=new TbItemParamExample();
		Criteria criteria = example.createCriteria();
		criteria.andItemCatIdEqualTo(cid);
		List<TbItemParam> list = itemParamMapper.selectByExample(example);
		if(list!=null&&list.size()>0){
			return E3Result.ok();
		}
		return null;
	}
	/**
	 * 添加成功方法
	 */
	@Override
	public E3Result addParam(String cid, String paramData) {
		TbItemParam itemParam=new TbItemParam();
		long cidLong=Long.parseLong(cid);
		itemParam.setItemCatId(cidLong);
		itemParam.setParamData(paramData);
		itemParam.setUpdated(new Date());
		itemParam.setCreated(new Date());
		long itemParamId = IDUtils.genItemId();
		itemParam.setId(itemParamId);
		itemParamMapper.insert(itemParam);
		return E3Result.ok();
	}

}

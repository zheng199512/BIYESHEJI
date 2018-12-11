package cn.e3mall.service;

import java.util.List;

import cn.e3mall.common.pojo.EasyUIDataGridResult;
import cn.e3mall.common.pojo.EasyUITreeNode;
import cn.e3mall.common.utils.E3Result;

public interface ParamService {
	EasyUIDataGridResult getParamlist(int page,int rows);

	E3Result deleteParam(String ids);

	E3Result addQueryParam(Long cid);

	E3Result addParam(String cid, String paramData);
}

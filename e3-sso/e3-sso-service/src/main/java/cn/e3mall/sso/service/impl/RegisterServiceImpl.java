package cn.e3mall.sso.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import com.mysql.fabric.xmlrpc.base.Data;

import cn.e3mall.common.utils.E3Result;
import cn.e3mall.mapper.TbUserMapper;
import cn.e3mall.pojo.TbUser;
import cn.e3mall.pojo.TbUserExample;
import cn.e3mall.pojo.TbUserExample.Criteria;
import cn.e3mall.sso.service.RegisterService;
@Service
public class RegisterServiceImpl implements RegisterService {
	@Autowired
	private TbUserMapper userMapper;

	@Override
	public E3Result checkData(String param, int type) {

		TbUserExample example=new TbUserExample();
		Criteria criteria = example.createCriteria();
		if(type==1){
			criteria.andUsernameEqualTo(param);
		}else if(type==2){
			criteria.andPhoneEqualTo(param);
		}else if(type==3){
			criteria.andEmailEqualTo(param);
		}else{
			return E3Result.build(400, "数据不符合！");
		}
		List<TbUser> list = userMapper.selectByExample(example);
		if(list!=null&&list.size()>0){
			return E3Result.ok(false);
		}
		return E3Result.ok(true);
	}

	@Override
	public E3Result register(TbUser user) {
		if("".equals(user.getUsername())){
			return E3Result.build(400, "用户名不能为空！");
		}
		if("".equals(user.getPassword())){
			return E3Result.build(400, "密码不能为空");
		}
		if("".equals(user.getPhone())){
			return E3Result.build(400, "手机号不能为空");
		}
		
		E3Result result=checkData(user.getUsername(), 1);
		if(!(boolean) result.getData()){
			return E3Result.build(400, "用户名已经被占用！");
		}
		E3Result result2=checkData(user.getPhone(), 2);
		if (!(boolean) result2.getData()) {
			return E3Result.build(400, "手机号已经被占用！");
		}
		
		user.setCreated(new Date());
		user.setUpdated(new Date());
		String md5 = DigestUtils.md5DigestAsHex(user.getPassword().getBytes());
		user.setPassword(md5);
		userMapper.insert(user);
		return E3Result.ok();
	}

}

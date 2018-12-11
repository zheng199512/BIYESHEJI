package cn.e3mall.sso.service;

import cn.e3mall.common.utils.E3Result;

public interface TokenService {
	//通过token获取用户的信息
	E3Result getUserByToken(String token);
}

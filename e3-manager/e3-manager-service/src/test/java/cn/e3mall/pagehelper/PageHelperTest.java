package cn.e3mall.pagehelper;


import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.e3mall.mapper.TbItemMapper;
import cn.e3mall.pojo.TbItem;
import cn.e3mall.pojo.TbItemExample;

public class PageHelperTest {
	@Test
	public void testPageHelper() throws Exception{
		ApplicationContext applicationContext=new ClassPathXmlApplicationContext("classpath:spring/applicationContext-*.xml");
		TbItemMapper itemMapper=applicationContext.getBean(TbItemMapper.class);
		PageHelper.startPage(1,30);
		TbItemExample example=new TbItemExample();
		List<TbItem> list=itemMapper.selectByExample(example);
		PageInfo<TbItem> pageInfo=new PageInfo<>(list);
		System.out.println(pageInfo.getTotal());
		System.out.println(pageInfo.getPages());
		System.out.println(pageInfo.getPageNum());
		System.out.println(pageInfo.getPageSize());
	}
	
}

package cn.e3mall.search.service.impl;

import java.util.List;

import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.e3mall.common.pojo.SearchItem;
import cn.e3mall.common.utils.E3Result;
import cn.e3mall.search.mapper.*;
import cn.e3mall.search.service.SearchItemService;
/**
 * 将商品添加到索引库
 * @author 郑小飞
 *
 */
@Service
public class SearchItemServiceImpl implements SearchItemService {

	@Autowired
	private ItemMapper itemMapper;
	@Autowired
	private SolrServer solrServer;
	
	
	@Override
	public E3Result importItems() {
		try {
			List<SearchItem> itemList=itemMapper.getItemList();
			for (SearchItem searchItem : itemList) {
				//创建文档对象，向文档中添加域
				SolrInputDocument document=new SolrInputDocument();
				document.addField("id", searchItem.getId());
				document.addField("item_title", searchItem.getTitle());
				document.addField("item_sell_point", searchItem.getSell_point());
				document.addField("item_price", searchItem.getPrice());
				document.addField("item_image", searchItem.getImage());
				document.addField("item_category_name", searchItem.getCategory_name());
				solrServer.add(document);
				
			}
			solrServer.commit();
			return E3Result.ok();
			
		} catch (Exception e) {
			e.printStackTrace();
			return E3Result.build(500, "商品导入失败！");
		}

	}

}

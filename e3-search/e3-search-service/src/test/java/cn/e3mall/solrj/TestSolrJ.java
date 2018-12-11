package cn.e3mall.solrj;

import java.util.List;
import java.util.Map;

import javax.swing.text.Document;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.junit.Test;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.filter.HiddenHttpMethodFilter;

public class TestSolrJ {
	@Test
	public void addDocument() throws Exception{
		SolrServer solrServer=new HttpSolrServer("http://192.168.25.129:8080/solr");
		SolrInputDocument document=new SolrInputDocument();
		document.addField("id","doc1");
		document.addField("item_title","shangpin");
		document.addField("title", "手机阿斯达");
		solrServer.add(document);
		solrServer.commit();
	}
	@Test
	public void deleteDocument() throws Exception{
		SolrServer solrServer=new HttpSolrServer("http://192.168.25.129:8080/solr");
		solrServer.deleteById("doc1");
		solrServer.commit();
	}
	@Test
	public void deleteDocumentByQuery() throws Exception{
		SolrServer solrServer=new HttpSolrServer("http://192.168.25.129:8080/solr");
		solrServer.deleteByQuery("title:手机阿斯达");
		solrServer.commit();
	}
	@Test
	public void queryDocument() throws Exception{
		//创建solrserver对象
		SolrServer solrServer=new HttpSolrServer("http://192.168.25.129:8080/solr");
		//创建query对象
		SolrQuery query=new SolrQuery();
		//查询条件
		query.setQuery("*:*");
		//执行查询结果，得到response对象
		QueryResponse response=solrServer.query(query);
		//取结果
		SolrDocumentList solrDocumentList = response.getResults();
		System.out.println("查询结果总记录数"+solrDocumentList.getNumFound());
		for (SolrDocument solrDocument : solrDocumentList) {
			System.out.println(solrDocument.get("id"));
			System.out.println(solrDocument.get("item_title"));
			System.out.println(solrDocument.get("item_price"));
		}
	}
	@Test
	public void queryDocumentWithHingLighting() throws Exception{
		SolrServer solrServer=new HttpSolrServer("http://192.168.25.129:8080/solr");
		SolrQuery query=new SolrQuery();
		query.setQuery("老人");
		query.set("df", "item_title");
		query.setHighlight(true);
		query.addHighlightField("item_title");
		query.setHighlightSimplePre("<em>");
		query.setHighlightSimplePost("</em>");
		
		QueryResponse response=solrServer.query(query);
		SolrDocumentList solrDocumentList = response.getResults();
		System.out.println(solrDocumentList.getNumFound());
		for (SolrDocument solrDocument : solrDocumentList) {
			System.out.println(solrDocument.get("id"));
			Map<String, Map<String, List<String>>> highlighting = response.getHighlighting();
			List<String> list = highlighting.get(solrDocument.get("id")).get("item_title");
			String item="";
			if (list!=null&&list.size()>0) {
				item=list.get(0);
			}else{
				item=(String) solrDocument.get("item_title");
			}
			System.out.println(item);
			System.out.println(solrDocument.get("item_price"));
			System.out.println(solrDocument.get("item_image"));
			System.out.println(solrDocument.get("item_category_name"));
		}
				
	}
	
	
	
}

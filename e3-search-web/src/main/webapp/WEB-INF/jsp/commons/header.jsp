<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!--shortcut start-->
<jsp:include page="shortcut.jsp" />
<!--shortcut end-->
<div id="header">
  <div class="header_inner">
    <div class="logo">
			<a name="sfbest_hp_hp_head_logo" href="http://www.e3mall.cn" class="trackref logoleft">
		</a>
			<div class="logo-text">
				<img src="/images/html/logo_word.jpg">
			</div>
		</div>
    <div class="index_promo"></div>
    <div class="search">
      <form action="http://localhost:8085/search.html" id="searchForm" name="query" method="GET">
        <input type="text" class="text keyword ac_input" name="keyword" id="keyword" value="${query }" style="color: rgb(153, 153, 153);" onkeydown="javascript:if(event.keyCode==13) search_keys('searchForm');" autocomplete="off">
        <input type="button" value="" class="submit" onclick="search_keys('searchForm')">
      </form>
      <div class="search_hot">      <a target="_blank" href="http://www.e3mall.cn/productlist/search?inputBox=1&amp;keyword=%E5%A4%A7%E9%97%B8%E8%9F%B9#trackref=sfbest_hp_hp_head_Keywords1">数码</a>
      <a target="_blank" href="http://www.e3mall.cn/productlist/search?inputBox=1&amp;keyword=%E7%9F%B3%E6%A6%B4#trackref=sfbest_hp_hp_head_Keywords2">APPLE</a>
      <a target="_blank" href="http://www.e3mall.cn/productlist/search?inputBox=1&amp;keyword=%E6%9D%BE%E8%8C%B8#trackref=sfbest_hp_hp_head_Keywords3">智能家居</a>
      <a target="_blank" href="http://www.e3mall.cn/productlist/search?inputBox=1&amp;keyword=%E7%89%9B%E6%8E%92#trackref=sfbest_hp_hp_head_Keywords4">穿戴设备</a>
      <a target="_blank" href="http://www.e3mall.cn/productlist/search?inputBox=1&amp;keyword=%E7%99%BD%E8%99%BE#trackref=sfbest_hp_hp_head_Keywords5">音箱</a>
      <a target="_blank" href="http://www.e3mall.cn/productlist/search?inputBox=1&amp;keyword=%E5%85%A8%E8%84%82%E7%89%9B%E5%A5%B6#trackref=sfbest_hp_hp_head_Keywords6">VR</a>
      <a target="_blank" href="http://www.e3mall.cn/productlist/search?inputBox=1&amp;keyword=%E6%B4%8B%E6%B2%B3#trackref=sfbest_hp_hp_head_Keywords7">机器人</a>
      <a target="_blank" href="http://www.e3mall.cn/productlist/search?inputBox=1&amp;keyword=%E7%BB%BF%E8%B1%86#trackref=sfbest_hp_hp_head_Keywords8">电脑</a>
      <a target="_blank" href="http://www.e3mall.cn/productlist/search?inputBox=1&amp;keyword=%E4%B8%80%E5%93%81%E7%8E%89#trackref=sfbest_hp_hp_head_Keywords9">手机</a></div>
    </div>
    <div class="shopingcar" id="topCart">
      <s class="setCart"></s><a href="http://cart.e3mall.cn" class="t" rel="nofollow">我的购物车</a><b id="cartNum">0</b>
      <span class="outline"></span>
      <span class="blank"></span>
      <div id="cart_lists">
        <!--cartContent-->   
        <div class="clear"></div>
      </div>
    </div>
  </div>
  <script type="text/javascript">
  	function search_keys(formName){
	   $('#'+formName).submit();
	}
  </script>
</div>
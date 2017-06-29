package com.scm.test;

import java.math.BigDecimal;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.scm.modules.goods.dao.GoodsImportDao;
import com.scm.modules.goods.entity.GoodsImport;

public class TemplateDaoTest extends SpringJunit4Utils{
	
	@Autowired
	private GoodsImportDao dao;

	@Test
	public void testInsert() {
		GoodsImport entity = new GoodsImport();
		entity.setBrandName("欧莱雅");
		entity.setSeriesName("火山泥");
		entity.setCategory("洗面奶");
		entity.setGoodsName("欧莱雅火山泥洗面奶男士专用");
		entity.setModel("A1996");
		entity.setArticleNo("20170620102355");
		entity.setThumbUrl("缩略图");
		entity.setImageUrl("商品图片");
		entity.setUnitId("1");
		entity.setRetailPrice(new BigDecimal(36.5));
		entity.setRemarks("备注");
		entity.setPropertySet("属性集");
		
		dao.insert(entity);
	}
	
	@Test
	public void testUpdate() {
		GoodsImport entity = new GoodsImport("2");
		entity.setBrandName("欧莱雅111");
		entity.setSeriesName("火山泥11");
		entity.setCategory("洗面奶11");
		entity.setGoodsName("欧莱雅火山泥洗面奶男士专用111");
		entity.setModel("A1996ww");
		entity.setArticleNo("20170620102355ww");
		entity.setThumbUrl("缩略图ww");
		entity.setImageUrl("商品图片ww");
		entity.setUnitId("12");
		entity.setRetailPrice(new BigDecimal(46.5));
		entity.setRemarks("备注11");
		entity.setPropertySet("属性集11");
		
		dao.update(entity);
	}
	
	@Test
	public void testDelete() {
		dao.delete(new GoodsImport("2"));
	}
	
	@Test
	public void testFind() {
		List<GoodsImport> list = dao.findList(new GoodsImport());
		System.out.println(list);
	}

}

//package com.eastorm.core.base;
//
//import com.eastorm.api.common.domain.CommType;
//import com.eastorm.api.common.domain.NodeLink;
//import com.eastorm.api.common.service.NodeLinkService;
//import junit.framework.Assert;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.data.domain.Pageable;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//
//import java.util.HashMap;
//import java.util.List;
//
///**
// * <一句话功能简述>
// * <功能详细描述>
// *
// * @auth:江东大人
// * @see: [相关类/方法]（可选）
// * @since [产品/模块版本] （可选）
// */
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations={"classpath:spring/testApplicationContext.xml"})
//public class NodeLinkServiceTest {
//    @Autowired
//    private NodeLinkService nodeLinkService;
//
//    @Test
//    public void findOne(){
//        Integer dictItem=nodeLinkService.qryListCountByParentId(92);
//        Assert.assertTrue(dictItem>0);
//    }
//
//    @Test
//    public void qryListByParentId(){
//        List<CommType> dictItem=nodeLinkService.qryListByParentId(92);
//        Assert.assertTrue(dictItem.size()>0);
//    }
//
//    @Test
//    public void queryPageByParentId(){
//        Pageable page=new PageRequest(1,10);
//        Page<CommType> dictItem=nodeLinkService.queryPageByParentId(page,"order by m.id desc",8,new HashMap());
//        Assert.assertTrue(dictItem.getSize()>0);
//    }
//
//    @Test
//    public void save(){
//        NodeLink nk=new NodeLink();
//        nk.setNodeId(22);
//        nk.setParentId(333);
//        NodeLink dictItem=nodeLinkService.save(nk);
//        Assert.assertTrue(dictItem.getId()!=null);
//    }
//}

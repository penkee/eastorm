//package com.eastorm.core.base;
//
//import com.eastorm.api.common.domain.DictItem;
//import com.eastorm.api.common.service.DictItemService;
//import junit.framework.Assert;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
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
//public class DictItemServiceTest {
//    @Autowired
//    private DictItemService dictItemService;
//
//    @Test
//    public void findOne(){
//        DictItem dictItem=dictItemService.findOne(3014);
//        Assert.assertNotNull(dictItem);
//    }
//    @Test
//    public void delete(){
//        dictItemService.delete(15156516);
//    }
//}

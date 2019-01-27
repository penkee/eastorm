package com.eastorm.core.member;

import com.eastorm.api.member.domain.SysRole;
import com.eastorm.api.member.service.SysMemberService;
import junit.framework.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * <一句话功能简述>
 * <功能详细描述>
 *
 * @auth:江东大人
 * @see: [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring/testApplicationContext.xml"})
public class MemberServiceTest {
    @Autowired
    private SysMemberService sysMemberService;

    @Test
    public void queryRole(){
        List<SysRole> dictItem=sysMemberService.queryRole(2);
        Assert.assertNotNull(dictItem);
    }
}

package jl.mybatis.test.mapper;

import jl.mybatis.MyBatisApplication;
import jl.mybatis.mapper.MybatisUserMapper;
import jl.mybatis.po.MybatisUser;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * MyBatisUserMapperTest
 *
 * @author Liu Chang
 * @date 2020/5/20
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = MyBatisApplication.class)
public class MyBatisUserMapperTest {

    @Resource
    private MybatisUserMapper mapper;

    @Test
    public void testMapperGet() {
        MybatisUser user = mapper.getByPrimaryKey(2L);
        System.out.println(user);
    }

    @Test
    public void testMapperSave() {
        MybatisUser user = new MybatisUser();
        user.setIdCard("371111111111111111");
        user.setMobile("13222222222");
        mapper.saveSelective(user);
    }

}

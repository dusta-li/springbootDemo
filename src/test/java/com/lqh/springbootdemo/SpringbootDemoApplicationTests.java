package com.lqh.springbootdemo;

import com.lqh.springbootdemo.model.AyMood;
import com.lqh.springbootdemo.model.AyUser;
import com.lqh.springbootdemo.producer.AyMoodProducer;
import com.lqh.springbootdemo.service.AyMoodService;
import com.lqh.springbootdemo.service.AyUserService;
import org.apache.activemq.command.ActiveMQQueue;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.annotation.Resource;
import javax.jms.Destination;
import java.io.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SpringBootTest
class SpringbootDemoApplicationTests {
    @Resource
    private JdbcTemplate jdbcTemplate;

    @Resource
    private AyUserService ayUserService;

    @Resource
    private RedisTemplate redisTemplate;

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Resource
    private AyMoodService ayMoodService;

    @Resource
    private AyMoodProducer ayMoodProducer;

    @Test
    void contextLoads() {
    }

    @Test
    public void mysqlTest(){
        String sql = "select id,name,password from ay_user";

        List<AyUser> userList = jdbcTemplate.query(sql, new RowMapper<AyUser>() {
            @Override
            public AyUser mapRow(ResultSet rs, int i) throws SQLException {
                AyUser user = new AyUser();
                user.setId(rs.getString("id"));
                user.setName(rs.getString("name"));
                user.setPassword(rs.getString("password"));
                return user;
            }
        });

        System.out.println("查询成功！");
        for (AyUser user: userList) {
            System.out.println("[id]:"+user.getId()+" [name]:"+user.getName()+" [password]:"+user.getPassword());
        }
    }

    @Test
    public void testRepository(){
        //查询所有数据
        List<AyUser> userList = ayUserService.findAll();
        System.out.println("findAll():"+userList.size());
        //通过name查询数据
        List<AyUser> userList2 = ayUserService.findByName("阿毅");
        System.out.println("findByName():"+userList2.size());
    }

    @Test
    public void testRedis(){
        redisTemplate.opsForValue().set("name","lqh");
        String name = (String) redisTemplate.opsForValue().get("name");
        System.out.println(name);
    }

    @Test
    public void testMybatis(){
        AyUser ayUser = ayUserService.findByNameAndPassword("阿毅", "123456");
        System.out.println("姓名:"+ayUser.getName());
    }

    @Test
    public void testAyMood(){
        AyMood ayMood = new AyMood();
        ayMood.setId("1");
        ayMood.setUserId("1");
        ayMood.setPraiseNum(0);
        ayMood.setContent("这是第一条微信说说内容");
        ayMood.setPublishTime(new Date());

        ayMoodService.save(ayMood);
    }

    @Test
    public void testActiveMQ(){
        Destination destination = new ActiveMQQueue("ay.queue");
        ayMoodProducer.sendMessage(destination,"hello,mq!!");
    }

    @Test
    public void testActiveMQAsynSave(){
        AyMood ayMood = new AyMood();
        ayMood.setId("2");
        ayMood.setUserId("2");
        ayMood.setPraiseNum(0);
        ayMood.setContent("这是第二条微信说说内容");
        ayMood.setPublishTime(new Date());
        String msg = ayMoodService.asynSave(ayMood);
        System.out.println("异步发表说说："+msg);


    }

    public static void main(String[] args) throws IOException {
        String path = "E:\\test\\test\\test3.txt";
        File file = new File(path);
        if(!file.exists()){
            file.getParentFile().mkdirs();
        }
        file.createNewFile();

        ArrayList<AyUser> ayUsers = new ArrayList<>();
        AyUser ayUser1 = new AyUser();
        ayUser1.setId("001");
        ayUser1.setName("zs");
        ayUser1.setPassword("123456");
        AyUser ayUser2 = new AyUser();
        ayUser2.setId("002");
        ayUser2.setName("ls");
        ayUser2.setPassword("123456");

        ayUsers.add(ayUser1);
        ayUsers.add(ayUser2);


        // write
        FileOutputStream writerStream = new FileOutputStream(file);
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(writerStream,"UTF-8"));

        for (AyUser ayUser:ayUsers) {
            bw.write(""+ayUser.getId()+"&"+ayUser.getName()+"&"+ayUser.getPassword()+"\r\n");
        }
//        bw.write("AAAAAA\r\n");
//        bw.write("BBBBBB\r\n");
        bw.flush();
        bw.close();
        writerStream.close();
    }
}

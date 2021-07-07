package com.lqh.springbootdemo.service;

import com.lqh.springbootdemo.api.GenerateFileService;
import com.lqh.springbootdemo.domain.AyUser;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
@Configurable
@EnableScheduling
public class GenerateFileServiceImpl implements GenerateFileService {

    /**
     * 如何生成二十万数据并且写入txt，且id不重复呢？
     * 10个线程，每个线程2000，跑十次。总共100个文件
     * @param path
     * @param ayUserArrayList
     * @return
     */
    @Override
    public boolean generateTxtAndbatchWriteIn(String path, ArrayList<AyUser> ayUserArrayList) {

//        path = "E:\\test\\test\\test2.txt";
        File file = new File(path);
        if(!file.exists()){
            file.getParentFile().mkdirs();
        }

        try {
            file.createNewFile();

            // write
            FileOutputStream writerStream = new FileOutputStream(file,true);
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(writerStream,"UTF-8"));

            for (AyUser ayUser:ayUserArrayList) {
                bw.write(""+ayUser.getId()+"&"+ayUser.getName()+"&"+ayUser.getPassword()+"\r\n");
            }

            bw.flush();
            bw.close();
            writerStream.close();
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }

        return true;
    }

//    @Scheduled(cron = "* */20 * * * * ")
//    public void runFunction(){
//        execThread();
//    }


    /**
     * 如何生成二十万数据并且写入txt，且id不重复呢？
     * 10个线程，每个线程2000，跑十次。总共100个文件
     */
    public void execThread(){
        ExecutorService executorService = Executors.newFixedThreadPool(10);

        int sum = 0;
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                int startSize = i*20000;
                int endSize = (startSize + ((j+1)*2000-1));

                Runnable runnable = new Runnable() {
                    @Override
                    public void run() {
                        int startInx = startSize;
                        int endInx = endSize;
                        ArrayList<AyUser> ayUsers = new ArrayList<>();

                        for (int k = startInx; k < endInx; k++) {
                            AyUser ayUser = new AyUser();
                            ayUser.setId(""+k);
                            ayUser.setName("zs"+startInx);
                            ayUser.setPassword("123456"+startInx);
                            ayUsers.add(ayUser);
                        }
                        generateTxtAndbatchWriteIn("E:\\test\\test\\test"+startInx+"_"+endInx+".txt",ayUsers);

                    }
                };

                executorService.execute(runnable);
            }
        }
    }

    public static void main(String[] args) {
        GenerateFileServiceImpl generateFileService = new GenerateFileServiceImpl();
        generateFileService.execThread();
    }
}

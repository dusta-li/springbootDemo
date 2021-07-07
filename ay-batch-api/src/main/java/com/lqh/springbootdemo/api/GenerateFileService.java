package com.lqh.springbootdemo.api;

import com.lqh.springbootdemo.domain.AyUser;

import java.util.ArrayList;

public interface GenerateFileService {
    //path路径下生成txt文件并且批量写入
    boolean generateTxtAndbatchWriteIn(String path, ArrayList<AyUser> ayUserArrayList);
}

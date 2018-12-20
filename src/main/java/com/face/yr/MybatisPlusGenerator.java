package com.face.yr;

import com.baomidou.mybatisplus.annotations.IdType;
import com.baomidou.mybatisplus.generator.AutoGeneratorMy;
import com.baomidou.mybatisplus.generator.ConfigGeneratorMy;

import java.io.IOException;

/***
 @ClassName :CustomGenerator
 *@Description :
 *@Author :xujialin
 *@CreationDate :2016年11月4日下午2:29:50
 */

public class MybatisPlusGenerator {
    public static void main(String[] args) throws
            IOException {
        ConfigGeneratorMy cg = new ConfigGeneratorMy();
        // 配置 MySQL 连接
        cg.setDbUrl("jdbc:mysql://127.0.0.1:3306/face?serverTimezone=GMT%2B8&useUnicode=true&characterEncoding=utf-8");
        cg.setDbUser("root");
        cg.setDbPassword("123456");
        cg.setDbDriverName("com.mysql.cj.jdbc.Driver");
        // 配置包名
        String[] table = {"face_user","face_sign","face_class"};
        cg.setTableNames(table);
        cg.setBuliderModel(true);
        cg.setResultMap(true);
        cg.setFileOverride(true);
        cg.setEntityPackage("com.face.yr.domain.po");
        cg.setMapperName("I%sMapper");
        cg.setMapperPackage("com.face.yr.dao");
        cg.setServiceImplName("%sService");
        cg.setServicePackage(null);
        cg.setXmlPackage("com.face.yr.dao");
        cg.setServiceImplPackage("com.face.yr.service");
        cg.setControllerPackage("com.face.yr.web");

        // 配置表主键策略
        cg.setIdType(IdType.AUTO);

        // 配置保存路径
        cg.setSaveDir(System.getProperty("user.dir") + "/src/test/java/");
        // 其他参数请根据上面的参数说明自行配置，当所有配置完善后，运行AutoGenerator.run()方法生成Code
        // 生成代码
        AutoGeneratorMy.run(cg);
    }
}
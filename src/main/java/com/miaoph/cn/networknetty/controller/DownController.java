package com.miaoph.cn.networknetty.controller;


import cn.hutool.core.io.IoUtil;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/down")
public class DownController {

    @RequestMapping("/export")
    @ResponseBody
    public void export(HttpServletResponse response) {

        List<User> list = new ArrayList<>();
        list.add(new User("zhangsan", "1231", new Date()));
        list.add(new User("zhangsan1", "1232", new Date()));
        list.add(new User("zhangsan2", "1233", new Date()));
        list.add(new User("zhangsan3", "1234", new Date()));
        list.add(new User("zhangsan4", "1235", new Date()));
        list.add(new User("zhangsan5", "1236", new Date()));
        // 通过工具类创建writer，默认创建xls格式
        ExcelWriter writer = ExcelUtil.getWriter(true);
        //自定义标题别名
        writer.addHeaderAlias("name", "姓名");
        writer.addHeaderAlias("age", "年龄");
        writer.addHeaderAlias("date", "生日");

//        writer.merge(2, "申请人员信息");
// 一次性写出内容，使用默认样式，强制输出标题
        writer.write(list, true);
//out为OutputStream，需要写出到的目标流
//response为HttpServletResponse对象
        final String name = writer.getDisposition("测试文件名", null);
        response.setContentType("application/vnd.ms-excel;charset=utf-8");
//test.xls是弹出下载对话框的文件名，不能为中文，中文请自行编码
        response.setHeader("Content-Disposition", "attachment;filename=" + name);
        ServletOutputStream out = null;
        try {
            out = response.getOutputStream();
            writer.flush(out, true);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
// 关闭writer，释放内存
            writer.close();
        }
//此处记得关闭输出Servlet流
        IoUtil.close(out);
    }
}

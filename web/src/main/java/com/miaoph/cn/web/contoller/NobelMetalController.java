package com.miaoph.cn.web.contoller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.Random;

@RestController
@RequestMapping("/test")
public class NobelMetalController {

  /**
   * 服务端持续向客户端推送消息
   *
   * @param response
   */
  @RequestMapping("push")
  public void pushRight(HttpServletResponse response) {
    response.setContentType("text/event-stream");
    response.setCharacterEncoding("utf-8");
    final Random r = new Random();
    try {
      final PrintWriter w = response.getWriter();
      int i = 0;
      while (i <= 10) {
        if (w.checkError()) {
          System.out.println("客户端断开连接");
          return;
        }
        Thread.sleep(1000L);
        w.write("这是第" + i + "次刷新");
        w.write("\r\n");
        w.flush();
        i++;
      }
      System.out.println("循环结束.....");
      w.write("data:stop\n\n");
      w.flush();
      w.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}

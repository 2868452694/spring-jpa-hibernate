package cn.agent.controller;

import cn.agent.pojo.Log;
import cn.agent.pojo.Users;
import cn.agent.service.LogService;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
@RequestMapping(value = "/log")
public class LogController {
    @Autowired
    private LogService logService;

    /**
     * 查看日志信息
     * @return
     */
    @RequestMapping(value = "findLog.json")
    @ResponseBody
    public Page<Log> getLogInfo(@Param("pageSum") Integer pageSum, String time, HttpSession session){
        System.out.println("================================time="+time);
        System.out.println("================================pageSum="+pageSum);
        Log log=new Log();
        log.setUsers((Users)session.getAttribute("user"));
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
        Date date= null;
        try {
            if(time!=null){
                date = simpleDateFormat.parse(time);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        log.setLogtime(date);
        int pagesize=10;
        System.out.println(log.getUsers().getRole().getRoleid());
        Page<Log> logs=logService.queryfindPageLogInfo(log,pageSum,pagesize);
        System.out.println(JSON.toJSONString(logs,true));
        return logs;
    }
}

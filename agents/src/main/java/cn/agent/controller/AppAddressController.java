package cn.agent.controller;

import cn.agent.pojo.Appaddress;
import cn.agent.pojo.Log;
import cn.agent.pojo.Servicetime;
import cn.agent.pojo.Users;
import cn.agent.service.AppaddressService;
import cn.agent.service.LogService;
import cn.agent.service.ServicetimeService;
import com.alibaba.fastjson.JSON;
import javafx.scene.media.SubtitleTrack;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.Date;

/**
 * App地址
 */
@Controller
@RequestMapping(value = "/appAddress")
public class AppAddressController {
    @Autowired
    private AppaddressService appaddressService;//APP地址
    @Autowired
    private ServicetimeService servicetimeService;//服务年限
    @Autowired
    private LogService logService;//日志
    /**
     * 查看服务年限
     * @param id
     * @return
     */
    @RequestMapping("/serviceTime.do")
    @ResponseBody
    public Servicetime  servicetime(@RequestParam Long id, HttpSession session){
        System.out.println("服务年限=====================================id="+id);
        Users user=(Users) session.getAttribute("user");
        Log log=new Log();
        log.setUsers(user);
        log.setLoginfo("查看服务年限");
        log.setLogtime(new Date());
        logService.insertLog(log);
        return servicetimeService.findById(id);
    }
    @RequestMapping("/serviceTime.up")
    @ResponseBody
    public Boolean serviceTimeUp(@RequestParam Long stid,@RequestParam String stname,@RequestParam Long stvalue, HttpSession session){
        System.out.println("修改服务年限==================================>");
        System.out.println("stid======================================>"+stid);
        System.out.println("stname======================================>"+stname);
        System.out.println("stValue======================================>"+stvalue);
        Servicetime servicetime=new Servicetime();
        servicetime.setStid(stid);
        servicetime.setStname(stname);
        servicetime.setStvalue(stvalue);

        Users user=(Users) session.getAttribute("user");
        Log log=new Log();
        log.setUsers(user);
        log.setLoginfo("修改服务年限");
        log.setLogtime(new Date());
        logService.insertLog(log);
        return servicetimeService.update(servicetime);
    }
    /**
     * 查看APP地址
     * @param id
     * @return
     */
    @RequestMapping("/appAddress.do")
    @ResponseBody
    public Appaddress  appaddress(@RequestParam long id, HttpSession session){
        System.out.println("APP地址=====================================id="+id);

        Users user=(Users) session.getAttribute("user");
        Log log=new Log();
        log.setUsers(user);
        log.setLoginfo("查看app地址");
        log.setLogtime(new Date());
        logService.insertLog(log);
        return appaddressService.findById(id);
    }
    @RequestMapping("/appAddress.up")
    @ResponseBody
    public Boolean appaddressUp(@RequestParam  Long appid,@RequestParam String configname ,@RequestParam String  configvalue, HttpSession session){
        System.out.println("APP地址==================================>");
        System.out.println("Appid======================================>"+appid);
        System.out.println("Configname======================================>"+configname);
        System.out.println("ConfigValue======================================>"+configvalue);
        Appaddress appaddress=new Appaddress();
        appaddress.setAppid(appid);
        appaddress.setConfigname(configname);
        appaddress.setConfigvalue(configvalue);

        Users user=(Users) session.getAttribute("user");
        Log log=new Log();
        log.setUsers(user);
        log.setLoginfo("修改app地址");
        log.setLogtime(new Date());
        logService.insertLog(log);
        return appaddressService.update(appaddress);
    }
}

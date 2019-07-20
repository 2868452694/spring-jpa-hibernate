package cn.agent.controller;

import cn.agent.pojo.Client;
import cn.agent.pojo.Log;
import cn.agent.pojo.Users;
import cn.agent.service.ClientService;
import cn.agent.service.LogService;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.Date;

/**
 * 客户
 */
@Controller
@RequestMapping(value = "/client")
public class ClientController {

    @Autowired
    private
    ClientService clientService;
    @Autowired
    private
    LogService logService;

    /**
     * //添加客户
     * @param session
     * @param client
     * @return
     */
   @ResponseBody
   @RequestMapping(value = {"/insertClient","/add"})
    public Object insertClient(HttpSession session,Client client){
        //参数监测
        Client result=clientService.insert( client );
        logService.insertLog( new Log( (Users) session.getAttribute( "user" ),"添加客户:"+client.getClientid() ,new Date(  )) );
        return JSON.parseObject( "{\"insertResult\":"+JSON.toJSONString( result )+"}" );
    }
    /**
     * //修改客户
     * @param session
     * @param client
     * @return
     */
    @ResponseBody
    @RequestMapping(value = {"/updateClient","/update"})
    public Object updateClient(HttpSession session,Client client){
        //参数监测
        client=clientService.update(client);
        logService.insertLog( new Log( (Users) session.getAttribute( "user" ),"修改客户:"+client.getClientid() ,new Date(  )) );
        return JSON.parseObject( "{\"updateResult\":\""+client+"\"}" );
    }
}

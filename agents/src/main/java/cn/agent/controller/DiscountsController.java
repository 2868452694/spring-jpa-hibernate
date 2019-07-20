package cn.agent.controller;

import cn.agent.pojo.Log;
import cn.agent.pojo.Types;
import cn.agent.pojo.Users;
import cn.agent.service.LogService;
import cn.agent.service.TypesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

/**
 * 优惠类型
 */
@Controller
@RequestMapping(value = "/discounts")
public class DiscountsController {
    @Autowired
    private TypesService typesService;
    @Autowired
    private LogService logService;//日志
    /**
     *查询优惠类型信息
     * @return
     */
    @RequestMapping(value = "/financeType.json")
    @ResponseBody
    public List<Types> findTypes(@Param("typeParentId") Long typeParentId, HttpSession session){
        List<Types> list=typesService.findTypesByParentid(typeParentId);
        /*  System.out.println(JSON.toJSONString(list,true));*/
        Users user=(Users) session.getAttribute("user");
        Log log=new Log();
        log.setUsers(user);
        log.setLoginfo("查看优惠类型");
        log.setLogtime(new Date());
        logService.insertLog(log);
        return list;
    }
    /**
     * 添加财务类型
     * @param typeName
     * @param status
     * @param session
     * @return
     */
    @RequestMapping(value="/financeType.do")
    @ResponseBody
    public boolean  saveFindTypes(@RequestParam String typeName, @RequestParam  Long status,@RequestParam Long typeValue,@RequestParam Long typeNum, HttpSession session){
        System.out.println("优惠类型================================================>");
        System.out.println("status======================================================>"+typeName);
        System.out.println("status======================================================>"+status);
        System.out.println("status======================================================>"+typeValue);
        System.out.println("status======================================================>"+typeNum);
        Long parent=28l;
        Types types=new Types();
        types.setParentid(parent);
        types.setTypename(typeName);
        types.setTypevalue(typeValue);
        types.setActualvalue(typeNum);
        types.setStatus(status);
        types.setExist(0l);
        boolean  find= typesService.insert(types);
        if(find==true){
            Users user=(Users) session.getAttribute("user");
            Log log=new Log();
            log.setUsers(user);
            log.setLoginfo("成功添加优惠类型");
            log.setLogtime(new Date());
            logService.insertLog(log);
        }else{
            Users user=(Users) session.getAttribute("user");
            Log log=new Log();
            log.setUsers(user);
            log.setLoginfo("添加优惠类型发生延迟");
            log.setLogtime(new Date());
            logService.insertLog(log);
        }
        return find;
    }

    /**
     * 修改财务类型
     * @param typeName
     * @param status
     * @param session
     * @return
     */
    @RequestMapping(value="/financeType.up")
    @ResponseBody
    public boolean  upFindTypes(@RequestParam String typeName,@RequestParam  Long status,@RequestParam Long typeValue,@RequestParam Long typeNum, @RequestParam Long typeId, HttpSession session){
        System.out.println("优惠类型================================================>");
        System.out.println("status======================================================>"+typeName);
        System.out.println("status======================================================>"+status);
        System.out.println("status======================================================>"+typeValue);
        System.out.println("status======================================================>"+typeNum);
        Long parent=28l;
        Types types=new Types();
        types.setTypeid(typeId);
        types.setParentid(parent);
        types.setTypename(typeName);
        types.setTypevalue(typeValue);
        types.setActualvalue(typeNum);
        types.setStatus(status);
        types.setExist(0l);
        boolean  find= typesService.update(types);
        if(find==true){
            Users user=(Users) session.getAttribute("user");
            Log log=new Log();
            log.setUsers(user);
            log.setLoginfo("成功修改优惠类型");
            log.setLogtime(new Date());
            logService.insertLog(log);
        }else{
            Users user=(Users) session.getAttribute("user");
            Log log=new Log();
            log.setUsers(user);
            log.setLoginfo("修改优惠类型发生延迟");
            log.setLogtime(new Date());
            logService.insertLog(log);
        }
        return find;
    }

    /**
     * 根据id删除服务类型
     * @param id
     * @param session
     * @return
     */
    @RequestMapping(value="/financeType.del")
    @ResponseBody
    public  boolean del(@RequestParam Long id,HttpSession session){
        System.out.println("优惠类型删除操作============================================>");
        System.out.println("id====================================="+id);
        Types types=typesService.findById(id);
        if(types!=null){
            boolean find= typesService.delete(types);
            Users user=(Users) session.getAttribute("user");
            Log log=new Log();
            log.setUsers(user);
            log.setLoginfo("删除优惠类型");
            log.setLogtime(new Date());
            logService.insertLog(log);

            return find;
        }else{
            Users user=(Users) session.getAttribute("user");
            Log log=new Log();
            log.setUsers(user);
            log.setLoginfo("删除优惠类型");
            log.setLogtime(new Date());
            logService.insertLog(log);
            return true;
        }

    }
}

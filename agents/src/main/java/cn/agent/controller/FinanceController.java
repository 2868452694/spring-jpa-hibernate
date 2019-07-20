package cn.agent.controller;

import cn.agent.pojo.Finance;
import cn.agent.pojo.Log;
import cn.agent.pojo.Types;
import cn.agent.pojo.Users;
import cn.agent.service.FinanceService;
import cn.agent.service.LogService;
import cn.agent.service.TypesService;
import cn.agent.service.UsersService;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.lang.reflect.Type;
import java.util.Date;
import java.util.List;

/**
 * 财务管理
 */
@Controller
@RequestMapping(value = "/finance")
public class FinanceController {

    @Autowired
    private TypesService typesService;
    @Autowired
    private LogService logService;//日志

    @Autowired
    private FinanceService financeService;
    @Autowired
    private
    UsersService usersService;
    /**
     *查询财务类型信息
     * @return
     */
    @RequestMapping(value = {"inserFinancial"})
    @ResponseBody
    public Object inserFinancial(Long userid,Long typeid,String remarks,Double money, HttpSession session) {
        System.out.println( "userid======================================================>" + userid );
        System.out.println( "typeid======================================================>" + typeid );
        System.out.println( "remarks======================================================>" + remarks );
        System.out.println( "money======================================================>" + money );

        Users user = (Users) session.getAttribute( "user" );
        if((user.getBalance()-money)<0){
            return "账户余额不足";
        }
        System.out.println("userMoney:"+(user.getBalance()-money));
        user.setBalance(user.getBalance()-money);
        Users user1=null;
        try {
            user1 = usersService.findById( userid );
        } catch (NullPointerException e) {
            return "用户不存在";
        }
        if((user1.getBalance()+money)<0){
            return "对方账户余额不足";
        }
        System.out.println("userMoney:"+(user1.getBalance()+money));
        user1.setBalance( user1.getBalance()+money);
        System.out.println( 1 + -2 );
        Finance finance = new Finance();
        finance.setBalance( user.getBalance() );
        finance.setUserid( user1.getUserid() );
        finance.setDescription( remarks );
        finance.setCreatetime( new Date() );
        finance.setOperationmoney(money  );
        try {
            usersService.update( user );
            usersService.update( user1 );
            finance.setFinatype( typesService.findById( typeid ).getTypeid() );
        } catch (NullPointerException e) {
            return "类型不存在";
        }
        boolean bool = financeService.insert( finance );
        if (bool) {
            logService.insertLog( new Log( user, "添加财务明细成功,用户"+user.getUserid(  )+":"+user.getUsername()+"款项增加"+money, new Date() ) );
            return "添加成功";
        } else {
            logService.insertLog( new Log( user, "添加财务明细失败,用户"+user.getUserid(  )+":"+user.getUsername()+"款项增加"+money, new Date() ) );
            return "添加失败,数据缺失";
        }
    }


    /**
     *查询财务类型信息
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
        log.setLoginfo("查看财务类型");
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
    @RequestMapping(value="/saveFinaceType.do")
    @ResponseBody
    public boolean  saveFindTypes(@RequestParam String typeName,@RequestParam  Long status, HttpSession session){
        System.out.println("财务类型================================================>");
        System.out.println("status======================================================>"+typeName);
        System.out.println("status======================================================>"+status);
       Long parent=27l;
        Types types=new Types();
       types.setParentid(parent);
       types.setTypename(typeName);
       types.setStatus(status);
       types.setExist(0l);
       boolean  find= typesService.insert(types);
       if(find==true){
           Users user=(Users) session.getAttribute("user");
           Log log=new Log();
           log.setUsers(user);
           log.setLoginfo("成功添加财务类型");
           log.setLogtime(new Date());
           logService.insertLog(log);
       }else{
           Users user=(Users) session.getAttribute("user");
           Log log=new Log();
           log.setUsers(user);
           log.setLoginfo("添加财务类型发生延迟");
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
    @RequestMapping(value="/saveFinaceType.up")
    @ResponseBody
    public boolean  upFindTypes(@RequestParam String typeName,@RequestParam  Long status,@RequestParam Long typeId, HttpSession session){
        System.out.println("财务类型================================================>");
        System.out.println("status======================================================>"+typeName);
        System.out.println("status======================================================>"+status);
        Long parent=27l;
        Types types=new Types();
        types.setTypeid(typeId);
        types.setParentid(parent);
        types.setTypename(typeName);
        types.setStatus(status);
        types.setExist(0l);
        boolean  find= typesService.update(types);
        if(find==true){
            Users user=(Users) session.getAttribute("user");
            Log log=new Log();
            log.setUsers(user);
            log.setLoginfo("成功修改财务类型");
            log.setLogtime(new Date());
            logService.insertLog(log);
        }else{
            Users user=(Users) session.getAttribute("user");
            Log log=new Log();
            log.setUsers(user);
            log.setLoginfo("修改财务类型发生延迟");
            log.setLogtime(new Date());
            logService.insertLog(log);
        }
        return find;
    }

    /**
     * 根据id删除财务类型
     * @param id
     * @param session
     * @return
     */
    @RequestMapping(value="/saveFinaceType.del")
    @ResponseBody
    public  boolean del(@RequestParam Long id,HttpSession session){
        System.out.println("财务类型删除操作============================================>");
        System.out.println("id====================================="+id);
        Types types=typesService.findById(id);
        if(types!=null){
          boolean find= typesService.delete(types);
            Users user=(Users) session.getAttribute("user");
            Log log=new Log();
            log.setUsers(user);
            log.setLoginfo("删除财务类型");
            log.setLogtime(new Date());
            logService.insertLog(log);

          return find;
        }else{
            Users user=(Users) session.getAttribute("user");
            Log log=new Log();
            log.setUsers(user);
            log.setLoginfo("删除财务类型");
            log.setLogtime(new Date());
            logService.insertLog(log);
            return true;
        }

    }
}

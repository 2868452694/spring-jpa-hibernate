package cn.agent.controller;

import cn.agent.pojo.Finance;
import cn.agent.pojo.Log;
import cn.agent.pojo.Role;
import cn.agent.pojo.Users;
import cn.agent.service.FinanceService;
import cn.agent.service.LogService;
import cn.agent.service.UsersService;
import cn.agent.util.DateUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.swing.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 用户
 */
@Controller
@RequestMapping(value = "/user")
public class UserController {
    //创建service层对象
    @Autowired
    private UsersService usersService;
    @Autowired
    private FinanceService financeService;
    @Autowired
    private LogService logService;
    //创建log4j
    private Logger logger=Logger.getLogger(UserController.class);

    /**
     * 进入登录页面
     * @return
     */
    @RequestMapping(value = "/login")
    public String Login(){
        return "login";
    }

    /**
     * 实现用户登录
     * @return
     */
    @RequestMapping(value = "/doLogin.json")
    @ResponseBody
    public String doLogin(@Param("username") String username, @Param("password") String password, HttpSession session){
        logger.debug("===========================name"+username);
        logger.debug("===========================password"+password);
        //调用service层方法，根据用户名查询
        Users users=usersService.findUsersByUsername(username);
        String falg=null;
        if(users!=null){ //如果返回的对象不为空，则判断密码是否正确
            if(password==users.getPassword()||password.equals(users.getPassword())){ //如果密码正确，则将用户信息保存到session
                    session.setAttribute("user",users);
                    Users user=(Users) session.getAttribute("user");
                    falg="success";
                    //进行日志记录
                    Log log=new Log();
                    log.setUsers(user);
                    log.setLoginfo("用户进行登录，操作成功");
                    log.setLogtime(new Date());
                    logService.insertLog(log);
            }else{
                falg="error";
            }
        }else{ //如果返回对象为空，则用户名不正确
            falg="failed";
        }
            return falg;
    }

    /**
     * 用户退出，将信息从session中移除
     * @param request
     * @return
     */
    @RequestMapping(value = "/logout.json")
    @ResponseBody
    public String logout(HttpServletRequest request){
        HttpSession session=request.getSession(); //创建session对象
        session.removeAttribute("user");
        return JSON.toJSONString("true");
    }

    /**
     * 修改用户密码
     * @param pwd
     * @param session
     * @return
     */
    @RequestMapping(value = "/updatePwd.json")
    @ResponseBody
    public String updatePwd(@Param("pwd") String pwd,HttpSession session){
        //通过id查询该用户的信息
        Users users=usersService.findById(((Users)session.getAttribute("user")).getUserid());
        //通过set将要修改的值加入
        users.setPassword(pwd);
        //调用service层修改方法
        boolean falg=usersService.update(users);
        if(falg){
            return JSON.toJSONString("true");
        }else{
            return JSON.toJSONString("false");
        }
    }

    /**
     * 查询账户明细
     * @param createTime1
     * @param createTime2
     * @param pageSum
     * @return
     */
    @RequestMapping(value="/UsersDetail")
    @ResponseBody
    public Page<Finance> getUsersDetail(@Param("finatype")Long finatype,@Param("createTime1")String createTime1,@Param("createTime2") String createTime2,@Param("pageSum") int pageSum,HttpSession session){
        System.out.println("===================================createTime1="+createTime1);
        System.out.println("===================================createTime2="+createTime2);
        System.out.println("========================================pageSum="+pageSum);
        System.out.println("==========================================finatype="+finatype);
        Long userid=((Users)session.getAttribute("user")).getUserid();
        int pageSize=5;
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
        Date time1= null;
        Date time2=null;
        try {
            if(createTime1!=null || createTime1!=""){
                time1 = simpleDateFormat.parse(createTime1);
            }
            if(createTime2!=null || createTime2!=""){
                time2=simpleDateFormat.parse(createTime2);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Page<Finance> finances=financeService.queryFinanceByCreatetimeBetween(time1,time2,userid,finatype,pageSum,pageSize);
       /* System.out.println(JSON.toJSONString(finances,true));*/
        return finances;
    }

    /**
     * 查看用户资料
     * @return
     */
    @RequestMapping(value = "/userInfo")
    @ResponseBody
    public Users findUserInfo(HttpSession session){
        Users users=usersService.findById(((Users)session.getAttribute("user")).getUserid());
        System.out.println(users.getRole().getRolename());
        return users;
    }

    /**
     * 查询所有的用户
     * @return
     */
    @RequestMapping(value = "/userInfo.json")
    @ResponseBody
    public String findAllUserInfo(@Param("username") String username,@Param("roleId") Long roleId,@Param("enable") Integer enable,@Param("pageSum")Integer pageSum){
        System.out.println("=================================username="+username);
        System.out.println("=================================roleId="+roleId);
        System.out.println("=================================enable="+enable);
        System.out.println("=================================pageSum="+pageSum);
        int pageSize=10;
        Users users=new Users();
        users.setUsername(username);
        Role role=new Role();
        role.setRoleid(roleId);
        users.setRole(role);
        users.setEnable(enable);
        Page<Users> users1=usersService.findPageUsers(users,pageSum,pageSize);
        System.out.println(JSON.toJSONString(users1,true));
        return JSON.toJSONString(users1);
    }
    /**
     * 查询所有的用户
     * @return
     */
    @RequestMapping(value = {"/getuserByusername"})
    @ResponseBody
    public Object getuserByusername(String username){
        List<Users> users1=usersService.findUserListByUsername( username );
        System.out.println(JSON.toJSONString(users1,true));
        return users1;
    }

}

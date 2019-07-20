package cn.agent.controller;

import cn.agent.pojo.*;
import cn.agent.service.*;
import cn.agent.util.DateUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.*;

/**
 * 关键字
 */
@Controller
@RequestMapping(value = "/keyword")
public
class KeywordController {
    @Autowired
    private KeywordService keywordService;
    @Autowired
    private UsersService usersService;
    @Autowired
    private ServicetimeService servicetimeService;
    @Autowired
    private TypesService typesService;
    @Autowired
    private LogService logService;
    @Autowired
    private ClientService clientService;
    @Autowired
    private FinanceService financeService;

    /**
     * 查询关键字和信息
     * @param kwname 关键字的名称
     * @param pageSum 页数
     * @return
     */
    @ResponseBody
    @RequestMapping("/selKeywordByPageAndName")
    public
    Object selKeywordByPageAndName(String kwname, Integer pageSum) {
        Map<String,Object> map=new HashMap<String,Object>(  );
        Page<Keyword> keywordPage = keywordService.findPageKeyword( kwname, pageSum==null?0:pageSum );
        //查出客户
        Object[] os=new Object[keywordPage.getContent().size()];
        for (int i=0;i<keywordPage.getContent().size();i++){
            os[i]=clientService.findById( keywordPage.getContent().get( i ).getClientid() );
        }
        map.put( "keywordPage",keywordPage );
        map.put( "clients",os );
        //查出关键字对应的用户
        return  map;
    }

    /**
     * 开通APP
     * @param session
     * @param username
     * @param password
     * @param keywordId
     * @return
     */
    @ResponseBody
    @RequestMapping("/openApp")
    public Object openApp(HttpSession session, String username, String password,Long keywordId){
        Users user=(Users)session.getAttribute( "user" );//获取当前登录的用户
        //如果登录名和密码一致则将当前APP开通并扣费记录日志
        if(!user.getUsername().equals( username )||!user.getPassword().equals( password )){
            return JSON.parseObject( "{\"error\":\"0\",\"message\":\"用户名或密码不正确\"}" );//用户名和密码不正确//返回错误
        }
        //获取关键字
        Keyword keyword=keywordService.findById( keywordId );
        //获取服务类型
        Types types=typesService.findById( 7l );
        Client client=clientService.findById( keyword.getClientid() );
        String result="开通成功(等待核审中)";
        //添加预付款信息
        if(user.getBalance()>=types.getTypevalue()*3){
            keyword.setUsers( user );
            //申请年限
            keyword.setApplylimit(3l);
            //到期时间
            keyword.setLasttime( DateUtil.addYear( new Date(  ),3l ) );
            //申请到期状态
            keyword.setStatus(0l);//
            //核审状态
            keyword.setAuditstatus(0l);
            //使用状态
            //app开通状态
            keyword.setAppStatus( 2l );
            //设置服务类型
            keyword.setTypes( types );//设置为上传到苹果商城
            //设置年限
            keyword.setApplylimit(3l);
            keyword.setApplytime( new Date(  ) );
            keyword.setLasttime( DateUtil.addYear( new Date(  ),3l) );//获取当前时间3年后的时间
            user.setBalance((double)( types.getTypevalue()*3));
            Finance finance =new Finance();
            finance.setUserid( user.getUserid() );
            finance.setCreatetime( new Date(  ) );
            finance.setDescription( user.getUsername()+"对关键字:"+keyword.getKwname()+"("+keyword.getKwid()+"),进行开通操作,扣除3年"+ types.getTypevalue()*3+"元" );//详细信息
            finance.setFinatype( 14l );
            finance.setBalance( user.getBalance() );
            keywordService.update( keyword );
            usersService.update( user );
            financeService.insert( finance );
            //添加日志
            logService.insertLog( new Log(user,"用户进行开通app操作成功(等待核审中),服务年限3年,服务类型:上传到苹果商城" ,new Date(  )) );
        }else{
            result="用户余额不足";
        }

        return JSON.parseObject( "{\"error\":{\"error\":\"1\",\"message\":\""+result+"\"},\"keyword\":"+JSON.toJSONString( keyword )+",\"types\":"+JSON.toJSONString( types )+",\"client\":"+JSON.toJSONString( client )+"}" );//返回成功后的信息
    }

    /**
     * 获取开通app中的数据
     * @param session
     * @param keywordId
     * @return
     */
    @ResponseBody
    @RequestMapping("/getOpenAppPageData")
    public Object getOpenAppPageData(HttpSession session,Long keywordId){
        //返回关键词名称和价格,客户名称和服务类型
        Keyword keyword=keywordService.findById( keywordId );
        Client client=clientService.findById(keyword.getClientid());
        Types types=typesService.findById( 7l );
        //当前用户
        Users user = (Users) session.getAttribute( "user" );
        return JSON.parseObject( "{\"user\":"+JSON.toJSONString( user )+",\"keyword\":"+JSON.toJSONString( keyword )+",\"client\":"+JSON.toJSONString(  client)+",\"types\":"+JSON.toJSONString( types )+"}" );
    }

    /**
     * 获取续费的数据
     * @param session
     * @param keywordId
     * @return
     */
    @ResponseBody
    @RequestMapping("/getRenewAppData")
    public Object getRenewAppData(HttpSession session,Long keywordId){
        //关键词名称
        Keyword keyword=keywordService.findById( keywordId );
        //客户名称
        Client client=clientService.findById(keyword.getClientid());
        //服务类别
        List<Types> types=typesService.findTypesByParentid( 26l );
        //服务年限
        Servicetime servicetime=servicetimeService.findById(1l);
        //当前用户
        Users user = (Users) session.getAttribute( "user" );
        return JSON.parseObject( "{\"user\":"+JSON.toJSONString( user )+",\"keyword\":"+JSON.toJSONString( keyword )+",\"client\":"+JSON.toJSONString( client )+",\"typess\":"+JSON.toJSONString( types )+",\"servicetime\":"+JSON.toJSONString( servicetime )+"}" );

    }

    /**
     *     //续费
     * @param session
     * @param typesId
     * @param keywordId
     * @param openYear
     * @return
     */
    @ResponseBody
    @RequestMapping("/renewApp")
    public Object renewApp(HttpSession session,Long typesId,Long keywordId,Long openYear){
        String result="续费成功";
        //更改服务类别
        //更改服务年限
        //扣除费用
        //记录日志
        Users user=(Users)session.getAttribute( "user" );//获取当前登录的用户
        //获取关键字
        Keyword keyword=keywordService.findById( keywordId );
        //获取服务类型
        Types types=typesService.findById( typesId );
        Client client=clientService.findById( keyword.getClientid() );
        if(user.getBalance()>=(types.getTypevalue()*openYear)){
            //数据更新
            //关键字名称
            //企业名称
            //代理商名称
            keyword.setUsers( user );
            //申请年限
            keyword.setApplylimit(keyword.getApplylimit()+openYear);
            //到期时间
            keyword.setLasttime( DateUtil.addYear( keyword.getLasttime(),openYear ));
            //使用状态
            //设置服务类型
            keyword.setTypes( types );//设置为上传到苹果商城
            //扣费
            user.setBalance(user.getBalance()-(types.getTypevalue()*openYear));
            usersService.update( user );
            keywordService.update( keyword );
            //添加预付款信息
            Finance finance =new Finance();
            finance.setUserid( user.getUserid() );
            finance.setCreatetime( new Date() );
            finance.setDescription( user.getUsername()+"对关键字:"+keyword.getKwname()+"("+keyword.getKwid()+"),进行开通操作,扣除"+openYear+"年"+ types.getTypevalue()*openYear+"元" );//详细信息
            finance.setFinatype( 14l );
            finance.setBalance( user.getBalance() );
            //financeService.insert( finance );
            //添加日志
            Log log=new Log(user,"用户进行续费app操作成功,服务年限"+openYear+"年,服务类型:"+types.getTypename()+",消费:"+(types.getTypevalue()*openYear),new Date(  ));
            logService.insertLog(log);
        }else{
            result="用户余额不足";
        }

        return JSON.parseObject( "{\"error\":{\"error\":\"1\",\"message\":\""+result+"\"},\"keyword\":"+JSON.toJSONString( keyword )+",\"types\":"+JSON.toJSONString( types )+",\"client\":"+JSON.toJSONString( client )+"}" );//返回成功后的信息

    }
}

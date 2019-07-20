package cn.agent.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * 页面跳转
 */
@Controller
@RequestMapping(value = "/page")
public class PageChange {
    /**
     * 进入首页
     * @return
     */
    @RequestMapping(value="/AgentManage")
    public String AgentManage(){
        return  "agentManage";
    }

    /**
     * 代理商客户管理
     * @return
     */
    @RequestMapping(value="/agent_Customer_Management.do")
    public String agent_Customer_Management(){
        return "agent_Customer_Management";
    }

    /**
     * 代理商预付款
     * @return
     */
    @RequestMapping(value="/agent_Advance_Payment.do")
    public String agent_Advance_Payment(){
        return "agent_Advance_Payment";
    }

    /**
     * 关键字申请
     * @return
     */
    @RequestMapping(value="/keyword_Application.do")
    public String keyword_Application(){
        return "keyword_Application";
    }

    /**
     * 关键词申请管理
     * @return
     */
    @RequestMapping(value="/keywords_Application_Management.do")
    public String keywords_Application_Management(){
        return "keywords_Application_Management";
    }

    /**
     * 操作日志
     * @return
     */
    @RequestMapping(value="/operation_Log.do")
    public String operation_Log(){
        return "operation_Log";
    }

    /**
     * 门户管理
     * @return
     */
    @RequestMapping(value="/portal_Management.do")
    public String portal_Management(){
        return "portal_Management";
    }

    /**
     * 报表管理
     * @return
     */
    @RequestMapping(value="/report_Management.do")
    public String report_Management(){
        return "report_Management";
    }

    /**
     * 财务管理
     * @return
     */
    @RequestMapping(value="/financial_Management.do")
    public String financial_Management(){
        return "financial_Management";
    }

    /**
     * 角色管理
     * @return
     */
    @RequestMapping(value="/role_Management.do")
    public String role_Management(){
        return "role_Management";
    }
    /**
     * 角色权限管理
     * @return
     */
    @RequestMapping(value="/role_Authority_Management.do")
    public String role_Authority_Management(){
        return "role_Authority_Management";
    }

    /**
     * 用户管理
     * @return
     */
    @RequestMapping(value="/user_Management.do")
    public String user_Management(){
        return "user_Management";
    }

    /**
     * 关键字审核
     * @return
     */
    @RequestMapping(value="/keywords_Audit.do")
    public String keywords_Audit(){
        return "keywords_Audit";
    }

    /**
     * 财务类型
     * @return
     */
    @RequestMapping(value="/financial_Types.do")
    public String financial_Types(){
        return "financial_Types";
    }

    /**
     * 服务类型
     * @return
     */
    @RequestMapping(value="/type_Of_Service.do")
    public String type_Of_Service(){
        return "type_Of_Service";
    }

    /**
     * 服务年限
     * @return
     */
    @RequestMapping(value="/length_Of_Service.do")
    public String length_Of_Service(){
        return "length_Of_Service";
    }

    /**
     * APP地址
     * @return
     */
    @RequestMapping(value="/address_Of_The_APP.do")
    public String address_Of_The_APP(){
        return "address_Of_The_APP";
    }

    /**
     * 客户管理
     * @return
     */
    @RequestMapping(value="/client_Type.do")
    public String client_Type(){
        return "client_Type";
    }

    /**
     * 证件类型
     * @return
     */
    @RequestMapping(value="/certificate_Type.do")
    public String certificate_Type(){
        return "certificate_Type";
    }
    /**
     * 优惠类型
     * @return
     */
    @RequestMapping(value="/preferential_Type.do")
    public String preferential_Type(){
        return "preferential_Type";
    }

    /**
     * 添加客户
     * @return
     */
    @RequestMapping(value="/add_The_Customer.do")
    public String add_The_Customer(){
        return "add_The_Customer";
    }

    /**
     * 查看门户
     * @return
     */
    @RequestMapping(value="/look_At_The_Portal.do")
    public String look_At_The_Portal(){
        return "look_At_The_Portal";
    }

    /**
     * 修改门户
     * @return
     */
    @RequestMapping(value="/modify_The_Portal.do")
    public String modify_The_Portal(){
        return "modify_The_Portal";
    }

    /**
     * 查看用户明细
     * @return
     */
    @RequestMapping(value="/check_Account_Details.do")
    public String check_Account_Details(){
        return "check_Account_Details";
    }

    /**
     * 进入账户信息页面
     * @return
     */
    @RequestMapping(value="/agent_Manage.do")
    public String agent_Manage(){
        return "agent_Manage";
    }
}

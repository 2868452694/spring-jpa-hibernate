package cn.agent.controller;

import cn.agent.pojo.Client;
import cn.agent.pojo.Linkman;
import cn.agent.pojo.Portal;
import cn.agent.pojo.Users;
import cn.agent.service.ClientService;
import cn.agent.service.LinkmanService;
import cn.agent.service.PortalService;
import cn.agent.service.TypesService;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/protal")
public
class PortalController {

    @Autowired
    private PortalService portalService;
    @Autowired
    private ClientService clientService;
    @Autowired
    private LinkmanService linkmanService;
    @Autowired
    private TypesService typesService;

    /**
     * get企业类型
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = {"/getEnterpriseTypes"})
    public
    Object getEnterpriseTypes() {
        return typesService.findTypesByParentid( 25l );
    }

    /**
     * get证件类型
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = {"/getPapersTypes"})
    public
    Object getPapersTypes() {
        return typesService.findTypesByParentid( 24l );
    }

    @ResponseBody
    @RequestMapping(value = {"/insert", "/add"})
    public
    Object add(HttpSession session, String jsonStr) {
        JSONObject jsonObject = JSON.parseObject( jsonStr );
        Portal portal = JSON.toJavaObject( jsonObject.getJSONObject( "portal" ), Portal.class );
        Client client = JSON.toJavaObject( jsonObject.getJSONObject( "client" ), Client.class );
        List<Linkman> linkmans = JSON.parseArray( jsonObject.getString( "linkmans" ), Linkman.class );

        if (portal.getPortalid() != null || client.getClientid() != null) {
            return null;
        }
        for (Linkman linkman : linkmans) {
            if (linkman.getLinkmanid() != null) {
                return null;
            }
        }
        //添加新的联系人
        //更新客户数据
        //更新门户数据
        try {
            Users user = (Users) session.getAttribute( "user" );
            for (Linkman linkman : linkmans) {
                linkman.setClient( client );
            }
            client.setUsers( user );
            client.setTypes( typesService.findById( client.getEnterprisetype() ) );
            client = clientService.insert( client );
            portal.setClientid( client.getClientid() );
            portal.setCreatetime( new Date() );
            portal.setTypes( typesService.findById( portal.getPaperstype() ) );
            portal = portalService.insert( portal );
            for (Linkman linkman : linkmans) {
                linkmanService.insert( linkman );
            }
            return portal;
        } catch (Exception e) {
            return null;
        }

    }

    @ResponseBody
    @RequestMapping(value = {"/update", "/change", "/up"})
    public
    Object update(HttpSession session, String jsonStr, String delLinkmanIds) {
        JSONArray jsonArray = JSON.parseArray( delLinkmanIds );
        JSONObject jsonObject = JSON.parseObject( jsonStr );
        Portal portal = JSON.toJavaObject( jsonObject.getJSONObject( "portal" ), Portal.class );
        Client client = JSON.toJavaObject( jsonObject.getJSONObject( "client" ), Client.class );
        List<Linkman> linkmans = JSON.parseArray( jsonObject.getString( "linkmans" ), Linkman.class );
        List<Long> lids = JSON.parseArray( delLinkmanIds, Long.class );
        //删除指定的联系人
        if (lids != null) {
            for (Long item : lids) {
                linkmanService.delById( item );
            }
        }
        //添加新的联系人
        //更新客户数据
        //更新门户数据
        try {
            Users user = (Users) session.getAttribute( "user" );
            for (Linkman linkman : linkmans) {
                linkman.setClient( client );
            }
            client.setUsers( user );
            client.setTypes( typesService.findById( client.getEnterprisetype() ) );
            client = clientService.update( client );
            portal.setClientid( client.getClientid() );
            portal.setTypes( typesService.findById( portal.getPaperstype() ) );
            portal.setCreatetime( portalService.findById( portal.getPortalid() ).getCreatetime() );
            portal = portalService.update( portal );
            for (Linkman linkman : linkmans) {
                linkmanService.update( linkman );
            }
            return portal;
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * get证件类型
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = {"/getPortalAllInfo"})
    public
    Object getPortalAllInfo(Long portalid) {
        Portal portal = portalService.findById( portalid );
        Client client = clientService.findById( portal.getClientid() );
        List<Linkman> linkmanList = linkmanService.findLinkmanByClientId( client.getClientid() );
        return JSON.parseObject( "{\"portal\":" + JSON.toJSONString( portal ) + ",\"client\":" + JSON.toJSONString( client ) + ",\"linkmans\":" + JSON.toJSONString( linkmanList ) + "}" );
    }
}

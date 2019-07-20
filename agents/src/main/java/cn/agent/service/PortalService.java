package cn.agent.service;

import cn.agent.pojo.Portal;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public
interface PortalService {
    /**
     * 修改门户信息
     *
     * @param portal 条件
     * @return 是否修改成功
     */
    Portal update(Portal portal);

    /**
     * 添加门户信息
     *
     * @param portal 条件
     * @return 是否添加成功
     */
    Portal insert(Portal portal);

    /**
     * 根据条件获取满足条件的门户信息
     *
     * @param portal 条件
     * @return 门户信息list
     */
    List<Portal> findAllPortal(Portal portal);

    /**
     * 根据条件获取指定页数的门户信息对象
     * @param portal 门户信息条件
     * @param pageSum 页数
     * @return 门户信息list
     */
    Page<Portal> findPagePortal(Portal portal, int pageSum);

    /**
     * 获取满足条件的记录数
     * @param portal 条件  条件为null时获取所有
     * @return 记录个数
     */
    Long getCount(Portal portal);
    /**
     * 根据id查询门户信息
     *
     * @param id 条件
     * @return 门户信息
     */
    Portal findById(Long id);

    /**
     * 删除门户信息
     *
     * @param portal 条件
     * @return 是否删除成功
     */
    boolean delete(Portal portal);

}
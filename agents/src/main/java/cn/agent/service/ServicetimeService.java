package cn.agent.service;

import cn.agent.pojo.Role;
import cn.agent.pojo.Servicetime;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public
interface ServicetimeService {
    /**
     * 修改服务年限
     *
     * @param servicetime 条件
     * @return 是否修改成功
     */
    boolean update(Servicetime servicetime);

    /**
     * 添加服务年限
     *
     * @param servicetime 条件
     * @return 是否添加成功
     */
    boolean insert(Servicetime servicetime);

    /**
     * 根据条件获取满足条件的服务年限
     *
     * @param servicetime 条件
     * @return 服务年限list
     */
    List<Servicetime> findAllServicetime(Servicetime servicetime);

    /**
     * 根据条件获取指定页数的服务年限对象
     * @param servicetime 服务年限条件
     * @param pageSum 页数
     * @return 服务年限list
     */
    Page<Servicetime> findPageServicetime(Servicetime servicetime, int pageSum);

    /**
     * 获取满足条件的记录数
     * @param servicetime 条件  条件为null时获取所有
     * @return 记录个数
     */
    Long getCount(Servicetime servicetime);
    /**
     * 根据id查询服务年限
     *
     * @param id 条件
     * @return 服务年限
     */
    Servicetime findById(Long id);

    /**
     * 删除服务年限
     *
     * @param servicetime 条件
     * @return 是否删除成功
     */
    boolean delete(Servicetime servicetime);

}
package cn.agent.service;

import cn.agent.pojo.Jurisdiction;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public
interface   JurisdictionService {
    /**
     * 修改权限
     *
     * @param jurisdiction 权限条件
     * @return 是否修改成功
     */
    boolean update(Jurisdiction jurisdiction);

    /**
     * 添加权限
     *
     * @param jurisdiction 权限条件
     * @return 是否添加成功
     */
    boolean insert(Jurisdiction jurisdiction);

    /**
     * 根据条件获取满足条件的权限
     *
     * @param jurisdiction 条件 为null 查询 所有权限
     * @return 权限list
     */
    List<Jurisdiction> findAllJurisdiction(Jurisdiction jurisdiction);

    /**
     * 根据条件获取指定页数的权限对象
     * @param jurisdiction 权限条件
     * @param pageSum 页数
     * @return 权限list
     */
    Page<Jurisdiction> findPageJurisdiction(Jurisdiction jurisdiction, int pageSum);

    /**
     * 获取满足条件的记录数
     * @param jurisdiction 条件  条件为null时获取所有
     * @return 记录个数
     */
    Long getCount(Jurisdiction jurisdiction);
    /**
     * 根据id查询权限
     *
     * @param id 条件
     * @return 权限
     */
    Jurisdiction findById(Long id);

    /**
     * 删除权限
     *
     * @param jurisdiction 权限条件
     * @return 是否删除成功
     */
    boolean delete(Jurisdiction jurisdiction);

}

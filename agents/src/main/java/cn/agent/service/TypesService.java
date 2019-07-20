package cn.agent.service;

import cn.agent.pojo.Types;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public
interface TypesService {
    /**
     * 修改类型
     *
     * @param types 条件
     * @return 是否修改成功
     */
    boolean update(Types types);

    /**
     * 添加类型
     *
     * @param types 条件
     * @return 是否添加成功
     */
    boolean insert(Types types);

    /**
     * 根据条件获取满足条件的类型
     *
     * @param types 条件
     * @return 类型list
     */
    List<Types> findAllTypes(Types types);

    /**
     * 根据条件获取指定页数的类型对象
     * @param types 类型条件
     * @param pageSum 页数
     * @return 类型list
     */
    Page<Types> findPageTypes(Types types, int pageSum);

    /**
     * 获取满足条件的记录数
     * @param types 条件  条件为null时获取所有
     * @return 记录个数
     */
    Long getCount(Types types);
    /**
     * 根据id查询类型
     *
     * @param id 条件
     * @return 类型
     */
    Types findById(Long id);

    /**
     * 删除类型
     *
     * @param types 条件
     * @return 是否删除成功
     */
    boolean delete(Types types);


    /**
     * 根据父类id查询类型信息
     * @param parentid
     * @return
     */
    public List<Types> findTypesByParentid(Long parentid);
}
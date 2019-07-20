package cn.agent.service;

import cn.agent.pojo.Keyword;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public
interface KeywordService {
    /**
     * 修改关键字
     *
     * @param keyword 条件 必须有id在数据库存在不然会变成新增的
     * @return 是否修改成功
     */
    boolean update(Keyword keyword);

    /**
     * 添加关键字
     *
     * @param keyword 条件
     * @return 是否添加成功
     */
    boolean insert(Keyword keyword);

    /**
     * 根据条件获取满足条件的关键字
     *
     * @param keyword 条件
     * @return 关键字list
     */
    List<Keyword> findAllKeyword(Keyword keyword);

    /**
     * 根据条件获取指定页数的关键字对象
     * @param kwname 关键字名称
     * @param pageSum 页数
     * @return 关键字list
     */
    Page<Keyword> findPageKeyword(String kwname, Integer pageSum);

    /**
     * 获取满足条件的记录数
     * @param kwname 名称条件  条件为null时获取所有
     * @return 记录个数
     */
    Long getCount(String kwname);
    /**
     * 根据id查询关键字
     *
     * @param id 条件
     * @return 关键字
     */
    Keyword findById(Long id);

    /**
     * 删除关键字
     *
     * @param id 条件
     * @return 是否删除成功
     */
    boolean delete(Long id);

    

}
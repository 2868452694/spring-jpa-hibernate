package cn.agent.service;

import cn.agent.pojo.Linkman;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public
interface LinkmanService {
    /**
     * 修改联系人
     *
     * @param linkman 条件
     * @return 是否修改成功
     */
    Linkman update(Linkman linkman);

    /**
     * 添加联系人
     *
     * @param linkman 条件
     * @return 是否添加成功
     */
    Linkman insert(Linkman linkman);

    /**
     * 根据条件获取满足条件的联系人
     *
     * @param linkman 条件
     * @return 联系人list
     */
    List<Linkman> findAllLinkman(Linkman linkman);

    /**
     * 获取指定客户的联系人
     * @param clientId
     * @return
     */
    List<Linkman> findLinkmanByClientId(Long clientId);
    /**
     * 根据条件获取指定页数的联系人对象
     * @param linkman 联系人条件
     * @param pageSum 页数
     * @return 联系人list
     */
    Page<Linkman> findPageLinkman(Linkman linkman, int pageSum);

    /**
     * 获取满足条件的记录数
     * @param linkman 条件  条件为null时获取所有
     * @return 记录个数
     */
    Long getCount(Linkman linkman);
    /**
     * 根据id查询联系人
     *
     * @param id 条件
     * @return 联系人
     */
    Linkman findById(Long id);
    /**
     * 根据id删除联系人
     *
     * @param id 条件
     * @return 联系人
     */
    Linkman delById(Long id);

    /**
     * 删除联系人
     *
     * @param linkman 条件
     * @return 是否删除成功
     */
    boolean delete(Linkman linkman);

}
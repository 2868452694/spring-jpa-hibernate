package cn.agent.dao;

import cn.agent.pojo.Appaddress;
import cn.agent.pojo.Finance;
import cn.agent.pojo.Types;
import cn.agent.pojo.Users;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.RepositoryDefinition;

import java.util.Date;
import java.util.List;


/**
 * 使用update和delete的时候一定要加上@Modifying这个注解，在这里使用到事务所有必须加上事务如果不加则会Bug" 爆炸~~~"
 * Query注解中加入nativeQuery=true 属性表示使用原生sql，否则默认是hql（实体类查）
 */
@RepositoryDefinition(domainClass= Finance.class,idClass=Long.class)
public
interface FinanceDao extends JpaRepository<Finance,Long> {
    /**
     *  查询财务信息
     * @param createtime  最小时间
     * @param createtime2 最大时间
     * @param userid 用户id
     * @param finatype 财务类型id
     * @param pageable
     * @return
     */
    Page<Finance> queryFinanceByCreatetimeBetweenAndUseridAndFinatype(Date createtime, Date createtime2, Long userid, Long finatype, Pageable pageable);

    /**
     * 根据时间和用户id查询财务信息
     * @param createtime 最小时间
     * @param createtime2 最大时间
     * @param userid 用户id
     * @param pageable
     * @return
     */
    Page<Finance> queryFinanceByCreatetimeBetweenAndUserid(Date createtime, Date createtime2, Long userid, Pageable pageable);

    /**
     * 根据财务类型和用户id查询财务信息
     * @param finatype
     * @param userid
     * @param pageable
     * @return
     */
    Page<Finance> queryFinanceByFinatypeAndUserid(Long finatype, Long userid, Pageable pageable);
    /**
     * 根据用户id查询财务明细分页查
     * @param userid
     * @param pageable
     * @return
     */
    Page<Finance> queryFinanceByUserid(Long userid, Pageable pageable);

}

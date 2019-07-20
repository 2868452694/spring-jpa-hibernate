package cn.agent.service.impl;

import cn.agent.dao.FinanceDao;
import cn.agent.pojo.Finance;
import cn.agent.pojo.Types;
import cn.agent.pojo.Users;
import cn.agent.service.FinanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service("financeService")
public
class FinanceServiceImple implements FinanceService {

    //创建dao层对象
    @Autowired
    private FinanceDao financeDao;
    @Override
    public
    boolean update(Finance finance) {
        return false;
    }

    @Override
    public
    boolean insert(Finance finance){
        return financeDao.saveAndFlush( finance )!=null;
    }

    @Override
    public
    List<Finance> findAllFinance(Finance finance) {
        return null;
    }

    @Override
    public
    Page<Finance> findPageFinance(Finance finance, int pageSum) {
        return null;
    }


    @Override
    public
    Long getCount(Finance finance) {
        return null;
    }

    @Override
    public
    Finance findById(Long id) {
        return null;
    }

    @Override
    public
    boolean delete(Finance finance) {
        return false;
    }


    /**
     * 根据条件查询，分页查
     * @param createtime 最小时间
     * @param createtime2 最大时间
     * @param pageSum
     * @param pageSize
     * @return
     */
    @Override
    public Page<Finance> queryFinanceByCreatetimeBetween(Date createtime, Date createtime2, Long userid, Long finatype,Integer pageSum, Integer pageSize) {
        Pageable pageable=PageRequest.of(pageSum==0?0:pageSum,pageSize,new Sort(Sort.Direction.DESC,"createtime"));
        Page<Finance> finances=null;
        //判断参数是否为空
        if((createtime==null ||createtime.equals(""))&&(createtime2==null || createtime2.equals(""))&&finatype==0){
            finances=financeDao.queryFinanceByUserid(userid,pageable);
        }else if(finatype==0 && (createtime!=null ||!createtime.equals(""))&&(createtime2!=null || !createtime2.equals(""))){
            finances=financeDao.queryFinanceByCreatetimeBetweenAndUserid(createtime,createtime2,userid,pageable);
        }else if(finatype!=0 && (createtime==null ||createtime.equals(""))&&(createtime2==null || createtime2.equals(""))){
            finances=financeDao.queryFinanceByFinatypeAndUserid(finatype,userid,pageable);
        }else{
            finances=financeDao.queryFinanceByCreatetimeBetweenAndUseridAndFinatype(createtime,createtime2,userid,finatype,pageable);
        }
        return finances;
    }


}

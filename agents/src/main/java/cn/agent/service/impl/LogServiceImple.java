package cn.agent.service.impl;

import cn.agent.dao.LogDao;
import cn.agent.pojo.Log;
import cn.agent.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service("logService")
public
class LogServiceImple implements LogService {

    //创建dao层对象
    @Autowired
    private LogDao logDao;

    /**
     * 添加日志
     * @param log 要填加的日志对象
     * @return
     */
    @Override
    public
    boolean insertLog(Log log) {
        Log log1=logDao.save(log);
        if(log1!=null || !log1.equals("")){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public
    List<Log> findAllLog(Log log) {
        return null;
    }

    @Override
    public
    Page<Log> findPageLog(Log log, int pageSum) {
        return null;
    }

    @Override
    public
    Long getCount(Log log) {
        return null;
    }

    /**
     * 根据条件分页查询日志信息
     * @param log
     * @param pageSum
     * @param pageSize
     * @return
     */
    @Override
    public Page<Log> queryfindPageLogInfo(Log log, Integer pageSum, Integer pageSize) {
        Date time=log.getLogtime();
        //生成Pageable 对象
        Pageable pageable= PageRequest.of(pageSum==0?0:pageSum,pageSize,new Sort(Sort.Direction.DESC,"logtime"));
        Page<Log> logs=null;
        if(log!=null && log.getUsers().getRole().getRoleid()==1){
            logs=logDao.findAll(pageable);
        }else if(log!=null && log.getUsers().getRole().getRoleid()!=1 && (log.getLogtime()==null ||log.getLogtime().equals(""))){
            logs=logDao.queryLogByUsers(log.getUsers(),pageable);
        }else if(log!=null && log.getUsers().getRole().getRoleid()!=1 && (log.getLogtime()!=null ||!log.getLogtime().equals(""))){
            logs=logDao.queryLogByUsersAndLogtime(log.getUsers(),log.getLogtime(),pageable);
        }
        return logs;
    }
}

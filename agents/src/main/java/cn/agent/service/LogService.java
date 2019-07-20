package cn.agent.service;

import cn.agent.pojo.Log;
import cn.agent.pojo.Users;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public
interface LogService {

    /**
     * 添加一个操作日志
     * @param log 要填加的日志对象
     * @return 是否添加成功
     */
    boolean insertLog(Log log);

    /**
     * 根据条件获取满足条件的日志
     *
     * @param log 条件
     * @return 日志list
     */
    List<Log> findAllLog(Log log);

    /**
     * 根据条件获取指定页数的日志对象
     * @param log 联系人条件
     * @param pageSum 页数
     * @return 日志list
     */
    Page<Log> findPageLog(Log log, int pageSum);

    /**
     * 获取满足条件的记录数
     * @param log 条件  条件为null时获取所有
     * @return 记录个数
     */
    Long getCount(Log log);

    /**
     *  根据条件分页查询
     * @param log
     * @param pageSum
     * @param pageSize
     * @return
     */
    Page<Log> queryfindPageLogInfo(Log log,Integer pageSum,Integer pageSize);

}

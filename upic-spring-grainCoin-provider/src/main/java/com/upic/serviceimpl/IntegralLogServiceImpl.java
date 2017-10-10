package com.upic.serviceimpl;

import com.upic.common.beans.utils.UpicBeanUtils;
import com.upic.common.support.spec.domain.AbstractDomain2InfoConverter;
import com.upic.common.support.spec.domain.converter.QueryResultConverter;
import com.upic.condition.IntegralLogCondition;
import com.upic.dto.IntegralLogIdInfo;
import com.upic.dto.IntegralLogInfo;
import com.upic.enums.IntegralLogStatusEnum;
import com.upic.enums.IntegralLogTypeEnum;
import com.upic.po.IntegralLog;
import com.upic.po.IntegralLogId;
import com.upic.repository.IntegralLogRepository;
import com.upic.repository.Spec.IntegralLogSpec;
import com.upic.service.IntegralLogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

@Service("integralLogService")
public class IntegralLogServiceImpl implements IntegralLogService {
    @Autowired
    private IntegralLogRepository integralLogRepository;

    protected static final Logger LOGGER = LoggerFactory.getLogger(IntegralLogServiceImpl.class);

    public void allOperation(String apartment, String operatorNum, IntegralLogStatusEnum status, IntegralLogTypeEnum type) {
        try {
            List<IntegralLog> integralLogs = integralLogRepository.getByApartmentAndType(apartment, type);
            for (IntegralLog integralLog : integralLogs) {
                integralLog.setStatus(status);
                integralLogRepository.saveAndFlush(integralLog);
            }
        } catch (Exception e) {
            LOGGER.info("allOperation:全部操作失败。错误信息：" + e.getMessage());
            e.printStackTrace();
        }
    }

    public Queue<Long> someOperation(Queue<Long> queue, String operatorNum, IntegralLogStatusEnum status, IntegralLogTypeEnum type) {
        try {
            boolean flag = true;
            while (flag) {
                Long id = queue.poll();
                if (id == null) {
                    flag = false;
                    continue;
                }
                IntegralLog integralLog = integralLogRepository.findOne(id);
                if (integralLog == null) throw new Exception();
                integralLog.setStatus(status);
                integralLogRepository.saveAndFlush(integralLog);
            }
            return queue;
        } catch (Exception e) {
            LOGGER.info("someOperation:部分操作失败。错误信息：" + e.getMessage());
            return null;
        }
    }

    public IntegralLogInfo saveIntegralLog(IntegralLogInfo integralLogInfo) {
        try {
            IntegralLog integralLog = new IntegralLog();
            UpicBeanUtils.copyProperties(integralLogInfo, integralLog);
            integralLog = integralLogRepository.save(integralLog);
            UpicBeanUtils.copyProperties(integralLog, integralLogInfo);
        } catch (Exception e) {
            LOGGER.info("saveIntegralLog:保存积分纪录失败。错误信息：" + e.getMessage());
            return null;
        }
        return integralLogInfo;
    }

    public double watchIntegral(String studentNum) {
        try {
//            IntegralLogId integralLogId = new IntegralLogId();
//            integralLogId.setStudentNum(studentNum);

            return integralLogRepository.findByStudentNum(studentNum);
        } catch (Exception e) {
            LOGGER.info("watchIntegral:查看积分失败。错误信息：" + e.getMessage());
            return 0;
        }
    }

    public IntegralLogInfo getByIntegralLogId(IntegralLogIdInfo integralLogIdInfo) {
        try {
            IntegralLogId integralLogId = new IntegralLogId();
            UpicBeanUtils.copyProperties(integralLogIdInfo, integralLogId);
            IntegralLogInfo integralLogInfo = new IntegralLogInfo();
            IntegralLog integralLog = integralLogRepository.findByIntegralLogId(integralLogId);
            if (integralLog == null) {
                return null;
            } else {
                UpicBeanUtils.copyProperties(integralLog, integralLogInfo);
            }
            return integralLogInfo;
        } catch (Exception e) {
            LOGGER.info("getByIntegralLogId:" + e.getMessage());
            return null;
        }
    }

    public void signUp(IntegralLogInfo integralLogInfo) {

    }

    public Page<IntegralLogInfo> searchIntegralLog(IntegralLogCondition integralLogCondition, Pageable pageable) {
        Page<IntegralLog> integralLogPage = null;
        try {
            integralLogPage = integralLogRepository.findAll(new IntegralLogSpec(integralLogCondition), pageable);
            return QueryResultConverter.convert(integralLogPage, pageable, new AbstractDomain2InfoConverter<IntegralLog, IntegralLogInfo>() {
                protected void doConvert(IntegralLog domain, IntegralLogInfo info) throws Exception {
                    UpicBeanUtils.copyProperties(domain, info);
                }
            });
        } catch (Exception e) {
            LOGGER.info("searchIntegralLog:" + e.getMessage());
            return null;
        }
    }

    public Page<IntegralLogInfo> getUserListByProjectNum(String projectNum, Pageable pageable) {
        Page<IntegralLog> integralLogPage = null;
        try {
            integralLogPage = integralLogRepository.getUserListByProjectNum(projectNum, pageable);
            return QueryResultConverter.convert(integralLogPage, pageable, new AbstractDomain2InfoConverter<IntegralLog, IntegralLogInfo>() {
                protected void doConvert(IntegralLog domain, IntegralLogInfo info) throws Exception {
                    UpicBeanUtils.copyProperties(domain, info);
                }
            });
        } catch (Exception e) {
            LOGGER.info("getUserListByProjectNum:" + e.getMessage());
            return null;
        }
    }

    public int getSignUpNumberByProjectNum(String projectNum) {
        try {
            return integralLogRepository.getSignUpNumberByProjectNum(projectNum);
        } catch (Exception e) {
            LOGGER.info("getSignUpNumberByProjectNum:" + e.getMessage());
            return 0;
        }
    }

    public Page<IntegralLogInfo> getIntegralLogByMySelf(String studentNum, Pageable pageable) {
        Page<IntegralLog> integralLogPage = null;
        try {
            integralLogPage = integralLogRepository.getIntegralLogByMySelf(studentNum, pageable);
            return QueryResultConverter.convert(integralLogPage, pageable, new AbstractDomain2InfoConverter<IntegralLog, IntegralLogInfo>() {
                protected void doConvert(IntegralLog domain, IntegralLogInfo info) throws Exception {
                    UpicBeanUtils.copyProperties(domain, info);
                }
            });
        } catch (Exception e) {
            LOGGER.info("getIntegralLogByMySelf:" + e.getMessage());
            return null;
        }
    }

    public Page<IntegralLogInfo> getAllIntegralLogByStudentNum(String studentNum, Pageable pageable) {
        Page<IntegralLog> integralLogPage = null;
        try {
            integralLogPage = integralLogRepository.getAllIntegralLogByStudentNum(studentNum, pageable);
            return QueryResultConverter.convert(integralLogPage, pageable, new AbstractDomain2InfoConverter<IntegralLog, IntegralLogInfo>() {
                protected void doConvert(IntegralLog domain, IntegralLogInfo info) throws Exception {
                    UpicBeanUtils.copyProperties(domain, info);
                }
            });
        } catch (Exception e) {
            LOGGER.info("getAllIntegralLogByStudentNum:" + e.getMessage());
            return null;
        }
    }

    public Page<IntegralLogInfo> getIntegralLogDeclaring(String studentNum, Pageable pageable) {
        Page<IntegralLog> integralLogPage = null;
        try {
            integralLogPage = integralLogRepository.getIntegralLogDeclaring(studentNum, pageable);
            return QueryResultConverter.convert(integralLogPage, pageable, new AbstractDomain2InfoConverter<IntegralLog, IntegralLogInfo>() {
                protected void doConvert(IntegralLog domain, IntegralLogInfo info) throws Exception {
                    UpicBeanUtils.copyProperties(domain, info);
                }
            });
        } catch (Exception e) {
            LOGGER.info("getIntegralLogDeclaring:" + e.getMessage());
            return null;
        }
    }

    public Page<IntegralLogInfo> integralLogSearchBar(String status, String keyword, Pageable pageable) {
        Page<IntegralLog> integralLogPage = null;
        try {
            integralLogPage = integralLogRepository.integralLogSearchBar(status, keyword, pageable);
            return QueryResultConverter.convert(integralLogPage, pageable, new AbstractDomain2InfoConverter<IntegralLog, IntegralLogInfo>() {
                @Override
                protected void doConvert(IntegralLog domain, IntegralLogInfo info) throws Exception {
                    UpicBeanUtils.copyProperties(domain, info);
                }
            });
        } catch (Exception e) {
            LOGGER.info("integralLogSearchBar:" + e.getMessage());
            return null;
        }
    }

    public String updateIntegralLogStatus(List<IntegralLogIdInfo> integralLogIdInfos, IntegralLogStatusEnum status) {
        try {
            for (IntegralLogIdInfo integralLogIdInfo : integralLogIdInfos) {
                IntegralLogId integralLogId = new IntegralLogId();
                integralLogId.setStudentNum(integralLogIdInfo.getStudentNum());
                integralLogId.setProjectNum(integralLogIdInfo.getProjectNum());
                IntegralLog integralLog = integralLogRepository.findByIntegralLogId(integralLogId);
                integralLog.setStatus(status);
                integralLogRepository.saveAndFlush(integralLog);
            }
            return "SUCCESS";
        } catch (Exception e) {
            LOGGER.info("updateIntegralLogStatus:" + e.getMessage());
            return null;
        }
    }

    @Override
    public List<IntegralLogInfo> getByProjectNum(String projectNum) {
        List<IntegralLog> integralLogList = null;
        List<IntegralLogInfo> integralLogInfoList = new ArrayList<>();
        try {
            integralLogList = integralLogRepository.getByProjectNum(projectNum);
            for (IntegralLog integralLog : integralLogList) {
                IntegralLogInfo integralLogInfo = new IntegralLogInfo();
                UpicBeanUtils.copyProperties(integralLog, integralLogInfo);
                integralLogInfoList.add(integralLogInfo);
            }
            return integralLogInfoList;
        } catch (Exception e) {
            LOGGER.info("getByProjectNum:" + e.getMessage());
            return null;
        }
    }
}

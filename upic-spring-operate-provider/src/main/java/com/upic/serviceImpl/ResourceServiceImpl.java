package com.upic.serviceImpl;

import com.upic.common.beans.utils.UpicBeanUtils;
import com.upic.common.support.spec.domain.AbstractDomain2InfoConverter;
import com.upic.common.support.spec.domain.converter.QueryResultConverter;
import com.upic.condition.ResourceCondition;
import com.upic.dto.ResourceInfo;
import com.upic.po.Resource;
import com.upic.repository.ResourceRepository;
import com.upic.repository.Spec.ResourceSpec;
import com.upic.service.ResourceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhubuqing on 2017/9/11.
 */
@Service("resourceService")
public class ResourceServiceImpl implements ResourceService {
    @Autowired
    private ResourceRepository resourceRepository;

    protected static final Logger LOGGER = LoggerFactory.getLogger(ResourceServiceImpl.class);

    public ResourceInfo addResource(ResourceInfo resourceInfo) {
        try {
            Resource resource = new Resource();
            UpicBeanUtils.copyProperties(resourceInfo, resource);
            resource = resourceRepository.save(resource);
            UpicBeanUtils.copyProperties(resource, resourceInfo);
            return resourceInfo;
        } catch (Exception e) {
            LOGGER.info("updateResource:资源" + resourceInfo.toString() + "添加失败。错误信息：" + e.getMessage());
            return null;
        }
    }

    public ResourceInfo updateResource(ResourceInfo resourceInfo) {
        try {
            Resource resource = new Resource();
            UpicBeanUtils.copyProperties(resourceInfo, resource);
            resource = resourceRepository.saveAndFlush(resource);
            UpicBeanUtils.copyProperties(resource, resourceInfo);
            return resourceInfo;
        } catch (Exception e) {
            LOGGER.info("updateResource:资源" + resourceInfo.toString() + "更新失败。错误信息：" + e.getMessage());
            return null;
        }
    }

    public Page<ResourceInfo> searchResource(ResourceCondition resourceCondition, Pageable pageable) {
        Page<Resource> resourcePage = null;
        try {
            resourcePage = resourceRepository.findAll(new ResourceSpec(resourceCondition), pageable);
            return QueryResultConverter.convert(resourcePage, pageable, new AbstractDomain2InfoConverter<Resource, ResourceInfo>() {
                protected void doConvert(Resource domain, ResourceInfo info) throws Exception {
                    UpicBeanUtils.copyProperties(domain, info);
                }
            });
        } catch (Exception e) {
            LOGGER.info("searchResource:资源列表查询失败。错误信息：" + e.getMessage());
            return null;
        }
    }

    public ResourceInfo getResourceById(long resourceId) {
        try {
            Resource resource = resourceRepository.findOne(resourceId);
            ResourceInfo resourceInfo = new ResourceInfo();
            UpicBeanUtils.copyProperties(resource, resourceInfo);
            return resourceInfo;
        } catch (Exception e) {
            LOGGER.info("getResourceById:资源查询失败。错误信息：" + e.getMessage());
            return null;
        }
    }

    public void deleteResource(long resourceId) {
        try {
            resourceRepository.delete(resourceId);
        } catch (Exception e) {
            LOGGER.info("deleteResource:资源删除失败。错误信息：" + e.getMessage());
        }
    }

    @Override
    public Page<ResourceInfo> findByFatherId(long fatherId, Pageable pageable) {
        Page<Resource> resourcePage = null;
        try {
            resourcePage = resourceRepository.findByFatherId(fatherId, pageable);
            return QueryResultConverter.convert(resourcePage, pageable, new AbstractDomain2InfoConverter<Resource, ResourceInfo>() {
                protected void doConvert(Resource domain, ResourceInfo info) throws Exception {
                    UpicBeanUtils.copyProperties(domain, info);
                }
            });
        } catch (Exception e) {
            LOGGER.info("deleteResource:资源删除失败。错误信息：" + e.getMessage());
            return null;
        }
    }

    @Override
    public List<ResourceInfo> listResource(ResourceCondition resourceCondition) {
        List<Resource> resourceList;
        List<ResourceInfo> resourceInfoList = new ArrayList<>();
        try {
            resourceList = resourceRepository.findAll(new ResourceSpec(resourceCondition));
            for (Resource resource : resourceList) {
                ResourceInfo resourceInfo = new ResourceInfo();
                UpicBeanUtils.copyProperties(resource, resourceInfo);
                resourceInfoList.add(resourceInfo);
            }
            return resourceInfoList;
        } catch (Exception e) {
            LOGGER.info("listResource。错误信息：" + e.getMessage());
            return null;
        }
    }
}

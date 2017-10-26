package com.upic.serviceImpl;

import com.upic.common.beans.utils.UpicBeanUtils;
import com.upic.common.support.spec.domain.AbstractDomain2InfoConverter;
import com.upic.common.support.spec.domain.converter.QueryResultConverter;
import com.upic.condition.CategoryNodeCondition;
import com.upic.dto.CategoryNodeInfo;
import com.upic.po.CategoryNode;
import com.upic.repository.CategoryNodeRepository;
import com.upic.repository.Spec.CategoryNodeSpec;
import com.upic.service.CategoryNodeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhubuqing on 2017/9/10.
 */
@Service("categoryNodeService")
public class CategoryNodeServiceImpl implements CategoryNodeService {
    @Autowired
    private CategoryNodeRepository categoryNodeRepository;

    protected static final Logger LOGGER = LoggerFactory.getLogger(CategoryNodeServiceImpl.class);

    public CategoryNodeInfo addCategoryNode(CategoryNodeInfo categoryNodeInfo) {
        try {
            CategoryNode categoryNode = new CategoryNode();
            UpicBeanUtils.copyProperties(categoryNodeInfo, categoryNode);
            categoryNode = categoryNodeRepository.save(categoryNode);
            UpicBeanUtils.copyProperties(categoryNode, categoryNodeInfo);
            return categoryNodeInfo;
        } catch (Exception e) {
            LOGGER.info("addCategoryNode " + e.getMessage());
            return null;
        }
    }

    public CategoryNodeInfo updateCategoryNode(CategoryNodeInfo categoryNodeInfo) {
        try {
            CategoryNode categoryNode = new CategoryNode();
            UpicBeanUtils.copyProperties(categoryNodeInfo, categoryNode);
            categoryNode = categoryNodeRepository.saveAndFlush(categoryNode);
            UpicBeanUtils.copyProperties(categoryNode, categoryNodeInfo);
            return categoryNodeInfo;
        } catch (Exception e) {
            LOGGER.info("updateCategoryNode ：" + e.getMessage());
            return null;
        }
    }

    public Page<CategoryNodeInfo> searchCategoryNode(CategoryNodeCondition categoryNodeCondition, Pageable pageable) {
        Page<CategoryNode> categoryNodePage = null;
        try {
            categoryNodePage = categoryNodeRepository.findAll(new CategoryNodeSpec(categoryNodeCondition), pageable);
            return QueryResultConverter.convert(categoryNodePage, pageable, new AbstractDomain2InfoConverter<CategoryNode, CategoryNodeInfo>() {
                protected void doConvert(CategoryNode domain, CategoryNodeInfo info) throws Exception {
                    UpicBeanUtils.copyProperties(domain, info);
                }
            });
        } catch (Exception e) {
            LOGGER.info("searchCategoryNode：" + e.getMessage());
            return null;
        }
    }

    public CategoryNodeInfo getCategoryNodeById(long categoryNodeId) {
        try {
            CategoryNode categoryNode = categoryNodeRepository.findOne(categoryNodeId);
            if (categoryNode == null) throw new Exception();
            CategoryNodeInfo categoryNodeInfo = new CategoryNodeInfo();
            UpicBeanUtils.copyProperties(categoryNode, categoryNodeInfo);
            return categoryNodeInfo;
        } catch (Exception e) {
            LOGGER.info("getCategoryNodeById：" + e.getMessage());
            return null;
        }
    }

    @Override
    public List<CategoryNodeInfo> getCategoryNodeByFatherId(long fatherId) {
        List<CategoryNode> categoryNodeList = new ArrayList<CategoryNode>();
        try {
            List<CategoryNodeInfo> categoryNodeInfoList = new ArrayList<CategoryNodeInfo>();
            categoryNodeList = categoryNodeRepository.getCategoryNodeByFatherId(fatherId);
            for (int i = 0; i < categoryNodeList.size(); i++) {
                CategoryNodeInfo categoryNodeInfo = new CategoryNodeInfo();
                UpicBeanUtils.copyProperties(categoryNodeList.get(i), categoryNodeInfo);
                categoryNodeInfoList.add(categoryNodeInfo);
            }
            return categoryNodeInfoList;
        } catch (Exception e) {
            LOGGER.info("getCategoryNodeByFatherId：" + e.getMessage());
            return null;
        }
    }

    @Override
    public List<CategoryNodeInfo> searchCategoryNodeList(CategoryNodeCondition categoryNodeCondition) {
        List<CategoryNode> categoryNodeList = null;
        try {
            categoryNodeList = categoryNodeRepository.findAll(new CategoryNodeSpec(categoryNodeCondition));
            List<CategoryNodeInfo> categoryNodeInfoList = new ArrayList<>();
            for (CategoryNode categoryNode : categoryNodeList) {
                CategoryNodeInfo categoryNodeInfo = new CategoryNodeInfo();
                UpicBeanUtils.copyProperties(categoryNode, categoryNodeInfo);
                categoryNodeInfoList.add(categoryNodeInfo);
            }
            return categoryNodeInfoList;
        } catch (Exception e) {
            LOGGER.info("searchCategoryNodeList：" + e.getMessage());
            return null;
        }
    }
}

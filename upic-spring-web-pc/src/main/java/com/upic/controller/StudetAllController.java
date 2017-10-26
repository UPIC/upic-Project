package com.upic.controller;

import com.upic.common.document.excel.ExcelDocument;
import com.upic.condition.*;
import com.upic.dto.*;
import com.upic.enums.IntegralLogStatusEnum;
import com.upic.enums.IntegralLogTypeEnum;
import com.upic.service.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/stu")
public class StudetAllController {
	protected static final Logger LOGGER = LoggerFactory.getLogger(StudetAllController.class);

	@Autowired
	private IntegralLogService integralLogService;

	@Autowired
	private GrainCoinLogService grainCoinLogService;

	@Autowired
	private ProjectService projectService;

	@Autowired
	private BannerService bannerService;

	@Autowired
	private PrizeService prizeService;

	/**
	 * 获取当前用户的积分*
	 *
	 * @return
	 * @throws Exception
	 */
	@GetMapping("/getIntegeral")
	public double getIntegeral() throws Exception {
		try {
			// Authentication authentication =
			// SecurityContextHolder.getContext().getAuthentication();
			// System.out.println(authentication);
			// SocialUser user = null;
			// if (authentication != null) {
			// user = (SocialUser) authentication.getPrincipal();
			// }
			// if(user==null){
			// throw new Exception("获取用户失败");
			// }
			return integralLogService.watchIntegral(getUser().getUserNum());
		} catch (Exception e) {
			LOGGER.info("getIntegeral :" + e.getMessage());
			throw new Exception(e.getMessage());
		}
	}

	/**
	 * 获取当前用户的积分*
	 *
	 * @return
	 * @throws Exception
	 */
	@GetMapping("/getIntegeralByUserNum")
	public double getIntegeralByUserNum(String userNum) throws Exception {
		try {
			// Authentication authentication =
			// SecurityContextHolder.getContext().getAuthentication();
			// System.out.println(authentication);
			// SocialUser user = null;
			// if (authentication != null) {
			// user = (SocialUser) authentication.getPrincipal();
			// }
			// if(user==null){
			// throw new Exception("获取用户失败");
			// }
			return integralLogService.watchIntegral(userNum);
		} catch (Exception e) {
			LOGGER.info("getIntegeral :" + e.getMessage());
			throw new Exception(e.getMessage());
		}
	}

	/**
	 * 获取当前学生素拓币*
	 *
	 * @return
	 * @throws Exception
	 */
	@GetMapping("/getGrainCoin")
	public double getGrainCoin() throws Exception {
		try {
			// Authentication authentication =
			// SecurityContextHolder.getContext().getAuthentication();
			// System.out.println(authentication);
			// SocialUser user = null;
			// if (authentication != null) {
			// user = (SocialUser) authentication.getPrincipal();
			// }
			// if(user==null){
			// throw new Exception("获取用户失败");
			// }
			return grainCoinLogService.watchGrainCoin(getUser().getUserNum());
		} catch (Exception e) {
			LOGGER.info("getGrainCoin:" + e.getMessage());
			throw new Exception(e.getMessage());
		}
	}

	/**
	 * 获取当前学生素拓币*
	 *
	 * @return
	 * @throws Exception
	 */
	@GetMapping("/getGrainCoinByUserNum")
	public double getGrainCoinByUserNum(String userNum) throws Exception {
		try {
			// Authentication authentication =
			// SecurityContextHolder.getContext().getAuthentication();
			// System.out.println(authentication);
			// SocialUser user = null;
			// if (authentication != null) {
			// user = (SocialUser) authentication.getPrincipal();
			// }
			// if(user==null){
			// throw new Exception("获取用户失败");
			// }
			return grainCoinLogService.watchGrainCoin(userNum);
		} catch (Exception e) {
			LOGGER.info("getGrainCoin:" + e.getMessage());
			throw new Exception(e.getMessage());
		}
	}

	/**
	 * 获取banner图*
	 *
	 * @param pageable
	 * @param b
	 * @return
	 * @throws Exception
	 */
	@GetMapping("/getBanner")
	public Page<BannerInfo> getBanner(@PageableDefault(size = 10) Pageable pageable, BannerCondition b)
			throws Exception {
		try {
			return bannerService.searchBanner(b, pageable);
		} catch (Exception e) {
			LOGGER.info("getBanner:" + e.getMessage());
			throw new Exception(e.getMessage());
		}
	}

	/**
	 * 奖品交换*
	 *
	 * @param prizeId
	 * @return
	 */
	@GetMapping("/getExchangePrize")
	public PrizeInfo getExchangePrize(Long prizeId) {
		return null;
	}

	/**
	 * 查找积分列表*
	 *
	 * @param i
	 * @param pageable
	 * @return
	 * @throws Exception
	 */
	@GetMapping("/getPageIntegral")
	public Page<IntegralLogInfo> getPageIntegral(IntegralLogCondition i, @PageableDefault(size = 10) Pageable pageable)
			throws Exception {
		try {
			return integralLogService.searchIntegralLog(i, pageable);
		} catch (Exception e) {
			LOGGER.info("getProjectInfo:" + e.getMessage());
			throw new Exception(e.getMessage());
		}
	}

	/**
	 * 是否已报名*（需要修改）
	 *
	 * @param studentNum
	 * @param projectNum
	 * @return
	 * @throws Exception
	 */
	@GetMapping("/isSignUpByIntegralLogId")
	public String isSignUpByIntegralLogId(String studentNum, String projectNum) throws Exception {
		try {
			IntegralLogIdInfo integralLogIdInfo = new IntegralLogIdInfo(studentNum, projectNum);
			IntegralLogInfo integralLogInfo = integralLogService.getByIntegralLogId(integralLogIdInfo);
			return integralLogInfo == null ? "error" : "success";
		} catch (Exception e) {
			LOGGER.info("isSignUpByIntegralLogId:" + e.getMessage());
			throw new Exception(e.getMessage());
		}
	}

	/**
	 * 积分申请提交*
	 *
	 * @param integralLogInfo
	 * @param file
	 * @param request
	 * @return
	 */
	@GetMapping("/postIntegralLog")
	public IntegralLogInfo postIntegralLog(IntegralLogInfo integralLogInfo, @RequestParam("file") MultipartFile file,
			MultipartHttpServletRequest request) throws Exception {
		try {
			integralLogInfo.setStatus(IntegralLogStatusEnum.PENDING_AUDIT);
			integralLogInfo.setType(IntegralLogTypeEnum.VOLUNTARY_APPLICATION);
			IntegralLogIdInfo integralLogIdInfo = new IntegralLogIdInfo();
			UserInfo userInfo = getUser();
			integralLogIdInfo.setStudentNum(userInfo.getUserNum());
			integralLogIdInfo.setProjectNum("VOLUNTARY_APPLICATION" + userInfo.getUserNum() + (new Date()).getTime());
			integralLogInfo = integralLogService.saveIntegralLog(integralLogInfo);

			return integralLogInfo;
		} catch (Exception e) {
			LOGGER.info("postIntegralLog:" + e.getMessage());
			throw new Exception(e.getMessage());
		}
	}

	/**
	 * 根据自身编号获取参加过的自主申报项目（可能没用）
	 *
	 * @param pageable
	 * @return
	 * @throws Exception
	 */
	@GetMapping("/getIntegralLogByMySelf")
	public Page<IntegralLogInfo> getIntegralLogByMySelf(@PageableDefault(size = 10) Pageable pageable) {
		try {
			return integralLogService.getIntegralLogByMySelf(getUser().getUserNum(), pageable);
		} catch (Exception e) {
			LOGGER.info("getIntegralLogByMySelf:" + e.getMessage());
			return null;
		}
	}

	/**
	 * 学生查看自身的积分日志（可能没用）
	 *
	 * @param pageable
	 * @return
	 * @throws Exception
	 */
	@GetMapping("/getAllIntegralLogByStudentNum")
	public Page<IntegralLogInfo> getAllIntegralLogByStudentNum(@PageableDefault(size = 10) Pageable pageable)
			throws Exception {
		try {
			Page<IntegralLogInfo> integralLogInfoPage = integralLogService
					.getAllIntegralLogByStudentNum(getUser().getUserNum(), pageable);
			return integralLogInfoPage;
		} catch (Exception e) {
			LOGGER.info("getAllIntegralLogByStudentNum:" + e.getMessage());
			throw new Exception(e.getMessage());
		}
	}

	/**
	 * 学生查看积分日志（可能没用）
	 *
	 * @param pageable
	 * @return
	 * @throws Exception
	 */
	@GetMapping("/getAllIntegralLogByUserNum")
	public Page<IntegralLogInfo> getAllIntegralLogByUserNum(String userNum,
			@PageableDefault(size = 10) Pageable pageable) throws Exception {
		try {
			Page<IntegralLogInfo> integralLogInfoPage = integralLogService.getAllIntegralLogByStudentNum(userNum,
					pageable);
			return integralLogInfoPage;
		} catch (Exception e) {
			LOGGER.info("getAllIntegralLogByUserNum:" + e.getMessage());
			throw new Exception(e.getMessage());
		}
	}

	/**
	 * 获取申报中的积分（可能没用）
	 *
	 * @param pageable
	 * @return
	 * @throws Exception
	 */
	@GetMapping("/getIntegralLogDeclaring")
	public Page<IntegralLogInfo> getIntegralLogDeclaring(@PageableDefault(size = 10) Pageable pageable)
			throws Exception {
		try {
			return integralLogService.getIntegralLogDeclaring(getUser().getUserNum(), pageable);
		} catch (Exception e) {
			LOGGER.info("getIntegralLogDeclaring:" + e.getMessage());
			throw new Exception(e.getMessage());
		}
	}

	/**
	 * 积分导入
	 * 
	 * @param file
	 * @param baseModel
	 * @return
	 */
	public String loadIntegralLogInfo(@RequestParam("file") CommonsMultipartFile file, List<String> baseModel) {
		try {
			InputStream inputStream = file.getInputStream();
			if (inputStream == null) {
				throw new Exception("文件为空");
			}
			List<Object> list = ExcelDocument.upload(inputStream, (String[]) baseModel.toArray(), IntegralLogInfo.class,
					file.getName());
			integralLogService.saveAll(list);
		} catch (Exception e) {
			LOGGER.info("loadIntegralLogInfo:" + e.getMessage());
			return e.getMessage();
		}
		return "SUCCESS";
	}

	/**
	 * 积分导出
	 * @return
	 */
	public void exportIntegralLog(HttpServletResponse response,IntegralLogCondition condition,List<String> baseModel) {
		try {
			List<IntegralLogInfo> byProjectNum = integralLogService.getByProjectNum("");
//			ExcelDocument.download((String [])baseModel.toArray(), IntegralLogInfo.class, byProjectNum);
		}catch (Exception e) {
			LOGGER.info("loadIntegralLogInfo:" + e.getMessage());
			try {
				response.getWriter().println("下载失败！");
			} catch (IOException e1) {
			}
		}
	}
	
	private UserInfo getUser() {
		// Authentication authentication =
		// SecurityContextHolder.getContext().getAuthentication();
		// SocialUser so=(SocialUser) authentication.getPrincipal();
		UserInfo userInfo = new UserInfo();
		userInfo.setUsername("山鸡");
		userInfo.setUserNum("1522110240");
		userInfo.setPic("assets/i/shanji.jpg");
		return userInfo;
	}
}

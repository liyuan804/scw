package com.kfz.scwproject1.controller;

import com.alibaba.fastjson.JSON;
import com.kfz.scwcommon.util.AppResponse;

import com.kfz.scwproject1.constance.ProjectConstant;
import com.kfz.scwproject1.service.OSSTemplate;
import com.kfz.scwproject1.vo.req.BaseVo;
import com.kfz.scwproject1.vo.req.ProjectRedisStorageVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;



import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

@Api(tags = "项目发起模块")
@RequestMapping("/project/create")
@RestController
@Slf4j
public class ProjectCreateController {

	@Autowired
	private OSSTemplate ossTemplate;
	
	@Autowired
	private StringRedisTemplate stringRedisTemplate;

	@ApiOperation(value = "1-项目初始创建")
	@PostMapping("/init")
	public AppResponse<Object> init(String accessToken) {

		if(StringUtils.isEmpty(accessToken)){
			 AppResponse response=AppResponse.fail(null);
			 response.setMsg("创建项目必须要accessToken");
			 return response;

		}

		//1 获取用户令牌
		String memberId = stringRedisTemplate.opsForValue().get(accessToken);
		if(StringUtils.isEmpty(memberId)){
			AppResponse response=AppResponse.fail(null);
			response.setMsg("请必须登录");
			return response;

		}
		try {
			//2  创建项目令牌
			String projectToken = UUID.randomUUID().toString().replace("-", "");

			//3  初始化ProjectRedisStrorageVo
			ProjectRedisStorageVo vo=new ProjectRedisStorageVo();
			vo.setAccessToken(accessToken);
			vo.setProjectToken(projectToken);

			//4  拷贝用户令牌和项目令牌信息到ProjectRedisStrorageVo
			//BeanUtils.copyProperties();

			String json = JSON.toJSONString(vo);

			//5 项目令牌作为KEY  ProjectRedisStrorageVo的json作为值

			//  存储ProjectRedisStrorageVo到Redis数据库中
			stringRedisTemplate.opsForValue().set(ProjectConstant.TEMP_PROJECT_PREFIX+vo.getProjectToken(),json);

			return AppResponse.ok(vo);
		} catch (Exception e) {
			e.printStackTrace();
			AppResponse response=AppResponse.fail(null);
			response.setMsg("创建ProjectToken异常");
		}


		return AppResponse.fail(null);
	}
	
	@ApiOperation(value = "添加项目回报档位")
	@PostMapping("/return")
	public AppResponse<Object> returnDetail() {
		return AppResponse.ok("ok");
	}
	
	@ApiOperation(value = "删除项目回报档位")
	@DeleteMapping("/return")
	public AppResponse<Object> deleteReturnDetail() {
		return AppResponse.ok("ok");
	}
	
	@ApiOperation(value = "上传图片")
	@PostMapping("/upload")
	public AppResponse<Object> upload(@RequestParam("file") MultipartFile[] file) {

		Map<String,Object> map = new HashMap<>();
		List<String> list = new ArrayList<>();
		if(file!=null && file.length>0){
			for (MultipartFile item : file) {
				if(!item.isEmpty()){
					String upload = null;
					try {
						upload = ossTemplate.upload(item.getInputStream(), item.getOriginalFilename());
					} catch (IOException e) {
						e.printStackTrace();
					}
					list.add(upload);
				}
			}
		}
		map.put("urls", list);
		log.debug("ossTemplate信息：{},文件上传成功访问路径：{}",ossTemplate,list);

//文件上传
		return AppResponse.ok(map);

	}

	@ApiOperation(value = "2-项目基础信息")
	@PostMapping("/baseProject")
	public AppResponse<Object> baseProjectInfo(){

		return  AppResponse.ok(null);
	}


	
	@ApiOperation(value = "确认项目法人信息")
	@PostMapping("/confirm/legal")
	public AppResponse<Object> legal() {
		return AppResponse.ok("ok");
	}
	
	@ApiOperation(value = "项目草稿保存")
	@PostMapping("/tempsave")
	public AppResponse<Object> tempsave() {
		return AppResponse.ok("ok");
	}
	
	@ApiOperation(value = "项目提交审核申请")
	@PostMapping("/submit")
	public AppResponse<Object> submit() {
		return AppResponse.ok("ok");
	}
	
}

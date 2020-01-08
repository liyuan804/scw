package com.kfz.scwproject.controller;

import com.kfz.scwcommon.util.AppResponse;
import com.kfz.scwproject.service.OSSTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Api(tags = "项目发起模块")
@RequestMapping("/project/create")
@RestController
@Slf4j
public class ProjectCreateController {

	@Autowired
	private OSSTemplate ossTemplate;

	@ApiOperation(value = "项目初始创建")
	@PostMapping("/init")
	public AppResponse<Object> init() {
		return AppResponse.ok("ok");
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

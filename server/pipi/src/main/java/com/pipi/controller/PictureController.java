package com.pipi.controller;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.aliyun.oss.OSSClient;
import com.pipi.constant.OSSConstant;
import com.pipi.exception.ImageException;
import com.pipi.utils.PIPIUtils;
import com.pipi.utils.json.JsonUtils;

/**
 * 图片上传
 * 
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/pic")
public class PictureController {

	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	@ResponseBody
	public String fileUpload(MultipartFile uploadFile) {
		// 接受上传的文件
		// 取扩展名
		String originFilename = uploadFile.getOriginalFilename();
		String etc = originFilename.substring(originFilename.lastIndexOf("."));
		// 上传到图片服务器
		if (uploadFile == null || uploadFile.getSize() <= 0) {
			throw new ImageException("图片不能为空");
		}
		OSSClient ossClient = null;
		try {
			// endpoint以杭州为例，其它region请按实际情况填写
			String endpoint = OSSConstant.ENDPOINT;
			// 阿里云主账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM账号进行API访问或日常运维，请登录
			// https://ram.console.aliyun.com 创建RAM账号
			String accessKeyId = OSSConstant.ACCESSKEYID;
			String accessKeySecret = OSSConstant.ACCESSKEYSECRET;
			// 创建OSSClient实例
			ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
			// 上传byte数组
			InputStream content = uploadFile.getInputStream();

			String fileName = OSSConstant.FILEDIR + PIPIUtils.getImgId() + etc;
			ossClient.putObject(OSSConstant.BUCKETNAME, fileName, content);

			String imgUrl = OSSConstant.PREFIX + fileName;

			Map<String, Object> result = new HashMap<>();
			result.put("error", 0);
			result.put("url", imgUrl);
			return JsonUtils.objectToJson(result);

		} catch (Exception e) {
			throw new ImageException("图片上传失败");
		} finally {
			// 关闭client
			ossClient.shutdown();
		}

	}
}
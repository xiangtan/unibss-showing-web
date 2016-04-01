package com.ai.showing.controller;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.ai.showing.api.IPartnerDubboApi;
import com.ai.showing.entity.Partner;
import com.alibaba.fastjson.JSON;

@Controller
@RequestMapping("/partner")
public class PartnerController
{
	private static Logger		log	= Logger.getLogger(PartnerController.class);
	@Autowired
	private IPartnerDubboApi	partnerDubboApi;

	/*
	 * /partner/info/6
	 */
	@RequestMapping("/info/{id}")
	public String info(@PathVariable("id") int id, Model model)
	{
		log.info(id);
		Partner partner = partnerDubboApi.getById(id);
		log.info(JSON.toJSONString(partner));
		model.addAttribute(partner);
		return "partner/info";
	}

	/*
	 * /partner/info?id=6
	 */
	@RequestMapping(value = "/info", method = RequestMethod.GET)
	public String info1(@RequestParam("id") int id, Model model)
	{
		log.info(id);
		Partner partner = partnerDubboApi.getById(id);
		log.info(JSON.toJSONString(partner));
		model.addAttribute(partner);
		return "partner/info";
	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String add()
	{
		return "partner/add";
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(@ModelAttribute Partner partner)
	{
		return "partner/add";
	}

	@RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
	public @ResponseBody Partner getPartnerAsResponseBody(@PathVariable int id)
	{
		return partnerDubboApi.getById(id);
	}

	@RequestMapping(value = "/jsontype/get/{id}", method = RequestMethod.GET)
	public ResponseEntity<Partner> getPartnerAsResponseEntity(@PathVariable int id)
	{
		Partner partner = partnerDubboApi.getById(id);
		return new ResponseEntity<Partner>(partner, HttpStatus.OK);
	}

	@RequestMapping(value = "/upload", method = RequestMethod.GET)
	public String showUploadPage(@RequestParam(value = "multi", required = false) Boolean multi)
	{
		if (multi != null && multi)
		{
			return "upload/multifile";
		}
		return "upload/file";
	}

	@RequestMapping(value = "/doUpload", method = RequestMethod.POST)
	public String doUploadFile(@RequestParam("file") MultipartFile file) throws IOException
	{

		if (!file.isEmpty())
		{
			log.debug("Process file: {}" + file.getOriginalFilename());
			FileUtils.copyInputStreamToFile(file.getInputStream(),
					new File("c:\\temp\\upload\\", System.currentTimeMillis() + file.getOriginalFilename()));
		}

		return "success";
	}

	@RequestMapping(value = "/doUpload2", method = RequestMethod.POST)
	public String doUploadFile2(MultipartHttpServletRequest multiRequest) throws IOException
	{

		Iterator<String> filesNames = multiRequest.getFileNames();
		while (filesNames.hasNext())
		{
			String fileName = filesNames.next();
			MultipartFile file = multiRequest.getFile(fileName);
			if (!file.isEmpty())
			{
				log.debug("Process file: {}" + file.getOriginalFilename());
				FileUtils.copyInputStreamToFile(file.getInputStream(),
						new File("c:\\temp\\upload\\", System.currentTimeMillis() + file.getOriginalFilename()));
			}

		}

		return "success";
	}
}

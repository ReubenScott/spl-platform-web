package com.kindustry.system.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kindustry.framework.web.BaseController;

/**
 * <p>
 * 图表演示
 * </p>
 * 
 * @author hubin
 * @Date 2016-05-11
 */
@Controller
@RequestMapping("/sys/echarts")
public class EchartsController extends BaseController {

	/**
	 * 地图
	 */
	@RequestMapping("/map")
	public String map(Model model) {
		
		return "/echarts/map";
	}

}

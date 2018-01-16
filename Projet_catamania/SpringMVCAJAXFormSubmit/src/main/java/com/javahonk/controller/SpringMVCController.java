package com.javahonk.controller;

import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.servlet.http.HttpServletRequest;

import org.codehaus.jackson.map.util.JSONPObject;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import fr.univlr.elasticsearch.ElasticSearchDriver;

//import fr.univlr.elasticsearch.ElasticSearchDriver;
//import jdk.nashorn.api.scripting.JSObject;


@Controller
public class SpringMVCController {
	
	
	@RequestMapping(value = "/helloWorld.web", method = RequestMethod.GET)
	public ModelAndView printWelcome(@ModelAttribute("user") Users user) throws UnknownHostException {
		ElasticSearchDriver els = new ElasticSearchDriver(InetAddress.getLoopbackAddress(), 9300);
		ModelAndView mav = new ModelAndView("SubmitForm");
		mav.addObject("message", "Hello World!!!");

		return mav;

	}

	@RequestMapping(value="/submitForm.web", method = RequestMethod.POST)
    public @ResponseBody Users  submittedFromData(@RequestBody Users user, HttpServletRequest request) throws UnknownHostException {	
		// TODO Auto-generated method stub
/*		ElasticSearchDriver els = new ElasticSearchDriver(InetAddress.getLoopbackAddress(), 9300);
		System.out.println(InetAddress.getLoopbackAddress());
		els.index("user", "credentials", JSONObject.valueToString("toto"));*/
		return user;
	}	
	
}

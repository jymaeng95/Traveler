package com.traveler.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Controller
@Log4j
@AllArgsConstructor
@RequestMapping("/")
public class TravelerController {
	/*
	 * @PostMapping("/") public String index(Model model) {
	 * 
	 * log.info("index"); return "traveler_index"; }
	 */
	@GetMapping("")
	public String tavelerIndex() {
		log.info("Traveler start");
		return "/index/index";
	}
	
}

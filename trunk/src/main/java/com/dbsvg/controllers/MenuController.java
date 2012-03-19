package com.dbsvg.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Handles requests for the application menu.
 */
@Controller
public class MenuController extends AbstractDiagramInitializingController {

	@RequestMapping(value = { "/menu", "/" })
	public String showMenu() throws Exception {

		initIDAO();
		return "menu";
	}

}
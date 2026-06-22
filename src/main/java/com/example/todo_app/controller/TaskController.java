package com.example.todo_app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.todo_app.service.TaskService;

/**
 * タスクコントローラー
 * @author koki_shinzato
 */
@Controller
public class TaskController {
	
	@Autowired
	private TaskService taskService;
	
	/**
	 * 一覧画面へアクセス
	 * 全タスクデータを取得し、Viewへ渡す → list.htmlへ遷移
	 * @param model
	 * @return
	 */
	@GetMapping("/task/list")
	public String list(Model model) {
		model.addAttribute("tasks", taskService.selectAll());
		return "list";
	}
}

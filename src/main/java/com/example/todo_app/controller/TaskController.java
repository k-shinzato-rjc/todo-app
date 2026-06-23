package com.example.todo_app.controller;

import java.util.Objects;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.todo_app.dto.TaskDto;
import com.example.todo_app.form.TaskForm;
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
	 * 全タスクデータを取得し、Modelへ渡す → list.htmlへ遷移
	 * @param model
	 * @return タスク一覧画面（list.html）
	 */
	@GetMapping("/task/list")
	public String list(Model model) {
		model.addAttribute("tasks", taskService.selectAll());
		return "list";
	}
	
	/**
	 * 一覧画面の新規登録ボタンを押下
	 * 空のFormをModelに渡し、add.htmlへ遷移
	 * @param model
	 * @return 新規登録画面（add.html）
	 */
	@GetMapping("/task/add")
	public String add(Model model) {
		model.addAttribute("form", new TaskForm());
		return "add";
	}
	
	/**
	 * 一覧画面テーブル内から更新ボタン押下
	 * ID該当のタスクをFormに変換 → Viewへ渡す
	 * @param taskId
	 * @param model
	 * @return 更新画面(edit.html） or　エラー画面（error.html）
	 */
	@PostMapping("/task/edit")
	public String edit(@RequestParam("taskId") Integer taskId, Model model) {
		TaskDto taskDto = taskService.selectById(taskId);
		if(Objects.nonNull(taskDto)) {
			model.addAttribute("form", taskDto.toForm());
			return "edit";
		}
		
		return "error";
	}
	
	/**
	 * 登録画面 or 変更画面で確認ボタンを押下
	 * バリデーションチェック → 確認画面 or 元の入力画面（内容保持）へ遷移
	 * @param taskForm
	 * @param result
	 * @param model
	 * @return 確認画面（confirm.html） or 元の入力画面（add.html or edit.html）
	 */
	@PostMapping("/task/confirm")
	public String confirm(@Valid @ModelAttribute("form") TaskForm taskForm,
			BindingResult result, Model model) {
		
		if(result.hasErrors()) {
			return "add".equals(taskForm.getSubmitView()) ? "add" : "edit";
		}
		
		return "confirm";
	}
	
	/**
	 * 確認画面で戻るボタンを押下
	 * Form内容を保持して元の入力画面へ遷移（Formの入力元画面を判別）
	 * @param taskForm
	 * @param model
	 * @return 新規登録画面（add.html） or 変更画面（edit.html）　or エラー画面（error.html）
	 */
	@PostMapping("/back")
	public String back(@ModelAttribute("form") TaskForm taskForm, Model model) {
		
		if(Objects.nonNull(taskForm.getSubmitView())) {
			return "add".equals(taskForm.getSubmitView()) ? "add" : "edit";
		}
		
		return "error";
	}
	
	/**
	 * 確認画面で完了ボタンを押下
	 * 登録画面からの入力 → insertを実行, 更新画面からの入力 → updateを実行
	 * 遷移元判別文字列をcompleteへリダイレクト
	 * @param taskForm
	 * @param model
	 * @param redirect
	 * @return 完了表示処理へリダイレクト
	 */
	@PostMapping("/task/save")
	public String save(@ModelAttribute("form") TaskForm taskForm, Model model, RedirectAttributes redirect) {
		
		if("add".equals(taskForm.getSubmitView())) {
			taskService.insert(taskForm.toDto());
			
		} else {
			taskService.update(taskForm.toDto());
		}
		
		redirect.addAttribute("submitView", taskForm.getSubmitView());
		return "redirect:/task/complete";
	}
	
	/**
	 * 一覧画面テーブル内の削除ボタン押下
	 * IDを受け取り、該当タスクをModelへ渡す　→ 削除確認画面(deleteConfirm.html）へ遷移
	 * @param taskId
	 * @param model
	 * @return
	 */
	@PostMapping("/task/deleteConf")
	public String deleteConf(@RequestParam("taskId") Integer taskId, Model model) {
		model.addAttribute("task", taskService.selectById(taskId));
		return "deleteConfirm";
	}
	
	/**
	 * 削除確認画面で完了ボタンを押下
	 * 削除処理 → 完了画面表示処理へリダイレクト
	 * @param taskId
	 * @param redirectAttributes
	 * @return
	 */
	@PostMapping("/task/delete")
	public String delete(@RequestParam Integer taskId, RedirectAttributes redirectAttributes) {
		taskService.delete(taskId);
		redirectAttributes.addAttribute("submitView", "delete");
		return "redirect:/task/complete";
	}
	
	/**
	 * リダイレクトで受け取った遷移元文字列に該当した成功メッセージをModelへ渡す → 完了画面へ遷移
	 * @param taskId
	 * @param model
	 * @return 完了画面（complete.html）
	 */
	@GetMapping("/task/complete")
	public String complete(@RequestParam("submitView") String submitView, Model model) {
		switch(submitView) {
			case "add" -> model.addAttribute("success", "saved");
			case "edit" -> model.addAttribute("success", "updated");
			case "delete" -> model.addAttribute("success", "delete");
		}
		
		return "complete";
	}
}

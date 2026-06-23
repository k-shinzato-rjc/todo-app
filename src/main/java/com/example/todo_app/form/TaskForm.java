package com.example.todo_app.form;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import com.example.todo_app.dto.TaskDto;

import lombok.Data;

/**
 * Form
 */
@Data
public class TaskForm {
	
	// タスクId（主キー） 
	private Integer taskId;
	
	// タスク名
	@NotBlank(message="タイトルを入力してください。")
	@Size(max=100, message="100文字以内で入力してください。")
	private String title;
	
	// タスク詳細
	@Size(max=200, message="説明は200文字以内で入力してください。")
	private String description;
	
	// 期限
	@NotNull(message="期限を入力してください。")
	private LocalDate deadline;
	
	// 進捗状況（未着手/作業中/完了)
	private String status;
	
	// 登録ユーザーID
	private Integer userId;
	
	// 登録日
	private LocalDate createdAt;
	
	// 更新日
	private LocalDate updatedAt;
	
	// 削除フラグ（0:有効 1:無効）
	private Integer deleteFlg;
	
	// 転送元画面 判別用
	private String submitView;
	
	/**
	 * 画面からの入力値によって、対応するステータスをセット
	 * 最初から日本語ステータスが入っていた場合（確認画面以降など）、そのままセット
	 * @param status 0 ~ 2 の文字列
	 */
	public void setStatus(String status) {
		switch(status) {
			case "0" -> this.status = "未着手";
			case "1" -> this.status = "作業中";
			case "2" -> this.status = "完了";
			default -> this.status = status;
		}
	}
	
	public String getStatus() {
		switch(status) {
			case "未着手": return "0";
			case "作業中": return "1";
			case "完了" : return "2";
		}
		
		return "";
	}
	
	/**
	 * Form → Dto 変換
	 * @return タスクデータ（Dto）
	 */
	public TaskDto toDto() {
		TaskDto taskDto = new TaskDto();
		taskDto.setTaskId(taskId);
		taskDto.setTitle(title);
		taskDto.setDescription(description);
		taskDto.setDeadline(deadline);
		taskDto.setStatus(status);
		taskDto.setUserId(userId);
		taskDto.setCreatedAt(createdAt);
		taskDto.setUpdatedAt(updatedAt);
		taskDto.setDeleteFlg(deleteFlg);
		
		return taskDto;
	}
}



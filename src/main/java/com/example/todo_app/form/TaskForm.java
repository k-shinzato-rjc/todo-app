package com.example.todo_app.form;

import java.time.LocalDate;

import com.example.todo_app.dto.TaskDto;

import lombok.Data;

/**
 * Form
 */
@Data
public class TaskForm {
	
	// タスクId（主キー） 読み取り専用
	private Integer taskId;
	// タスク名
	private String title;
	// タスク詳細
	private String description;
	// 締切
	private LocalDate deadline;
	// 進捗状況（未着手/作業中/完了)
	private String status;
	// 登録ユーザーID
	private Integer userId;
	// 登録日　読み取り専用
	private LocalDate createdAt;
	// 更新日 読み取り専用
	private LocalDate updatedAt;
	// 削除フラグ（0:有効 1:無効）
	private Integer deleteFlg;
	
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



package com.example.todo_app.dto;

import java.time.LocalDate;

import com.example.todo_app.entity.TaskEntity;

import lombok.Data;

/**
 * Dto
 * @author koki_shinzato
 */
@Data
public class TaskDto {

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
	 * Dto → Entity 変換
	 * @return タスクデータ（Entity)
	 */
	public TaskEntity toEntity() {
		TaskEntity taskEntity = new TaskEntity();
		taskEntity.setTaskId(taskId);
		taskEntity.setTitle(title);
		taskEntity.setDescription(description);
		taskEntity.setDeadline(deadline);
		taskEntity.setStatus(status);
		taskEntity.setUserId(userId);
		taskEntity.setCreatedAt(createdAt);
		taskEntity.setUpdatedAt(updatedAt);
		taskEntity.setDeleteFlg(deleteFlg);
		
		return taskEntity;
	}
}

package com.example.todo_app.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.todo_app.entity.TaskEntity;

/**
 * Mybatis用マッパー
 * @author koki_shinzato
 */
@Mapper
public interface TaskMapper {
	
	/**
	 * 全タスクデータを取得
	 * @return タスクリスト（Entity)
	 */
	List<TaskEntity> selectAll();
	
	/**
	 * タスク登録
	 * @param taskEntity タスクデータ（Entity）
	 */
	void insert(TaskEntity taskEntity);
	
	/**
	 * タスク更新
	 * @param taskId　変更タスクID
	 */
	void update(TaskEntity taskEntity);
	
	/**
	 * ID指定でタスク削除
	 * @param taskid 削除対象タスクID
	 */
	void deleteById(Integer taskid);
	
	/**
	 * ID指定でタスクを取得
	 * @param taskId
	 * @return タスクデータ（Entity）
	 */
	TaskEntity selectById(Integer taskId);
}

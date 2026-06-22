package com.example.todo_app.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.todo_app.dto.TaskDto;
import com.example.todo_app.entity.TaskEntity;
import com.example.todo_app.mapper.TaskMapper;

/**
 * タスクサービス
 * @author koki_shinzato
 */
@Service
public class TaskService {
	
	@Autowired
	private TaskMapper taskMapper;
	
	/**
	 * 全タスクデータを取得
	 * @return タスクリスト（Dto）
	 */
	public List<TaskDto> selectAll(){
		return toDtoList(taskMapper.selectAll());
	}
	
	/**
	 * IDに該当するタスクデータを取り出す
	 * @param taskId
	 * @return タスクデータ（Dto）
	 */
	public TaskDto selectById(Integer taskId) {
		TaskEntity taskEntity = taskMapper.selectById(taskId);
		return taskEntity.toDto();
	}
	
	/**
	 * タスク登録
	 * @param taskDto
	 */
	public void insert(TaskDto taskDto) {
		taskMapper.insert(taskDto.toEntity());
	}
	
	/**
	 * Entityリスト → Dtoリスト 変換
	 * @param entityList
	 * @return Dtoリスト
	 */
	public List<TaskDto> toDtoList(List<TaskEntity> entityList){
		List<TaskDto> dtoList = new ArrayList<TaskDto>();
		entityList.stream().forEach(e -> dtoList.add(e.toDto()));
		return dtoList;
	}
}

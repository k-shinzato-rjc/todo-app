package com.example.todo_app.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.todo_app.dto.TaskDto;
import com.example.todo_app.entity.TaskEntity;

/**
 * タスクサービス
 * @author koki_shinzato
 */
@Service
public class TaskService {
	
	
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

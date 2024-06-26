package com.squad22podA.task_mgt.controller;


import com.squad22podA.task_mgt.entity.enums.Status;
import com.squad22podA.task_mgt.payload.request.TaskRequest;
import com.squad22podA.task_mgt.payload.response.TaskResponseDto;
import com.squad22podA.task_mgt.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    private final TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping("/new-task")
    public ResponseEntity<TaskResponseDto> createTask(@RequestBody TaskRequest taskRequest, @RequestParam String email) {

        return ResponseEntity.ok(taskService.createTask(email, taskRequest));

    }

    // edit the task
    @PutMapping("/edit-task")
    public ResponseEntity<TaskResponseDto> editTask(@RequestBody TaskRequest taskRequest, @RequestParam String email, @RequestParam Long id) {

        return ResponseEntity.ok(taskService.editTask(email, id, taskRequest));

    }

    // get task by id
    @GetMapping("/get-task")
    public ResponseEntity<TaskResponseDto> getTask( @RequestParam String email, @RequestParam Long id) {

        return ResponseEntity.ok(taskService.getTask(email, id));

    }

    // get task by status
    @GetMapping("/by-status")
    public ResponseEntity<List<TaskResponseDto>> getTaskByStatus(@RequestParam String email, @RequestParam String status) {
        return ResponseEntity.ok(taskService.getTaskByStatus(email, Status.valueOf(status)));
    }

    // get all task for a single user
    @GetMapping("/get-all-task")
    public ResponseEntity<List<TaskResponseDto>> getAllTask(@RequestParam String email) {
        return ResponseEntity.ok(taskService.getAllTask(email));
    }
}

package com.akinc.crud_module.api.task;

import com.akinc.crud_module.application.service.task.TaskService;
import com.akinc.crud_module.domain.request.task.CreateTaskRequest;
import com.akinc.crud_module.domain.request.task.UpdateTaskRequest;
import com.akinc.crud_module.domain.response.task.GetTaskResponse;
import com.akinc.crud_module.infrastructure.persistence.annotation.OwnershipCheck;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/task")
@RequiredArgsConstructor
public class TaskController {

    private static final String MEMBER_ID_HEADER = "X-Member-Id";

    private final TaskService service;


    @PostMapping("")
    public ResponseEntity<String> createTask(@RequestHeader(value = MEMBER_ID_HEADER) UUID memberId, @Valid @RequestBody CreateTaskRequest request) {

        service.createTask(request, memberId);
        return ResponseEntity.ok().build();
    }

    @OwnershipCheck
    @PutMapping("/{id}")
    public ResponseEntity<String> updateTask(@PathVariable("id") Long id,
                                             @Valid @RequestBody UpdateTaskRequest request) {

        service.updateTask(id, request);
        return ResponseEntity.ok().build();

    }

    @OwnershipCheck
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable("id") Long id) {

        service.deleteTask(id);
        return ResponseEntity.ok().build();
    }

    @OwnershipCheck
    @PutMapping("/complete/{id}")
    public ResponseEntity<String> completeTask(@PathVariable("id") Long id) {

        service.completeTask(id);
        return ResponseEntity.ok().build();
    }

    @OwnershipCheck
    @GetMapping("/detail/{id}")
    public ResponseEntity<GetTaskResponse> getById(@PathVariable("id") Long id) {

        var response = service.getDetailById(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/all")
    public ResponseEntity<List<GetTaskResponse>> getAll(@RequestHeader(value = MEMBER_ID_HEADER) UUID memberId,
                                                        @Positive @RequestParam(defaultValue = "0") int page,
                                                        @Positive @RequestParam(defaultValue = "10") int size)

    {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(service.getWithPagination(memberId, pageable));
    }
}

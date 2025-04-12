package com.intellivort_Assi.autosave.controller;

import com.intellivort_Assi.autosave.dto.AutoSaveRequest;
import com.intellivort_Assi.autosave.service.AutoSaveService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

@RestController
@RequestMapping("/autosave")
@RequiredArgsConstructor
public class AutoSaveController {

    private final AutoSaveService autoSaveService;

    @PostMapping
    public ResponseEntity<String> autoSave(@RequestBody AutoSaveRequest request) {
        try {
            autoSaveService.saveResponses(request);
            return ResponseEntity.ok("Responses saved successfully");
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while saving responses.");
        }
    }
}

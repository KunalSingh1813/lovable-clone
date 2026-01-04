package com.personal.projects.lovable_clone.service;

import com.personal.projects.lovable_clone.dto.project.FileControlResponse;
import com.personal.projects.lovable_clone.dto.project.FileNode;

import java.util.List;

public interface FileService {
    List<FileNode> getFileTree(Long projectId, Long userId);

    FileControlResponse getFileContent(Long projectId, String path, Long userId);
}

package com.task.permission.service;

import com.task.permission.model.Permission;
import com.task.permission.repository.PermissionRepository;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class PermissionService {
    private final PermissionRepository permissionRepository;

    public PermissionService(PermissionRepository permissionRepository) {
        this.permissionRepository = permissionRepository;
    }

    public void savePermisson(Permission permission){
        permissionRepository.save(permission);
    }

    public Permission findPermissionById(Integer id){
        return permissionRepository.findById(id).orElseThrow(()->new NoSuchElementException(id+" numaralı izin bulunamadı"));
    }


}

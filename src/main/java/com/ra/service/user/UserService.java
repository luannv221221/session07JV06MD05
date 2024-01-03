package com.ra.service.user;

import com.ra.exception.CustomException;
import com.ra.model.dto.request.UserRequest;
import com.ra.model.dto.response.UserResponse;
import com.ra.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {
    Page<UserResponse> findAll(Pageable pageable);
    UserResponse saveOrUpdate(UserRequest userRequest) throws CustomException;
    UserResponse findById(Long id) throws CustomException;
}

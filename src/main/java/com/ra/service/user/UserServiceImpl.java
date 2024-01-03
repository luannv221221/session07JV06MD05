package com.ra.service.user;

import com.ra.exception.CustomException;
import com.ra.model.dto.request.UserRequest;
import com.ra.model.dto.response.UserResponse;
import com.ra.model.entity.User;
import com.ra.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserRepository userRepository;
    @Override
    public Page<UserResponse> findAll(Pageable pageable) {
        Page<User> userPage = userRepository.findAll(pageable);
        return userPage.map(UserResponse::new);
    }
    @Override
    public UserResponse saveOrUpdate(UserRequest userRequest) throws CustomException {
        // check trung
        if(userRepository.existsByUserName(userRequest.getUserName())){
            throw new CustomException("UserName existed");
        }
        User user = User.builder()
                .userName(userRequest.getUserName()).
                fullName(userRequest.getFullName()).
                password(userRequest.getPassword()).build();
        User userNew = userRepository.save(user);
        return UserResponse.builder().id(userNew.getId()).userName(userNew.getUserName()).fullName(user.getFullName()).build();
    }

    @Override
    public UserResponse findById(Long id) throws CustomException {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()){
            User user = userOptional.get();

            return UserResponse.builder().
                    userName(user.getUserName()).
                    fullName(user.getFullName()).status(user.getStatus())
                    .build();
        }

        throw new CustomException("Nót phao");
    }
}

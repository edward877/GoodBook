package com.goodbook.book.controller.api;


import com.goodbook.book.model.CommentDto;
import com.goodbook.book.model.UserDto;
import com.goodbook.book.service.interfaces.CommentService;
import com.goodbook.book.service.interfaces.UserService;
import org.apache.tomcat.jni.Error;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@PreAuthorize("hasAnyRole('ADMIN', 'USER')")
@RestController
@RequestMapping(value = "/api/comments/")
public class CommentController {


    @Autowired
    private CommentService commentService;

    @Autowired
    private UserService userService;

    @PostMapping("comment")
    public  Map comment(@ModelAttribute CommentDto commentDto, int bookId) {
        commentDto.setDate(LocalDateTime.now());
        try {
            Optional<UserDto> user = userService.getUser();
            user.ifPresent(commentDto::setUser);
        } catch (Exception e) {
            Map map = new HashMap();
            map.put("error", e.getMessage());
            return map;
        }
        return commentService.addComment(commentDto, bookId);
    }
}

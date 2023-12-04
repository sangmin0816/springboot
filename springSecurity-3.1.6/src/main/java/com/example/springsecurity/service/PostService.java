package com.example.springsecurity.service;

import com.example.springsecurity.dto.PostDTO;
import com.example.springsecurity.entity.Post;
import org.springframework.beans.factory.annotation.Autowired;

public interface PostService {
    public Long postAdd(PostDTO dto);
    public Long postEdit(Post post);
    public Long postRemove(Long pno);

    public void postDelete(Long pno);
}

package com.example.springsecurity.service;

import com.example.springsecurity.dto.PostDTO;
import com.example.springsecurity.entity.Layout;
import com.example.springsecurity.entity.Post;
import com.example.springsecurity.entity.PostFile;
import com.example.springsecurity.repository.LayoutRepository;
import com.example.springsecurity.repository.PostFileRepository;
import com.example.springsecurity.repository.PostRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class PostServiceImpl implements PostService{
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private PostRepository postRepo;
    @Autowired
    private LayoutRepository layoutRepo;
    @Autowired
    private PostFileRepository fileRepo;

    @Override
    public Long postAdd(PostDTO dto){
        // bno, author, content 필수
        Post post = mapper.map(dto, Post.class);
        Long pno = postRepo.save(post).getPno();

        if(dto.getLayout()!=null){
            Layout layout = mapper.map(dto, Layout.class);
            layoutRepo.save(layout);
        }

        if(dto.getFile().getSavePath()!=null){
            PostFile file = mapper.map(dto, PostFile.class);
            fileRepo.save(file);
        }

        return pno;
    }

    @Override
    public Long postEdit(Post post) {
        return postRepo.save(post).getPno();
    }

    @Override
    public Long postRemove(Long pno) {
        Post post = postRepo.findById(pno).orElseThrow();
        post.setStatus("REMOVE");

        return postRepo.save(post).getPno();
    }

    @Override
    public void postDelete(Long pno) {
        postRepo.deleteById(pno);
    }
}

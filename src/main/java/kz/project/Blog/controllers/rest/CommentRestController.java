package kz.project.Blog.controllers.rest;

import kz.project.Blog.controllers.MainController;
import kz.project.Blog.models.entities.Comment;
import kz.project.Blog.models.entities.Post;
import kz.project.Blog.services.CommentService;
import kz.project.Blog.services.impl.CommentServiceImpl;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/comments")
public class CommentRestController extends MainController {
    private CommentService commentService;
    public CommentRestController( CommentService commentService){
        this.commentService = commentService;
    }
    @GetMapping("{id}")
    public ResponseEntity<?> findAllByPostId(@PathVariable(name = "id") Long id){
        return buildResponse(commentService.findByPostId(id), HttpStatus.OK);
    }

    @PostMapping("add")
    public ResponseEntity<?> findAllByPostId(@RequestBody Comment comment){
        return buildResponse(commentService.save(comment), HttpStatus.OK);
    }

}

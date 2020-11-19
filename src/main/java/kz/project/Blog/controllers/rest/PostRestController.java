package kz.project.Blog.controllers.rest;

import kz.project.Blog.controllers.MainController;
import kz.project.Blog.models.entities.Image;
import kz.project.Blog.models.entities.Post;
import kz.project.Blog.models.entities.User;
import kz.project.Blog.repositories.ImageRepository;
import kz.project.Blog.services.PostService;
import kz.project.Blog.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.zip.DataFormatException;
import java.util.zip.Inflater;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(value = "/api/posts")
public class PostRestController extends MainController {

    private PostService postService;
    private ImageRepository imageRepository;
    private UserService userService;

    @Autowired
    public PostRestController(UserService userService,ImageRepository imageRepository,PostService postService){
        this.postService = postService;
        this.imageRepository = imageRepository;
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<?> findAll(Authentication authentication){
        List<Post> posts = postService.findAll();
        User user = userService.getUserByAuthentication(authentication);
        for (int i = 0; i <posts.size() ; i++) {
            if (posts.get(i).getAuthor().getLogin().equals(user.getLogin())){
                posts.remove(i);
            }
        }
        return buildResponse(posts, HttpStatus.OK);
    }

    @PostMapping("add")
    public ResponseEntity<?> add(@RequestBody Post post) {
        return buildResponse(postService.save(post), HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<?> findAllPosts(@PathVariable(name = "id") Long id) throws Exception{
        return buildResponse(postService.findById(id), HttpStatus.OK);
    }

    @GetMapping("user/{id}")
    public ResponseEntity<?> findAllAuthor(@PathVariable(name = "id") Long id) throws Exception{
        return buildResponse(postService.findAllByAuthorId(id), HttpStatus.OK);
    }
}

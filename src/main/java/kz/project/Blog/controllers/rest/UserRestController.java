package kz.project.Blog.controllers.rest;

import kz.project.Blog.controllers.MainController;
import kz.project.Blog.exceptions.ServiceException;
import kz.project.Blog.models.entities.Image;
import kz.project.Blog.models.entities.Role;
import kz.project.Blog.models.entities.User;
import kz.project.Blog.repositories.ImageRepository;
import kz.project.Blog.services.UserService;
import kz.project.Blog.shared.utils.codes.ErrorCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Optional;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(value = "/api/users")
public class UserRestController extends MainController {

    private UserService userService;
    private ImageRepository imageRepository;

    @Autowired
    public UserRestController(UserService userService, ImageRepository imageRepository){
        this.userService = userService;
        this.imageRepository= imageRepository;
    }

    @GetMapping
    public ResponseEntity<?> findAll(){
        return buildResponse(userService.findAll(), HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<?> findAll(@PathVariable(name = "id") Long id){
        System.out.println(id);
        return buildResponse(userService.findOne(id), HttpStatus.OK);
    }

    @PostMapping("current")
    public ResponseEntity<?> currentUser(Authentication authentication) throws ServiceException {
        System.out.println(authentication.getName());
        User user = userService.getUserByAuthentication(authentication);
        return buildResponse(user, HttpStatus.OK);
    }

    @RequestMapping(value = "/update", method = {RequestMethod.PATCH, RequestMethod.PUT})
    public ResponseEntity<?> update(@RequestBody User user) throws ServiceException {
        if (user.getId() ==null) {
            throw ServiceException.builder()
                    .errorCode(ErrorCode.SYSTEM_ERROR)
                    .message("id is null")
                    .build();
        }
        return buildResponse(userService.update(user), HttpStatus.OK);
    }
    @PostMapping("register")
    public ResponseEntity<?> register(@RequestBody User user) throws ServiceException{
        User u = userService.findByLogin(user.getLogin());
        if (u != null){
            throw ServiceException.builder()
                    .errorCode(ErrorCode.SYSTEM_ERROR)
                    .message("login exists").build();
        }
        Role role = new Role();
        role.setId(Role.ROLE_USER_ID);
        user.setRole(role);
        return buildResponse(userService.add(user), HttpStatus.OK);
    }

    public static byte[] decompressBytes(byte[] data) {
        Inflater inflater = new Inflater();
        inflater.setInput(data);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
        byte[] buffer = new byte[1024];
        try {
            while (!inflater.finished()) {
                int count = inflater.inflate(buffer);
                outputStream.write(buffer, 0, count);
            }
            outputStream.close();
        } catch (IOException ioe) {
        } catch (DataFormatException e) {
        }
        return outputStream.toByteArray();
    }
}

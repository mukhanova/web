package hello;

/**
 * Created by Meruyert on 29.04.17.
 */
import hello.storage.StorageFileNotFoundException;
import hello.storage.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Collectors;
import java.io.File;

import hello.User;

@Controller
public class FileUploadController {

    @Autowired
    private final UserRepository userRepository;

    private final StorageService storageService;
    private static String UPLOAD_DIR_FILES = "/Users/macbook/Desktop/WEB/repos/up/upload/files/";

    @Autowired
    public FileUploadController(UserRepository userRepository, StorageService storageService) {
        this.userRepository = userRepository;
        this.storageService = storageService;
    }


    @GetMapping("/")
    public String listUploadedFiles(Model model) throws IOException {

        model.addAttribute("files", storageService
                .loadAll()
                .map(path ->
                        MvcUriComponentsBuilder
                                .fromMethodName(FileUploadController.class, "serveFile", path.getFileName().toString())
                                .build().toString())
                .collect(Collectors.toList()));

        return "uploadForm";
    }

    @GetMapping("/files/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> serveFile(@PathVariable String filename) {

        Resource file = storageService.loadAsResource(filename);
        return ResponseEntity
                .ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\""+file.getFilename()+"\"")
                .body(file);
    }

    @PostMapping(value = "/")
    public ResponseEntity handleFileUpload(@RequestParam("file") MultipartFile file , @RequestParam String username) {
        try {
            byte[] bytes = file.getBytes();


            File theDir = new File("/Users/macbook/Desktop/WEB/repos/up/upload/files/" + username);
            Path path_by_username = Paths.get("/Users/macbook/Desktop/WEB/repos/up/upload/files/" + username);
            if(!Files.exists(path_by_username)){
                try{
                    Files.createDirectories(path_by_username);
                } catch (IOException e){
                    e.printStackTrace();
                }
            }

            if (!theDir.exists()) {
                System.out.println("creating directory: " + theDir.getName());
                boolean result = false;

                try{
                    theDir.mkdir();
                    result = true;
                }
                catch(SecurityException se){
                    //handle it
                }
                if(result) {
                    System.out.println("DIR created");
                }
            }

            Path path = Paths.get(theDir + "/" + file.getOriginalFilename());
            Files.write(path, bytes);
            User test_user = userRepository.findByName(username);
            if( test_user != null ){
                test_user.setPaths(path.toString());
                System.out.println("OLD USER - ADDED NEW PATH");
            }else{
                test_user = new User();
                test_user.setName(username);
                test_user.setPaths(path.toString());System.out.println("NEW USER");
            }
            userRepository.save(test_user);
            System.out.println("DONES");
        } catch (IOException e) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }





    @ExceptionHandler(StorageFileNotFoundException.class)
    public ResponseEntity handleStorageFileNotFound(StorageFileNotFoundException exc) {
        return ResponseEntity.notFound().build();
    }

    @Controller    // This means that this class is a Controller
    @RequestMapping(path="/demo") // This means URL's start with /demo (after Application path)
    public static class MainController {
        @Autowired // This means to get the bean called userRepository
        // Which is auto-generated by Spring, we will use it to handle the data
        private UserRepository userRepository;

        @GetMapping(path="/add") // Map ONLY GET Requests
        public @ResponseBody String addNewUser (@RequestParam String name
                , @RequestParam String email) {
            // @ResponseBody means the returned String is the response, not a view name
            // @RequestParam means it is a parameter from the GET or POST request

            User n = new User();
            n.setName(name);
            userRepository.save(n);
            return "Saved";
        }

        @GetMapping(path="/all")
        public @ResponseBody Iterable<User> getAllUsers() {
            // This returns a JSON or XML with the users
            return userRepository.findAll();
        }
    }
}
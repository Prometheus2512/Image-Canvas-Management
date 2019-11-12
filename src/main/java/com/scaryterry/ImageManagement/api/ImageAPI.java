package com.scaryterry.ImageManagement.api;

import com.scaryterry.ImageManagement.model.Image;
import com.scaryterry.ImageManagement.service.ImageService;
import com.scaryterry.ImageManagement.FileUpload.StorageService;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.util.Base64;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value = "/api/image")
public class ImageAPI {
    @Autowired(required = true)
    private ImageService imageService;
    private final StorageService storageService;
    @Autowired
    public ImageAPI(StorageService storageService) {
        this.storageService = storageService;
    }



    @PostMapping("/add")
    public int addImage(@RequestParam("file") MultipartFile file,@RequestParam("title") String title,
                           @RequestParam("description") String description,@RequestParam("UID") int user) {
        Image img=new Image(title,description,new Date(),user);
        System.out.println(img);
        return imageService.addImage(img,file);

    }
    @GetMapping("/files/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> serveFile(@PathVariable String filename) {

        Resource file = storageService.loadAsResource(filename);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }
    @GetMapping("/base64/{filename:.+}")
    @ResponseBody
    public String serveFileBase64(@PathVariable String filename) {
    try {
        byte[] fileContent = FileUtils.readFileToByteArray(new File(storageService.getURL(filename)));
        String encodedString = Base64.getEncoder().encodeToString(fileContent);
        return encodedString;
    }
    catch(Exception e){
        return "wrong";
    }
    }
    @GetMapping("/find/{IID:.+}")
    @ResponseBody
    public ResponseEntity<Image> findImage(@PathVariable int IID) {
        return new ResponseEntity<Image>(imageService.searchImage(IID), HttpStatus.OK);

    }
    @GetMapping("/all")
    @ResponseBody
    public ResponseEntity<List<Image>> findImage() {

        return new ResponseEntity<List<Image>>(imageService.allImages(), HttpStatus.OK);

    }
    @GetMapping("/remove/{IID:.+}")
    @ResponseBody
    public ResponseEntity<String> removeImage(@PathVariable int IID) {
         int success=imageService.removeImage(IID);
        return new ResponseEntity<String>("good", HttpStatus.OK);

    }

}

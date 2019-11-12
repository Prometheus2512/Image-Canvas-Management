package com.scaryterry.ImageManagement.api;

import com.scaryterry.ImageManagement.model.Canva;
import com.scaryterry.ImageManagement.model.Image;
import com.scaryterry.ImageManagement.service.CanvaService;
import com.scaryterry.ImageManagement.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value = "/api/canva")
public class CanvaAPI {
    @Autowired(required = true)
    private ImageService imageService;
    @Autowired(required = true)
    private CanvaService canvaService;

    @PostMapping("/add")
    public ResponseEntity<Canva> addCanva(@RequestBody Canva canva) {
        canva.setPublishingDate(new Date());
        Canva canv=canvaService.addCanva(canva);
        return new ResponseEntity<Canva>(canv, HttpStatus.OK);

    }
    @GetMapping("/all")
    @ResponseBody
    public ResponseEntity<List<Canva>> findImage() {

        return new ResponseEntity<List<Canva>>(canvaService.allCanva(), HttpStatus.OK);

    }
    @GetMapping("/add/{CID}/{IID}")
    public ResponseEntity<Canva> addtoCanva(@PathVariable int CID,@PathVariable int IID) {

        return new ResponseEntity<Canva>(canvaService.addImageToCanva(CID,IID), HttpStatus.OK);

    }

    @GetMapping("/remove/{CID}/{IID}")
    public ResponseEntity<Canva> removefromCanva(@PathVariable int CID,@PathVariable int IID) {

        return new ResponseEntity<Canva>(canvaService.removeImagefromCanva(CID,IID), HttpStatus.OK);

    }
    @GetMapping("/test")
    public ResponseEntity<Canva> test() {

        Canva canv=new Canva("title","description", new Date(), 5);
        return new ResponseEntity<Canva>(canv, HttpStatus.OK);

    }

}

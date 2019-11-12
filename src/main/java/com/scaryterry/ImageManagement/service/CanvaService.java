package com.scaryterry.ImageManagement.service;

import com.scaryterry.ImageManagement.FileUpload.StorageService;
import com.scaryterry.ImageManagement.dao.CanvaDAO;
import com.scaryterry.ImageManagement.dao.ImageDAO;
import com.scaryterry.ImageManagement.model.Canva;
import com.scaryterry.ImageManagement.model.Image;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.Base64;
import java.util.List;

@Service
public class CanvaService {
    private StorageService storageService;
    public ImageDAO imageDAO;
    public CanvaDAO canvaDAO;


    public CanvaService(StorageService storageService,ImageDAO imageDAO,CanvaDAO canvaDAO) {
        this.storageService = storageService;
        this.imageDAO=imageDAO;
        this.canvaDAO=canvaDAO;
    }
    public Canva addCanva(Canva canva)
    {
           return canvaDAO.save(canva);

    }

    public Canva addImageToCanva(int CID, int IID )
    {
         Canva canva =canvaDAO.findById(CID).get();
         Image image= imageDAO.findById(IID).get();
        if (!canva.getImages().contains(image)) {
            canva.getImages().add(image);
            canvaDAO.save(canva);
        }
        return canva;


    }
    public Canva removeImagefromCanva(int CID, int IID )
    {
        Canva canva =canvaDAO.findById(CID).get();
        Image image= imageDAO.findById(IID).get();
        if (canva.getImages().contains(image)) {
            canva.getImages().remove(image);
            canvaDAO.save(canva);
        }
        return canva;


    }
    public List<Canva> allCanva()
    {
        List<Canva> canvas = canvaDAO.findAll();
        for (Canva cnv:canvas)
        {
            for (Image img:cnv.getImages()) {
                try {
                    byte[] fileContent = FileUtils.readFileToByteArray(new File(storageService.getURL(img.getId()+"."+img.getFileExtension())));
                    String encodedString = Base64.getEncoder().encodeToString(fileContent);
                    img.setFileExtension(encodedString);
                }
                catch(Exception e){
                    System.out.println(e);
                }
            }
        }



        return canvas;
    }
}

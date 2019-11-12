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
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@Service
public class ImageService {
    private StorageService storageService;
    public ImageDAO imageDAO;
    public CanvaDAO canvaDAO;
    public ImageService(StorageService storageService,ImageDAO imageDAO,CanvaDAO canvaDAO) {
        this.storageService = storageService;
        this.imageDAO=imageDAO;
        this.canvaDAO=canvaDAO;}
    public int addImage(Image image, MultipartFile file)
    {   image.setFileExtension(FilenameUtils.getExtension(file.getOriginalFilename()));
        int IID= imageDAO.save(image).getId();

        String filename=IID+"."+FilenameUtils.getExtension(file.getOriginalFilename());
        storageService.store(file,filename);
        return IID;

    }
    public Image searchImage(int IID)
    {   Image img=imageDAO.findById(IID).get();
        try {
            //  Image img=imageService.searchImage(Integer.parseInt(filename));
            String imagename=img.getId()+"."+img.getFileExtension();
            System.out.println(imagename);
            byte[] fileContent = FileUtils.readFileToByteArray(new File(storageService.getURL(imagename)));
            String encodedString = Base64.getEncoder().encodeToString(fileContent);
            img.setFileExtension(encodedString);

        }
        catch(Exception e){
            System.out.println(e);
        }
        return img;
    }
    public int removeImage(int IID)
    {   Image img=imageDAO.findById(IID).get();
        List<Canva> lc=canvaDAO.findCanvasbyimage(img);
        for (Canva c:lc)
        {
            c.getImages().remove(img);
            canvaDAO.save(c);
        }

        imageDAO.deleteById(IID);

        return 0;
    }
    public List<Image> allImages()
    {
        List<Image> images = imageDAO.findAll();

        for (Image img:images) {
            try {
                byte[] fileContent = FileUtils.readFileToByteArray(new File(storageService.getURL(img.getId()+"."+img.getFileExtension())));
                String encodedString = Base64.getEncoder().encodeToString(fileContent);
                img.setFileExtension(encodedString);
            }
            catch(Exception e){
                System.out.println(e);
            }
        }

        return images;
    }

}

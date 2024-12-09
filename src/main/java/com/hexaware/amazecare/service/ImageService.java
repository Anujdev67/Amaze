package com.hexaware.amazecare.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.hexaware.amazecare.exceptions.ResourceNotFoundException;
import com.hexaware.amazecare.model.LabImage;
import com.hexaware.amazecare.model.Report;
import com.hexaware.amazecare.repository.ImageRepository;
import com.hexaware.amazecare.repository.ReportRepository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

@Service
public class ImageService {

    private final String location = "C:/Users/senan/git/AmazeCare/AmazeCare/public/images";

    @Autowired
    private ImageRepository imageRepository;

    @Autowired
    private ReportRepository reportRepository;

    public LabImage uploadImage(int id, MultipartFile file) throws IOException, ResourceNotFoundException {
        Path path = Paths.get(location, file.getOriginalFilename());
        Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);

        Report report = reportRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Report not found"));

        LabImage labImage = new LabImage();
        labImage.setReport(report);
        labImage.setFileName(file.getOriginalFilename());
        labImage.setPath(path.toString());

        return imageRepository.save(labImage);
    }

    public List<LabImage> find() {
        return imageRepository.findAll();
    }
}
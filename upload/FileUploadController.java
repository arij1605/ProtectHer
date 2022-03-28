package tn.esprit.meetico.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import tn.esprit.meetico.entities.FileUtil;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

@Controller
//@CrossOrigin(origins = "http://localhost:8082") open for specific port
@CrossOrigin() // open for all ports
public class FileUploadController {


    /**
     * Method to upload file
     *
     * @param file
     * @return
     */
    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
        try {
            createDirIfNotExist();

            byte[] bytes = new byte[0];
            bytes = file.getBytes();
            Files.write(Paths.get(FileUtil.folderPath + file.getOriginalFilename()), bytes);
            return ResponseEntity.status(HttpStatus.OK)
                    .body("Files uploaded successfully: " + file.getOriginalFilename());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
                    .body("Exception occurred for: " + file.getOriginalFilename() + "!");
        }
    }

    /**
     * Create directory to save files, if not exist
     */
    private void createDirIfNotExist() {
        //create directory to save the files
        File directory = new File(FileUtil.folderPath);
        if (!directory.exists()) {
            directory.mkdir();
        }
    }

    /**
     * Method to get the list of files from the file storage folder.
     *
     * @return file
     */
    @GetMapping("/files")
    public ResponseEntity<String[]> getListFiles() {
        return ResponseEntity.status(HttpStatus.OK)
                .body(new java.io.File(FileUtil.folderPath).list());
    }

}
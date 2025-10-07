package com.example.VideoAnalyzer.va.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;
import java.util.concurrent.ExecutorService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VideoFrameExtractorService {
    @Autowired
    private ExecutorService executorService;



    public void extractFrames(String videoPath, String outputDir) {
        executorService.submit(() -> {
            try {
                System.out.println(videoPath);
                System.out.println(outputDir);

                if (!validatePath(outputDir)) {
                    throw new IllegalArgumentException("Invalid output directory: " + outputDir);
                }
                if (!validateFilePath(videoPath)) {
                    throw new IllegalArgumentException("Invalid video file path: " + videoPath);
                }

                String newOutputDir = createDirectory(videoPath, outputDir);
                // Command to extract frames using ffmpeg
                //String command = "ffmpeg -v verbose -i /Users/amanojhaa/Documents/Projects/Resources/Videos/test.mp4 -vf \"select='gte(t-prev_selected_t,0.333)+eq(pict_type,I)',setpts=N/FRAME_RATE/TB\" -vsync vfr /Users/amanojhaa/Documents/Projects/Resources/Frames/frame_%04d.png";
                
                String[] command = {
                            "ffmpeg",
                            "-v", "verbose",
                            "-i", videoPath,
                            "-vf", "select='gte(t-prev_selected_t,0.333)+eq(pict_type,I)',setpts=N/FRAME_RATE/TB",
                            "-vsync", "vfr",
                            newOutputDir + "/frame_%04d.png"
                        };
                System.out.println(command);
                Process process = Runtime.getRuntime().exec(command);

                // Read standard output (stdout)
                BufferedReader stdInput = new BufferedReader(new InputStreamReader(process.getInputStream()));
                // Read error output (stderr)
                BufferedReader stdError = new BufferedReader(new InputStreamReader(process.getErrorStream()));

                String s;
                // Print stdout
                while ((s = stdInput.readLine()) != null) {
                    System.out.println(s);
                }
                // Print stderr
                while ((s = stdError.readLine()) != null) {
                    System.out.println(s);
                }

                int exitCode = process.waitFor();
                if (exitCode == 0) {
                    System.out.println("Frame extraction completed successfully.");
                } else {
                    System.err.println("Frame extraction failed with exit code: " + exitCode);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });


    }

    private static String extractFileName(String filePath) {

        // Extract the file name without extension
        Path path = Paths.get(filePath);
        String fileName = path.getFileName().toString();
        int dotIndex = fileName.lastIndexOf('.');
        String baseName = (dotIndex == -1) ? fileName : fileName.substring(0, dotIndex);

        // Generate a random string (UUID without dashes)
        String randomString = UUID.randomUUID().toString().replaceAll("-", "");

        // Combine base name with random string
        String newFileName = baseName + randomString;

        System.out.println("Original file name: " + fileName);
        System.out.println("New file name: " + newFileName);
        return newFileName;
    }

    private static String createDirectory(String inputFilePath, String filePath){
         try {
            // Extract the directory name ('gus' in your example)
            String newDirName = extractFileName(inputFilePath);
            System.out.println(newDirName);

            // Combine base path and new directory name ('foo/bar/gus')
            Path dirPath = Paths.get(filePath, newDirName);

            // Create the specified directory (and parent directories if not exist)
            Files.createDirectories(dirPath);

            System.out.println("Directory created successfully: " + dirPath.toString());
            return dirPath.toString();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to create directory: " + e.getMessage());
        }
    }

    private static boolean validatePath(String path) {
        Path p = Paths.get(path);
        if (!Files.exists(p) || !Files.isDirectory(p) || !Files.isWritable(p)) {
            throw new IllegalArgumentException("Invalid output directory: " + path);
        }
        return true;
    }

    private static boolean validateFilePath(String path) {
        Path p = Paths.get(path);
        if (!Files.exists(p) || Files.isDirectory(p) || !Files.isReadable(p)) {
            throw new IllegalArgumentException("Invalid video file path: " + path);
        }
        return true;
    }
}

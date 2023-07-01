import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.HashMap;
import java.util.Map;

public class FileSorting {
    private File sourceFolder;
    private Map<String, String> folderNames;

    public FileSorting(String sourceFolderPath) {
        this.sourceFolder = new File(sourceFolderPath);
        this.folderNames = new HashMap<>();
        initializeFolderNames();
    }

    public void RunTheApp() {
        try {
            sortFilesByType();
            System.out.println("Files sorted successfully!");
        } catch (IOException e) {
            System.err.println("An error occurred: " + e.getMessage());
        }
    }

    public void sortFilesByType() throws IOException {
        if (!sourceFolder.isDirectory()) throw new IllegalArgumentException("Source folder is not a directory.");

        File[] files = sourceFolder.listFiles();
        if (files == null)  return;

        for (File file : files) {
            if (file.isFile()) {
                String fileType = getFileType(file);

                String folderName = getFolderName(fileType);
                File typeDir = new File(sourceFolder, folderName);

                if (!typeDir.exists()) typeDir.mkdir();

                Path sourcePath = file.toPath();
                Path destPath = typeDir.toPath().resolve(file.getName());
                Files.move(sourcePath, destPath, StandardCopyOption.REPLACE_EXISTING);
            }
        }
    }

    private void initializeFolderNames() {
        folderNames.put("exe", "runnable");
        folderNames.put("jpg", "images");
        folderNames.put("txt", "text_files");
        folderNames.put("mp3", "audio");
        folderNames.put("pdf", "documents");
        folderNames.put("doc", "documents");
        folderNames.put("xls", "documents");
        folderNames.put("ppt", "documents");
        folderNames.put("zip", "compressed");
        folderNames.put("rar", "compressed");
        folderNames.put("png", "images");
        folderNames.put("gif", "images");
        folderNames.put("java", "source_code");
    }

    private String getFolderName(String fileType) {
        String folderName = folderNames.get(fileType);

        if (folderName == null) folderName = "other";
        
        return folderName;
    }

    private String getFileType(File file) {
        String fileName = file.getName();
        int dotIndex = fileName.lastIndexOf('.');
        if (dotIndex > 0 && dotIndex < fileName.length() - 1) {
            return fileName.substring(dotIndex + 1).toLowerCase();
        }
        return "other";
    }
}

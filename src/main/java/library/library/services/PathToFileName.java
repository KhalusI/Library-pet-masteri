package library.library.services;

import org.springframework.stereotype.Service;

@Service
public class PathToFileName {
    public String getFileNameFromPath(String filePath) {
        int lastIndex = filePath.lastIndexOf("/");
        if (lastIndex != -1 && lastIndex < filePath.length() - 1) {
            return filePath.substring(lastIndex + 1);
        } else {
            return filePath;
        }
    }
}

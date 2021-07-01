package co.kjm.formPrj.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileUtil {

    private FileInputStream fileInputStream;
    private FileOutputStream fileOutputStream;

    public byte[] getFileBytesFrom(String path) throws IOException {
        // 파일 객체 생성
        File file = new File(path);
        fileInputStream = new FileInputStream(file);

        byte[] fileBytes = new byte[(int)file.length()];

        fileInputStream.read(fileBytes);

        fileInputStream.close();
        return fileBytes;
    }

    public void saveFile(String path, byte[] fileData) throws IOException {
        fileOutputStream = new FileOutputStream(path);

        fileOutputStream.write(fileData);

        fileOutputStream.close();
    }
}

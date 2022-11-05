import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class ReadFile {

    public static String ReadFileContentsOrNull(String path) {
        try {
            return Files.readString(Path.of(path));
        } catch(IOException e){
            System.out.print("Невозможно прочитать файл. Возможно файл не находится в нужной директории.");
            return null;
        }
    }
}

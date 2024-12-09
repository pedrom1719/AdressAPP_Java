import java.io.FileWriter;
import java.io.IOException;

public class FileGenerator {
    public void jsonFile(String listCEPJson) throws IOException {

        FileWriter myFile = new FileWriter("Endereços.json");
        myFile.write(listCEPJson);
        myFile.close();

    }
}

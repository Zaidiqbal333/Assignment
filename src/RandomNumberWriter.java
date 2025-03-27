import Utility.Utility;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class RandomNumberWriter {

    public static void main(String[] args) throws IOException {
        // Challenge A
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("Resources/randomNumbers.txt"))) {
            long fileMemorySize = 0;
            boolean checkFirst = true;
            //file size must be 10MB
            long fileSize = 10 * 1024 * 1024;
            //we can also use for-loop here but while is more efficient
            while (fileMemorySize < fileSize) {
                String randomData = Utility.createRandomData();
                //we can also use StringBuilder here for better performance
                String entry = checkFirst ? randomData : "," + randomData;

                // Check if writing this entry would exceed target
                if (fileMemorySize + entry.length() > fileSize) {
                    //fileSize and FileMemorySize types are not same so we have to type cast here
                    int maxSize = (int) (fileSize - fileMemorySize);
                    entry = entry.substring(0, maxSize);
                    writer.write(entry);
                    break;
                }
                writer.write(entry);
                fileMemorySize += entry.length();
                checkFirst = false;
            }
            //Challenge B
            String fileData = new String(Files.readAllBytes(Paths.get("Resources/randomNumbers.txt")));
            String[] objects = fileData.split(",");
//            System.out.println("Total number of random numbers: " + objects.length);
            for (String obj : objects) {
                // Trim spaces around
                String removed = obj.trim();
                boolean spaces = !obj.equals(removed);
                String type;
                if (spaces) {
                    type = "alphanumeric";
                } else if (Utility.checkAlphabet(removed)) {
                    type = "alphabet";
                } else if (Utility.checkInteger(removed)) {
                    type = "integer";
                } else if (Utility.checkRealNumber(removed)) {
                    type = "realNumber";
                } else if (Utility.checkAlphanumeric(removed)) {
                    type = "alphanumeric";
                } else {
                    type = "unknown";
                }
                System.out.printf("value : '%s', Type: %s%n", removed, type);
            }

        } catch (IOException ex) {
            System.err.println("Exception occurred while writing file: " + ex.getMessage());
        }

    }
}
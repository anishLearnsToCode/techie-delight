package Odoo;

import java.io.*;

public class DeletingEverySeventhByte {
    public static void main(String[] args) throws IOException {
        // Read the file and create a byte[] array
        final String filePath = "" ; // random file path;
        final File file = new File(filePath); // file path
        final byte[] bytesArray = new byte[(int) file.length()];
        final FileInputStream fis = new FileInputStream(file);
        fis.read(bytesArray);
        fis.close();

        // test byte array to demonstrate the program
        byte[] array = new byte[] {
                1, 2, 3, 4, 5, 6, 7, 8,
                9, 10, 11, 12, 13, 14, 15, 16
        };

        // get the modified bytes[] array
        array = clearBytes(array);

        // write the modified array to a new file
        writeByte(array, filePath);
    }


    // Method which write the bytes into a file
    private static void writeByte(byte[] bytes, final String filePath) {
        final File outputFile = new File(filePath);
        try (OutputStream outputStream = new FileOutputStream(outputFile)) {
            outputStream.write(bytes);
        } catch (Exception ignored) { }
    }

    private static byte[] clearBytes(byte[] array) {
        int leftShiftAmount = 0;
        for (int index = 0; index + leftShiftAmount < array.length ; index++) {
            if ((index + 1) % 7 == 0) {
                leftShiftAmount++;
            }
            array[index] = array[index + leftShiftAmount];
        }
        int truncatedArrayLength = array.length - leftShiftAmount ;
        return truncate(array, truncatedArrayLength);
    }

    private static byte[] truncate(byte[] array, int length) {
        byte[] result = new byte[length];
        for (int index = 0 ; index < length ; index++) {
            result[index] = array[index];
        }
        return result;
    }
}

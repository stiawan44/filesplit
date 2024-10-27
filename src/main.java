import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

class FileSplitter {
    private Queue<String> linesQueue;

    public FileSplitter() {
        linesQueue = new LinkedList<>();
    }


    public void readFile(String filePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                linesQueue.add(line);
            }
            System.out.println("\nFile berhasil dibaca. Total baris: " + linesQueue.size());
        } catch (IOException e) {
            System.out.println("Terjadi kesalahan saat membaca file: " + e.getMessage());
        }
    }


    public void splitFile(int partSize) {
        int totalLines = linesQueue.size();
        int totalParts = (int) Math.ceil((double) totalLines / partSize);

        System.out.println("\nMembagi file menjadi " + totalParts + " bagian");
        System.out.println("Jumlah baris per bagian: " + partSize);

        int partNumber = 1;
        while (!linesQueue.isEmpty()) {
            try {

                String outputFileName = "output_bagian_" + partNumber + ".txt";
                BufferedWriter writer = new BufferedWriter(new FileWriter(outputFileName));

                System.out.println("\nBagian " + partNumber + ":");
                int linesWritten = 0;


                while (linesWritten < partSize && !linesQueue.isEmpty()) {
                    String line = linesQueue.poll();
                    System.out.println(line);
                    writer.write(line);
                    writer.newLine();
                    linesWritten++;
                }

                writer.close();
                System.out.println("Berhasil menyimpan ke " + outputFileName);
                partNumber++;

            } catch (IOException e) {
                System.out.println("Terjadi kesalahan saat menulis file: " + e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        FileSplitter fileSplitter = new FileSplitter();

        // Baca file input
        System.out.println("=====================");
        System.out.println("=====PEMISAH FILE ===");
        System.out.println("=====================");
        System.out.print("Masukkan path file input: ");
        String filePath = scanner.nextLine();
        fileSplitter.readFile(filePath);


        System.out.print("\nMasukkan jumlah baris per bagian: ");
        int partSize = scanner.nextInt();


        fileSplitter.splitFile(partSize);
        scanner.close();
    }
}
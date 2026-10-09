package Utility;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class openpdf {
	
	public static void openLatestDownloadedPdf() throws Exception {

        String folderPath =
                "/sdcard/Android/data/com.adhik.retail_pos/files";

        String latestFile = null;

        // Wait up to 20 seconds for PDF
        for (int i = 0; i < 20; i++) {

            Process process = Runtime.getRuntime().exec(
                    "adb shell find " + folderPath + " -type f -iname \"*.pdf\""
            );

            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(process.getInputStream())
            );

            String line;

            while ((line = reader.readLine()) != null) {
                if (line.trim().toLowerCase().endsWith(".pdf")) {
                    latestFile = line.trim();
                }
            }

            reader.close();

            if (latestFile != null) {
                break;
            }

            System.out.println("PDF not found yet. Waiting...");
            Thread.sleep(1000);
        }

        // Fail test if PDF not found
        if (latestFile == null || latestFile.isEmpty()) {

            throw new AssertionError(
                    "PDF was not generated/found in: " + folderPath
            );
        }

        System.out.println("PDF found: " + latestFile);

        // Open PDF
        Process openPdf = Runtime.getRuntime().exec(
                "adb shell am start " +
                "-a android.intent.action.VIEW " +
                "-d \"file://" + latestFile + "\" " +
                "-t \"application/pdf\""
        );

        openPdf.waitFor();

        System.out.println("Latest PDF opened successfully.");
    }
}

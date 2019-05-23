package webwviewselenium.compareImages;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

public class CompareImages {

    /*
     * This method compares images and draws if the images are different.
    */
    public static void compareImages(String pathToScannedImage, String pathToTemplateImage, String pathForResultImage) {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        if (!imagesAreEquals(pathToScannedImage, pathToTemplateImage)) {
            try {
                IndicateImagesDifferences.findDifferencesBetweenImages(pathToScannedImage, pathToTemplateImage, pathForResultImage);
            } catch (IOException ex) {
                Logger.getLogger(CompareImages.class.getName()).log(Level.SEVERE, null, ex);
            } catch (URISyntaxException ex) {
                Logger.getLogger(CompareImages.class.getName()).log(Level.SEVERE, null, ex);
            }
        } 
    }

    public static boolean imagesAreEquals(String pathToScannedImage, String pathToTemplateImage) {
        Mat original = Imgcodecs.imread(pathToScannedImage);
        Mat template = Imgcodecs.imread(pathToTemplateImage);
        Mat result = new Mat();
        
        Imgproc.cvtColor(original, original, Imgproc.COLOR_BGR2GRAY);
        Imgproc.cvtColor(template, template, Imgproc.COLOR_BGR2GRAY);

        Core.absdiff(original, template, result);

        if (Core.countNonZero(result) == 0) {
            return true;
        }
        return false;
    }
}


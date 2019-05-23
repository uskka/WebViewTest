package webwviewselenium.compareImages;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import javax.imageio.ImageIO;
import com.github.romankh3.image.comparison.ImageComparison;

public class IndicateImagesDifferences {

    /*
     * This method indicates images differences by drawing a red rectangle on scanned image.
     */
    public static void findDifferencesBetweenImages(String pathToScannedImage, String pathToTemplateImage, String pathForResultImage)
            throws IOException, URISyntaxException {

        BufferedImage scannedImage = ImageIO.read(new File(pathToScannedImage));
        BufferedImage templateImage = ImageIO.read(new File(pathToTemplateImage));

        File resultImage = new File(pathForResultImage);

        ImageComparison imageComparison = new ImageComparison(templateImage, scannedImage, resultImage);
        imageComparison.setRectangleLineWidth(15);
        BufferedImage drawDifferences = imageComparison.compareImages();
    }   
}
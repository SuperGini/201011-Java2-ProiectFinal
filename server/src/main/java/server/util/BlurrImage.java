package server.util;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

public class BlurrImage {

    private BlurrImage() {
    }

    public static void getBufferdImage(String path){

        String p = Paths.get(path).getFileName().toString();
        String p1 ="./server/src/main/resources/blurredImages/" + p;
        System.out.println("blurring image: " + p1);


        int size = 25;
        float number = 1.0f / (size*size);
        float [] data = new float[size*size]; //-> efect de blurr

        for(int i = 1; i< data.length; i++){
            data[i] = number;
        }

        Kernel kernel = new Kernel(size,size,data);

        File file = new File(path);

        try {
            //fac o imagine buferizata
            BufferedImage  readImage = ImageIO.read(file);
            //face o subimagine a imaginii buferizata de mai sus
            BufferedImage subImage1 = readImage.getSubimage(0,1135,1920,144);
            BufferedImage subImage2 = readImage.getSubimage(0,0,1920,144);

            ColorModel cm = subImage1.getColorModel();

            //refac noua imagine si fac niste chestii
            BufferedImage src = new BufferedImage(cm, subImage1.copyData(subImage1.getRaster().createCompatibleWritableRaster()), cm.isAlphaPremultiplied(), null).getSubimage(0, 0, subImage1.getWidth(), subImage1.getHeight());
            BufferedImage src2 = new BufferedImage(cm, subImage2.copyData(subImage2.getRaster().createCompatibleWritableRaster()), cm.isAlphaPremultiplied(), null).getSubimage(0, 0, subImage2.getWidth(), subImage2.getHeight());
            //baga modificare de pixeli
            ConvolveOp convolveOp = new ConvolveOp(kernel,ConvolveOp.EDGE_NO_OP, null);
            //baga efectul de blurr pe poza
            convolveOp.filter(src, subImage1);
            convolveOp.filter(src2, subImage2);


            //fac crop la poza ca sa se potriveasca
            BufferedImage c=  readImage.getSubimage(15,15,readImage.getWidth()-30,readImage.getHeight()-30);
            ImageIO.write(c, "jpg", new File(p1));


        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

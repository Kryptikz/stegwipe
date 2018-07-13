import java.io.*;
import java.awt.image.*;
import javax.imageio.*;
import java.awt.Color;
import cs1.*;
public class Filter {
    public static void main(String[] args) {
        String arg = args[0];
        String name = arg.substring(0,arg.indexOf("."));
        String ext = arg.substring(arg.indexOf(".")+1);
        boolean b = wipe(name,ext);
        if (b) 
            System.out.println("file modification successful");
        else 
            System.out.println("file modification failed");
    }
    public static boolean wipe(String FILENAME, String EXTENSION) {
        boolean status = true;
        try {
            File f = new File(FILENAME + "." + EXTENSION);
            BufferedImage image = ImageIO.read(f);
            int WIDTH = image.getWidth();
            int HEIGHT = image.getHeight();
            System.out.println("import image has WIDTH: " + WIDTH + " and HEIGHT: " + HEIGHT);
            for(int r=0;r<HEIGHT;r++) {
                for(int c=0;c<WIDTH;c++) {
                    int mod=(int)(Math.random()*5)-2;
                    int ch=(int)(Math.random()*3);
                    int rgb = image.getRGB(c,r);
                    int a = (rgb>>24)&0xFF;
                    int red = (rgb>>16)&0xFF;
                    int green = (rgb>>8)&0xFF;
                    int blue = (rgb)&0xFF;
                    switch(ch) {
                        case 0: 
                            a+=mod;
                            break;
                        case 1:
                            red+=mod;
                            break;
                        case 2:
                            green+=mod;
                            break;
                        case 3:
                            blue+=mod;
                            break;
                    }
                    if (a>255)
                        a=255;
                    if (red>255)
                        red=255;
                    if (green>255)
                        green=255;
                    if (blue>255)
                        blue=255;
                    Color co = new Color(red,green,blue,a);
                    image.setRGB(c, r, co.getRGB());
                }   
            }   
            ImageIO.write(image,EXTENSION,f);
        } catch (Exception e) {
            status=false;
            e.printStackTrace();
        }
        return status;
    }
}
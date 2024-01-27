import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.function.Function;

import javax.imageio.ImageIO;
import java.awt.*;

public class Image_Editor {

    // This function prints the value of all the pixels in the terminal
    public static void printpixel(BufferedImage inputImage) {
        int height = inputImage.getHeight();
        int width = inputImage.getWidth();
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                Color pixel = new Color(inputImage.getRGB(j, i));
                System.out.print(" (" + pixel.getRed() + " " + pixel.getGreen() + " " + pixel.getBlue() + ")");
            }
            System.out.println();
        }
    }

    // This function is going to convert the given inmage into a greyscale image
    public static BufferedImage convertToGrayscale(BufferedImage inputImage) {
        int width = inputImage.getWidth();
        int height = inputImage.getHeight();
        BufferedImage outputImage = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_GRAY);
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                outputImage.setRGB(j, i, inputImage.getRGB(j, i));
            }
        }
        return outputImage;
    }

    // Function to increase/decrease the brightness of an image by a provided value
    public static BufferedImage changeBrightness(BufferedImage inputImage, int increase) {
        int width = inputImage.getWidth();
        int height = inputImage.getHeight();
        BufferedImage outputImage = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                Color pixel = new Color(inputImage.getRGB(j, i));
                int red = pixel.getRed(), green = pixel.getGreen(), blue = pixel.getBlue();
                red = red + ((red * increase) / 100);
                green = green + ((green * increase) / 100);
                blue = blue + ((blue * increase) / 100);
                if (red > 255)
                    red = 255;
                if (green > 255)
                    green = 255;
                if (blue > 255)
                    blue = 255;
                if (red < 0)
                    red = 0;
                if (red < 0)
                    green = 0;
                if (red < 0)
                    blue = 0;
                Color newPixel = new Color(red, green, blue);
                outputImage.setRGB(j, i, newPixel.getRGB());
            }
        }
        return outputImage;
    }

    // This function inverts the image horizontally
    public static BufferedImage invertHorizontal(BufferedImage inputImage) {
        int width = inputImage.getWidth();
        int height = inputImage.getHeight();
        BufferedImage outputImage = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                outputImage.setRGB((width - 1) - j, i, inputImage.getRGB(j, i));
            }
        }
        return outputImage;
    }

    // This function inverts the image horizontally
    public static BufferedImage invertvertical(BufferedImage inputImage) {
        int width = inputImage.getWidth();
        int height = inputImage.getHeight();
        BufferedImage outputImage = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                outputImage.setRGB(j, (height - 1) - i, inputImage.getRGB(j, i));
            }
        }
        return outputImage;
    }

    // This function blurs the image
    public static BufferedImage blurImage(BufferedImage inputImage, int strength) {
        int width = inputImage.getWidth();
        int height = inputImage.getHeight();
        BufferedImage outputImage = new BufferedImage((width), (height), BufferedImage.TYPE_3BYTE_BGR);
        for (int i = 0; i < height; i += strength) {
            for (int j = 0; j < width; j += strength) {
                int red = 0, green = 0, blue = 0, count = 0;
                for (int m = i; m < (i + strength) && (m < height); m++) {
                    for (int n = j; n < (j + strength) && (n < width); n++) {
                        Color pixel = new Color(inputImage.getRGB(n, m));
                        red = red + pixel.getRed();
                        green = green + pixel.getGreen();
                        blue = blue + pixel.getBlue();
                        count++;
                    }
                }
                red = red / count;
                green = green / count;
                blue = blue / count;
                Color new_pixel = new Color(red, green, blue);
                for (int m = i; m < (i + strength) && (m < height); m++) {
                    for (int n = j; n < (j + strength) && (n < width); n++) {
                        outputImage.setRGB(n, m, new_pixel.getRGB());
                    }
                }
            }
        }
        return outputImage;
    }

    // This function applies a gaussain blur to the image
    public static BufferedImage gaussainBlur(BufferedImage inputImage, int strength) {
        int width = inputImage.getWidth();
        int height = inputImage.getHeight();
        BufferedImage outputImage = new BufferedImage((width), (height), BufferedImage.TYPE_3BYTE_BGR);
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                int red = 0, green = 0, blue = 0, count = 0;
                for (int m = i; m < (i + strength) && (m < height); m++) {
                    for (int n = j; n < (j + strength) && (n < width); n++) {
                        Color pixel = new Color(inputImage.getRGB(n, m));
                        red = red + pixel.getRed();
                        green = green + pixel.getGreen();
                        blue = blue + pixel.getBlue();
                        count++;
                    }
                }
                red = red / count;
                green = green / count;
                blue = blue / count;
                Color new_pixel = new Color(red, green, blue);
                outputImage.setRGB(j, i, new_pixel.getRGB());
            }
        }
        return outputImage;
    }

    // This function makes a tranpose image
    public static BufferedImage transposeImg(BufferedImage inputImage) {
        int width = inputImage.getWidth();
        int height = inputImage.getHeight();
        BufferedImage outputImage = new BufferedImage(height, width, BufferedImage.TYPE_3BYTE_BGR);
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                outputImage.setRGB(i, j, inputImage.getRGB(j, i));
            }
        }
        return outputImage;
    }

    public static void main(String args[]) {

        Scanner sc = new Scanner(System.in);
        try {
            while (true) {
                System.out.println("\n\t********IMAGE EDITOR********");
                System.out.println("Press 1 : To create a greyscale image");
                System.out.println("Press 2 : Change the brightness of the image");
                System.out.println("Press 3 : To Invert the image horizontally");
                System.out.println("Press 4 : To Invert the image vertically");
                System.out.println("Press 5 : Mossiac Blur");
                System.out.println("Press 6 : Gaussian Blur");
                System.out.println("Press 7 : Transpose the image");
                System.out.println("Press 8 : To rotate the image 90 degree clockwise");
                System.out.println("Press 9 : To rotate the image 90 degree anticlockwise");
                System.out.println("Press 10 : To print pixel values");
                System.out.println("Press 11 : Exit\n");
                System.out.print("Enter the file name or path: ");
                String filepath = sc.next();
                File inputFile = new File(filepath);
                BufferedImage inputImage = ImageIO.read(inputFile); // Taking the input of the image
                BufferedImage transImage = transposeImg(inputImage); // Makes a transpose of original image
                System.out.print("\nEnter your choice: ");
                int n = sc.nextInt();
                int exitCode = 0;
                boolean isError = false;
                switch (n) {

                    case 1:
                        BufferedImage grayscale = convertToGrayscale(inputImage);
                        File grayscaleImage = new File("/Users/rudrachauhan/Developer/Image_Editor/Output_Images/grayscaleImage.jpeg"); // Creating a greyscale image and saving it.
                        ImageIO.write(grayscale, "jpeg", grayscaleImage);
                        break;

                    case 2:
                        System.out.print("\nEnter the brightness level (Enter negative value for dimming the image): ");
                        int brightness = sc.nextInt();
                        if (brightness >= -100 && brightness <= 100) {
                            BufferedImage changedBrightness = changeBrightness(inputImage, brightness);
                            File changedBrightnessImage = new File("/Users/rudrachauhan/Developer/Image_Editor/Output_Images/changedBrightnessImage.jpeg"); // Creating an image with increased brightness and saving it.
                            ImageIO.write(changedBrightness, "jpeg", changedBrightnessImage);
                        } else
                            System.out.println("ERROR!!! The input value should be within [-100,100] range.");
                        break;

                    case 3:
                        BufferedImage invHorizontal = invertHorizontal(inputImage);
                        File invertHorintallyImage = new File("/Users/rudrachauhan/Developer/Image_Editor/Output_Images/invertHorintallyImage.jpeg"); // Inverting the image horizontally.
                        ImageIO.write(invHorizontal, "jpeg", invertHorintallyImage);
                        break;

                    case 4:
                        BufferedImage invVertical = invertvertical(inputImage);
                        File invertVerticallyImage = new File("/Users/rudrachauhan/Developer/Image_Editor/Output_Images/invertVerticallyImage.jpeg"); // Inverting the image vertically.
                        ImageIO.write(invVertical, "jpeg", invertVerticallyImage);
                        break;

                    case 5:
                        System.out.print("\nEnter the blur strength: ");
                        int strength = sc.nextInt();
                        if (strength >= 0 && strength <= 100) {
                            BufferedImage bluredImg = blurImage(inputImage, strength);
                            File bluredImage = new File("/Users/rudrachauhan/Developer/Image_Editor/Output_Images/bluredImage.jpeg"); // Mossiac Blur the image.
                            ImageIO.write(bluredImg, "jpeg", bluredImage);
                        } else
                            System.out.println("ERROR!!! The input value should be within [0,100] range");
                        break;

                    case 6:
                        System.out.print("\nEnter the blur strength: ");
                        int strength2 = sc.nextInt();
                        if (strength2 >= 0 && strength2 <= 100) {
                            BufferedImage GBlurImg = gaussainBlur(inputImage, strength2);
                            File gaussianBlur = new File("/Users/rudrachauhan/Developer/Image_Editor/Output_Images/gaussianBlur.jpeg"); // Gaussian Blur the image.  
                            ImageIO.write(GBlurImg, "jpeg", gaussianBlur);
                        } else
                            System.out.println("ERROR!!! The input value should be within [0,100] range");
                        break;

                    case 7:
                        File tranpose = new File("/Users/rudrachauhan/Developer/Image_Editor/Output_Images/transpose.jpeg"); // Makes a transpose image.
                        ImageIO.write(transImage, "jpeg", tranpose);
                        break;

                    case 8:
                        BufferedImage clock90 = invertHorizontal(transImage);
                        File clockwise90 = new File("/Users/rudrachauhan/Developer/Image_Editor/Output_Images/clockwise90.jpeg"); // Rotates the image 90 degree clockwise.
                        ImageIO.write(clock90, "jpeg", clockwise90);
                        break;

                    case 9:
                        BufferedImage anticlock90 = invertvertical(transImage);
                        File anticlockwise90 = new File("/Users/rudrachauhan/Developer/Image_Editor/Output_Images/anticlock90.jpeg"); // Rotates the image 90 degree anticlockwise.
                        ImageIO.write(anticlock90, "jpeg", anticlockwise90);
                        break;

                    case 10:
                        printpixel(inputImage); // Printing the image pixel by pixel in the terminal
                        break;

                    case 11:
                        exitCode = 1;
                        break;

                    default:
                        System.out.println("SOME ERROR OCCURED!!! ");
                        isError = true;

                }
                if (exitCode == 1) {
                    break;
                }
                if (!isError) {
                    System.out.println("\nThe image was succesfully created.");
                }

            }
        }

        catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}

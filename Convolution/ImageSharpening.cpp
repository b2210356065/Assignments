#include "ImageSharpening.h"
#include "Convolution.h"
#include "ImageMatrix.h"
#include "iostream"
ImageSharpening::ImageSharpening() {
    // Default kernel

}

ImageSharpening::~ImageSharpening() {

}



ImageMatrix ImageSharpening::sharpen(const ImageMatrix& input_image, double k) {
    double* blurringkernel[3] = {
            new double[3]{1.0/9.0, 1.0/9.0, 1.0/9.0},
            new double[3]{1.0/9.0, 1.0/9.0, 1.0/9.0},
            new double[3]{1.0/9.0, 1.0/9.0, 1.0/9.0}
    };
    Convolution* bmb = new Convolution(blurringkernel,3,3,1, true);
    ImageMatrix blurred = bmb->convolve(input_image);
    ImageMatrix image;
    image=input_image;
    int height=input_image.get_height();
    int width=input_image.get_width();
    ImageMatrix sharpened = image+ (image - blurred) * k;
    for (int i = 0; i < height; i++) {
        for (int j = 0; j < width; j++) {

            if (sharpened.get_data()[i][j] < 0) {
                sharpened.get_data()[i][j] = 0;
            } else if (sharpened.get_data()[i][j] > 255) {
                sharpened.get_data()[i][j] = 255;
            }
        }
    }

    return sharpened;
}
#ifndef IMAGE_SHARPENING_H
#define IMAGE_SHARPENING_H

#include "ImageMatrix.h"
#include "Convolution.h"

class ImageSharpening {
    public:
        ImageSharpening(); // default constructor
        ~ImageSharpening(); 

        ImageMatrix sharpen(const ImageMatrix& input_image, double k);  

    private:
        // add your private functions and variables
        double* blurringkernel[3] = {
                new double[3]{1.0, 1.0, 1.0},
                new double[3]{1.0, 1.0, 1.0},
                new double[3]{1.0, 1.0, 1.0}
        };
};

#endif // IMAGE_SHARPENING_H

// Convolution.h
#ifndef CONVOLUTION_H
#define CONVOLUTION_H

#include "ImageMatrix.h"

// Class `Convolution`: Provides the functionality to convolve an image with
// a kernel. Padding is a bool variable, indicating whether to use zero padding or not.
class Convolution {
public:
    // Constructors and destructors
    Convolution(); // Default constructor
    Convolution(double** customKernel, int kernelHeight, int kernelWidth, int stride, bool padding); // Parametrized constructor for custom kernel and other parameters
    ~Convolution(); // Destructor

    Convolution(const Convolution &other); // Copy constructor
    Convolution& operator=(const Convolution &other); // Copy assignment operator

    /**
     * Function `convolve`: Responsible for convolving the input image with a kernel and return the convolved image.
     * @param input_image: The input image to be convolved.
     * @return The convolved image.
     */

    ImageMatrix convolve(const ImageMatrix& input_image) const; // Convolve Function: Responsible for convolving the input image with a kernel and return the convolved image.


private:
    // Add any private member variables and functions .
    int height;
    int width;
    double **data;
    double** kernel;    int kh=3;
    int kw=3;
    int stride_val=1;
    double** convelvedata;
    bool pad;

};

#endif // CONVOLUTION_H

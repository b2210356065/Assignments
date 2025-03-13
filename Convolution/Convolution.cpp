#include <iostream>

#include "Convolution.h"

// Default constructor 
Convolution::Convolution() {
    this->kw=3;
    this->kh=3;
    this->stride_val=1;
    this->pad= true;
    this->kernel = new double*[kh];
    for (int i=0;i<kh;i++){
        this->kernel[i] = new double[kw];
        for (int j = 0; j < kw; ++j) {
            this->kernel[i][j]=0.0;
        }
    }
}

// Parametrized constructor for custom kernel and other parameters
Convolution::Convolution(double** customKernel, int kh, int kw, int stride_val, bool pad){
    this->kw=kw;
    this->kh=kh;
    this->stride_val=stride_val;
    this->pad=pad;
    this->kernel = new double*[kh];
    for (int i=0;i<kh;i++){
        this->kernel[i] = new double[kw];
        for (int j = 0; j < kw; ++j) {
            this->kernel[i][j]=customKernel[i][j];
        }
    }
}
// Destructor
Convolution::~Convolution() {
    if (kernel != nullptr) {
        for (int i = 0; i < kw; i++) {
            delete[] kernel[i];
        }
        delete[] kernel;
        kernel = nullptr;
    }
}

// Copy constructor
Convolution::Convolution(const Convolution &other){
    height = other.height;
    width = other.width;
    data = new double*[height];
    for(int i = 0; i < height; ++i) {
        data[i] = new double[width];
        for(int j = 0; j < width; ++j) {
            data[i][j] = other.data[i][j];
        }
    }
}

// Copy assignment operator
Convolution& Convolution::operator=(const Convolution &other) {
    if(this == &other) {
        return *this;
    }

    if(data != nullptr) {
        for(int i = 0; i < height; ++i) {
            delete[] data[i];
        }
        delete[] data;
    }


    height = other.height;
    width = other.width;
    data = new double*[height];
    for(int i = 0; i < height; ++i) {
        data[i] = new double[width];
        for(int j = 0; j < width; ++j) {
            data[i][j] = other.data[i][j];
        }
    }


    return *this;
}
//---------------------------------------------------------------------------------------------

// Convolve Function: Responsible for convolving the input image with a kernel and return the convolved image.
ImageMatrix Convolution::convolve(const ImageMatrix& input_image) const {
    int padding=(pad) ? 1 : 0;
    int height=input_image.get_height();
    int width=input_image.get_width();

    int outputheight=((height-kh+2*padding)/stride_val)+1;

    int outputwidth=((width-kw+2*padding)/stride_val)+1;

    ImageMatrix result(outputheight, outputwidth);
    ImageMatrix convdata(height+2*padding,width+2*padding);
    for (int i = 0; i <height ; ++i) {
        for (int j = 0; j < width; ++j) {
            convdata.get_data()[i+padding][j+padding]=input_image.get_data()[i][j];
        }
    }

    int dx=-1;
    int dy=-1;
    for (int i = 0; i < height+2*padding-kh+1; i=i+stride_val) {
        dy++;
        dx=-1;
        for (int j = 0; j < width+2*padding-kw+1; j=j+stride_val) {
            dx++;
            double sum=0;
            int flag= 1;
            for (int k = 0; k < kh; ++k) {
                for (int l = 0; l <kw ; ++l) {
                    if (i+kh-1>height+2*padding||j+kw-1>height+2*padding){
                        sum=sum+convdata.get_data()[i+k][j+l]*kernel[k][l];
                        flag= 0;
                    }
                    else{
                        sum=sum+convdata.get_data()[i+k][j+l]*kernel[k][l];
                    }
                }
                if(flag== 0){
                    sum=0;
                }
                result.get_data()[dy][dx]=sum;
            }
        }
    }


    return result;
}

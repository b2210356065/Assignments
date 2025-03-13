// EdgeDetector.cpp

#include "EdgeDetector.h"
#include <cmath>
#include "iostream"
#include "EdgeDetector.h"
#include <cmath>

// Default constructor
EdgeDetector::EdgeDetector() {
}


// Destructor
EdgeDetector::~EdgeDetector() {

}

// Detect Edges using the given algorithm
std::vector<std::pair<int, int>> EdgeDetector::detectEdges(const ImageMatrix& input_image) {
    std::vector<std::pair<int, int>> edge_points;
    int width = input_image.get_width();
    int height = input_image.get_height();
    Convolution* dx = new Convolution(sobel_x,3,3,1, true);
    Convolution* dy = new Convolution(sobel_y,3,3,1, true);
    ImageMatrix Gx = dx->convolve(input_image);
    ImageMatrix Gy = dy->convolve(input_image);
    Convolution convolution(sobel_x, 3, 3, 1, true);
    double sum=0;
    for (int i = 0; i < height; ++i) {
        for (int j = 0; j < width; ++j) {
            double gradx=Gx.get_data(i,j);
            double grady=Gy.get_data(i,j);
            double magn= sqrt(gradx*gradx+grady*grady);
            sum=sum+magn;
        }
    }
    double threshold=sum/(height*width);
    for (int i = 0; i < height; ++i) {
        for (int j = 0; j < width; ++j) {
            double gradx=Gx.get_data(i,j);
            double grady=Gy.get_data(i,j);
            double magn= sqrt(gradx*gradx+grady*grady);
            if (magn>threshold){
                edge_points.push_back(std::make_pair(i,j));
            }
        }
    }


    return edge_points;
}


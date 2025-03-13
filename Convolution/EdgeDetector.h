    #ifndef EDGE_DETECTOR_H
    #define EDGE_DETECTOR_H

    #include "ImageMatrix.h"
    #include "Convolution.h"
    #include <vector>

    class EdgeDetector {
        public:
            EdgeDetector(); 
            ~EdgeDetector(); 
            
            std::vector<std::pair<int, int>> detectEdges(const ImageMatrix& input_image);
        double* sobel_x[3] =     {  new double[3]{-1, 0, 1},
                               new double[3]{-2, 0, 2},
                               new double[3]{-1, 0, 1}};
        double* sobel_y[3] =      {new double[3]{-1, -2, -1},
                              new double[3]{0, 0, 0},
                              new double[3]{1, 2, 1}};


    private:
            // add your private member variables and functions
    };

    #endif // EDGE_DETECTOR_H



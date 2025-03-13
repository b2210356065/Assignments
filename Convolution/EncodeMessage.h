#ifndef ENCODE_MESSAGE_H
#define ENCODE_MESSAGE_H

#include <string>
#include <vector>
#include "ImageMatrix.h"

class EncodeMessage {
public:
    EncodeMessage();
    ~EncodeMessage();

    ImageMatrix encodeMessageToImage(const ImageMatrix &img, const std::string &message, const std::vector<std::pair<int, int>>& positions);

    bool isPrime(int num);
    int fibonacci(int x);
    std::string circular(std::string x);
private:
    // Any private helper functions or variables if necessary


};

#endif // ENCODE_MESSAGE_H

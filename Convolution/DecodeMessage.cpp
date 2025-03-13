#include "DecodeMessage.h"
#include <iostream>
#include <bitset>

// Default constructor
DecodeMessage::DecodeMessage() {
    // Nothing specific to initialize here
}

// Destructor
DecodeMessage::~DecodeMessage() {
    // Nothing specific to clean up
}

std::string DecodeMessage::decodeFromImage(const ImageMatrix& image, const std::vector<std::pair<int, int>>& edgePixels) {
    std::string decoded_message="";
    std::string ascii="";
    std::string lsb_value;
    for (int i = 0; i < edgePixels.size(); ++i) {
        int value = image.get_data(edgePixels.at(i).first, edgePixels.at(i).second);
        int LSb = value & 1;
        std::string str_num = std::to_string(LSb);
        ascii+=(str_num);
    }

    int sevens=7-ascii.size()%7;

    for (int z = 0; z <sevens ; ++z) {
        ascii.insert(0,"0") ;
    }
    for (int i = 0; i < ascii.size(); i+=7) {
        std::string substring ="";
        for (int j = 0; j < 7; ++j) {
            substring+=ascii[i+j];
        }


        int decimalValue = std::bitset<7>(substring).to_ulong();

        if (decimalValue>126){
            decimalValue=126;
        }
        else if(decimalValue<0){
            decimalValue=33;
        }
        else if (decimalValue<33){
            decimalValue+=33;
        }
        char asciiChar = static_cast<char>(decimalValue);
        decoded_message+=asciiChar;
    }
    return decoded_message;
}

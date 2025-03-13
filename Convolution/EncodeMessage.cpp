#include "EncodeMessage.h"
#include <cmath>
#include <bitset>

#include "iostream"

// Default Constructor
EncodeMessage::EncodeMessage() {

}

// Destructor
EncodeMessage::~EncodeMessage() {
    
}

// Function to encode a message into an image matrix
ImageMatrix EncodeMessage::encodeMessageToImage(const ImageMatrix &img, const std::string &message, const std::vector<std::pair<int, int>>& positions) {
    std::string kodmesaj="";
    std::string message1=std::string (message);
    for (int i = 0; i < message1.size(); ++i) {
        bool flag= true;
        char harf=message1[i];
        int sayi=harf;
        if (i < 2) {
            flag= false;
        }
        for (int j = 2; j <= i/2; ++j) {
            if (i % j == 0) {
                flag= false;
            }
        }
        if(flag){
            int fib=0;
            if (i == 1) {
                fib=1;
            }
            else if (i == 0){
                fib=0;
            } else {
                 fib = 1;
                 int prev = 0;
                for (int j = 2; j <= i; ++j) {
                    int temp = fib;
                    fib += prev;
                    prev = temp;
                }

            }
            sayi=fib+sayi;
        }
        if(sayi>=127) {
            sayi = 126;
        }
        else if (sayi<=32){
            sayi=sayi+33;
        }
        char asciiChar = static_cast<char>(sayi);
        kodmesaj+=asciiChar;
    }



    int leng=kodmesaj.size();

    std::string encod=kodmesaj.substr(leng/2+1)+kodmesaj.substr(0,leng/2+1);
    std::string binar="";
    for (int i = 0; i < encod.size(); ++i) {
        int decimalValue=encod[i];
        std::string binaryValue = "";
        while (decimalValue > 0) {
            int remainder = decimalValue % 2;
            binaryValue =std:: to_string(remainder) + binaryValue;
            decimalValue /= 2;
        }
        binar+=binaryValue;
    }
    int size=positions.size();
    leng=binar.size();
    if(leng>size){
        binar=binar.substr(0,size);
    }
    for (int i =0;i<binar.size();i++){
        int fet = binar[i];
        fet=fet-48;
        int decimalValue=img.get_data(positions[i].first,positions[i].second);
        int fedd=decimalValue%1;
        img.get_data()[positions[i].first][positions[i].second]=decimalValue-fedd+fet;
    }
    return img;

}
//0 0 0 0 0 0 0 0 0 1 1 61 2 43 119 193 118 118 61 0 1 0 0 0 0 0 0 0
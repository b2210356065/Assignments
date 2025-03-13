#include "ImageProcessor.h"
#include "ImageMatrix.h"
#include "ImageSharpening.h"
#include "Convolution.h"
#include "EdgeDetector.h"
#include "DecodeMessage.h"
#include "EncodeMessage.h"
ImageProcessor::ImageProcessor() {

}

ImageProcessor::~ImageProcessor() {

}


std::string ImageProcessor::decodeHiddenMessage(const ImageMatrix &img) {
    ImageSharpening Isharpened;
    ImageMatrix ads = Isharpened.sharpen(img, 2);
    EdgeDetector edge;

    DecodeMessage decodeMessage;
    return     decodeMessage.decodeFromImage(ads,edge.detectEdges(ads));

}

ImageMatrix ImageProcessor::encodeHiddenMessage(const ImageMatrix &img, const std::string &message) {
    EdgeDetector edg;
    EncodeMessage encode;
    ImageMatrix encodedImage = encode.encodeMessageToImage(img, message,  edg.detectEdges(img));

    return encodedImage;

}

#ifndef PA2_BLOCK_H
#define PA2_BLOCK_H

#include <vector>

using namespace std;

class Block {
public:
    int point=0;
    int maksimumpoint=0;

    vector<vector<bool>> shape; // Two-dimensional vector corresponding to the block's shape
    Block * right_rotation = nullptr; // Pointer to the block's clockwise neighbor block (its right rotation)
    Block * left_rotation = nullptr; // Pointer to the block's counter-clockwise neighbor block (its left rotation)
    Block * next_block = nullptr; // Pointer to the next block to appear in the game

    bool operator==(const Block& other) const {
        if (other.shape.size() != shape.size() || other.shape[0].size() != shape[0].size()) {
            return false;  // Boyutlar eşit değilse hemen false döndür
        }

        for (int i = 0; i < shape.size(); ++i) {
            for (int j = 0; j < shape[0].size(); ++j) {
                if (other.shape[i][j] == shape[i][j]) {
                    // İçerik farklıysa false döndür
                }
                else{
                    return false;
                }
            }
        }
        // TODO: Overload the == operator to compare two blocks based on their shapes
        return true;
    }

    bool operator!=(const Block& other) const {
        // TODO: Overload the != operator to compare two blocks based on their shapes
        return !(shape == other.shape);
    }
};


#endif //PA2_BLOCK_H

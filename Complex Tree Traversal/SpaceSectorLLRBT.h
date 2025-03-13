#ifndef SPACESECTORLLRBT_H
#define SPACESECTORLLRBT_H

#include "Sector.h"
#include <iostream>
#include <fstream>  
#include <sstream>
#include <vector>

class SpaceSectorLLRBT {
public:
    Sector* root;
    SpaceSectorLLRBT();
    ~SpaceSectorLLRBT();
    void readSectorsFromFile(const std::string& filename);
    void insertSectorByCoordinates(int x, int y, int z);
    void displaySectorsInOrder();
    void displaySectorsPreOrder();
    void displaySectorsPostOrder();
    std::vector<Sector*> getStellarPath(const std::string& sector_code);
    void printStellarPath(const std::vector<Sector*>& path);
    void moid(Sector*& node, Sector* point,Sector *&rot);
    Sector* getNode(int x, int y, int z);
    void controlbalance(Sector * node);
    void rotater(Sector *node);
    void rotatel(Sector *node);
    void inorder(Sector * point);
    void preorder(Sector * point);
    void postorder(Sector * point);
    int findpath(Sector* point,std::string sectorcode,std::string sectorcode2,std::vector<Sector*> &path);
    void yikolmyik(Sector* node);
    bool isred(Sector* node);

};

#endif // SPACESECTORLLRBT_H

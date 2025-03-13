#ifndef SPACESECTORBST_H
#define SPACESECTORBST_H

#include <iostream>
#include <fstream>  
#include <sstream>
#include <vector>

#include "Sector.h"

class SpaceSectorBST {
  
public:
    Sector *root;
    SpaceSectorBST();
    ~SpaceSectorBST();
    void readSectorsFromFile(const std::string& filename); 
    void insertSectorByCoordinates(int x, int y, int z);
    void deleteSector(const std::string& sector_code);
    void displaySectorsInOrder();
    void displaySectorsPreOrder();
    void displaySectorsPostOrder();
    std::vector<Sector*> getStellarPath(const std::string& sector_code);
    void printStellarPath(const std::vector<Sector*>& path);
    Sector* getNode(int x, int y, int z);
    void inorder(Sector * point);
    void preorder(Sector * point);
    void postorder(Sector * point);
    void moid(Sector *&node, Sector *point,Sector *&rot);
    bool findpath(Sector* point,std::string sectorcode,std::vector<Sector*> &path);
    void yikolmyik(Sector* node);
    Sector* find(Sector* node,std::string sector);
    Sector* findinorder(Sector* node);
};

#endif // SPACESECTORBST_H

#include <valarray>
#include "SpaceSectorBST.h"

using namespace std;

Sector* SpaceSectorBST::getNode(int x, int y, int z) {
    return new Sector(x, y, z);
}

void SpaceSectorBST::inorder(Sector * point){
    Sector *temp=point;
    if(temp== nullptr){
        return;
    }
    inorder(temp->left);
    std::cout<<temp->sector_code<<"\n";
    inorder(temp->right);
}
void SpaceSectorBST::preorder(Sector * point){
    Sector *temp=point;
    if(temp== nullptr){
        return;
    }
    std::cout<<temp->sector_code<<"\n";
    preorder(temp->left);
    preorder(temp->right);
}
void SpaceSectorBST::postorder(Sector* point){
    if(point== nullptr){
        return;
    }
    postorder(point->left);
    postorder(point->right);
    std::cout<<point->sector_code<<"\n";
}

SpaceSectorBST::SpaceSectorBST() : root(nullptr) {}
void   SpaceSectorBST::yikolmyik(Sector* node){
    if(node== nullptr){
        return;
    }
    yikolmyik(node->left);
    yikolmyik(node->right);
    if(node==root){
        delete *&root;
        return;
    }
    delete node;
}
SpaceSectorBST::~SpaceSectorBST() {
    // Free any dynamically allocated memory in this class.
    yikolmyik(root);
}

void SpaceSectorBST::readSectorsFromFile(const std::string& filename) {
    // TODO: read the sectors from the input file and insert them into the BST sector map
    // according to the given comparison critera based on the sector coordinates.
    ifstream file(filename);
    string line;
    if (std::getline(file, line)) {}
    while (std::getline(file, line)){
        std::vector<std::string> result;
        std::stringstream ss(line);
        std::string item;

        while (std::getline(ss, item, ',')) {
            result.push_back(item);
        }
        int x,y,z;
        x=std::stoi(result[0]);
        y=std::stoi(result[1]);
        z=std::stoi(result[2]);
        insertSectorByCoordinates(x,y,z);
    }
}

void SpaceSectorBST::insertSectorByCoordinates(int x, int y, int z) {
    // Instantiate and insert a new sector into the space sector BST map according to the 
    // coordinates-based comparison criteria.
    if(root== nullptr){
        root= getNode(0,0,0);
        return;
    }
    Sector *moint= getNode(x,y,z);
    moid(root,moint, root);
}

void SpaceSectorBST::moid(Sector*& node, Sector* point,Sector *&rot) {
    if (node == nullptr) {
        node = point;
        node->parent=rot;
        return;
    }

    if (node->x < point->x) {
        moid(node->right, point,node);
    } else if (node->x > point->x) {
        moid(node->left, point,node);
    } else {
        if (node->y < point->y) {
            moid(node->right, point,node);
        } else if (node->y > point->y) {
            moid(node->left, point,node);
        } else {
            if (node->z < point->z) {
                moid(node->right, point,node);
            } else if (node->z > point->z) {
                moid(node->left, point,node);
            } else {
                // Handle the case where node is equal to point
                // This part may depend on your specific requirements
            }
        }
    }
}

void SpaceSectorBST::deleteSector(const std::string& sector_code) {
    // TODO: Delete the sector given by its sector_code from the BST.
    Sector * k= find(root,sector_code);
    if(k->right== nullptr&&k->left== nullptr){
        delete k;
        return;
    }
    if(k->right== nullptr){
        if(k->parent!= nullptr&&k->parent->right==k){
            k->parent->right=k->left;
            delete k;
        }
        if(k->parent!= nullptr&&k->parent->left==k){
            k->parent->left=k->left;
            delete k;

        }
        return;

    }
    if(k->left== nullptr){
        if(k->parent!= nullptr&&k->parent->right==k){
            k->parent->right=k->right;
            delete k;

        }
        if(k->parent!= nullptr&&k->parent->left==k){
            k->parent->left=k->right;
            delete k;

        }
        return;
    }
    Sector* l = findinorder(k->right);
    if(k->parent!= nullptr&&k->parent->right==k){
        if(l->right!= nullptr){
            l->parent->right=l->right;
        }
        l->parent=k->parent;
        l->right=k->right;
        l->left=k->left;
        k->parent->right=l;
        delete k;
    }
    if(k->parent!= nullptr&&k->parent->left==k){
        if(l->right!= nullptr){
            l->parent->right=l->right;
        }
        l->parent=k->parent;
        l->right=k->right;
        l->left=k->left;
        k->parent->left=l;
        delete k;
    }

}
Sector* SpaceSectorBST::find(Sector* node,std::string sector){
    if(node== nullptr){
        return nullptr;
    }
    Sector* k=find(node->left,sector);
    Sector* l=find(node->right,sector);
    if(k!= nullptr){
        return k;
    }
    if(l!= nullptr){
        return l;
    }
    if(node->sector_code==sector){
        return node;
    }
    return nullptr;
}
Sector* SpaceSectorBST::findinorder(Sector* node){
    if(node->left== nullptr){
        return node;
    }
    Sector* k=findinorder(node->left);
    if(k!= nullptr){
        return k;
    }
}



void SpaceSectorBST::displaySectorsInOrder() {
    // TODO: Traverse the space sector BST map in-order and print the sectors 
    // to STDOUT in the given format.
    std::cout<<"Space sectors inorder traversal:\n";
    inorder(root);
    std::cout<<"\n";
}


void SpaceSectorBST::displaySectorsPreOrder() {
    // TODO: Traverse the space sector BST map in pre-order traversal and print 
    // the sectors to STDOUT in the given format.
    std::cout<<"Space sectors preorder traversal:\n";
    preorder(root);
    std::cout<<"\n";
}

void SpaceSectorBST::displaySectorsPostOrder() {
    // TODO: Traverse the space sector BST map in post-order traversal and print 
    // the sectors to STDOUT in the given format.
    std::cout<<"Space sectors postorder traversal:\n";
    postorder(root);
    std::cout<<"\n";
}
bool SpaceSectorBST::findpath(Sector* point,string sectorcode,std::vector<Sector*> &path){
    if(point== nullptr){
        return 0;
    }
    if(point->sector_code==sectorcode){
        path.push_back(point);
        return 1;
    }
    if(findpath(point->left,sectorcode,path)){
        path.push_back(point);
        return 1;
    }
    if(findpath(point->right,sectorcode,path)){
        path.push_back(point);
        return 1;
    }
    return 0;
}

std::vector<Sector*> SpaceSectorBST::getStellarPath(const std::string& sector_code) {
    std::vector<Sector*> path;
    std::vector<Sector*> pat1;
    // TODO: Find the path from the Earth to the destination sector given by its
    // sector_code, and return a vector of pointers to the Sector nodes that are on
    // the path. Make sure that there are no duplicate Sector nodes in the path!
    if(findpath(root,sector_code,pat1)){
        for (int i = 0; i < pat1.size(); ++i) {
            path.push_back(pat1[pat1.size()-1-i]);
        }
    };
    return path;
}

void SpaceSectorBST::printStellarPath(const std::vector<Sector*>& path) {
    // TODO: Print the stellar path obtained from the getStellarPath() function 
    // to STDOUT in the given format.
    if(path.size()==0){
        std::cout<<"A path to Dr. Elara could not be found.\n";
        return;
    }
    std::cout<<"The stellar path to Dr. Elara: ";
    for (int i = 0; i < path.size(); ++i) {
        if(i==path.size()-1){
            std::cout<<path[i]->sector_code<<"\n";
            return;
        }
        std::cout<<path[i]->sector_code<<"->";
    }
}
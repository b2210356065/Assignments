#include "SpaceSectorLLRBT.h"
#include "Sector.h"
#include "SpaceSectorBST.h"
using namespace std;

SpaceSectorLLRBT::SpaceSectorLLRBT() : root(nullptr) {}

void SpaceSectorLLRBT::readSectorsFromFile(const std::string& filename) {
    // TODO: read the sectors from the input file and insert them into the LLRBT sector map
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

// Remember to handle memory deallocation properly in the destructor.
SpaceSectorLLRBT::~SpaceSectorLLRBT() {
    // TODO: Free any dynamically allocated memory in this class.
    yikolmyik(root);
}

void SpaceSectorLLRBT::insertSectorByCoordinates(int x, int y, int z) {
    // TODO: Instantiate and insert a new sector into the space sector LLRBT map 
    // according to the coordinates-based comparison criteria.
    if(root== nullptr){
        root= getNode(0,0,0);
        root->color= false;
        root->parent= nullptr;
        return;
    }
    Sector *moint= getNode(x,y,z);
    moid(root,moint, root);
}
Sector* SpaceSectorLLRBT::getNode(int x,int y,int z){
    return new Sector(x, y, z);
}
bool SpaceSectorLLRBT::isred(Sector *node){
    if(node== nullptr){
        return 0;
    }
    return node->color;
}

void SpaceSectorLLRBT::controlbalance(Sector * node){
    if(node== nullptr){
        return;
    }
    bool x= false;
    bool y= false;
    if(node->left!= nullptr){
        x=node->left->color;
    }
    if(node->right!= nullptr){
        y=node->right->color;
    }

    if(!x&&y){
        rotater(node);
    }
    else if(x&&!y){
        if(isred(node->left->left)){
            rotatel(node);
        }
    }
    else if(x&&y){
        node->left->color= false;
        node->right->color= false;
        node->color=!node->color;
        root->color= false;
    }
    controlbalance(node->parent);

}
void SpaceSectorLLRBT::rotater(Sector * node){
    Sector *point=node->right;
    if(node==root){
        point->parent= nullptr;
        root=point;
    }else{
        Sector *moint=node->parent;
        if(moint->right==node){
            moint->right=point;
        }else{
            moint->left=point;
        }
        point->parent=moint;
    }
    if(point->left!= nullptr){
        node->right=point->left;
        point->left->parent=node;
    }else{
        node->right= nullptr;
    }
    point->left= node;
    node->parent=point;
    point->color= false;
    node->color= true;
    controlbalance(node);
}
void SpaceSectorLLRBT::rotatel(Sector * node){
    Sector *point=node->left;
    if(node==root){
        point->parent= nullptr;
        root=point;
    }else{
        Sector *moint=node->parent;
        if(moint->right==node){
            moint->right=point;
        }else{
            moint->left=point;
        }
        point->parent=moint;
    }
    if(point->right!= nullptr){
        node->left=point->right;
        point->right->parent=node;
    }else{
        node->left= nullptr;
    }
    point->right= node;
    node->parent=point;
    point->color= false;
    node->color= true;
    controlbalance(node);
}
void SpaceSectorLLRBT::moid(Sector*& node, Sector* point,Sector *&rot) {
    if (node == nullptr) {
        node = point;
        node->color= true;
        node->parent=rot;
        controlbalance(node);
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
            }
        }
    }
}

void SpaceSectorLLRBT::displaySectorsInOrder() {
    // TODO: Traverse the space sector LLRBT map in-order and print the sectors 
    // to STDOUT in the given format.
    std::cout<<"Space sectors inorder traversal:\n";
    inorder(root);
    std::cout<<"\n";
}


void SpaceSectorLLRBT::displaySectorsPreOrder() {
    // TODO: Traverse the space sector LLRBT map in pre-order traversal and print 
    // the sectors to STDOUT in the given format.
    std::cout<<"Space sectors preorder traversal:\n";
    preorder(root);
    std::cout<<"\n";
}

void SpaceSectorLLRBT::displaySectorsPostOrder() {
    // TODO: Traverse the space sector LLRBT map in post-order traversal and print 
    // the sectors to STDOUT in the given format.
    std::cout<<"Space sectors postorder traversal:\n";
    postorder(root);
    std::cout<<"\n";
}
void SpaceSectorLLRBT::inorder(Sector * point){
    Sector *temp=point;
    if(temp== nullptr){
        return;
    }

    inorder(temp->left);
    if(temp->color) std::cout<<"RED sector: ";
    else std::cout<<"BLACK sector: ";
    std::cout<<temp->sector_code<<"\n";
    inorder(temp->right);
}
void SpaceSectorLLRBT::preorder(Sector * point){
    Sector *temp=point;
    if(temp== nullptr){
        return;
    }
    if(temp->color) std::cout<<"RED sector: ";
    else std::cout<<"BLACK sector: ";
    std::cout<<temp->sector_code<<"\n";
    preorder(temp->left);
    preorder(temp->right);
}
void SpaceSectorLLRBT::postorder(Sector* point){
    if(point== nullptr){
        return;
    }
    postorder(point->left);
    postorder(point->right);
    if(point->color) std::cout<<"RED sector: ";
    else std::cout<<"BLACK sector: ";
    std::cout<<point->sector_code<<"\n";
}
int SpaceSectorLLRBT::findpath(Sector* point,std::string sectorcode,std::string sectorcode2,std::vector<Sector*> &path){
    if(point== nullptr)return 0;
    int a =findpath(point->left,sectorcode,sectorcode2,path);
    int b=findpath(point->right,sectorcode,sectorcode2,path);
    if(point->sector_code==sectorcode){
        path.push_back(point);
        return 1;
    }
    if(point->sector_code==sectorcode2){
        path.push_back(point);
        return -1;
    }
    if(a*b==-1){
        path.push_back(point);
        return 0;
    }
    if(a==1||a==-1){
        path.push_back(point);
        return a;
    }
    if(b==1||b==-1){
        path.push_back(point);
        return b;
    }
    return 0;
}


std::vector<Sector*> SpaceSectorLLRBT::getStellarPath(const std::string& sector_code) {
    std::vector<Sector*> path;
    // TODO: Find the path from the Earth to the destination sector given by its
    // sector_code, and return a vector of pointers to the Sector nodes that are on
    // the path. Make sure that there are no duplicate Sector nodes in the path!
    findpath(root,sector_code,"0SSS",path);
    bool x= false;
    bool y= false;
    for(int i=0;i<path.size();i++){
        if(path[i]->sector_code==sector_code){
            x= true;
        }
        if(path[i]->sector_code=="0SSS"){
            y= true;
        }
    }
    if(x&&y){

    }else{
        for(int i=0;i<path.size();i++){
            path.erase(path.begin());
        }
    }
    if(path[0]==root){
    }else{
        for (int i = 0; i < path.size(); ++i) {
            Sector *temp=path[path.size()-1-i];
            path.erase(path.begin()+path.size()-1-i);
            path.push_back(temp);
        }
    }

    return path;
}
void   SpaceSectorLLRBT::yikolmyik(Sector* node){
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
void SpaceSectorLLRBT::printStellarPath(const std::vector<Sector*>& path) {
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
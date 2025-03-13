#include <fstream>
#include <iostream>
#include "BlockFall.h"
#include "Block.h"

BlockFall::BlockFall(string grid_file_name, string blocks_file_name, bool gravity_mode_on, const string &leaderboard_file_name, const string &player_name) : gravity_mode_on(
        gravity_mode_on), leaderboard_file_name(leaderboard_file_name), player_name(player_name) {
    initialize_grid(grid_file_name);
    read_blocks(blocks_file_name);
    leaderboard.read_from_file(leaderboard_file_name);

}

void BlockFall::read_blocks(const string &input_file) {
    // TODO: Read the blocks from the input file and initialize "initial_block" and "initial_block" member variables
    // TODO: For every block, generate its rotations and properly implement the multilevel linked list structure
    //       that represents the game blocks, as explained in the PA instructions.
    // TODO: Initialize the "power_up" member variable as the last block from the input file (do not add it to the linked list!)
    std::ifstream file(input_file);
    if (file.is_open()) {
        vector<vector<vector<bool>>>shap={};
        vector<vector<bool>>shapes={};
        string line;
        while (std::getline(file, line)){
            vector<bool> sekiller={};
            if(line!=""){
                for (int i = 0; i < line.size(); i++){
                    char character= line[i];
                    if (character=='['){
                    }else if(character=='1'){
                        sekiller.push_back(1);
                    }else if (character=='0'){
                        sekiller.push_back(0);
                    }
                    else if(character=='\n'){
                    }
                    else if(character==']'){
                    }
                    else{

                    }
                }if(sekiller.size()>0){
                    power_up.push_back(sekiller);
                }

            }
            else {
                if(power_up.size()>0){
                    shap.push_back(power_up);
                }
                power_up={};

            }

        }
        std::vector<vector<bool>>rotar={};
        std::vector<vector<bool>>rotal={};
        std::vector<vector<bool>>rotad={};
        head=new Block;
        head->next_block=new Block;
        initial_block=head->next_block;
        for (int k =0;k<shap.size();k++){
            Block* next=new Block;
            Block* right=new Block;
            Block* left=new Block;
            Block* down=new Block;
            std::vector<std::vector<bool>> rotar(shap[k][0].size(), std::vector<bool>(shap[k].size(), false));
            std::vector<std::vector<bool>> rotal(shap[k][0].size(), std::vector<bool>(shap[k].size(), false));
            std::vector<std::vector<bool>> rotad(shap[k].size(), std::vector<bool>(shap[k][0].size(), false));
            for(int i=0;i<shap[k].size();i++){
                for(int j=0;j<shap[k][i].size();j++){
                    rotar[j][i]=shap[k][shap[k].size()-1-i][j];
                    rotal[j][i]=shap[k][i][shap[k][0].size()-1-j];
                    rotad[i][j]=shap[k][shap[k].size()-i-1][shap[k][0].size()-j-1];
                }
            }
            if(k==shap.size()-1){
                initial_block->right_rotation=right;
                initial_block->left_rotation=left;
                initial_block->right_rotation->right_rotation=down;
                initial_block->shape=shap[k];
                initial_block->right_rotation->shape=rotar;
                initial_block->right_rotation->right_rotation->shape=rotad;
                initial_block->left_rotation->shape=rotal;
                initial_block->right_rotation->right_rotation->right_rotation=initial_block->left_rotation;
                initial_block->left_rotation->left_rotation=initial_block->right_rotation->right_rotation;
                initial_block->right_rotation->right_rotation->left_rotation=initial_block->right_rotation;
                initial_block->right_rotation->left_rotation=initial_block;
                initial_block->left_rotation->right_rotation=initial_block;
            }
            else{
                initial_block->next_block=next;
                initial_block->right_rotation=right;
                initial_block->left_rotation=left;
                initial_block->right_rotation->right_rotation=down;
                initial_block->shape=shap[k];
                initial_block->right_rotation->shape=rotar;
                initial_block->right_rotation->right_rotation->shape=rotad;
                initial_block->left_rotation->shape=rotal;
                initial_block->right_rotation->right_rotation->right_rotation=initial_block->left_rotation;
                initial_block->left_rotation->left_rotation=initial_block->right_rotation->right_rotation;
                initial_block->right_rotation->right_rotation->left_rotation=initial_block->right_rotation;
                initial_block->right_rotation->left_rotation=initial_block;
                initial_block->left_rotation->right_rotation=initial_block;
                initial_block->right_rotation->next_block=next;
                initial_block->left_rotation->next_block=next;
                initial_block->right_rotation->right_rotation->next_block=next;
            }
            rotar={};
            rotal={};
            rotad={};

            if(k==shap.size()-1){

            }
            else{
                initial_block= initial_block->next_block;

            }

        }
        initial_block->next_block= nullptr;
        initial_block=head->next_block;
    }
    else{
    }

}

void BlockFall::initialize_grid(const std::string& input_file) {
    std::ifstream file(input_file);
    if (file.is_open()) {
        string line;
        while (std::getline(file, line)){
            std::vector<int>grit;
            if(line==""){

            } else {
                for (int i = 0; i < line.size(); i++) {
                    if (line[i] == '0') {
                        grit.push_back(0);
                    }
                    if (line[i] == '1') {
                        grit.push_back(1);
                    }
                }
                grid.push_back(grit);
            }
        }
        rows=grid[0].size();
        cols=grid.size();
    }
}

BlockFall::~BlockFall() {
    // TODO: Free dynamically allocated memory used for storing game blocks
    initial_block=head->next_block;
    Block *s = nullptr;
    while(initial_block->next_block!= nullptr){
        initial_block->right_rotation->right_rotation->next_block = nullptr;
        initial_block->right_rotation->next_block = nullptr;
        initial_block->left_rotation->next_block = nullptr;
        delete initial_block->right_rotation->right_rotation;
        delete initial_block->right_rotation;
        delete initial_block->left_rotation;
        s=initial_block->next_block;
        initial_block->next_block = nullptr;
        delete initial_block;
        initial_block =s;
    }
    initial_block->right_rotation->right_rotation->next_block = nullptr;
    initial_block->right_rotation->next_block = nullptr;
    initial_block->left_rotation->next_block = nullptr;
    delete initial_block->right_rotation->right_rotation;
    delete initial_block->right_rotation;
    delete initial_block->left_rotation;
    s= nullptr;
    delete initial_block;
    delete head;
    active_rotation= nullptr;
    head = nullptr;
    initial_block = nullptr;
}

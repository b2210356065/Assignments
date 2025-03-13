#include <fstream>
#include <iostream>
#include "GameController.h"
#include "Block.h"
#include "BlockFall.h"
#include "LeaderboardEntry.h"
void GameController::printgrid(BlockFall & game){
    for(int k=0;k<game.grid.size();k++){
        for (int l = 0; l < game.grid[0].size(); ++l) {
            if(game.grid[k][l]==1){
                std::cout<<occupiedCellChar;
            }
            else{
                std::cout<<unoccupiedCellChar;
            }
        }
        std::cout<<"\n";
    }
    std::cout<<"\n";
    std::cout<<"\n";
}
void GameController::control(BlockFall& game){
    int say=0;
    int col=0;
    int sayma=0;
    for(int j=0;j<game.grid.size();j++) {
        for (int i = 0; i < game.grid[0].size(); i++) {
            if (game.grid[j][i] == 0) {
                sayma=0;
            }
            else if(game.grid[j][i] == 1){
                sayma++;
            }
        }
        if(sayma==game.grid[0].size()){
            std::cout<<"Before clearing:\n";
            printgrid(game);
            break;
        }
        sayma=0;
    }
    for(int j=0;j<game.grid.size();j++) {
        for (int i = 0; i < game.grid[0].size(); i++) {
            if (game.grid[j][i] == 0) {
                say=0;
            }
            else if(game.grid[j][i] == 1){
                say++;
            }
        }
        if(say==game.grid[0].size()){

            col=j;
            change(game,col);

        }
        say=0;
    }



}
void GameController::change(BlockFall& game,int col){
    game.grid.erase(game.grid.begin()+col);
    game.current_score+=game.grid[0].size();
    std::vector<int> a;
    for (int i = 0; i < game.grid[0].size(); ++i) {
        a.push_back(0);
    }
    game.grid.insert(game.grid.begin(), a);

}
void GameController::ongra(BlockFall &game,int loca){
    std::vector <std::vector<bool>> gamecontroller=game.initial_block->shape;
    for (int i = 0; i < gamecontroller.size(); i++) {
        for (int j = 0; j < gamecontroller[i].size(); j++) {
            game.grid[i][j + loca] = 0;
        }
    }
    int ctrl=game.grid.size()-1;
    int ctrl1=game.grid.size()-1;
    for (int i=0;i<gamecontroller[0].size();i++){
        for (int j=0;j<gamecontroller.size();j++){
            if(gamecontroller[gamecontroller.size()-1-j][i]==1){
                for (int f=0;f<game.grid.size();f++){
                    if(gamecontroller.size()-1-j+f+1>game.grid.size()-1){
                        ctrl=game.grid.size()-gamecontroller.size()+j;
                        if(ctrl1>ctrl){
                            ctrl1=ctrl;
                        }
                    }
                    else{
                        if(game.grid[gamecontroller.size()-1-j+f+1][loca+i]==1){
                            ctrl=f;
                            if(ctrl1>ctrl){
                                ctrl1=ctrl;
                            }
                        }
                    }
                }
            }
        }
    }
    int k=0;
    for (int i=0;i<gamecontroller.size();i++){
        for (int j = 0; j < gamecontroller[0].size(); ++j) {
            if(gamecontroller[i][j]==1){
                k++;
            }
        }
    }
    k=k*ctrl1;
    game.current_score+=k;
    for (int i=0;i<gamecontroller[0].size();i++) {
        for (int j = 0; j < gamecontroller.size(); j++) {
            if (gamecontroller[gamecontroller.size() - 1 - j][i] == 1) {
                for (int f = 0; f < game.grid.size(); f++) {
                    if(gamecontroller.size()-j+f==game.grid.size()){
                        game.grid[game.grid.size()-1][i+loca]=1;
                    }
                    else if(gamecontroller.size()-j+f<game.grid.size()){
                        if(game.grid[gamecontroller.size()-j+f][i+loca]==1){
                            game.grid[gamecontroller.size()-j+f-1][i+loca]=1;

                        }
                    }
                }
            }
        }
    }


}
void GameController::nongra(BlockFall &game,int loca){

    std::vector <std::vector<bool>> gamecontroller=game.initial_block->shape;
    int ctrl=game.grid.size()-1;
    int ctrl1=game.grid.size()-1;
    for (int i = 0; i < gamecontroller.size(); i++) {
        for (int j = 0; j < gamecontroller[i].size(); j++) {
            game.grid[i][j + loca] = 0;
        }
    }
    for (int i=0;i<gamecontroller[0].size();i++){
        for (int j=0;j<gamecontroller.size();j++){
            if(gamecontroller[gamecontroller.size()-1-j][i]==1){
                for (int f=0;f<game.grid.size();f++){
                    if(gamecontroller.size()-1-j+f+1>game.grid.size()-1){
                        ctrl=game.grid.size()-gamecontroller.size()+j;
                        if(ctrl1>ctrl){
                            ctrl1=ctrl;
                        }
                    }
                    else{
                        if(game.grid[gamecontroller.size()-1-j+f+1][loca+i]==1){
                            ctrl=f;
                            if(ctrl1>ctrl){
                                ctrl1=ctrl;
                            }
                        }
                    }
                }
            }
        }
    }
    int k=0;
    for (int i=0;i<gamecontroller.size();i++){
        for (int j = 0; j < gamecontroller[0].size(); ++j) {
            if(gamecontroller[i][j]==1){
                k++;
            }
        }
    }
    k=k*ctrl1;
    game.current_score+=k;
    for (int i=0;i<gamecontroller.size();i++){
        for(int j=0;j<gamecontroller[0].size();j++){
            if(game.grid[i+ctrl1][j+loca]==1&&gamecontroller[i][j]==0){
            }
            else{
                game.grid[i+ctrl1][j+loca]=gamecontroller[i][j];
            }
        }
    }

}
//////////////////////////////////////////////////////////////////////////////////////////////////
bool GameController::play(BlockFall& game, const string& commands_file){

    // TODO: Implement the gameplay here while reading the commands from the input file given as the 3rd command-line
    //       argument. The return value represents if the gameplay was successful or not: false if game over,
    //       true otherwise.
    Leaderboard leaderboard;
    std::ifstream file(commands_file);
    int loca=0;
    std::vector <std::vector<bool>> gamecontroller=game.initial_block->shape;
    bool gme=1;
    bool std=1;
    if (file.is_open()) {
        std::vector<std::vector<bool>> tutucu;
        string line;
        for (int i = 0; i < gamecontroller.size(); i++) {
            for (int j = 0; j < gamecontroller[0].size(); j++) {
                game.grid[i][j +loca] = gamecontroller[i][j];
            }
        }
        bool ss= false;
        while (getline(file, line)) {
            if(ss){
                for (int i=0;i<gamecontroller.size();i++){
                    for (int j=0;j<gamecontroller[0].size();j++){
                        if(gamecontroller[i][j]==1&&game.grid[i][j]==1){
                            std=0;
                            gme=0;
                            std::cout<<"GAME OVER!\n"
                                       "Next block that couldn't fit:\n";
                            for(int t=0;t<gamecontroller.size();t++){
                                for(int v=0;v<gamecontroller[0].size();v++){
                                    if(gamecontroller[t][v]==1){
                                        std::cout<<occupiedCellChar;
                                    }
                                    else{
                                        std::cout<<unoccupiedCellChar;
                                    }
                                }
                                std::cout<<"\n";
                            }
                            std::cout<<"\n";
                            std::cout<<"Final grid and score:\n\n"
                                       "Score: "<<game.current_score<<"\n"
                                                                      "High Score:"<<makspoint<<"\n";
                            printgrid(game);
                            game.leaderboard.insert_new_entry(new LeaderboardEntry(game.current_score, time(nullptr), game.player_name));
                            game.leaderboard.print_leaderboard();
                        }

                    }
                }
                if(std){
                    for (int i = 0; i < gamecontroller.size(); i++) {
                        for (int j = 0; j < gamecontroller[0].size(); j++) {
                            if(game.grid[i][j+loca]==1&&gamecontroller[i][j]==0){
                                game.grid[i][j+loca] = 1;
                            }
                            else{
                                game.grid[i][j+loca] = gamecontroller[i][j];
                            }
                        }
                    }
                } else{break;
                }

                ss= false;
            }
            if(makspoint<game.current_score){
                makspoint=game.current_score;
            }
            if (line == "MOVE_RIGHT") {
                if (loca + gamecontroller[0].size() == game.grid[0].size()) {
                }
                else {
                    for (int i = 0; i < gamecontroller.size(); i++) {
                        for (int j = 0; j < gamecontroller[0].size(); j++) {
                            game.grid[i][j + loca] = 0;
                        }
                    }
                    loca++;
                    for (int i = 0; i < gamecontroller.size(); i++) {
                        for (int j = 0; j < gamecontroller[i].size(); j++) {
                            game.grid[i][j +loca] = gamecontroller[i][j];      //burada saga kaydirmada cakisma oluyormu kontrol edicem
                        }
                    }

                }
            } else if (line == "MOVE_LEFT") {
                if (loca == 0) {

                } else {
                    for (int i = 0; i < gamecontroller.size(); i++) {
                        for (int j = 0; j < gamecontroller[0].size(); j++) {
                            game.grid[i][j + loca] = 0;
                        }
                    }
                    loca--;
                    for (int i = 0; i < gamecontroller.size(); i++) {
                        for (int j = 0; j < gamecontroller[0].size(); j++) {
                            game.grid[i][j + loca] = gamecontroller[i][j];
                        }
                    }



                }
            } else if (line == "DROP") {
                if (game.gravity_mode_on==0){
                    nongra(game,loca);
                }
                else if(game.gravity_mode_on==1){
                    ongra(game,loca);
                }
                for(int i=0;i<game.grid.size()-game.power_up.size()+1;i++){
                    for(int j=0;j<game.grid[0].size()-game.power_up[0].size()+1;j++){
                        bool xx= true;
                        for(int k=0;k<game.power_up.size();k++){
                            for(int l=0;l<game.power_up[0].size();l++){
                                if(game.power_up[k][l]==game.grid[i+k][l+j]){

                                }
                                else{
                                    xx=false;
                                }
                            }
                        }
                        if(xx){
                            game.current_score+=1000;

                            for(int s=0;s<game.grid.size();s++) {
                                for (int n = 0; n < game.grid[0].size(); n++) {
                                    if(game.grid[s][n]==1){
                                        game.current_score+=1;
                                    }
                                }
                            }
                            if(makspoint<game.current_score){
                                makspoint=game.current_score;
                            }
                            std::cout<<"Before clearing:\n";
                            printgrid(game);
                            for(int s=0;s<game.grid.size();s++) {
                                for (int n = 0; n < game.grid[0].size(); n++) {

                                    game.grid[s][n]=0;
                                }
                            }
                            break;

                        }
                    }
                }
                control(game);

                loca = 0;

                game.initial_block=game.initial_block->next_block;
                if(game.initial_block== nullptr){
                    break;
                }
                gamecontroller=game.initial_block->shape;
                ss= true;

            }
            else if(line=="PRINT_GRID"){
                std::cout<<                                       "Score: "<<game.current_score<<"\n"
                                                                                                 "High Score:"<<makspoint<<"\n";
                printgrid(game);
            }
            else if(line=="ROTATE_RIGHT"){
                for (int i = 0; i < gamecontroller.size(); i++) {
                    for (int j = 0; j < gamecontroller[0].size(); j++) {
                        game.grid[i][j + loca] = 0;
                    }
                }
                if (loca + gamecontroller.size()<=game.grid[0].size()){
                    game.initial_block=game.initial_block->right_rotation;
                    game.active_rotation=game.initial_block;
                    gamecontroller={};
                    for(int i=0;i<game.active_rotation->shape.size();i++){
                        std::vector<bool>bs;
                        for(int j =0;j <game.active_rotation->shape[i].size();j++){
                            bs.push_back(game.active_rotation->shape[i][j]);
                        }
                        gamecontroller.push_back(bs);
                    }
                    for (int i = 0; i < gamecontroller.size(); i++) {
                        for (int j = 0; j < gamecontroller[i].size(); j++) {
                            if(game.grid[i][j+loca]==1&&gamecontroller[i][j]==0){
                                game.grid[i][j+loca] = 1;
                            }
                            else{
                                game.grid[i][j+loca] = gamecontroller[i][j];
                            }

                        }
                    }
                }
                else{
                }

            }
            else if(line=="ROTATE_LEFT"){
                for (int i = 0; i < gamecontroller.size(); i++) {
                    for (int j = 0; j < gamecontroller[0].size(); j++) {
                        game.grid[i][j + loca] = 0;
                    }
                }
                if (loca + gamecontroller.size()<=game.grid[0].size()){

                    game.initial_block=game.initial_block->left_rotation;
                    game.active_rotation=game.initial_block;
                    gamecontroller={};
                    for(int i=0;i<game.active_rotation->shape.size();i++){
                        std::vector<bool>bs;
                        for(int j =0;j <game.active_rotation->shape[i].size();j++){
                            bs.push_back(game.active_rotation->shape[i][j]);
                        }
                        gamecontroller.push_back(bs);
                    }
                    for (int i = 0; i < gamecontroller.size(); i++) {
                        for (int j = 0; j < gamecontroller[i].size(); j++) {
                            if(game.grid[i][j+loca]==1&&gamecontroller[i][j]==0){
                                game.grid[i][j+loca] = 1;
                            }
                            else{
                                game.grid[i][j+loca] = gamecontroller[i][j];
                            }                        }
                    }
                }else{

                }

            }
            else if(line=="GRAVITY_SWITCH"){
                for (int i = 0; i < gamecontroller.size(); i++) {
                    for (int j = 0; j < gamecontroller[i].size(); j++) {
                        game.grid[i][j + loca] = 0;
                    }
                }
                if(game.gravity_mode_on==1){
                    game.gravity_mode_on=0;
                }
                else{

                    game.gravity_mode_on=1;
                    int say=0;
                    for (int a =0;a<game.grid[0].size();a++){
                        int j=a;
                        int point=0;
                        for (int b=0;b<game.grid.size();b++){
                            int i =game.grid.size()-1-b;
                            if(game.grid[i][j]==1){
                                point+=i;
                                say++;
                                game.grid[i][j]=0;
                            }
                        }
                        point=point-say;
                        for (int v=0;v<say;v++){
                            game.grid[game.grid.size()-1-v][j]=1;
                        }
                        say=0;
                        game.current_score+=point;
                    }
                    control(game);
                }
                ss= true;

            }
            else{}



        }
    }
    if(gme){
        game.leaderboard.insert_new_entry(new LeaderboardEntry(game.current_score, time(nullptr), game.player_name));
        if(game.initial_block== nullptr){
            std::cout<<"GAME FINISHED!\n"
                       "No more blocks.\n"
                       "Final grid and score:\n\n"
                       "Score: "<<game.current_score<<"\n"
                                                      "High Score:"<<makspoint<<"\n";
            printgrid(game);
            game.leaderboard.print_leaderboard();
        }
        else{
            std::cout<<"GAME FINISHED!\n"
                       "No more commands.\n"
                       "Final grid and score:\n\n"
                       "Score: "<<game.current_score<<"\n"
                       "High Score:"<<makspoint<<"\n";
            printgrid(game);
            game.leaderboard.print_leaderboard();
        }
    }
    return gme;
}

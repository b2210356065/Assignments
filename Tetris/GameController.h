#ifndef PA2_GAMECONTROLLER_H
#define PA2_GAMECONTROLLER_H

#include "BlockFall.h"

using namespace std;

class GameController {
public:
    bool play(BlockFall &game, const string &commands_file); // Function that implements the gameplay
    void control(BlockFall& game);
    void change(BlockFall&, int col);
    void nongra(BlockFall &game,int loca);
    void ongra(BlockFall &game,int loca);
    unsigned long makspoint =0;
    void printgrid(BlockFall& game);
};


#endif //PA2_GAMECONTROLLER_H

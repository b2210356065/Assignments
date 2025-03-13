#include "Leaderboard.h"
#include "iostream"
#include <fstream>
#include <vector>
#include <algorithm>
#include "LeaderboardEntry.h"
void Leaderboard::insert_new_entry(LeaderboardEntry* new_entry) {
    if(head_leaderboard_entry!= nullptr){
        if(head_leaderboard_entry->next_leaderboard_entry!=nullptr){
            ite=head_leaderboard_entry;
            while (ite->next_leaderboard_entry!= nullptr){
                if(new_entry->score>head_leaderboard_entry->score){
                    new_entry->next_leaderboard_entry=head_leaderboard_entry;
                    head_leaderboard_entry=new_entry;
                    break;
                }
                else if(ite->next_leaderboard_entry->next_leaderboard_entry== nullptr){
                    if(ite->next_leaderboard_entry->score<new_entry->score){
                        new_entry->next_leaderboard_entry=ite->next_leaderboard_entry;
                        ite->next_leaderboard_entry=new_entry;
                        break;
                    }
                    else if(ite->next_leaderboard_entry->score>=new_entry->score){
                        ite->next_leaderboard_entry->next_leaderboard_entry=new_entry;
                        break;
                    }
                }
                else if(ite->next_leaderboard_entry->score<new_entry->score){
                    new_entry->next_leaderboard_entry=ite->next_leaderboard_entry;
                    ite->next_leaderboard_entry=new_entry;
                    break;
                }
                else{
                    ite=ite->next_leaderboard_entry;
                }
            }
            int sayac=0;
            ite=head_leaderboard_entry;
            while(ite->next_leaderboard_entry!= nullptr){
                sayac++;
                if(sayac==10){
                    break;
                }
                else{
                    ite=ite->next_leaderboard_entry;
                }
            }
            if(sayac==10){
                delete ite->next_leaderboard_entry;
                ite->next_leaderboard_entry= nullptr;
            }
        }
        else{
            if(head_leaderboard_entry->score>new_entry->score){
                head_leaderboard_entry->next_leaderboard_entry=new_entry;
            }
            else if(head_leaderboard_entry->score<=new_entry->score){
                new_entry->next_leaderboard_entry=head_leaderboard_entry;
                head_leaderboard_entry=new_entry;
            }
            else{}
        }
    }
    else{
        head_leaderboard_entry=new_entry;
    }
}

void Leaderboard::write_to_file(const string& filename) {
    // TODO: Write the latest leaderboard status to the given file in the format specified in the PA instructions
    ofstream file(filename);
    if (file.is_open()) {
        ite=head_leaderboard_entry;
        while (ite!= nullptr) {
            file << ite->score << " " << ite->last_played << " " << ite->player_name <<std:: endl;
            ite = ite->next_leaderboard_entry;
        }

        file.close();
    }



}

void Leaderboard::read_from_file(const string& filename) {
    // TODO: Read the stored leaderboard status from the given file such that the "head_leaderboard_entry" member
    //       variable will point to the highest all-times score, and all other scores will be reachable from it
    //       via the "next_leaderboard_entry" member variable pointer.
    std::ifstream file(filename);
    string line;
    if (file.is_open()) {
        while (getline(file, line)) {
            if(line!=""){
                int sayac=0;
                string score;
                string time1;
                string name;
                for(int i =0;i<line.size();i++){
                    if(line[i]==' '){
                        sayac++;
                    }
                    else{
                        if(sayac==0){
                            score.push_back(line[i]);
                        }
                        if(sayac==1){
                            time1.push_back(line[i]);
                        }
                        if(sayac==2){
                            name.push_back(line[i]);
                        }
                    }
                }
                unsigned long cc=std::stoul(score);
                time_t time=std::stoul(time1);

                LeaderboardEntry *newentry=new LeaderboardEntry(cc,time,name);
                newentry->next_leaderboard_entry = nullptr;
                insert_new_entry(newentry);
            }

        }
    }
}


void Leaderboard::print_leaderboard() {
    // TODO: Print the current leaderboard status to the standard output in the format specified in the PA instructions
    ite=head_leaderboard_entry;
    std::cout<<"Leaderboard\n"
               "-----------\n";
//    int sira=0;
//    while (ite!= nullptr) {
//        if (ite->score = !0&&ite->last_played!=0) {
//            sira++;
//            time_t lastPlayedTime = ite->last_played; // Burada ite, kullanmak istediğiniz iterator veya struct gibi bir öğeyi temsil etmelidir.
//
//            // time_t'i tm yapısına dönüştür
//            struct tm *timeInfo = localtime(&lastPlayedTime);
//            std::cout << sira << ". " << ite->player_name << " " <<ite->score << " ";
//            std::cout << timeInfo->tm_hour << ":" << timeInfo->tm_min << ":" << timeInfo->tm_sec << "/";
//            std::cout << timeInfo->tm_mday << "." << (timeInfo->tm_mon + 1) << "." << (timeInfo->tm_year + 1900)
//                      << std::endl;
//            std::cout << "\n";
//            std::cout << "\n";
//            if(ite->next_leaderboard_entry!=nullptr){
//                ite = ite->next_leaderboard_entry;
//            }
//            else{ break;}
//
//        }
//    }
}

Leaderboard::~Leaderboard() {
//    // TODO: Free dynamically allocated memory used for storing leaderboard entries
        LeaderboardEntry* temp =head_leaderboard_entry;
        while(temp!= nullptr){
            LeaderboardEntry* fabber_castell_silgi=temp;
            temp=temp->next_leaderboard_entry;
            delete fabber_castell_silgi;
        }
        head_leaderboard_entry= nullptr;
        ite= nullptr;
}

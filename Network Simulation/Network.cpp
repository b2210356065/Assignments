#include "Network.h"
#include <sstream>
Network::Network() {

}

void Network::process_commands(vector<Client> &clients, vector<string> &commands, int message_limit,
                      const string &sender_port, const string &receiver_port) {
    int comindex = 0;

    while (comindex < commands.size()){
        string commandline = commands[comindex];
        stringstream ss(commandline);
        string command;
        ss >> command;
        int tmp = ss.str().size()+9;
        cout << string(tmp,'-') << endl;
        cout << "Command: " << ss.str() << endl;
        cout << string(tmp,'-') << endl;

        if (command == "MESSAGE"){
            string sender,receiver;
            ss >> sender >> receiver;
            string left = ss.str();
            for (int i = 0; i < left.length(); i++){
                if (left[i] == '#'){
                    left = left.substr(i+1);
                    break;
                }
            }
            //del from right
            for (int i = left.length()-1; i >= 0; i--){
                if (left[i] == '#'){
                    left = left.substr(0,i);
                    break;
                }
            }
            string message = left;
            int i = 0;
            for (i = 0; i < clients.size(); i++) {
                if (clients[i].client_id == sender){
                    break;
                }
            }
            int j = 0;
            for (j = 0; j < clients.size(); ++j) {
                if (clients[j].client_id == receiver){
                    break;
                }
            }
            int size = 0;
            while (size*message_limit < message.length()){
                size++;
            }
            cout << "Message to be sent: " <<'"' <<  message << '"'     << endl << endl;
            int no = 0;
            for (int ii = 0; ii < size; ++ii) {
                no++;

                //message to be added
                string data = message.substr(ii*message_limit, message_limit);

                //add it to queue
                stack<Packet*> for_out_queue;
                for_out_queue.push(new ApplicationLayerPacket(0, sender, receiver, data));
                for_out_queue.push(new TransportLayerPacket(1, sender_port, receiver_port));
                for_out_queue.push(new NetworkLayerPacket(2, clients[i].client_ip, clients[j].client_ip));
                int k = 0;
                for (k = 0; k < clients.size(); ++k) {
                    if (clients[k].client_id == clients[i].routing_table[receiver]){
                        break;
                    }   
                }
                for_out_queue.push(new PhysicalLayerPacket(3, clients[i].client_mac,clients[k].client_mac ));
                clients[i].outgoing_queue.push(for_out_queue);
                //should print
                cout << "Frame #" << no  << endl;
                cout << "Sender MAC address: " << clients[i].client_mac << ", Receiver MAC address: " << clients[k].client_mac << endl;
                std::cout << "Sender IP address: " << sender << ", Receiver IP address: " << receiver << std::endl;
                std::cout << "Sender port number: " << sender_port << ", Receiver port number: " << receiver_port << std::endl;
                std::cout << "Sender ID: " << clients[i].client_ip << ", Receiver ID: " << clients[j].client_ip << std::endl;

                cout << "Message chunk carried: " << '"' << data  << '"' << endl;
                cout << "Number of hops so far: " << 0 << endl;
                cout << "--------" << endl;
            
            }


 
            
        }   



        
        comindex++;
    }

}

vector<Client> Network::read_clients(const string &filename) {
    vector<Client> clients;
    // TODO: Read clients from the given input file and return a vector of Client instances.
    ifstream file(filename);
    int clientcount = 0;
    file >> clientcount;
    string tmp;
    getline(file,tmp);
    while (clientcount!=0){
        string line;
        getline(file,line);
        string ID; 
        string IP;
        string MAC;
        for (int i = 0; i < line.length(); i++){
            if (line[i] == ' '){
                ID = line.substr(0,i);
                line = line.substr(i+1);
                break;
            }
        }
        for (int i = 0; i < line.length(); i++){
            if (line[i] == ' '){
                IP = line.substr(0,i);
                line = line.substr(i+1);
                break;
            }
        }
        MAC = line;
        for (int i = 0; i < line.length(); i++){
            if (line[i] == ' '){
                MAC = line.substr(0,i);
                break;
            }
        }
        Client client(ID,IP,MAC);
        clients.push_back(client);
        clientcount--;
    }
    return clients;
}

void Network::read_routing_tables(vector<Client> &clients, const string &filename) {
    // TODO: Read the routing tables from the given input file and populate the clients' routing_table member variable.
    ifstream file(filename);
    int client = 0;
    string line;
    while (getline(file,line)){
        if (line == "-"){
            client++;
            continue;
        }
        string id,id2;
        stringstream ss(line);
        ss >> id >> id2;
        clients[client].routing_table[id] = id2;
    }

}

// Returns a list of token lists for each command
vector<string> Network::read_commands(const string &filename) {
    vector<string> commands;
    // TODO: Read commands from the given input file and return them as a vector of strings.
    ifstream file(filename);
    string line;
    int commandcount = 0;
    file >> commandcount;
    getline(file,line);
    while (commandcount!=0){
        getline(file,line);
        if (line == ""){
            continue;
        }
        commands.push_back(line);
        commandcount--;
    }


    return commands;
}

Network::~Network() {
    // TODO: Free any dynamically allocated memory if necessary.
}

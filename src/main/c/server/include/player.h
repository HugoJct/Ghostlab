#ifndef PLAYER_H
#define PLAYER_H

#include "dependencies.h"

struct player {
	char id[8];
	int score;
	int x;
	int y;
	int tcp_socket_fd;
	int udp_port;
	int ready;
};

struct player* player_create(char*,int,int); 	//create a player 
void player_print(void *player);

#endif

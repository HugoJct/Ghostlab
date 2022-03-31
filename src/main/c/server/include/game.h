#ifndef GAME_H
#define GAME_H

#include "dependencies.h"
#include "llist.h"

struct game {
	int id;
	int max_capacity;
	struct in_addr diffusion_ip;
	int diffusion_port;
	llist *players;
};

struct game* game_create(int);
void game_print(void *game);
void game_send_list(int, llist*);

#endif

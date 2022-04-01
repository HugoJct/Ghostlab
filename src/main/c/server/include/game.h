#ifndef GAME_H
#define GAME_H

#include "dependencies.h"
#include "llist.h"
#include "player.h"

struct game {
	int id;
	int max_capacity;
	struct in_addr diffusion_ip;
	u_int16_t diffusion_port;
	llist *players;
};

struct game* game_create(int);
void game_print(void *game);
struct game* game_get_by_id(int id);
void game_send_list(int, llist*);
void *game_start(void *arg);

#endif

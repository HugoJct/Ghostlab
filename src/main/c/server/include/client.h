#ifndef CLIENT_H
#define CLIENT_H

#include "dependencies.h"

struct client {
	struct player *player;
	struct game *game;
	int *fd;	
};

struct client *create_client(struct game *g, struct player *p, int *fd);

#endif

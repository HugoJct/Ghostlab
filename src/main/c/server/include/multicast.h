#ifndef MULTICAST_H
#define MULTICAST_H

#include "game.h"
#include "client.h"
#include "dependencies.h"

void multicast_send(char *buf, int len, struct game *g);
void multicast_messa(char *messa, struct client *c);

#endif

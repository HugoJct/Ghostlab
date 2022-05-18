#ifndef REQUESTS_H
#define REQUESTS_H

#include "dependencies.h"
#include "game.h"
#include "player.h"
#include "send_responses.h"
#include "client.h"

enum {UP,DOWN,RIGHT,LEFT};

struct client *request_newpl(char buf[],int fd);
struct client *request_regis(char buf[], int fd);
int request_read_tcp(char *buf, int fd);
int request_read_udp(char *buf, int fd);
void request_list(char *buf, int fd);
void request_movement(char *buf, struct client *c, int direction);

#endif

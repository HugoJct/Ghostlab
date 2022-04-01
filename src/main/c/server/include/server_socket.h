#ifndef SERVER_SOCKET_H
#define SERVER_SOCKET_H

#include "dependencies.h"
#include "game.h"
#include "player.h"

void server_socket_init(int , int);
int server_socket_accept(int);
void *server_socket_connection_prompt(void *arg);
void server_socket_receive_newpl_regis(int fd);

#endif

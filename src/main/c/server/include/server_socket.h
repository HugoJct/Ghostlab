#ifndef SERVER_SOCKET_H
#define SERVER_SOCKET_H

#include "dependencies.h"
#include "game.h"

void server_socket_init(int , int);
int server_socket_accept(int);
void *server_socket_connection_prompt(void *arg);

#endif

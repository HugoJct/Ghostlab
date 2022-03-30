#ifndef SERVER_SOCKET_H
#define SERVER_SOCKET_H

#include "dependencies.h"

void init_server(int , int);
int wait_for_connection(int);

#endif

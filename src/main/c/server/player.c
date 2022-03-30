#include "player.h"

struct player* create_player(char *name, int tcp_socket, int port) {
	struct player *p = malloc(sizeof(p));

	/* make sure the name is 8 characters long */
	char name_tmp[8];
	memcpy(name_tmp,name,8);

	p->score = 0;
	p->x = -1;
	p->y = -1;
	p->tcp_socket_fd = tcp_socket;
	p->udp_port = port;

	return p;
}	

#include "player.h"

struct player* player_create(char *name, int tcp_socket, int port) {
	struct player *p = (struct player *)malloc(sizeof(struct player));

	/* make sure the name is 8 characters long */
	memcpy(p->id,name,8);

	p->score = 0;
	p->ready = 0;
	p->x = -1;
	p->y = -1;
	p->tcp_socket_fd = tcp_socket;
	p->udp_port = port;
	p->ready = FALSE;

	return p;
}	

void player_print(void *player) {
	struct player *p = (struct player *) player;
	if(player == NULL) {
		printf("Empty list...\n");
		return;
	}

	char tmp_name[9];
	strcpy(tmp_name,p->id);
	tmp_name[8] = '\0';

	printf("name: %s score: %d x: %d y: %d port: %d",tmp_name,p->score,p->x,p->y,p->udp_port);
}

int player_is_ready(struct player *p) {
	return p->ready;
}

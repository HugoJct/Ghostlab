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

int player_move(struct client *c, int count, int direction) {
	int **lab = c->game->labyrinth->cells;
	int moved = 0;
	int ghost = 0;

	int x = c->player->x;
	int y = c->player->y;

	for(int i=0;i<count;i++) {

		if(direction == UP && (y == 0 || lab[y-moved][x] == 1)) {
			if(game_is_there_ghost(x,y))
				ghost++;
			break;
		} else if(direction == DOWN && ((y == c->game->labyrinth->height - 1)  || lab[y+moved][x] == 1)) {
			if(game_is_there_ghost(x,y))
				ghost++;
			break;
		} else if(direction == LEFT && ( x == 0 || lab[y][x-moved] == 1)) {
			if(game_is_there_ghost(x,y))
				ghost++;
			break;
		} else if(direction == RIGHT && ((y == c->game->labyrinth->width - 1) || lab[y][x+moved] == 1)) {
			if(game_is_there_ghost(x,y))
				ghost++;
			break;
		}
		moved++;
	}

	switch(direction) {
		case UP:
			c->player->y -= moved;
			break;
		case DOWN:
			c->player->y += moved;
			break;
		case LEFT:
			c->player->x -= moved;
			break;
		case RIGHT:
			c->player->x += moved;
			break;
	}

	c->player->score += ghost * GHOST_VALUE;

	return moved;
}

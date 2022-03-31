#include "game.h"

int game_id_counter = 0;

struct game* game_create(int cap) {
	struct game *new_game = malloc(sizeof(struct game));	
	new_game->players = llist_create(NULL);

	new_game->id = game_id_counter++;
	new_game->max_capacity = cap;

	/* TODO: Some stuff left to initialize 
	 *		Such as: 
	 *			The port for multi-diffusion
	 *			The ip for multi-diffusion
	 *
	 *	I don't how to define these for now
	 */

	return new_game;
}

void game_delete(struct game *g) {
	llist_free(g->players);
	free(g);
}

void game_print(void *game) {
	struct game *g = (struct game *) game;
	if(game == NULL) {
		printf("Empty list...\n");
		return;
	}
	char *ip = inet_ntoa(g->diffusion_ip);
	printf("id: %d max: %d ip: %s port: %d\n",g->id,g->max_capacity,ip,g->diffusion_port);
}


void game_send_count(int socket_fd, llist *games) {
	char buf[100];

	u_int8_t games_number = llist_size(games);

	char *cmd = "GAMES ";
	char *end = "***";

	memcpy(buf,cmd,6);
	memcpy(buf+6,&games_number,1);
	memcpy(buf+7,end,3);

	int ret = send(socket_fd,buf,10,0);
	assert(ret >= 0);	//not sure if this is optimal
}

void game_send_details(int socket_fd, llist *games) {

	struct node *cur = *games;
	while(cur->data != NULL) {

		char buf[100];

		struct game *g = (struct game*) cur->data;

		u_int8_t game_id = g->id;
		u_int8_t players_count = llist_size(g->players);

		char *cmd = "OGAME ";
		char *end = "***";

		memcpy(buf,cmd,6);
		memcpy(buf+6,&game_id,1);
		memcpy(buf+7,cmd+5,1);
		memcpy(buf+8,&players_count,1);
		memcpy(buf+9,end,3);

		int ret = send(socket_fd,buf,12,0);
		assert(ret >= 0);	//not sure if this is optimal
		

		if(cur->next == NULL)
			break;
		cur = cur->next;
	}
}

void game_send_list(int socket_fd, llist *games) {
	game_send_count(socket_fd,games);
	game_send_details(socket_fd,games);
	printf("oui\n");
}

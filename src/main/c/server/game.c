#include "game.h"

struct game* create_game() {
	struct game *new_game = malloc(sizeof(struct game));	
	new_game->players = llist_create(NULL);

	/* TODO: Some stuff left to initialize */

	return new_game;
}

void game_print(void *game) {
	struct game *g = (struct game *) game;
	if(game == NULL) {
		printf("Empty list...\n");
		return;
	}
	char *ip = inet_ntoa(g->diffusion_ip);
	printf("id: %d max: %d ip: %s port: %d\n\t",g->id,g->max_capacity,ip,g->diffusion_port);
}

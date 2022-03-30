#include "game.h"

int game_id_counter = 0;

struct game* create_game(int cap) {
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

void game_print(void *game) {
	struct game *g = (struct game *) game;
	if(game == NULL) {
		printf("Empty list...\n");
		return;
	}
	char *ip = inet_ntoa(g->diffusion_ip);
	printf("id: %d max: %d ip: %s port: %d",g->id,g->max_capacity,ip,g->diffusion_port);
}

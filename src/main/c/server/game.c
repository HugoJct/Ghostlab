#include "game.h"
#include "labs_parser.h"

int game_id_counter = 0;
int multi_diffusion_field = 3;
int multi_diffusion_port = 1999;

pthread_mutex_t game_list_mutex = PTHREAD_MUTEX_INITIALIZER;

struct game* game_create(int cap) {
	struct game *new_game = malloc(sizeof(struct game));	
	new_game->id = game_id_counter++;
	new_game->max_capacity = cap;
	new_game->players = llist_create(NULL);
	new_game->labyrinth = parse_lab("assets/lab3.lab");

	pthread_mutex_init(&(new_game->game_lock),NULL);

	char *ip = (char *) malloc(15);
	sprintf(ip,"225.12.35.%d",(multi_diffusion_field++));
	int ret = inet_aton(ip,&new_game->diffusion_ip);
	assert(ret != 0);
	free(ip);

	new_game->diffusion_port = multi_diffusion_port++;

	//pthread_mutex_lock(&game_list_mutex);

	extern llist *games;
	llist_push(games,new_game);
	
//	pthread_mutex_unlock(&game_list_mutex);

	return new_game;
}

void game_delete(struct game *g) {
	llist_free(g->players);
	for (int i = 0; i < g->labyrinth->height; i++) {
	    free(g->labyrinth->cells[i]);
	}
	free(g->labyrinth->cells);
	free(g->labyrinth);
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

void game_add_player(struct game *g, struct player *p) {
	pthread_mutex_lock(&g->game_lock);

	llist_push(g->players,p);

	pthread_mutex_unlock(&g->game_lock);
}

struct game* game_get_by_id(int id) {

	pthread_mutex_lock(&game_list_mutex);

	struct game *g = NULL;

	extern llist *games;

	struct node *cur = *games;
	while(1) {
		if(cur->data == NULL)
			break;	

		if(((struct game*) cur->data)->id == id) {
			g = cur->data;
			break;
		}

		if(cur->next == NULL)
			break;

		cur = cur->next;
	}

	pthread_mutex_unlock(&game_list_mutex);

	return g;
}



int game_are_all_players_ready(struct game *g) {
	pthread_mutex_lock(&(g->game_lock));

	int ready = 1;
	struct node *cur = *(g->players);
	while(cur->data != NULL) {
		struct player *p = cur->data;		

		if(p->ready == 0) {
			ready = 0;
			break;
		}

		if(cur->next == NULL)
			break;
		cur = cur->next;
	}

	pthread_mutex_unlock(&(g->game_lock));
	return ready;
}

void *game_start(void *arg) {

	extern llist *games;

	struct game *g = (struct game*) arg;

	/* TODO handle game unfolding */
	while(1) {
		if(game_are_all_players_ready(g))
			break;		
	}
	puts("All players are ready");
	pause();

	return NULL;
}

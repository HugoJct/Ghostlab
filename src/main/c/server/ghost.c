#include "ghosts.h"

void *ghosts_move(void *arg) {

	struct game *g = (struct game *) arg;

	while(g->remaining_ghosts > 0) {
		for(int i=0;i<MAX_GHOST_NUMBER;i++) {
			if(g->ghosts[i].x != -1) {
				while(1) {
					int tmp_x = rand() % g->labyrinth->width;
					int tmp_y = rand() % g->labyrinth->height;
					if(g->labyrinth->cells[tmp_y][tmp_x] != 1) {
						g->ghosts[i].x = tmp_x;
						g->ghosts[i].y = tmp_y;
						break;
					}
				}
			}	
		}
		usleep(GHOST_MOVE_FREQUENCY);	
		puts("ghosts moved");
		//TODO: send ghost positions 
	}

	return NULL;
}

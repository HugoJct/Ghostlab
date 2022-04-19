#include "requests.h"

void request_newpl(char buf[],int fd) {
	char name[8];
	char porttmp[5];

	memcpy(name,buf+6,8);
	memcpy(porttmp,buf+15,4);
	porttmp[5] = '\0';
	int port = atoi(porttmp);

	struct game *g = game_create(4);
	struct player *p = player_create(name,fd,port);
	game_add_player(g,p);

	uint8_t id = g->id;
	send_regok(fd,id);

	pthread_t t;
	pthread_create(&t,NULL,game_start,g);
}

void request_regis(char buf[], int fd) {

	char name[8];
	memcpy(name,buf+6,8);

	char porttmp[5];
	memcpy(porttmp,buf+15,4);
	porttmp[5] = '\0';
	int port = atoi(porttmp);

	u_int8_t game_nb = 0;
	memcpy(&game_nb,buf+20,1);

	struct player *p = player_create(name,fd,port);

	struct game *requested_game = game_get_by_id(game_nb);

	//TODO check if the game is in progress

	if(requested_game != NULL) {
		printf("Okay\n");
		game_add_player(requested_game,p);	
		send_regok(fd,game_nb);
	} else {
		printf("non\n");		
		send_regno(fd);
	}

	
}

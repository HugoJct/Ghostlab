#include "requests.h"

int read_request(char *buf, int fd,char delimitor) {
	int stars_read = 0;
	int offset = 0;
	char tmp;
	while(stars_read != 3) {	//while delimitor hasn't been read three times in a row
		int ret = recv(fd,&tmp,1,0);
		if(ret <= 0) {	//the client disconnected or an error occured (handled the same way)
			puts("A client has disconnected");
			return -1;
		}

		memcpy(buf+offset,&tmp,1);

		if(tmp == delimitor)
			stars_read++;
		else
			stars_read = 0;
		offset++;
	}
	return 0;
}

int request_read_tcp(char *buf, int fd) {
	return read_request(buf,fd,'*');
}

int request_read_udp(char *buf, int fd) {
	return read_request(buf,fd,'+');
}

struct client *request_newpl(char buf[],int fd) {
	char porttmp[5];

	memcpy(porttmp,buf+15,4);
	porttmp[5] = '\0';
	int port = atoi(porttmp);

	struct game *g = game_create(4);
	struct player *p = player_create(buf+6,fd,port);
	game_add_player(g,p);

	struct client *c = create_client(g,p,NULL);

	uint8_t id = g->id;
	send_regok(fd,id);

	pthread_t t;
	pthread_create(&t,NULL,game_start,g);

	return c;
}

struct client *request_regis(char buf[], int fd) {

	//get port from buffer then convert it to int
	char porttmp[5] = {0,0,0,0,0};
	memcpy(porttmp,buf+15,4);
	int port = atoi(porttmp);

	//get game number from buffer
	u_int8_t game_nb = 0;
	memcpy(&game_nb,buf+20,1);

	struct player *p = player_create(buf+6,fd,port);

	struct game *requested_game = game_get_by_id(game_nb);

	struct client *c ;

	if(requested_game != NULL && requested_game->started == FALSE) {

		game_add_player(requested_game,p);	
		send_regok(fd,game_nb);	//answer to the client
		
		c = create_client(requested_game,p,NULL);	//create the struct client to return 

	} else {	//if the requested game does not exist, answer and return NULL
		send_regno(fd);
		return NULL;
	}
	return c;
}

void request_list(char *buf, int fd) {
	uint8_t id = 0;
	memcpy(&id,buf+6,1);	//extract id from buffer
	send_players_list(fd,id);
}

void request_movement(char *buf, struct client *c, int direction) {
	char distbuf[4];
	memcpy(distbuf,buf+6,3);
	distbuf[3] = '\0';	
	
	int player_score = c->player->score;

	int dist = atoi(distbuf);

	int dist_moved = player_move(c,dist,direction);
	
	if( player_score != c->player->score) { //if the player found a ghost
		//TODO: respond MOVE!
	} else {
		//TODO: repond MOVEF
	}
}

void request_mall(char *buf, struct client *c) {
	char mess[201];
	int count = 0;
	int i = 0;
	while(1) {
		if(buf[i++] == '*')
			break;
		count++;
	}
	//TODO: add sceurity to prevent buf overflow
	memcpy(mess,buf,count);
	mess[count] = '\0';

	multicast_messa(mess,c);
}

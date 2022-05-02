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

	//get name from buffer
	char name[8];
	memcpy(name,buf+6,8);

	//get port from buffer then convert it to int
	char porttmp[5];
	memcpy(porttmp,buf+15,4);
	porttmp[5] = '\0';
	int port = atoi(porttmp);

	//get game number from buffer
	u_int8_t game_nb = 0;
	memcpy(&game_nb,buf+20,1);

	struct player *p = player_create(name,fd,port);

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

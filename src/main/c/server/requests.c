#include "requests.h"

void read_request(char *buf, int fd,char delimitor) {
	int stars_read = 0;
	int offset = 0;
	while(stars_read != 3) {
		int ret = recv(fd,buf+offset,1,0);
		if(ret < 0) {
			perror("recv");
			exit(EXIT_FAILURE);
		}
		if(buf[offset] == delimitor)
			stars_read++;
		else
			stars_read = 0;
		offset++;
	}
}

void request_read_tcp(char *buf, int fd) {
	read_request(buf,fd,'*');
}

void request_read_udp(char *buf, int fd) {
	read_request(buf,fd,'+');
}

struct client *request_newpl(char buf[],int fd) {
	char name[8];
	char porttmp[5];

	memcpy(name,buf+6,8);
	memcpy(porttmp,buf+15,4);
	porttmp[5] = '\0';
	int port = atoi(porttmp);

	struct game *g = game_create(4);
	struct player *p = player_create(name,fd,port);
	game_add_player(g,p);

	struct client *c = create_client(g,p,NULL);

	uint8_t id = g->id;
	send_regok(fd,id);

	pthread_t t;
	pthread_create(&t,NULL,game_start,g);

	return c;
}

struct client *request_regis(char buf[], int fd) {

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

	struct client *c ;

	if(requested_game != NULL) {
		printf("Okay\n");
		game_add_player(requested_game,p);	
		send_regok(fd,game_nb);
		
		c = create_client(requested_game,p,NULL);

	} else {
		printf("non\n");		
		send_regno(fd);
	}
	return c;
}

#include "send_responses.h"

void send_regok(int fd, uint8_t game_id) {
	char buf[10];
	memcpy(buf, "REGOK ", 6);
	memcpy(buf + 6, &game_id, 1);
	memcpy(buf + 7, "***", 3);

	int ret = send(fd, buf, 10, 0);
	assert(ret == 10);
}

void send_regno(int fd) {
	char buf[8];
	memcpy(buf, "REGNO***", 8);

	int ret = send(fd, buf, 8, 0);
	assert(ret == 8);
}

void send_dunno(int fd) {
	int ret = send(fd, "DUNNO***", 3, 0);
	assert(ret >= 0);
}

void send_unrok(int fd, uint8_t game_id) {
	char buf[10];
	memcpy(buf, "UNROK ", 6);
	memcpy(buf + 6, &game_id, 1);
	memcpy(buf + 7, "***", 3);

	int ret = send(fd, buf, 10, 0);
	assert(ret == 10);
}

void send_player_count(int fd, int id) {
	struct game *g = game_get_by_id(id);

	uint8_t nb_players = llist_size(g->players);

	char buf[100];
	memcpy(buf, "LIST! ", 6);
	memcpy(buf + 6, &id, 1);
	memcpy(buf + 7, " ", 1);
	memcpy(buf + 8, &nb_players, 1);
	memcpy(buf + 9, "***", 3);

	int ret = send(fd, buf, 12, 0);
	assert(ret >= 0);
}

void send_player_details(int fd, int id) {
	struct game *g = game_get_by_id(id);

	struct node *cur = *(g->players);
	while (cur->data != NULL) {

		struct player *p = (struct player *) cur->data;

		char buf[100];

		memcpy(buf,"PLAYR ",6);
		memcpy(buf+6,p->id,8);
		memcpy(buf+14,"***",3);

		int ret = send(fd,buf,17,0);
		assert(ret >= 0);
	
		if (cur->next == NULL)
			break;
		cur = cur->next;
	}
}

void send_players_list(int fd, int id) {
	send_player_count(fd, id);
	send_player_details(fd,id);
}

void game_send_count(int socket_fd, llist *games) {
	char buf[100];

	extern pthread_mutex_t game_list_mutex; 
	pthread_mutex_lock(&game_list_mutex);
	u_int8_t games_number = llist_size(games);

	char *cmd = "GAMES ";
	char *end = "***";

	memcpy(buf,cmd,6);
	memcpy(buf+6,&games_number,1);
	memcpy(buf+7,end,3);

	int ret = send(socket_fd,buf,10,0);
	assert(ret >= 0);	//not sure if this is optimal

	pthread_mutex_unlock(&game_list_mutex);
}

void game_send_details(int socket_fd, llist *games) {
	extern pthread_mutex_t game_list_mutex; 
	pthread_mutex_lock(&game_list_mutex);

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
	pthread_mutex_unlock(&game_list_mutex);
}

void send_games(int socket_fd, llist *games) {
	game_send_count(socket_fd,games);
	game_send_details(socket_fd,games);
}

void send_size(int socket_fd, uint8_t game_id) {
	char buf[100];

	extern pthread_mutex_t game_list_mutex; 
	pthread_mutex_lock(&game_list_mutex);

	struct game *g = game_get_by_id(game_id);
	if(!g) {
		pthread_mutex_unlock(&game_list_mutex);
		return;
	}

	u_int16_t height = g->labyrinth->height;
	u_int16_t width = g->labyrinth->width;

	pthread_mutex_unlock(&game_list_mutex);

	char *cmd = "SIZE! ";
	char *spacing = " ";
	char *end = "***";

	memcpy(buf, cmd, 6);
	memcpy(buf+6, &game_id, sizeof(u_int8_t));
	memcpy(buf+7, spacing, 1);
	u_int16_t inv_h = htons(height);
	memcpy(buf+8, &inv_h, sizeof(u_int16_t));
	memcpy(buf+10, spacing, 1);
	u_int16_t inv_w = htons(width);
	memcpy(buf+11, &inv_w, sizeof(u_int16_t));
	memcpy(buf+12, end, 3);

	send(socket_fd, buf, 15, 0);
}

void send_welco(int fd, struct game *g) {
	char buf[100];

	memcpy(buf,"WELCO ",6);
	memcpy(buf+6,&g->id,1);
	memcpy(buf+7," ",1);
	memcpy(buf+8,&g->labyrinth->height,2);
	memcpy(buf+10," ",1);
	memcpy(buf+11,&g->labyrinth->width,2);
	memcpy(buf+13," ",1);
	memcpy(buf+14,&g->remaining_ghosts,1);
	memcpy(buf+15," ",1);
	
	//formatting and copying ip address
	char ip[15];
	memset(ip,'#',15);
	char *ipaddr = inet_ntoa(g->diffusion_ip);
	memcpy(ip,ipaddr,strlen(ipaddr));
	memcpy(buf+16,ip,15);
	//******************

	memcpy(buf+31," ",1);

	char port[5];
	sprintf(port,"%hu",g->diffusion_port);

	memcpy(buf+32,port,4);
	memcpy(buf+36,"***",3);

	int ret = send(fd,buf,39,0);
	assert(ret >= 0);
}

void send_move(int fd, int x, int y) {
	char buf[100];

	char x_tmp[4];
	char y_tmp[4];

	sprintf(x_tmp,"%d",x);
	sprintf(y_tmp,"%d",y);

	char x_pos[3];
	memset(x_pos,'0',3);
	char y_pos[3];
	memset(y_pos,'0',3);

	if(x < 10)
		memcpy(x_pos+2,x_tmp,1);
	else if(x < 100)
		memcpy(x_pos+1,x_tmp,2);
	else
		memcpy(x_pos,x_tmp,3);

	if(y < 10)
		memcpy(y_pos+2,y_tmp,1);
	else if(y < 100)
		memcpy(y_pos+1,y_tmp,2);
	else
		memcpy(y_pos,y_tmp,3);

	memcpy(buf,"MOVE! ",6);
	memcpy(buf+6,&x_pos,3);
	memcpy(buf+9," ",1);
	memcpy(buf+10,&y_pos,3);
	memcpy(buf+13,"***",3);

	int ret = send(fd,buf,16,0);
	assert(ret >= 0);
}

void send_movef(int fd, int x, int y, int points) {
	char buf[100];

	char x_tmp[4];
	char y_tmp[4];
	char p_tmp[5];

	sprintf(x_tmp,"%d",x);
	sprintf(y_tmp,"%d",y);
	sprintf(p_tmp,"%d",points);

	char x_pos[3];
	memset(x_pos,'0',3);
	char y_pos[3];
	memset(y_pos,'0',3);
	char new_points[4];
	memset(new_points,'0',4);

	if(x < 10)
		memcpy(x_pos+2,x_tmp,1);
	else if(x < 100)
		memcpy(x_pos+1,x_tmp,2);
	else
		memcpy(x_pos,x_tmp,3);

	if(y < 10)
		memcpy(y_pos+2,y_tmp,1);
	else if(y < 100)
		memcpy(y_pos+1,y_tmp,2);
	else
		memcpy(y_pos,y_tmp,3);
	
	if(points < 10)
		memcpy(new_points+3,&p_tmp,1);
	else if(points < 100)
		memcpy(new_points+2,&p_tmp,2);
	else if(points < 1000)
		memcpy(new_points+1,&p_tmp,3);
	else
		memcpy(new_points,&points,4);


	memcpy(buf,"MOVEF ",6);
	memcpy(buf+6,&x_pos,3);
	memcpy(buf+9," ",1);
	memcpy(buf+10,&y_pos,3);
	memcpy(buf+13," ",1);
	memcpy(buf+14,&new_points,4);
	memcpy(buf+18,"***",3);

	int ret = send(fd,buf,21,0);
	assert(ret >= 0);
}

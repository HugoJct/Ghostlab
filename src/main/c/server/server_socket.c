#include "server_socket.h"

void server_socket_init(int socket, int port) {

	/* Initializing the tcp socket for the server *****************************************/
	struct sockaddr_in local_address = {
		.sin_family = AF_INET,
		.sin_port = htons(port),
		.sin_addr.s_addr = htonl(INADDR_ANY)
	};

	int ret = bind(socket,(struct sockaddr *) &local_address,sizeof(local_address));
	if(ret < 0) {
		perror("Bind");
		exit(EXIT_FAILURE);
	}

	ret = listen(socket,0);
	if(ret < 0) {
		perror("Listen");
		exit(EXIT_FAILURE);
	}
	/*************************************************************************************/
}

int server_socket_accept(int socket_fd) {

	struct sockaddr_in remote;
	socklen_t size_remote = sizeof(remote);
	int fd = accept(socket_fd,(struct sockaddr *) &remote,&size_remote);

	if(fd < 0) {
		perror("Accept");
		exit(EXIT_FAILURE);
	}
	printf("A client has connected\n");

	return fd;
}

void *server_socket_before_game_start(void *arg) {

	extern llist *games;
	struct client *c = (struct client *) arg;
	int fd = *(c->fd);

	while(1) {
		char buf[100];	
		request_read_tcp(buf,fd);

		char cmd[6];
		memcpy(cmd,buf,5);
		cmd[5] = '\0';

		if(strcmp(cmd,"UNREG") == 0) {
			//TODO: remove the player from the game 
			close(*(c->fd));
			break;
		} else if(strcmp(cmd,"SIZE?") == 0) {
			//TODO: sendd labyrinth size
		} else if(strcmp(cmd,"LIST?") == 0) {
			//TODO: send game player list
		} else if(strcmp(cmd,"GAME?") == 0) {
			send_games(fd,games);
		} else if(strcmp(cmd,"START") == 0) {
			c->player->ready = TRUE;
			break;
		} else {
			send_dunno(fd);
		}
	}

	return NULL;
}

void *server_socket_connection_prompt(void *arg) {

	int fd = *((int*) arg);

	extern llist *games;
	send_games(fd,games);

	struct client *c = server_socket_receive_newpl_regis(fd);
	c->fd = arg;

	pthread_t t;
	pthread_create(&t,NULL,server_socket_before_game_start,c);

	return NULL;	
}

struct client *server_socket_receive_newpl_regis(int fd) { 		
	extern llist *games;
	struct client *c;

	while(1) {		//this function exits once the player sent REGIS or NEWPL
		char buf[100];
		request_read_tcp(buf,fd);

		char cmd[6];
		memcpy(cmd,buf,5);
		cmd[5] = '\0';


		if(strcmp(cmd,"NEWPL") == 0) {

			printf("New game creation requested\n");
			c = request_newpl(buf,fd);
			break;

		} else if(strcmp(cmd,"REGIS") == 0) {

			printf("Game join request\n");
			c = request_regis(buf,fd);
			break;

		} else if(strcmp(cmd,"GAME?") == 0) {
			send_games(fd,games);
		} else if(strcmp(cmd,"SIZE?") == 0) {
			//TODO
		} else if(strcmp(cmd,"LIST?") == 0) {
			request_list(buf,fd);
		} else {
			send_dunno(fd);
		}
	}
	return c;
}

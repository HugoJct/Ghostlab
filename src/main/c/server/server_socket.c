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

void *server_socket_connection_prompt(void *arg) {

	int fd = *((int*) arg);

	extern llist *games;
	game_send_list(fd,games);

	server_socket_receive_newpl_regis(fd);

	free(arg);

	return NULL;	
}

void server_socket_receive_newpl_regis(int fd) { 		
	//TODO: move execution in functions

	int output = 0;

	extern llist *games;

	char buf[100];
	int ret = recv(fd,buf,100,0);
	assert(ret >= 0);

	char cmd[6];
	memcpy(cmd,buf,5);
	cmd[5] = '\0';

	while(output != 0) {		//this function exits once the player sent REGIS or NEWPL

		output = 0;

		if(strcmp(cmd,"NEWPL") == 0) {

			printf("New game creation requested\n");

			char *name = (char *) malloc(8);
			memcpy(name,buf+6,8);

			char porttmp[5];
			memcpy(porttmp,buf+15,4);
			porttmp[5] = '\0';

			int port = atoi(porttmp);

			struct game *g = game_create(4);
			struct player *p = player_create(name,fd,port);
			game_add_player(g,p);

			free(name);

			pthread_t t;
			pthread_create(&t,NULL,game_start,g);

		} else if(strcmp(cmd,"REGIS") == 0) {

			printf("Game join request\n");

			char *name = malloc(8);
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
				game_add_player(requested_game,p);	//TODO send REGOK
			} else
				printf("non\n");		//TODO: send REGNO

			free(name);

		} else if(strcmp(cmd,"LIST?") == 0) {
			//TODO
			output = 1;
		} else if(strcmp(cmd,"GAME?") == 0) {
			//TODO	
			output = 1;
		} else {
			//TODO send DUNNO
			output = -1;
		}
	}
}

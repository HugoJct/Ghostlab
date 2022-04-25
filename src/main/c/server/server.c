#include "dependencies.h"
#include "server_socket.h"
#include "game.h"
#include "player.h"
#include "labs_parser.h"

llist *games;

int main(int argc, char **argv) {

	/* The list of games existing on the server */
	games = llist_create(NULL);
	llist_push(games, game_create(2));
	llist_push(games, game_create(2));
	llist_push(games, game_create(2));
	struct game* temp = llist_pop(games);
	while (temp != NULL) {
		puts("=======");
		printf("ID: %d\n", temp->id);
		debug_lab(temp->labyrinth);
		temp = llist_pop(games);
	}
	
	/* Check that the arguments are correct */
	if(argc != 2) {
		dprintf(2,"Wrong usage\n\tExpected usage: ./server <port>\n");
		return EXIT_FAILURE;
	}

	/* Initialize the tcp socket for accepting incoming connection */
	int server_socket_fd = socket(PF_INET,SOCK_STREAM,0);
	assert(server_socket_fd >= 0);

	/* bind the server to an address and make it listen */
	server_socket_init(server_socket_fd,atoi(argv[1]));

	/* infinite loop for accepting new connections */
	while(1) {
		int *new_player = (int *) malloc(sizeof(int));
		*new_player = server_socket_accept(server_socket_fd);
		
		pthread_t t;
		pthread_create(&t,NULL,server_socket_connection_prompt,new_player);
	}

	llist_free(games);
	close(server_socket_fd);

	return EXIT_SUCCESS;
}

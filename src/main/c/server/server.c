#include "dependencies.h"
#include "server_socket.h"
#include "game.h"
#include "player.h"

llist *games;

int main(int argc, char **argv) {

	/* The list of games existing on the server */
	games = llist_create(NULL);
	
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
		int *new_player = (int *) malloc(sizeof(int));;
		*new_player = server_socket_accept(server_socket_fd);
		
		pthread_t t;
		pthread_create(&t,NULL,server_socket_connection_prompt,new_player);

		/* TODO handle the connection 
		 * 	-> send the player the game infos
		 * 	-> wait for its response 
		 * 	-> create a game or add the plyaer to a game 
		 * 	-> wait for new connection
		 */
	}

	llist_free(games);
	close(server_socket_fd);

	return EXIT_SUCCESS;
}

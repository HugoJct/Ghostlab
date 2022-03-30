#include "dependencies.h"
#include "server_socket.h"
#include "game.h"

int main(int argc, char **argv) {

	if(argc != 2) {
		dprintf(2,"Wrong usage\n\tExpected usage: ./server <port>\n");
		return EXIT_FAILURE;
	}

	int server_socket_fd = socket(PF_INET,SOCK_STREAM,0);
	assert(server_socket_fd >= 0);

	init_server(server_socket_fd,atoi(argv[1]));

	while(1) {
		int new_player = wait_for_connection(server_socket_fd);
	}

	close(server_socket_fd);

	return EXIT_SUCCESS;
}

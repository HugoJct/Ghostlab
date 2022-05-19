#ifndef SEND_RESPONSES
#define SEND_RESPONSES

#include "dependencies.h"
#include "game.h"

void send_regok(int fd, uint8_t game_id);
void send_regno(int fd);
void send_dunno(int fd);
void send_unrok(int fd, uint8_t game_id);
void send_players_list(int fd, int id);
void send_games(int socket_fd, llist *games);
void send_size(int socket_fd, uint8_t game_id);
void send_welco(int fd, struct game *g);
void send_move(int fd, int x, int y);

#endif

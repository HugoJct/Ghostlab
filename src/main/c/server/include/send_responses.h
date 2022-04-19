#ifndef SEND_RESPONSES
#define SEND_RESPONSES

#include "dependencies.h"


void send_regok(int fd, uint8_t game_id);
void send_regno(int fd);

#endif

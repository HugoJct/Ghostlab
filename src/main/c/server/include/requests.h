#ifndef REQUESTS_H
#define REQUESTS_H

#include "dependencies.h"
#include "game.h"
#include "player.h"
#include "send_responses.h"

void request_newpl(char buf[],int fd);
void request_regis(char buf[], int fd);

#endif

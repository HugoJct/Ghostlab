#include "send_responses.h"

void send_regok(int fd, uint8_t game_id) {
	char buf[10];
	memcpy(buf,"REGOK ",6);
	memcpy(buf+6,&game_id,1);
	memcpy(buf+7,"***",3);

	int ret = send(fd,buf,10,0);
	assert(ret == 10);
}

void send_regno(int fd){
	char buf[8];
	memcpy(buf,"REGOK***",8);

	int ret = send(fd,buf,8,0);
	assert(ret == 8);
}

void send_dunno(int fd) {
	int ret = send(fd,"DUNNO***",3,0);
	assert(ret >= 0);
}

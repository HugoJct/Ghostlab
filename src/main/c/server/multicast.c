#include "multicast.h"

void multicast_send(char *buf, int len, struct game *g) {
	struct sockaddr_in addr = {
		.sin_family = PF_INET,
		.sin_port = htons(g->diffusion_port),
		.sin_addr = g->diffusion_ip
	};
	int ret = sendto(g->socket_fd,buf,len,0,(struct sockaddr *) &addr,(socklen_t) sizeof(struct sockaddr_in));
	if(ret < 0) {
		perror("send");
		exit(1);
	}
}

void multicast_messa(char *messa, struct client *c) {
	char buf[100];
	memcpy(buf,"MESSA ",6);
	memcpy(buf+6,c->player->id,8);
	memcpy(buf+14," ",1);
	memcpy(buf+15,messa,strlen(messa));
	memcpy(buf+15+strlen(messa),"***",3);
	multicast_send(buf,18+strlen(messa),c->game);
}

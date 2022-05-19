#include "format.h"

char *format_3digits(int x) {
	char *format = malloc(3);
	memset(format,'0',3);

	char tmp[4];
	sprintf(tmp,"%d",x);

	if(x < 10)
		memcpy(format+2,&tmp,1);
	else if(x < 100)
		memcpy(format+1,&tmp,2);
	else
		memcpy(format,&tmp,3);

	return format;
}

char *format_4digits(int x) {
	char *format = malloc(4);
	memset(format,'0',4);

	char tmp[5];

	sprintf(tmp,"%d",x);

	if(x < 10)
		memcpy(format+3,&tmp,1);
	else if(x < 100)
		memcpy(format+2,&tmp,2);
	else if(x < 1000)
		memcpy(format+1,&tmp,3);
	else
		memcpy(format,&tmp,4);

	return format;
}

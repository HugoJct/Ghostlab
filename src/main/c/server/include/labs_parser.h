#ifndef LABS_PARSER_H
#define LABS_PARSER_H

#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <string.h>
#include <stdbool.h>
#include <fcntl.h>

#define BUFFER_SIZE 64

typedef struct labyrinth {
   int width;
   int height;
   int** cells;
} labyrinth;

labyrinth* parse_lab(char* filename);

bool is_valid(labyrinth *l);

void debug_lab(labyrinth *l);

#endif

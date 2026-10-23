#ifndef UTF8_H
#define UTF8_H

#include <stdint.h>

int8_t is_ascii(char string[]);
int32_t capitalize_ascii(char str[]);
int8_t codepoint_size(char string[]);
int32_t utf8_strlen(char str[]);
void utf8_substring(const char str[], int start, int end, char result[]);
int32_t codepoint_at(char str[], int32_t byte_index);
int is_animal_emoji_at(const char str[], int index);

#endif
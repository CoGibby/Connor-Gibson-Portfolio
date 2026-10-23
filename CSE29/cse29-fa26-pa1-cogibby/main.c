#include <stdio.h>
#include "utf8.h"

int main() {
    char input[1024];
    fgets(input, 1024, stdin);

    printf("Valid ASCII: %s\n", is_ascii(input) ? "true" : "false");

    char upper[1024];
    int len_of_byte = 0;
    while (input[len_of_byte] != '\0') {
        upper[len_of_byte] = input[len_of_byte];
        len_of_byte++;
    }
    upper[len_of_byte] = '\0';
    capitalize_ascii(upper);
    printf("Uppercased ASCII: \"%s\"\n", upper);
    printf("Length in bytes: %d\n", len_of_byte);

    int len_of_codepoint = utf8_strlen(input);
    printf("Number of code points: %d\n", len_of_codepoint);

    printf("Bytes per code point: ");
    int i = 0;
    int first = 1;
    while (input[i] != '\0') {
        int size = codepoint_size(input + i);
        if (!first){
            printf(" ");
        }
        printf("%d", size);
        first = 0;
        i += size;
    }
    printf("\n");

    char sub[1024];
    utf8_substring(input, 0, 6, sub);
    printf("Substring of the first 6 code points: \"%s\"\n", sub);

    printf("Code points as decimal numbers: ");
    i = 0;
    first = 1;
    while (input[i] != '\0') {
        int codepoint = codepoint_at(input, i);
        if (!first) printf(" ");
        printf("%d", codepoint);
        first = 0;
        i += codepoint_size(input + i);
    }
    printf("\n");

    printf("Animal emojis: ");
    for (int idx = 0; idx < len_of_codepoint; idx++) {
        if (is_animal_emoji_at(input, idx)) {
            char emoji[8];
            utf8_substring(input, idx, idx + 1, emoji);
            printf("%s", emoji);
        }
    }
    printf("\n");

    return 0;
}

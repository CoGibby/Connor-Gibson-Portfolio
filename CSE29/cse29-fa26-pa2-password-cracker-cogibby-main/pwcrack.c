#define _GNU_SOURCE

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <openssl/sha.h>

#include "compute_sha256.h"

int try_single_capitalization(char *password,
                              char *target_hash,
                              char *matching_password)
{
    size_t length = strlen(password);
    char *variant;
    char hash_hex[65];

    variant = malloc(length + 1);
    if (variant == NULL)
    {
        fprintf(stderr, "pwcrack: out of memory\n");
        return -1;
    }

    memcpy(variant, password, length + 1);

    for (size_t i = 0; i < length; i++)
    {
        unsigned char character = (unsigned char)password[i];

        if (character >= 'a' && character <= 'z')
        {
            variant[i] = (char)(character - 'a' + 'A');
            compute_sha256(variant, hash_hex);

            if (strcasecmp(hash_hex, target_hash) == 0)
            {
                memcpy(matching_password, variant, length + 1);
                free(variant);
                return 1;
            }

            variant[i] = (char)character;
        }
        else if (character >= 'A' && character <= 'Z')
        {
            variant[i] = (char)(character - 'A' + 'a');
            compute_sha256(variant, hash_hex);

            if (strcasecmp(hash_hex, target_hash) == 0)
            {
                memcpy(matching_password, variant, length + 1);
                free(variant);
                return 1;
            }

            variant[i] = (char)character;
        }
    }

    free(variant);
    return 0;
}

static char *read_line(size_t *length)
{
    char *line = NULL;
    char chunk[4096];
    size_t line_length = 0;
    size_t capacity = 0;

    while (fgets(chunk, sizeof(chunk), stdin) != NULL)
    {
        size_t chunk_length = strlen(chunk);
        char *newline = memchr(chunk, '\n', chunk_length);
        size_t keep_length = (newline == NULL) ? chunk_length
                                               : (size_t)(newline - chunk);

        if (line_length + keep_length + 1 > capacity)
        {
            size_t new_capacity = capacity == 0 ? 1024 : capacity;
            while (line_length + keep_length + 1 > new_capacity)
            {
                new_capacity *= 2;
            }
            char *new_line = realloc(line, new_capacity);
            if (new_line == NULL)
            {
                free(line);
                return NULL;
            }
            line = new_line;
            capacity = new_capacity;
        }

        memcpy(line + line_length, chunk, keep_length);
        line_length += keep_length;
        line[line_length] = '\0';

        if (newline != NULL)
        {
            break;
        }
    }

    if (line == NULL)
    {
        return NULL;
    }

    *length = line_length;
    return line;
}

int main(int argc, char **argv)
{
    if (argc < 2)
    {
        printf("Usage: pwcrack <sha256-hash>\n");
        return 1;
    }

    char *target_hash = argv[1];

    char *buffer;
    size_t buffer_capacity;
    char hash_hex[65];
    char matching_password[1001];

    while ((buffer = read_line(&buffer_capacity)) != NULL)
    {
        size_t len = strlen(buffer);

        if (len > 0 && buffer[len - 1] == '\n')
        {
            buffer[len - 1] = '\0';
        }

        compute_sha256(buffer, hash_hex);

        if (strcasecmp(hash_hex, target_hash) == 0)
        {
            printf("Found password: SHA256(%s) = %s\n",
                   buffer,
                   target_hash);

            return 0;
        }

        int status = try_single_capitalization(buffer,
                                               target_hash,
                                               matching_password);
        free(buffer);

        if (status < 0)
        {
            return 1;
        }

        if (status > 0)
        {
            printf("Found password: SHA256(%s) = %s\n",
                   matching_password,
                   target_hash);

            return 0;
        }
    }

    printf("Did not find a matching password\n");

    return 0;
}

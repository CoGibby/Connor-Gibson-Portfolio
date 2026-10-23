#include <openssl/sha.h>
#include <string.h>
#include <stdio.h>
#include "compute_sha256.h"

void compute_sha256(char *input, char output_hex[65])
{
    unsigned char hash[32];

    SHA256((unsigned char*)input, strlen(input), hash);

    for (int i = 0; i < 32; i++)
        sprintf(&output_hex[i * 2], "%02x", hash[i]);

    output_hex[64] = '\0';
}


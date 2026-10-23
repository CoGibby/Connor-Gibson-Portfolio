#include <string.h>
#include <stdint.h>
// Given a char[8] representing a binary number, returns the signed 8-bit integer it represents
// For example:
//   "10000000" produces -128 (most negative 8-bit number)
//   "00000001" produces 1
//   "00000000" produces 0
//   "11111111" produces -1
//   "01111111" produces 127 (most positive 8-bit number)
int8_t bin8_to_dec(char binary[]) {
    int8_t sum = 0;

    for (int i = 0; i < 8; i++) {
        sum = (sum << 1);
        int bit_value = binary[i] - '0';

        sum += bit_value;
    }

    return sum;
}

// Given a char[] of ASCII '0' and '1' (length ≤ 32), print the unsigned 32-bit integer it represents.
// If the string is shorter than 32, assume leading 0's.
void bin_to_dec(char str[]){
    uint32_t sum = 0;
    int length = strlen(str);
    for (int i = 0; i < length; i++) {
        sum = (sum << 1);
        int bit_value = str[i] - '0';

        sum += bit_value;
    }

    return sum;
}

// Given a char[] of ASCII '0' and '1' (length ≤ 32), print the signed 32-bit integer it represents.
void bin_to_dec_signed(char str[]){
    int32_t sum = 0;
    int length = strlen(str);
    for (int i = 0; i < length; i++) {
        int bit_value = str[i] - '0';
        if (i == 0) {
            sum = -bit_value; 
        } else {
            sum = (sum << 1) + bit_value;
        }
    }

    return sum;
}


// Given a uint32_t, returns the least significant (rightmost) 16 bits as a uint16_t
// For example:
//   0xFFFFFFFF -> 0xFFFF (65535)
//   0x00000000 -> 0x0000 (0)
//   0x00010001 -> 0x0001 (1)
//   0x80008000 -> 0x8000 (32768)
//   0xDEADBEEF -> 0xBEEF (48879)
uint16_t second_half(uint32_t number){
    return (number & 0x0000FFFF);
}
// Given a char, returns 1 if it starts with the bits `10` and 0 if it does not
// For example:
//   0b10110101 -> 1 (starts with 10)
//   0b11000000 -> 0 (starts with 11)
//   0b01110000 -> 0 (starts with 01)
//   0b10000000 -> 1 (starts with 10)
//   0b00001111 -> 0 (starts with 00)
int starts_with_10(char c){
    if ((c & 0b11000000) == 0b10000000){
        return 1;
    } else {
        return 0;
    }
}

// Given a int8_t, return 1 if the number is even or 0 if the number is odd only using bitwise operators
// For example:
// Input -> Output
// 0 -> 1
// 1 -> 0
// -2 -> 1
// 29 -> 0
int bitwise_is_even(int8_t n){
    if ((n & 0x1) == 0) {
        return 1;
    }
    return 0;
}

// Given a 32-bit integer, return the number of 1 bits (aka the popcount/hamming weight) only using bitwise operators
// For example:
// Input -> Output
// 128 -> 1
//-1 -> 32
// 0 -> 0
// 2147483647 -> 31
int count_1_bits(int32_t n){
    int count = 0;
    uint32_t num = (uint32_t)n;
    //count takes all positions with a 1 and returns
    for (int i = 0; i < 32; i++) {
        count += (num & 1);

        num = (num >> 1);
    }

    return count;
}
// Given a 32-bit unsigned integer x and an unsigned 8-bit integer n,
// return an unsigned 32-bit integer containing just the least n significant bits of x.
// If n is greater than or equal to 32, return x unchanged.
// If n is 0, return 0.
// For example:
//   x = 0xABCDEF12, n = 4 produces 0x00000002 (keeps only last 4 bits: 0010)
//   x = 0x89ABCDEF, n = 12 produces 0x00000DEF (keeps last 12 bits)
//   x = 0xFFFFFFFF, n = 0 produces 0x00000000 (keeps no bits)
//   x = 0x12345678, n = 40 produces 0x12345678 (n >= 32, returns original)
uint32_t lowest_n(uint32_t x, uint8_t n){
    if (n >= 32){
        return x;
    }
    if (n == 0){
        return 0;
    }
    uint32_t mask = (1 << n) - 1;

    return x & mask;
}

// Given a char[] of characters, print the char representation and int representation
// For example:
// Input: "abc"
// Output:
// a 97
// b 98
// c 99
void print_abc_char_and_int(char str[]){
    int length = strlen(str);
    for (int i = 0; i < length; i++){
        printf("%c %d\n", str[i], str[i]);
    }
    return;
}

// Returns `1` if the given string contains only ASCII characters (or
// is empty), and `0` if there are any non-ASCII characters in the string
uint8_t is_ascii(char str[]){
    for (int i = 0; str[i] != '\0'; i++){
        if ((unsigned char)str[i] > 127){ //unsigned chars are automatically >=0
            return 0;
        }
    }
    return 1;
}

// Returns the number of characters capitalized and capitalizes the lowercase 
// a-z ASCII characters of str in-place.
int32_t capitalize_ascii(char str[]){
    int count = 0;
    for (int i = 0; str[i] != '\0'; i++){
        if (str[i] >= 'a' && str[i] <= 'z'){
            str[i] -= 32;
            count++;
        }
    }
    return count;
}

// Given a char[] representing a UTF-8 encoded string, and a byte index,
// return the decimal codepoint value of the Unicode character that starts at that byte index,
// or -1 if the byte at that index cannot be the start of a valid UTF-8 character.
//
// For example:
//   "Hello", 0 -> 72 (H has codepoint value 72)
//   "Hello", 4 -> 111 (o has codepoint value 111)
//   "Café", 3 -> 233 (é has codepoint value 233)
//   "🌟star", 0 -> 127775 (🌟 has codepoint value 127775)
//   "🌟star", 1 -> -1 (byte is 10xxxxxx continuation byte)
//   "🌟star", 4 -> 115 (s has codepoint value 115)
int32_t codepoint_at(char str[], int32_t byte_index){
    unsigned char byte = (unsigned char)str[byte_index];

    // 2. Check if it's an invalid continuation byte (starts with binary 10xxxxxx)
    if ((byte & 0b11000000) == 0b10000000) {
        return -1;
    }

    // 3. If it's valid, just return the value
    return byte;
}

// Returns 1, 2, 3, or 4 depending on the size of the first codepoint in the string.
// Returns 0 if the string is empty
// Returns -1 if the first byte is not a valid UTF-8 start byte
int8_t codepoint_size(char string[]){
    if (strlen(string) == 0){
        return 0;
    }
    uint8_t first = (uint8_t)string[0];
    if ((first & 0b11111000) == 0b11110000){
        return 4;
    }
    if ((first & 0b11110000) == 0b11100000){
        return 3;
    }
    if ((first & 0b11100000) == 0b11000000){
        return 2;
    }
    if ((first & 0b10000000) == 0b00000000){
        return 1;
    }
    return -1;
}
// Given a char[] representing a UTF-8 encoded string,
// return the number of UTF-8 codepoints (characters) in the string.
//
// For example:
//   "Hello" -> 5 (5 ASCII characters)
//   "Café" -> 4 (3 ASCII + 1 two-byte character)
//   "🌟" -> 1 (1 four-byte character)
int32_t utf8_strlen(char str[]){
    int count = 0;
    for (int i = 0; str[i] != '\0'; i++){
        uint8_t individual = (uint8_t)str[i];
        if ((individual & 0b11000000) != 0b10000000){
            count++;
        }
    }
    return count;
}

// Given a UTF-8 encoded string, extract the substring from codepoint index start (inclusive) 
// to end (exclusive) and print it to standard output (stdout), followed by a newline.
// The substring should be null-terminated in a local buffer.
void utf8_substring(const char str[], int start, int end){
    
}

// Given a UTF-8 encoded string and a codepoint index, return 1 if the codepoint at that index is an animal emoji, 0 otherwise.
int is_animal_emoji_at(const char str[], int index){
    int count = 0;
    for (int i = 0; str[i] != '\0'; i++){
        if ((str[i] & 0b11000000) == 0b10000000){
            if (index == count){
                if ((str[i] >= U+1F400 && str[i] <= U+1F43F) || (str[i] >= U+1F980 && str[i] <= U+1F9AE)){
                    return 1;
                } else {
                    return 0;
                }
            } else {
                count++;
            }
        }
    }
    return 0;
}
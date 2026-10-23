#include <limits.h>
#include <stdint.h>
#include <stdio.h>
#include <string.h>

#include "../utf8.h"

static int failures;

#define CHECK_INT(expected, actual) do { \
    int32_t expected_value = (expected); \
    int32_t actual_value = (actual); \
    if (expected_value != actual_value) { \
        printf("FAIL %s:%d: expected %d, got %d\n", __FILE__, __LINE__, expected_value, actual_value); \
        failures++; \
    } \
} while (0)

#define CHECK_STRING(expected, actual) do { \
    const char *expected_value = (expected); \
    const char *actual_value = (actual); \
    if (strcmp(expected_value, actual_value) != 0) { \
        printf("FAIL %s:%d: expected \"%s\", got \"%s\"\n", __FILE__, __LINE__, expected_value, actual_value); \
        failures++; \
    } \
} while (0)

static void copy_bytes(char *destination, const unsigned char bytes[], size_t count) {
    memcpy(destination, bytes, count);
    destination[count] = '\0';
}

static void test_is_ascii(void) {
    CHECK_INT(1, is_ascii(""));
    CHECK_INT(1, is_ascii("abcdefghijklmnopqrstuvwxyz"));
    CHECK_INT(1, is_ascii("ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789"));
    CHECK_INT(1, is_ascii(" !\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~"));
    CHECK_INT(1, is_ascii("\x01\x1f\x7f"));
    CHECK_INT(0, is_ascii("\x80"));
    CHECK_INT(0, is_ascii("\xbf"));
    CHECK_INT(0, is_ascii("\xc0"));
    CHECK_INT(0, is_ascii("\xc3\xa9"));
    CHECK_INT(0, is_ascii("\xf0\x9f\x90\xb6"));
    CHECK_INT(0, is_ascii("\xff"));
}

static void test_capitalize_ascii(void) {
    char text[32];

    text[0] = '\0';
    CHECK_INT(0, capitalize_ascii(text));
    CHECK_STRING("", text);

    memcpy(text, "abcdefghijklmnopqrstuvwxyz", 27);
    CHECK_INT(26, capitalize_ascii(text));
    CHECK_STRING("ABCDEFGHIJKLMNOPQRSTUVWXYZ", text);

    copy_bytes(text, (const unsigned char []){'A', 'Z', '0', '9', '!', 0xC3, 0xA9, 0}, 7);
    CHECK_INT(0, capitalize_ascii(text));
    CHECK_STRING("AZ09!\xc3\xa9", text);

    copy_bytes(text, (const unsigned char []){'`', 'a', 'z', '{', 0}, 5);
    CHECK_INT(2, capitalize_ascii(text));
    CHECK_STRING("`AZ{", text);

    copy_bytes(text, (const unsigned char[]){'m', 0xC3, 0xA9, 'n', 0xF0, 0x9F, 0x90, 0xB6, 'o', 0}, 9);
    CHECK_INT(3, capitalize_ascii(text));
    CHECK_STRING("M\xc3\xa9N\xf0\x9f\x90\xb6O", text);
}

static void test_codepoint_size(void) {
    char text[8];

    CHECK_INT(0, codepoint_size(""));
    CHECK_INT(1, codepoint_size("a"));
    CHECK_INT(1, codepoint_size("\x7f"));

    copy_bytes(text, (const unsigned char[]){0xC2, 0xA0, 0}, 2);
    CHECK_INT(2, codepoint_size(text));
    copy_bytes(text, (const unsigned char[]){0xE2, 0x82, 0xAC, 0}, 3);
    CHECK_INT(3, codepoint_size(text));
    copy_bytes(text, (const unsigned char[]){0xF0, 0x9F, 0x90, 0xB6, 0}, 4);
    CHECK_INT(4, codepoint_size(text));

    copy_bytes(text, (const unsigned char[]){0x80, 0}, 1);
    CHECK_INT(-1, codepoint_size(text));
    copy_bytes(text, (const unsigned char[]){0xBF, 0}, 1);
    CHECK_INT(-1, codepoint_size(text));
    copy_bytes(text, (const unsigned char[]){0xF8, 0}, 1);
    CHECK_INT(-1, codepoint_size(text));
    copy_bytes(text, (const unsigned char[]){0xFF, 0}, 1);
    CHECK_INT(-1, codepoint_size(text));

    copy_bytes(text, (const unsigned char[]){0xC3, 0}, 1);
    CHECK_INT(2, codepoint_size(text));
    copy_bytes(text, (const unsigned char[]){0xE2, 0x82, 0}, 2);
    CHECK_INT(3, codepoint_size(text));
    copy_bytes(text, (const unsigned char[]){0xF0, 0x9F, 0x90, 0}, 3);
    CHECK_INT(4, codepoint_size(text));
}

static void test_utf8_strlen(void) {
    char text[16];

    CHECK_INT(0, utf8_strlen(""));
    CHECK_INT(5, utf8_strlen("hello"));
    CHECK_INT(1, utf8_strlen("\xc3\xa9"));
    CHECK_INT(1, utf8_strlen("\xe2\x82\xac"));
    CHECK_INT(1, utf8_strlen("\xf0\x9f\x90\xb6"));

    copy_bytes(text, (const unsigned char[]){'a', 0xC3, 0xA9, 0xE2, 0x82, 0xAC,
        0xF0, 0x9F, 0x90, 0xB6, 'b', 0}, 11);
    CHECK_INT(5, utf8_strlen(text));

    copy_bytes(text, (const unsigned char[]){0x80, 0}, 1);
    CHECK_INT(-1, utf8_strlen(text));
    copy_bytes(text, (const unsigned char[]){0xC3, 0}, 1);
    CHECK_INT(-1, utf8_strlen(text));
    copy_bytes(text, (const unsigned char[]){0xE2, 0x82, 0}, 2);
    CHECK_INT(-1, utf8_strlen(text));
    copy_bytes(text, (const unsigned char[]){0xF0, 0x9F, 0x90, 0}, 3);
    CHECK_INT(-1, utf8_strlen(text));

    copy_bytes(text, (const unsigned char[]){0xC3, 'A', 0}, 2);
    CHECK_INT(-1, utf8_strlen(text));
}

static void test_utf8_substring(void) {
    char result[32];
    char mixed[16];

    copy_bytes(mixed, (const unsigned char[]){'a', 0xC3, 0xA9, 0xE2, 0x82, 0xAC,
        0xF0, 0x9F, 0x98, 0x80, 'b', 0}, 11);

    result[0] = 'Z';
    utf8_substring("", 0, 0, result);
    CHECK_STRING("", result);
    utf8_substring("", 0, 100, result);
    CHECK_STRING("", result);

    utf8_substring(mixed, -1, 1, result);
    CHECK_STRING("", result);
    utf8_substring(mixed, 0, 0, result);
    CHECK_STRING("", result);
    utf8_substring(mixed, 2, 2, result);
    CHECK_STRING("", result);
    utf8_substring(mixed, 3, 2, result);
    CHECK_STRING("", result);

    utf8_substring(mixed, 0, 5, result);
    CHECK_STRING("a\xc3\xa9\xe2\x82\xac\xf0\x9f\x98\x80""b", result);
    utf8_substring(mixed, 0, 2, result);
    CHECK_STRING("a\xc3\xa9", result);
    utf8_substring(mixed, 2, 4, result);
    CHECK_STRING("\xe2\x82\xac\xf0\x9f\x98\x80", result);
    utf8_substring(mixed, 4, 5, result);
    CHECK_STRING("b", result);
    utf8_substring(mixed, 4, 100, result);
    CHECK_STRING("b", result);
    utf8_substring(mixed, 0, INT_MAX, result);
    CHECK_STRING("a\xc3\xa9\xe2\x82\xac\xf0\x9f\x98\x80""b", result);
    utf8_substring(mixed, 5, INT_MAX, result);
    CHECK_STRING("", result);
    utf8_substring(mixed, 6, INT_MAX, result);
    CHECK_STRING("", result);

    copy_bytes(mixed, (const unsigned char[]){0xC3, 'A', 0}, 2);
    utf8_substring(mixed, 0, 1, result);
    CHECK_STRING("", result);
}

static void test_codepoint_at(void) {
    char mixed[16];

    copy_bytes(mixed, (const unsigned char[]){'a', 0xC3, 0xA9, 0xE2, 0x82, 0xAC,
        0xF0, 0x9F, 0x98, 0x80, 'b', 0}, 11);
    CHECK_INT(97, codepoint_at(mixed, 0));
    CHECK_INT(233, codepoint_at(mixed, 1));
    CHECK_INT(-1, codepoint_at(mixed, 2));
    CHECK_INT(8364, codepoint_at(mixed, 3));
    CHECK_INT(-1, codepoint_at(mixed, 4));
    CHECK_INT(-1, codepoint_at(mixed, 5));
    CHECK_INT(128512, codepoint_at(mixed, 6));
    CHECK_INT(-1, codepoint_at(mixed, 7));
    CHECK_INT(-1, codepoint_at(mixed, 8));
    CHECK_INT(-1, codepoint_at(mixed, 9));
    CHECK_INT(98, codepoint_at(mixed, 10));
    CHECK_INT(-1, codepoint_at(mixed, 11));

    char truncated[8];
    copy_bytes(mixed, (const unsigned char[]){0xC3, 'A', 0}, 2);
    CHECK_INT(-1, codepoint_at(mixed, 0));
    copy_bytes(truncated, (const unsigned char[]){0xE2, 0x82, 0}, 2);
    CHECK_INT(-1, codepoint_at(truncated, 0));
    copy_bytes(truncated, (const unsigned char[]){0xF0, 0x9F, 0x90, 0}, 3);
    CHECK_INT(-1, codepoint_at(truncated, 0));
    CHECK_INT(-1, codepoint_at(mixed, -1));
}

static void check_animal_bytes(const unsigned char bytes[], size_t count, int expected) {
    char text[16];
    copy_bytes(text, bytes, count);
    CHECK_INT(expected, is_animal_emoji_at(text, 0));
}

static void test_is_animal_emoji_at(void) {
    check_animal_bytes((const unsigned char[]){0xF0, 0x9F, 0x8F, 0xBF, 0}, 4, 0);
    check_animal_bytes((const unsigned char[]){0xF0, 0x9F, 0x90, 0x80, 0}, 4, 1);
    check_animal_bytes((const unsigned char[]){0xF0, 0x9F, 0x90, 0xBF, 0}, 4, 1);
    check_animal_bytes((const unsigned char[]){0xF0, 0x9F, 0x91, 0x80, 0}, 4, 0);
    check_animal_bytes((const unsigned char[]){0xF0, 0x9F, 0xA5, 0xBF, 0}, 4, 0);
    check_animal_bytes((const unsigned char[]){0xF0, 0x9F, 0xA6, 0x80, 0}, 4, 1);
    check_animal_bytes((const unsigned char[]){0xF0, 0x9F, 0xA6, 0x81, 0}, 4, 1);
    check_animal_bytes((const unsigned char[]){0xF0, 0x9F, 0xA6, 0xAE, 0}, 4, 1);
    check_animal_bytes((const unsigned char[]){0xF0, 0x9F, 0xA6, 0xAF, 0}, 4, 0);
    check_animal_bytes((const unsigned char[]){0xF0, 0x9F, 0x9B, 0xB0, 0}, 4, 0);

    char mixed[32];
    copy_bytes(mixed, (const unsigned char[]){'x', 0xF0, 0x9F, 0x90, 0x80,
        0xC3, 0xA9, 0xF0, 0x9F, 0xA6, 0x80, 'y', 0}, 12);
    CHECK_INT(0, is_animal_emoji_at(mixed, -1));
    CHECK_INT(0, is_animal_emoji_at(mixed, 0));
    CHECK_INT(1, is_animal_emoji_at(mixed, 1));
    CHECK_INT(0, is_animal_emoji_at(mixed, 2));
    CHECK_INT(1, is_animal_emoji_at(mixed, 3));
    CHECK_INT(0, is_animal_emoji_at(mixed, 4));
    CHECK_INT(0, is_animal_emoji_at(mixed, 5));
}

int main(void) {
    test_is_ascii();
    test_capitalize_ascii();
    test_codepoint_size();
    test_utf8_strlen();
    test_utf8_substring();
    test_codepoint_at();
    test_is_animal_emoji_at();

    if (failures == 0) {
        printf("All hidden-edge unit checks passed.\n");
        return 0;
    }

    printf("%d hidden-edge unit check(s) failed.\n", failures);
    return 1;
}

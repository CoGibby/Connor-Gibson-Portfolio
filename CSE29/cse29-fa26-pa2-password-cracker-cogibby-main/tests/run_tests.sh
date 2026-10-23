#!/usr/bin/env bash
set -euo pipefail

PWCRACK="${1:-./pwcrack}"

assert_case() {
    local name=$1
    local input=$2
    local target=$3
    local expected=$4
    local actual

    actual=$(printf '%b' "$input" | "$PWCRACK" "$target")
    if [[ "$actual" != "$expected" ]]; then
        printf 'FAIL: %s\nexpected: %s\nactual:   %s\n' \
            "$name" "$expected" "$actual" >&2
        exit 1
    fi
    printf 'PASS: %s\n' "$name"
}

SECRET_HASH='2bb80d537b1da3e38bd30361aa855686bde0eacd7162fef6a25fe97bf527a25b'
SECREt_HASH='a2c3b02cb22af83d6d1ead1d4e18d916599be7c2ef2f017169327df1f7c844fd'
SE_CRET_HASH='f68b9ca01fe26d28a968df38e5ac901b58d2f1dfe46746d00b613301a41e0578'
EMPTY_HASH='e3b0c44298fc1c149afbf4c8996fb92427ae41e4649b934ca495991b7852b855'
CAFE_HASH='850f7dc43910ff890f8879c0ed26fe697c93a067ad93a7d50f466a7028a9bf4e'
ACCENTED_HASH='a8d31220659de076526e77fdc0e75d0fa4dc468c358ee389009f5310f9513493'
PUNCTUATION_HASH='5751a44782594819e4cb8aa27c2c9d87a420af82bc6a5a05bc7f19c3bb00452b'
LONG_A_HASH='41edece42d63e8d9bf515a9ba6932e1c20cbc9f5a5d134645adb5db1b9737ea3'
LONG_A_5000_HASH='c526c6222044dab5674de9c4ac7f4566ebb5e4d8bf9d8ea34c9cc8a7cc3c869c'

found_for() {
    printf 'Found password: SHA256(%s) = %s' "$1" "$2"
}

assert_case 'exact match before variants' \
    'seCret\nother\n' "$SECREt_HASH" "$(found_for seCret "$SECREt_HASH")"

assert_case 'single lowercase-to-uppercase variant' \
    'Password\nNeverGuessMe!!\nsecret\n' \
    "$SECREt_HASH" "$(found_for seCret "$SECREt_HASH")"

assert_case 'single uppercase-to-lowercase variant' \
    'SeCret\n' "$SECREt_HASH" "$(found_for seCret "$SECREt_HASH")"

assert_case 'uppercase-to-mixed variant is not tried' \
    'SECRET\n' "$SECREt_HASH" 'Did not find a matching password'

assert_case 'uppercase-to-lowercase exact sequence' \
    'SECREt\n' '0917b13a9091915d54b6336f45909539cce452b3661b21f386418a257883b30a' \
    "$(found_for SECRET '0917b13a9091915d54b6336f45909539cce452b3661b21f386418a257883b30a')"

assert_case 'first exact match wins' \
    'SECRET\nsecret\n' '0917b13a9091915d54b6336f45909539cce452b3661b21f386418a257883b30a' \
    "$(found_for SECRET '0917b13a9091915d54b6336f45909539cce452b3661b21f386418a257883b30a')"

assert_case 'single uppercase-to-lowercase leading match' \
    'Secret\n' "$SE_CRET_HASH" "$(found_for SeCret "$SE_CRET_HASH")"

assert_case 'empty line exact match' \
    'Password\n\nsecret\n' "$EMPTY_HASH" \
    "$(found_for '' "$EMPTY_HASH")"

assert_case 'no match exits with no-match message' \
    'Password\nnotinlist\n' "$SECRET_HASH" \
    'Did not find a matching password'

assert_case 'non-ASCII UTF-8 is unchanged' \
    'café\n' "$CAFE_HASH" "$(found_for café "$CAFE_HASH")"

assert_case 'non-ASCII UTF-8 leading character' \
    'élong\n' "$ACCENTED_HASH" "$(found_for élong "$ACCENTED_HASH")"

assert_case 'punctuation and digits are not changed' \
    'Password123!\n' "$PUNCTUATION_HASH" \
    "$(found_for password123! "$PUNCTUATION_HASH")"

assert_case 'line without trailing newline' \
    'SeCret' "$SECREt_HASH" "$(found_for seCret "$SECREt_HASH")"

assert_case 'CRLF input is accepted' \
    'SeCret\r\n' "$SECREt_HASH" "$(found_for seCret "$SECREt_HASH")"

assert_case 'exact 1000-character password' \
    "$(printf 'a%.0s' $(seq 1 1000))" "$LONG_A_HASH" \
    "$(found_for "$(printf 'a%.0s' $(seq 1 1000))" "$LONG_A_HASH")"

assert_case 'long line with single-character variant' \
    "$(printf 'a%.0s' $(seq 1 4999))A" "$LONG_A_5000_HASH" \
    "$(found_for "$(printf 'a%.0s' $(seq 1 5000))" "$LONG_A_5000_HASH")"

if printf 'Password\n' | "$PWCRACK" >/dev/null 2>&1; then
    printf 'FAIL: missing command-line argument should fail\n' >&2
    exit 1
fi

printf 'All 16 tests passed.\n'

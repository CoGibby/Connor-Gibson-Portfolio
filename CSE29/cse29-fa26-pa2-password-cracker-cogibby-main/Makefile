CC ?= gcc
CFLAGS ?= -std=c11 -O2 -Wall -Wextra -Wpedantic
LDLIBS ?= -lcrypto -lssl

SRC := pwcrack.c compute_sha256.c

all: pwcrack

pwcrack: $(SRC)
	$(CC) $(CFLAGS) $(SRC) -o $@ $(LDLIBS)

test: pwcrack
	./tests/run_tests.sh ./pwcrack

clean:
	rm -f pwcrack pwcrack_test

.PHONY: all test clean

import random
def choose_kill_num(kill_num):
    count = 0
    while True:
        random_number = random.randint(1, 6)
        if random_number != kill_num:
            count += 1
        else:
            return count
            break

kill_num = int(input("What number should we look for? "))
print(choose_kill_num(kill_num))

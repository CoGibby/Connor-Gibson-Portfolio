import random
def roll_the_die(my_num):
    rolls = 0
    while True:
        random_number = random.randint(1,6)
        print(random_number)
        rolls += 1
        if random_number == my_num:
            break
    return rolls
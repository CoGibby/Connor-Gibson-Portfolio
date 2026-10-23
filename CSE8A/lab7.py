# income_this_week = [
#    [1, 2, 3, 4, 5, 6, 7],
#    [70, 60, 50, 40, 30, 20, 10],
#    [-1,-1, -1, -1, -1, -1, -1],
# ]

income_this_week = [
   [10, 20, 54, 23, 18, -1, -1],
   [15, -1, 30, 28, 32, -1, 49],
   [20, 30, 43, 21, 3,  -1, 51],
]

# income_this_week = [
#    [10, 20, 54, 23, 18, -1, -1],
#    [15, -1, 30, 28, 32, -1, 49],
#    [20, 30, 43, 21, 3,  -1, 51],
# ]

oned_list = []
new_list = []
income_list = []
consistent_list = []
def weekly_income_by_store(income_this_week):
    for store in income_this_week:
        total = 0
        for number in store:
            if number != -1: 
                total += number 
        oned_list.append(total)
    return oned_list
print(weekly_income_by_store(income_this_week))

def best_day_by_store(income_this_week):
    for store in income_this_week:
        greatest_number = 0
        for number in store:
            if number != -1: 
                if number > greatest_number:
                    greatest_number = number
        new_list.append(greatest_number)
    return new_list
print(best_day_by_store(income_this_week))

def great_days_by_store(income_this_week):
    for store in income_this_week:
        great_day = 0
        for number in store:
            if number > 10:
                great_day += 1
        income_list.append(great_day)
    return income_list
print(great_days_by_store(income_this_week))

def most_consistent_store(income_this_week):
    for store in income_this_week:
        
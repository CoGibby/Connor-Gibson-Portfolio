def calculate_points(events_list):
    total = 0
    for event in events_list:
        if event == "gold": 
            total += 2
        if event == "silver": 
            total += 1
        if event == "trap":
            total += -1 
    return total
events_sample = ["gold", "gold", "trap", "mushroom", "greatness"]
events_sample2 = ["silver", "trap", "trap"]
events_sample3 = ["gold", "gold", "success", "trap"]
print(calculate_points(events_sample))
print(calculate_points(events_sample2))
print(calculate_points(events_sample3))
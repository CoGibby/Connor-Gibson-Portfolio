data = [[23, 24, 25, 27, 26],
        [0, -1, 1, -3, -2],
        [9, 10, 11, 12, 11]]

def get_a_days_weather(wdata, day):
    if day > -1 and day < 5: 
        specific_data = []
        for weeks in wdata: 
            specific_data.append(weeks[day])
        return specific_data
    else: 
        return f"day {day} is invalid!"
print(get_a_days_weather(data, 0)) # Expected output: [23, 0, 9]
print(get_a_days_weather(data, 2)) # Expected output: [25, 1, 11]
print(get_a_days_weather(data, 4)) # Expected output: [26, -2, 11]
print(get_a_days_weather(data, 5)) # Expected output: 'day 5 is invalid!'
print(get_a_days_weather(data, -1)) # Expected output: 'day -1 is invalid!'

def calculate_average_costs(student_name, costs_list):
    total_cost = 0
    if costs_list == []: 
        return f"Average cost for {student_name} is 0"
    for i in range(len(costs_list)):
        total_cost += costs_list[i]
    average_cost = total_cost // len(costs_list)
    return f"Average cost for {student_name} is {float(average_cost)}"


print(calculate_average_costs("Bala", [10.0, 20.0, 30.0]))
# Expected output: Average cost for Bala is 20.0
print(calculate_average_costs("Gayathri", [20, 50, 100, 70]))
# Expected output: Average cost for Gayathri is 60.0


print(calculate_average_costs("Ritinha", [15.0, 25.0]))
# Expected output: Average cost for Ritinha is 20.0


print(calculate_average_costs("Noble", []))
# Expected output: Average cost for Noble is 0




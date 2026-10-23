def calculate_average_costs(student_name, costs_list):
  total_cost = 0
  if costs_list == [] or costs_list == None: 
     return f"Average cost for {student_name} is 0"
  for i in range(len(costs_list)):
      total_cost += costs_list[i]
  average_cost = total_cost // len(costs_list)
  return f"Average cost for {student_name} is {float(average_cost)}"


# Test cases
print(calculate_average_costs("Bala", [10.0, 20.0, 30.0]))
# Expected output: Average cost for Bala is 20.0
print(calculate_average_costs("Gayathri", [20, 50, 100, 70]))
# Expected output: Average cost for Gayathri is 60.0


print(calculate_average_costs("Ritinha", [15.0, 25.0]))
# Expected output: Average cost for Ritinha is 20.0


print(calculate_average_costs("Noble", []))
# Expected output: Average cost for Noble is 0

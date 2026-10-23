def print_step(step_num):
    print(f"--- Step {step_num} ---")
    return step_num + 1

def process_data(a, b):
    c = print_step(1) # 1+1
    d = print_step(2) # 2+1
    return a + b + c + d  #5+10+2+3

# Program execution begins here:
print("START (Line A)") 
result = process_data(5, 10)
print("END (Line B)")
print(f"Final Result: {result}")

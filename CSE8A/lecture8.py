def add_one_to_integer(n):
    n += 1
    return n


def add_one_to_list(nums):
    for i in range(len(nums)):
        nums[i] += 1


num = 5
add_one_to_integer(num)
print(num)  # Output: 5


num = 10
num = add_one_to_integer(num)
print(num)  # Output: 11
   
num_list = [10, 20, 30]
print(num_list) 
# Initial Output: [10, 20, 30]
add_one_to_list(num_list)
print(num_list) 
# Final Output: [10,20,30]
def count_candy(candies): 
    candy_dict = {}
    for candy in candies:
        if candy not in candy_dict:
            candy_dict[candy] = 1
        elif candy in candy_dict:
            candy_dict[candy] += 1
    return candy_dict

##print(count_candy(["M&Ms", "M&Ms", "M&Ms"]))
print(count_candy(["Prud", "Is", "The", "Best", "Best", "Best", 1]))
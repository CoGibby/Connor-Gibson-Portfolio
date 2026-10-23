def average_rainfall(rainfall_data):
    new_list = []
    for months in rainfall_data: 
        if months == []:
            new_list.append(0)
            continue
        average = 0 
        for measurements in months: 
            average += measurements 
        average = average / len(months)
        new_list.append(average)
    return new_list
                  
rainfall_sample = [
    [],
    [3.2, 3.6, 2.2],
    [4.1, 4.6]
]
rainfall_sample2 = [
    [3, 4.0, 5],
    [2.1, 2, 2],
    [0, 1.2, 0]
]
rainfall_sample3 = [
    [],
    [],
    [6]
]
print(average_rainfall(rainfall_sample))
print(average_rainfall(rainfall_sample2))
print(average_rainfall(rainfall_sample3))


cities = ["Chennai", "Madison", "San Diego"]
wdata = [[23, 24, 25, 27, 26],
         [0, -1, 1, -3, -2],
         [9, 10, 11, 12, 11]]

for city in range(len(cities)):
    print(cities[city], end=":")
    for day in range(len(wdata[city])):
        print(wdata[city][day], end = " ")
    print()


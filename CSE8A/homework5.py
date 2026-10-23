orders = [
["Vanilla", "Chocolate", "Rocky Road"],
["Sprinkles", "Oreo", "Nuts"],
["Blueberry", "Cherry", "Banana"]
]
size = len(orders)
new_orders = []
for i in range(size):
    new_orders.append([])
    for j in range(size):
        item = orders[j][i]
        new_orders[i].append(item)
print(new_orders)
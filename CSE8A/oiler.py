sang_courses = [
['CSE202', 'CSE250A', 'CSE282', 'CSE284', 'CSE283'], # first year
['MAT283', 'CSE280A', 'CSE256', 'CSE299'], # second year
['CSE210', 'CSE290', 'CSE299'], # third year
]
fourth_year = ['CSE500', 'CSE599', 'CSE290']
sang_courses.append(fourth_year)

sang_courses[1][0] = 'MATH283'
sang_courses[3].insert(0, 'CSE8A')

for i in range(len(sang_courses)): 
    for j in range(len(sang_courses[i])): 
        print(sang_courses[i][j])